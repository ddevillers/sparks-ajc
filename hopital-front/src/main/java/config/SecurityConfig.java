package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import security.AuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //Ou à positionner sur WebConfig
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthService authService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/medecin/**").hasRole("MEDECIN")
			.antMatchers("/**").hasAnyRole("ADMIN", "USER")
//			.antMatchers("/**").authenticated()
			.and()
//			.httpBasic();
			.formLogin()
				.loginPage("/connect") //Lien vers le @GetMapping
				.loginProcessingUrl("/connect") //Lien du POST du form html, c'est Spring qui crée un @PostMapping("/connect")
				.defaultSuccessUrl("/visite") //Page par défaut après connexion
				.failureUrl("/connect?error=true") //Page d'erreur de connexion
				.permitAll()
			
//			.and().csrf().disable() //Si vous voulez désactiver la protection CSRF
				;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.authService);
		
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("{noop}admin123456").roles("ADMIN")
//			.and()
//			.withUser("user").password("{noop}user123456").roles("USER")
//			.and()
//			.withUser("medecin").password("{noop}123456").roles("MEDECIN");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}