package tn.esprit.ecommerce.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.ecommerce.domain.AppUser;
import tn.esprit.ecommerce.domain.util.AppUserForm;
import tn.esprit.ecommerce.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.ecommerce.service.interfaces.AppUserService;


@Service
public class AppUserServiceImpl implements AppUserService {

	
	@Autowired
	AppUserRepository appUserRep ;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public AppUser loadUserByUsername(String username) {
			return appUserRep.findByEmail(username);
	}
	@Override
	public AppUser addUser(AppUserForm user)  {
		AppUser newUser = new AppUser();
		if(user!=null)
		{
			if(appUserRep.findByEmail(user.getEmail())!=null){
				return null ;
			}
			if(user.getEmail()!=null){
				newUser.setEmail(user.getEmail());
			}
			if(user.getPassword()!=null){
				newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			}
			if(user.getFullName()!=null)
			{
				newUser.setFullName(user.getFullName());
			}
			if(user.getRole()!=null){
				newUser.setRole(user.getRole());
			}
			newUser.setDeleted(false);
			newUser.setRole("SIMPLE_USER");
			return appUserRep.save(newUser);
		}
		return newUser;
	}
	@Override
	public List<AppUser> findAllUsers() {
		List<AppUser> activeUsers=new ArrayList<>();
		for(AppUser user : appUserRep.findAll())
		{
			if(!user.isDeleted())
			{
				activeUsers.add(user);
			}
		}
		return activeUsers;
	}
	@Override
	public AppUser findByEmail(String email) {
		return appUserRep.findByEmail(email);
	}
	@Override
	public AppUser findByFullName(String fullName) {
		return appUserRep.findByFullName(fullName);
	}
/*	@Override
	public List<AppUser> findAllUsersOrderedByEmailAsc() {
		return appUserRep.findAllOrderByEmail();
	}
	@Override
	public List<AppUser> findAllUsersOrderedByEmailDesc() {
		return appUserRep.findAllOrderByEmailDesc();
	}
	@Override
	public List<AppUser> findAllUsersOrderedByFullNameAsc() {
		return appUserRep.findAllOrderByFullName();
	}
	@Override
	public List<AppUser> findAllUsersOrderedByFullNameDesc() {
		return appUserRep.findAllOrderByFullNameDesc();
	} */

}
