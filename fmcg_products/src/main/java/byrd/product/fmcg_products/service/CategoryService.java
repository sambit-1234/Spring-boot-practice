package byrd.product.fmcg_products.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;

	@Transactional
	public Category saveCategory(Category category) {
		return repo.save(category);
	}
	
	public List<Category> getAllCatagories(){
		return repo.findAll();
	}
}
