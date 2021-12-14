package tn.esprit.ecommerce.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ecommerce.domain.Panier;
import tn.esprit.ecommerce.domain.Product;
import tn.esprit.ecommerce.repository.AppUserRepository;
import tn.esprit.ecommerce.repository.PanierRepository;
import tn.esprit.ecommerce.repository.ProductRepository;
import tn.esprit.ecommerce.service.interfaces.PanierService;

@Service
public class PanierServiceImpl implements PanierService{
	
	@Autowired PanierRepository panierRep;
	@Autowired AppUserRepository appRep;
	@Autowired ProductRepository prodRep;

	@Override
	public void addPanier(Panier p,String email) {
		p.setUser(appRep.findByEmail(email));
		panierRep.save(p);
	}

	@Override
	public List<Panier> findAll() {
		List<Panier> paniers = new ArrayList<>();
		for (Panier p : panierRep.findAll())
		{
			paniers.add(p);
		}
		return paniers ;
	}

	@Override
	public Panier getById(int id) {
		return panierRep.findById(id).get();
	}

	@Override
	public List<Panier> findByDate(Calendar date) {
		return panierRep.findByDatePanier(date);
	}

	@Override
	public List<Panier> findByProductsCount(int count) {
		return panierRep.findByProductsCount(count);
	}

	@Override
	public void addProductToPanier(List<Product> products, Authentication auth) {
		// TODO Auto-generated method stub
		
	}

	/* @Override
	public void addProductToPanier(int idPanier, int idProd) {
		Panier p = panierRep.findById(idPanier).get();
		Product prod = prodRep.findById(idProd).get();
		List<Product> prods = p.getProducts();
		prods.add(prod);
		p.setProducts(prods);
		panierRep.save(p);
		
	} **/


}
