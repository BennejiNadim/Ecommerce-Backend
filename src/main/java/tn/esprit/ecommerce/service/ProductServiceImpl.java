package tn.esprit.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ecommerce.domain.Product;
import tn.esprit.ecommerce.repository.ProductRepository;
import tn.esprit.ecommerce.service.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired ProductRepository prodRep;

	@Override
	public void addProduct(Product p) {
		if(p!=null&&(prodRep.findByNomProduit(p.getNomProduit())==null)||prodRep.findByNomProduit(p.getNomProduit()).isDeleted()){
			prodRep.save(p);
		}
		
	}

	@Override
	public void modiferProduct(Product p) {
		Product prod = prodRep.findByNomProduit(p.getNomProduit());
		p.setProductId(prod.getProductId());
		if((p!=null)&&(prod!=null)){
			prodRep.save(p);
		}
		
	}

	@Override
	public void deleteProductById(int id) {
		Product prod = prodRep.findById(id).get();
		if(prod!=null)
		{
			prod.setDeleted(true);
			prodRep.save(prod);
		}
		
	}

	@Override
	public List<Product> findAll() {
		List<Product> products=new ArrayList<>();
		for(Product prod : prodRep.findAll())
		{
			if(!prod.isDeleted())
			{
				products.add(prod);
			}
		}
		return products;
	}

	@Override
	public Product findByName(String name) {
		return prodRep.findByNomProduit(name);
	}

	@Override
	public List<Product> filterProductsByPrice(float min, float max) {
		List<Product> products=this.findAll();
		List<Product> filteredProducts=new ArrayList<>();
		for(Product p : products)
		{
			if(p.getPrix()>=min&&p.getPrix()<=max)
			{
				filteredProducts.add(p);
			}
		}
		return filteredProducts;
	}

}
