package fr.formation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOCompte;
import fr.formation.model.Compte;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private IDAOCompte daoCompte;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Compte compte = this.daoCompte.findByLogin(username);

		UserDetails userDetails = new UserPrincipal(compte);

		return userDetails;
	}
}