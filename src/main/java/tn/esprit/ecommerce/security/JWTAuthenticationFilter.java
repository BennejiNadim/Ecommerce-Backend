package tn.esprit.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.userdetails.User;

import tn.esprit.ecommerce.repository.AppUserRepository;

import tn.esprit.ecommerce.domain.AppUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private AppUserRepository appRep;
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,AppUserRepository appRep) {
		this.authenticationManager = authenticationManager;
		this.appRep = appRep;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AppUser appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);
			
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(),appUser.getPassword()));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User user=(User)authResult.getPrincipal();
		AppUser appUser = appRep.findByEmail(user.getUsername());
		List<String> roles=new ArrayList<>();
		authResult.getAuthorities().forEach(a->{
			roles.add(a.getAuthority());
		});
		String jwt= JWT.create()
				.withIssuer(request.getRequestURI())
				.withSubject(user.getUsername())
				.withArrayClaim("roles", roles.toArray(new String[roles.size()]))
				.withExpiresAt(new Date(System.currentTimeMillis()+10*24*3600*1000))
				.sign(Algorithm.HMAC256("YOUR KEY"));
		response.addHeader("Authorization", jwt);
	}

}
