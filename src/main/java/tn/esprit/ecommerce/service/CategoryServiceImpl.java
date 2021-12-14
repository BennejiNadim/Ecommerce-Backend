package tn.esprit.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.ecommerce.domain.Category;
import tn.esprit.ecommerce.repository.CategoryRepository;
import tn.esprit.ecommerce.service.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository catRep ;

	@Override
	public void addCategory(Category category) {
		catRep.save(category);
		
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) catRep.findAll();
	}

	@Override
	public Category findByName(String name) {
	
		return null;
	}

}
