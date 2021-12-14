package tn.esprit.ecommerce.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.esprit.ecommerce.domain.AppUser;
import tn.esprit.ecommerce.domain.util.AppUserForm;

@Service
public interface AppUserService {
	public AppUser loadUserByUsername(String username);
	public AppUser addUser(AppUserForm user);
	public List<AppUser> findAllUsers();
	public AppUser findByEmail(String Email);
	public AppUser findByFullName(String fullName);
	public AppUser addAdmin(AppUser user);
	
/*	public List<AppUser> findAllUsersOrderedByEmailAsc();
	public List<AppUser> findAllUsersOrderedByEmailDesc();
	public List<AppUser> findAllUsersOrderedByFullNameAsc();
	public List<AppUser> findAllUsersOrderedByFullNameDesc(); */
}
