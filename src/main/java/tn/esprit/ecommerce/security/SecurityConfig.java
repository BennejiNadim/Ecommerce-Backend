package tn.esprit.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.esprit.ecommerce.repository.AppUserRepository;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	@Autowired
	private AppUserRepository userRepository;
	

	@Bean
	BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login").permitAll();
/*		http.authorizeRequests().antMatchers("/login","/registerApi/**").permitAll();
/*		http.authorizeRequests().antMatchers("/apiHF/**","/userapi/**").authenticated();
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN"); 
		http.authorizeRequests().antMatchers("/apiHF/addAnnounce").hasAnyAuthority("NORMAL_SUBSCRIBER","PLATINIUM_SUBSCRIBER","GOLDEN_SUBSCRIBER");*/
		//http.authorizeRequests().anyRequest().permitAll();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(),userRepository));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class); 
	
	}

}
