package tn.esprit.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.ecommerce.domain.Category;


public interface CategoryRepository extends CrudRepository<Category,Integer>{

}
