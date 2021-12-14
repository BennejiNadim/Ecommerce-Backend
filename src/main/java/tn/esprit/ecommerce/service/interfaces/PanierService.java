package tn.esprit.ecommerce.service.interfaces;

import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import tn.esprit.ecommerce.domain.Panier;
import tn.esprit.ecommerce.domain.Product;

public interface PanierService {
	public void addPanier(Panier p,String email);
	public List<Panier> findAll();
	public Panier getById(int id);
	public List<Panier> findByDate(Calendar date);
	public List<Panier> findByProductsCount(int count);
	public void addProductToPanier(List<Product> products,Authentication auth);
	

}
