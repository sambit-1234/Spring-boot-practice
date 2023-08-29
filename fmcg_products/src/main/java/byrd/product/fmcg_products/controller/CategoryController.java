package byrd.product.fmcg_products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.CategoryDTO;
import byrd.product.fmcg_products.projectiondto.CategoryDtoImpl;
import byrd.product.fmcg_products.projectiondto.CategoryProductDto;
import byrd.product.fmcg_products.projectiondto.ProductDto;
import byrd.product.fmcg_products.service.CategoryService;

@RestController
public class CategoryController {

	
	@Autowired
	CategoryService service;
	
	
	@GetMapping("/category")
	public List<CategoryDTO> getAllCategories(){
		return service.getAllCategories();
		
	}
	
	@GetMapping("/category/{categoryCode}")
	public List<ProductDto> getProductByCategoryCode(@PathVariable int categoryCode){
		return service.findProductDTOsByCategoryCode(categoryCode);
	}
}
