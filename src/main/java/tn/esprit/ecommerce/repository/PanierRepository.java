package tn.esprit.ecommerce.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.ecommerce.domain.Panier;


public interface PanierRepository extends CrudRepository<Panier,Integer> {
	List<Panier> findByDatePanier(Calendar date);

	List<Panier> findByProductsCount(int count);

}
