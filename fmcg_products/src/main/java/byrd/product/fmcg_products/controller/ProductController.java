 package byrd.product.fmcg_products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.service.CategoryService;
import byrd.product.fmcg_products.service.ClassificationService;
import byrd.product.fmcg_products.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ClassificationService classificationService;
	@Autowired
	CategoryService categoryService;
	
	
	
	@GetMapping("/product")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/product")
	public Product insertProduct(@RequestBody Product product) {
		
		return productService.insertProduct(product).get();

	}
}
