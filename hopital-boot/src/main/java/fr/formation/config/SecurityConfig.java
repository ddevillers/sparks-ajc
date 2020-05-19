package fr.formation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Ou à positionner sur WebConfig
@Profile("!dev")
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(1)
	@Profile("!dev")
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/api/**")
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/**").permitAll()
//				.antMatchers("/**").hasAnyRole("ADMIN", "USER")
				.and().httpBasic()
				.and().csrf().disable()
				;
		}
	}

	@Configuration
	@Profile("!dev")
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/medecin/**").hasRole("MEDECIN")
			.antMatchers("/**").hasAnyRole("ADMIN", "USER")
	//		.antMatchers("/**").authenticated()
			.and()
			.formLogin()
				.loginPage("/connect") //Lien vers le @GetMapping
				.loginProcessingUrl("/connect") //Lien du POST du form html, c'est Spring qui crée un @PostMapping("/connect")
				.defaultSuccessUrl("/visite") //Page par défaut après connexion
				.failureUrl("/connect?error=true") //Page d'erreur de connexion
				.permitAll()
			
	//		.and().csrf().disable() //Si vous voulez désactiver la protection CSRF
				;
        }
	}
}