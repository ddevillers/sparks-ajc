package fr.timebomb.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import fr.timebomb.dao.ICardDao;
import fr.timebomb.dao.IMatchDao;
import fr.timebomb.dao.IUserDao;
import fr.timebomb.emitter.MatchEmitterService;
import fr.timebomb.enumerator.CardType;
import fr.timebomb.enumerator.MatchState;
import fr.timebomb.enumerator.Role;
import fr.timebomb.exception.CardNotFoundException;
import fr.timebomb.exception.MatchCreatedException;
import fr.timebomb.exception.MatchMovementException;
import fr.timebomb.exception.MatchNotFoundException;
import fr.timebomb.exception.MatchNotYourTurnException;
import fr.timebomb.exception.NotOwnerException;
import fr.timebomb.exception.UserNotFoundException;
import fr.timebomb.model.Card;
import fr.timebomb.model.Match;
import fr.timebomb.model.User;
import fr.timebomb.projection.MatchDetailedProjection;
import fr.timebomb.projection.Views;
import fr.timebomb.security.UserSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/matches")
public class MatchApiController {
	private final Logger logger = LoggerFactory.getLogger(MatchApiController.class);
	
	@Autowired
	private UserSession userSession;

	@Autowired
	private IUserDao daoUser;

	@Autowired
	private IMatchDao daoMatch;

	@Autowired
	private ICardDao daoCard;

	@Autowired
	private MatchEmitterService srvEmitter;

	@GetMapping
	@JsonView(Views.Match.class)
	public List<Match> findAllWaitingOrPlaying() {
		this.logger.trace("Liste des parties demandées");
		return this.daoMatch.findAllByStateIn(Arrays.asList(MatchState.WAITING, MatchState.READY, MatchState.PLAYING));
	}

	@GetMapping("/terminated")
	@JsonView(Views.Match.class)
	public List<Match> findAllTerminated() {
		this.logger.trace("Liste des parties terminées demandées");
		return this.daoMatch.findAllByStateIn(Arrays.asList(MatchState.TERMINATED));
	}

	@GetMapping("/{id}")
	public MatchDetailedProjection findById(@PathVariable int id) {
		this.logger.trace("Détail de la partie {} demandé", id);
		
		return new MatchDetailedProjection(
			this.daoMatch
				.findById(id)
				.orElseThrow(MatchNotFoundException::new), this.userSession.getId());
	}

