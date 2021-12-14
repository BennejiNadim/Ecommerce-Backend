package tn.esprit.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.esprit.ecommerce.domain.AppUser;
import tn.esprit.ecommerce.domain.Category;
import tn.esprit.ecommerce.domain.Product;
import tn.esprit.ecommerce.domain.util.AppUserForm;
import tn.esprit.ecommerce.repository.AppUserRepository;
import tn.esprit.ecommerce.service.interfaces.AppUserService;
import tn.esprit.ecommerce.service.interfaces.CategoryService;
import tn.esprit.ecommerce.service.interfaces.ProductService;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	AppUserRepository appRep;
	@Autowired
	AppUserService userServ;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	ProductService prodServ;
	@Autowired
	CategoryService cargSev;
	public static void main(String[] args) {
		
	
		SpringApplication.run(EcommerceApplication.class, args);
		
		

}
	  @Bean
	    public CommandLineRunner demoData(AppUserService userServ,ProductService prodServ,CategoryService cargSev) {
	   	AppUser admin =new AppUser();
    		AppUser simple=new AppUser();
    		admin.setFullName("admin");
    		admin.setDeleted(false);
    		admin.setEmail("admin@gmail.com");
    		admin.setPassword("admin");
    		admin.setRole("ADMIN");
    		simple.setFullName("John Doe");
    		simple.setPassword("123456");
    		simple.setEmail("john.doe@gmail.com");
    		simple.setRole("SIMPLE_USER");
    		AppUserForm af=new AppUserForm();
    		af.setEmail(simple.getEmail());
    		af.setFullName(simple.getFullName());
    		af.setPassword(simple.getPassword());
    		af.setRole(simple.getRole());
    		userServ.addAdmin(admin);
	        userServ.addUser(af); 
		  Product p = new Product();
		  p.setNomProduit("PC");
		  p.setPrix(1000);
		  Product p1 = new Product();
		  p1.setNomProduit("Iphone");
		  p1.setPrix(600);
		  Product p2 = new Product();
		  p2.setNomProduit("Hoodie");
		  p2.setPrix(60);
		  Product p3 = new Product();
		  p3.setNomProduit("microwave");
		  p3.setPrix(400);
		  Product p4 = new Product();
		  p4.setNomProduit("Desk");
		  p4.setPrix(700);
		  Category c = new Category();
		  c.setNomCategory("Food");
		  Category c1 = new Category();
		  c1.setNomCategory("Electronics");
		  Category c2 = new Category();
		  c2.setNomCategory("Computers");
		  Category c3 = new Category();
		  c3.setNomCategory("Clothes");
		  Category c4 = new Category();
		  c4.setNomCategory("Furniture");
		  prodServ.addProduct(p);
		  prodServ.addProduct(p1);
		  prodServ.addProduct(p2);
		  prodServ.addProduct(p3);
		  prodServ.addProduct(p4);
		  cargSev.addCategory(c);
		  cargSev.addCategory(c1);
		  cargSev.addCategory(c2);
		  cargSev.addCategory(c3);
		  cargSev.addCategory(c4);
				return null;
	       
	    }

}
