package fr.timebomb.api;

import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.timebomb.dao.IUserDao;
import fr.timebomb.exception.UserCreatedException;
import fr.timebomb.exception.UserNotFoundException;
import fr.timebomb.model.User;
import fr.timebomb.projection.Views;
import fr.timebomb.security.UserSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserApiController {
	@Autowired
	private IUserDao daoUser;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserSession userSession;

	@PostMapping("/login")
	@JsonView(Views.User.class)
	public User login(@RequestBody User user) {
		User dbUser = this.daoUser.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
		if (!passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
			throw new UserNotFoundException();
		}

		this.userSession.setId(dbUser.getId());
		this.userSession.setName(dbUser.getName());

		return dbUser;
	}

	@PostMapping("/subscribe")
	@ResponseStatus(HttpStatus.CREATED)
	public void subscribe(@RequestBody User user) {
		try {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			this.daoUser.save(user);
		}

		catch (Exception e) {
			throw new UserCreatedException();
		}
	}
}