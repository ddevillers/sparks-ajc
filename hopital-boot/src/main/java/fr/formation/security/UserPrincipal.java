package fr.formation.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.formation.model.Compte;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private Compte compte;
	
	public UserPrincipal(Compte compte) {
		if (compte == null) {
			throw new UsernameNotFoundException("Le compte n'existe pas");
		}
		
		this.compte = compte;
	}
	
	public String spec() {
		return "Spécificités";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		
		if (this.compte.getLogin().equals("Jordan")) {
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		else {
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return roles;
	}

	@Override
	public String getPassword() {
		return this.compte.getPassword();
	}

	@Override
	public String getUsername() {
		return this.compte.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}