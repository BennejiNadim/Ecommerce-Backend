package tn.esprit.ecommerce.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.esprit.ecommerce.domain.AppUser;
import tn.esprit.ecommerce.service.interfaces.AppUserService;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserService.loadUserByUsername(username);
		if (appUser == null)
			throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(appUser.getRole()));
		return new User(appUser.getEmail(), appUser.getPassword(),authorities);

	}

}
