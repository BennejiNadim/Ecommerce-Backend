package tn.esprit.ecommerce.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPanier ;
	private Calendar datePanier;
	@OneToMany(mappedBy="panier")
	private List<Product> products ;
	@ManyToOne
	private AppUser user ;
	private int productsCount;
	private int total ;
	public Panier() {

	}
	public Calendar getDatePanier() {
		return datePanier;
	}
	public void setDatePanier(Calendar datePanier) {
		this.datePanier = datePanier;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	public int getIdPanier() {
		return idPanier;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getProductsCount() {
		return productsCount;
	}
	public void setProductsCount(int productsCount) {
		this.productsCount = productsCount;
	}
	
}
