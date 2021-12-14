package tn.esprit.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.ecommerce.service.interfaces.ProductService;

@RestController
@CrossOrigin( origins = "http://localhost:4200" )
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductService prodServ ;
	@PutMapping("/deleteProduct")
	@CrossOrigin( origins = "http://localhost:4200" )
	public void register(@RequestParam("prodId") int id) {
		 prodServ.deleteProductById(id);
	}

}