	@GetMapping("/mine")
	public MatchDetailedProjection findMine() {
		this.logger.trace("Un joueur demande sa partie ...");
		
		User user = this.daoUser.findById(this.userSession.getId()).orElseThrow(UserNotFoundException::new);

		this.logger.trace("Le joueur {} a demandé sa partie", user.getId());
		
		return new MatchDetailedProjection(
			this.daoMatch.findByPlayers(user), this.userSession.getId());
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void deleteById(@PathVariable int id) {
		this.logger.trace("Suppression de la partie {} demandée", id);
		Match match = this.daoMatch.findById(id).orElseThrow(MatchNotFoundException::new);

		if (match.getOwner().getId() != this.userSession.getId()) {
			throw new NotOwnerException();
		}

		match.getPlayers().forEach(p -> {
			p.setCurrentMatch(null);
		});

		this.daoUser.saveAll(match.getPlayers());
		this.daoCard.deleteAll(match.getDeck());
		this.daoMatch.delete(match);
		this.srvEmitter.send("Match deleted.", null);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(Views.Match.class)
	@Transactional
	public Match add(@RequestBody Match match) {
		this.logger.trace("Création d'une nouvelle partie demandée ({})", match.getName());
		if (match.getState() != MatchState.WAITING || match.getCurrent() != null) {
			throw new MatchCreatedException();
		}

		if (match.getSize() < 4) {
			this.logger.trace("Taille de la partie trop petite ({})", match.getSize());
			match.setSize(4);
		}

		else if (match.getSize() > 8) {
			this.logger.trace("Taille de la partie trop grande ({})", match.getSize());
			match.setSize(8);
		}

		User owner = this.daoUser.findById(this.userSession.getId()).orElseThrow(UserNotFoundException::new);
		match.setOwner(owner);
		
		try {
			this.daoMatch.save(match);
			match.setPlayers(new ArrayList<>());

			this.generateCard(match, CardType.BAIT, (15 + ((match.getSize() - 4) * 4)));
			this.generateCard(match, CardType.DIFFUSE, match.getSize());
			this.generateCard(match, CardType.BOMB, 1);
		}
		
		catch (Exception e) {
			throw new MatchCreatedException();
		}

		this.srvEmitter.send(match, Views.Match.class);
		return match;
	}

	@PutMapping("/{id}")
	@Transactional
	public MatchDetailedProjection join(@PathVariable int id) {
		this.logger.trace("Un joueur veut rejoindre la partie {} ...", id);
		Match match = this.daoMatch.findById(id).orElseThrow(MatchNotFoundException::new);
		User player = this.daoUser.findById(this.userSession.getId()).orElseThrow(UserNotFoundException::new);

		this.logger.trace("Le joueur {} veut rejoindre la partie {} ...", player.getId(), id);
		
		if (match.getState() == MatchState.WAITING) {
			if (player.getCurrentMatch() == match) {
				return new MatchDetailedProjection(match, this.userSession.getId());
			}

			match.addPlayer(player);
			this.daoUser.save(player);

			// Il y a assez de joueurs, on démarre la partie
			if (match.getPlayers().size() == match.getSize()) {
				this.logger.trace("La partie {} est prête", match.getId());
				match.setState(MatchState.READY);
			}

			this.srvEmitter.send(match);
			this.daoMatch.save(match);
		}

		return new MatchDetailedProjection(match, this.userSession.getId());
	}

	@PutMapping("/{id}/start")
	@Transactional
	public MatchDetailedProjection start(@PathVariable int id) {
		this.logger.trace("Démarrage de la partie {} demandé", id);
		Match match = this.daoMatch.findById(id).orElseThrow(MatchNotFoundException::new);

		if (match.getState() == MatchState.READY && match.getOwner().getId() == this.userSession.getId() && match.getPlayers().size() == match.getSize()) {
			match.setState(MatchState.PLAYING);
			match.setCurrent(match.getOwner());

			this.distributeRoles(match);
			this.distribute(match);

			this.srvEmitter.send(match);
			this.daoMatch.save(match);
		}

		return new MatchDetailedProjection(match, this.userSession.getId());
	}

	@PutMapping("/{id}/play")
	@JsonView(Views.CardRevealed.class)
	@Transactional
	public Card play(@PathVariable int id, @RequestBody Card card) {
		this.logger.trace("Carte {} de la partie {} demandé à être révélée", card.getId(), id);
		Match match = this.daoMatch.findById(id).orElseThrow(MatchNotFoundException::new);

		if (match.getCurrent() == null || match.getCurrent().getId() != this.userSession.getId()) {
			this.logger.trace("Ce n'était pas au tour du joueur {}", this.userSession.getId());
			throw new MatchNotYourTurnException();
		}

		// On révèle la carte seulement si c'est pas la carte du joueur actuel
		Card cardRevealed = this.daoCard.findById(card.getId()).orElseThrow(CardNotFoundException::new);

		//Si c'est la carte du joueur actuel
		if (cardRevealed.getOwner().getId() == this.userSession.getId()) {
			this.logger.trace("Le joueur {} a tenté de retourner sa propre carte {}", this.userSession.getId(), card.getId());
			throw new MatchMovementException();
		}

		cardRevealed.setRevealed(true);
		this.daoCard.save(cardRevealed);
		

		// Si c'est la carte BOMBE
		if (cardRevealed.getType() == CardType.BOMB) {
			this.logger.trace("FIN DE PARTIE : La bombe a été trouvée dans la partie {}", id);
			match.setState(MatchState.TERMINATED);
			match.setWinner(Role.MORIARTY);
		}

		// S'il ne reste plus de carte DIFFUSE
		else if (match.getDeck().stream().filter(c -> !c.isRevealed() && c.getType() == CardType.DIFFUSE).count() == 0) {
			this.logger.trace("FIN DE PARTIE : Toute les cartes de désamorçage ont été trouvées dans la partie {}", id);
			match.setState(MatchState.TERMINATED);
			match.setWinner(Role.SHERLOCK);
		}

		// S'il ne reste plus assez de cartes
		else if (match.getDeck().stream().filter(c -> !c.isRevealed()).count() <= match.getPlayers().size()) {
			this.logger.trace("FIN DE PARTIE : Il n'y a plus assez de cartes dans la partie {}", id);
			match.setState(MatchState.TERMINATED);
			match.setWinner(Role.MORIARTY);
		}

		// Sinon, la partie continue ...
		else {
			match.setState(MatchState.PLAYING);

			// On change le joueur en cours
			int currentIndex = match.getPlayers().indexOf(match.getCurrent()) + 1;

			// NOUVEAU TOUR
			if (currentIndex == match.getPlayers().size()) {
				this.logger.trace("Nouveau tour dans la partie {}", match.getId());
				currentIndex = 0;

				// Changement de tour = redistribution des cartes
				this.distribute(match);
			}

			match.setCurrent(match.getPlayers().get(currentIndex));
		}

		try {
			// On sauvegarde les modifications
			this.daoMatch.save(match);
		}
		
		catch (Exception e) {
			throw new MatchMovementException();
		}

		this.srvEmitter.send(cardRevealed.getMatch());
		return cardRevealed;
	}

	@PutMapping("/leave")
	@Transactional
	public void leave() {
		this.logger.trace("Un joueur quitte sa partie ...");
		User player = this.daoUser.findById(this.userSession.getId()).orElseThrow(UserNotFoundException::new);

		this.logger.trace("Le joueur {} quitte sa partie", player.getId());
		
		player.setCurrentMatch(null);
		player.setCurrentRole(null);

		this.daoUser.save(player);
	}

	@GetMapping("/sse-stream")
	public SseEmitter stream() {
		this.logger.debug("Un abonnement au flux a été demandé ...");
		
		User player = this.daoUser.findById(this.userSession.getId()).orElseThrow(UserNotFoundException::new);
		
		if (player.getCurrentMatch() == null) {
			this.logger.debug("Un abonnement au flux général a été demandé par le joueur {}", player.getId());
			return this.srvEmitter.create(player.getId());
		}

		this.logger.debug("Un abonnement au flux a été demandé par le joueur {} pour le match {}", player.getId(), player.getCurrentMatch().getId());
		
		return this.srvEmitter.create(player.getId(), player.getCurrentMatch().getId());
	}

	private void generateCard(Match match, CardType type, int size) {
		for (int i = 0; i < size; i++) {
			Card card = new Card();

			card.setType(type);
			card.setMatch(match);

			this.daoCard.save(card);
		}
	}

	private void distributeRoles(Match match) {
		List<Role> rolesToDistribute = new ArrayList<>();

		rolesToDistribute.add(Role.SHERLOCK);
		rolesToDistribute.add(Role.SHERLOCK);
		rolesToDistribute.add(Role.SHERLOCK);
		rolesToDistribute.add(Role.MORIARTY);
		rolesToDistribute.add(Role.MORIARTY);

		if (match.getSize() > 5) {
			rolesToDistribute.add(Role.SHERLOCK);
		}

		if (match.getSize() > 6) {
			rolesToDistribute.add(Role.SHERLOCK);
			rolesToDistribute.add(Role.MORIARTY);
		}

		Collections.shuffle(rolesToDistribute);

		for (User player : match.getPlayers()) {
			player.setCurrentRole(rolesToDistribute.remove(0));
		}

		this.daoUser.saveAll(match.getPlayers());
	}

	private void distribute(Match match) {
		List<Card> cardsToDistribute = match.getDeck().stream()
				.filter(c -> !c.isRevealed())
				.collect(Collectors.toList());

		Collections.shuffle(cardsToDistribute);

		while (cardsToDistribute.size() > 0) {
			for (User player : match.getPlayers()) {
				cardsToDistribute.remove(0).setOwner(player);
			}
		}

		this.daoCard.saveAll(cardsToDistribute);
	}
}