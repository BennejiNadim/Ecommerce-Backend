package tn.esprit.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.ecommerce.domain.Category;
import tn.esprit.ecommerce.domain.Product;
import tn.esprit.ecommerce.service.interfaces.CategoryService;
import tn.esprit.ecommerce.service.interfaces.ProductService;

@RestController
@CrossOrigin( origins = "http://localhost:4200" )
@RequestMapping("/products")
public class ProductController {
	@Autowired 
	ProductService prodServ ;
	@Autowired
	CategoryService catgServ;
	@GetMapping("/products")
	@CrossOrigin( origins = "http://localhost:4200" )
	public  List<Product> fetchProducts() {
		return prodServ.findAll();
	}
	@PostMapping("/addProduct")
	@CrossOrigin( origins = "http://localhost:4200" )
	public  void addProduct(@RequestBody Product product) {
		prodServ.addProduct(product);
	}
	@GetMapping("/categories")
	@CrossOrigin( origins = "http://localhost:4200" )
	public List<Category> getAll()
	{
		return catgServ.findAll();
	}
	@PostMapping("/addCategory")
	@CrossOrigin( origins = "http://localhost:4200" )
	public void addCategory(@RequestBody Category catg)
	{
		catgServ.addCategory(catg);
	}

}
