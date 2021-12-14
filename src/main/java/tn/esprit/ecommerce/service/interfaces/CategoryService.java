package tn.esprit.ecommerce.service.interfaces;

import java.util.List;

import tn.esprit.ecommerce.domain.Category;

public interface CategoryService {
	public void addCategory(Category category);
	public List<Category> findAll();
	public Category findByName(String name);
}
