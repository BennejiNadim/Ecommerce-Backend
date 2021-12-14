package tn.esprit.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
		//LES METHODES AUTORI
		response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH");
		
		
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else if(request.getRequestURI().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		}

		else {

			String jwtToken = request.getHeader("Authorization");
			System.out.println("Token = "+jwtToken);
			if (jwtToken == null || !jwtToken.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256("YOUR KEY")).build();
			String jwt = jwtToken.substring("Bearer ".length());
			DecodedJWT decodedJWT = verifier.verify(jwt);
			String username = decodedJWT.getSubject();
			List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
			System.out.println("Username = "+username);
			System.out.println("Roles = "+roles);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(rn -> {
				authorities.add(new SimpleGrantedAuthority(rn));
			});
			UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null,
					authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
			filterChain.doFilter(request, response);
		}

	}

}
