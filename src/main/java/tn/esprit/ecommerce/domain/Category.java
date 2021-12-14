package tn.esprit.ecommerce.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;
	private String nomCategory;
	@OneToMany(mappedBy="category")
	private List<Product> products;
	public Category() {
		
	}
	public String getNomCategory() {
		return nomCategory;
	}
	public void setNomCategory(String nomCategory) {
		this.nomCategory = nomCategory;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public int getIdCategory() {
		return idCategory;
	}
	
	
	

}
