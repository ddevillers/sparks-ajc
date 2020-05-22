package fr.timebomb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.timebomb.dao.IUserDao;
import fr.timebomb.model.User;

@Service
public class AuthService implements UserDetailsService {
	@Autowired
	private IUserDao daoUser;

	@Autowired
	private UserSession userSession;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.daoUser.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found."));
		
		this.userSession.setId(user.getId());
		this.userSession.setName(user.getName());

		return new UserPrincipal(user);
	}
}