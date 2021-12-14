package tn.esprit.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ecommerce.domain.AppUser;
import tn.esprit.ecommerce.domain.util.AppUserForm;
import tn.esprit.ecommerce.service.interfaces.AppUserService;


@RestController
@CrossOrigin( origins = "http://localhost:4200" )
@RequestMapping("/registerApi")
public class UserController {
	@Autowired 
	AppUserService appUserServ;

	
	@PostMapping("/Signup")
	@CrossOrigin( origins = "http://localhost:4200" )
	public AppUser register(@RequestBody AppUserForm appUser) {
		return appUserServ.addUser(appUser);
	}
	@GetMapping("/users")
	@CrossOrigin( origins = "http://localhost:4200" )
	public List<AppUser> register() {
		return appUserServ.findAllUsers();
	}

}
