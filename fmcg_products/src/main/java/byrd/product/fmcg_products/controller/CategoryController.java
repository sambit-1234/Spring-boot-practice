package byrd.product.fmcg_products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.service.CategoryService;

@RestController
public class CategoryController {

	
	@Autowired
	CategoryService service;
	
	
	@GetMapping("/category")
	public List<Category> getAllCategories(){
		return service.getAllCatagories();
	}
}
