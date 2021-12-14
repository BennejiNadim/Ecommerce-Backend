package tn.esprit.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.ecommerce.domain.AppUser;



public interface AppUserRepository  extends CrudRepository<AppUser,Integer>{

	AppUser findByEmail(String username);
	AppUser findByFullName(String fullName);
/*	List<AppUser >findAllOrderByEmail();
	List<AppUser >findAllOrderByEmailDesc();
	List<AppUser >findAllOrderByFullName();
	List<AppUser >findAllOrderByFullNameDesc();*/

}
