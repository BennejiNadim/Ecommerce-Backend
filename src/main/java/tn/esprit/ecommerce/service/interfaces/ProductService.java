package tn.esprit.ecommerce.service.interfaces;

import java.util.List;

import tn.esprit.ecommerce.domain.Product;

public interface ProductService {

	public void addProduct(Product p);
	public void modiferProduct(Product p);
	public void deleteProductById(int id);
	public List<Product> findAll();
	public Product findByName(String name);
	public List<Product> filterProductsByPrice(float min,float max);
}
