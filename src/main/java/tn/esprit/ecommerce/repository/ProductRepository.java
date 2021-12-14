package tn.esprit.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.ecommerce.domain.Product;


public interface ProductRepository extends CrudRepository<Product,Integer>{

	Product findByNomProduit(String nom);

}
