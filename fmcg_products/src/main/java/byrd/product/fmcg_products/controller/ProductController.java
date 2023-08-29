 package byrd.product.fmcg_products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import byrd.product.fmcg_products.customvalidations.ValidAvailability;
import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.ProductDto;
import byrd.product.fmcg_products.response.ProductResponse;
import byrd.product.fmcg_products.service.CategoryService;
import byrd.product.fmcg_products.service.ClassificationService;
import byrd.product.fmcg_products.service.ProductService;

@Validated
@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ClassificationService classificationService;
	@Autowired
	CategoryService categoryService;
	
	
	//Returns all the products at once along with the child associations
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> prod =  productService.getAllProducts();
		return ResponseEntity.ok(prod);
	}
	
	
	@GetMapping("product/{productCode}")
	public ResponseEntity<?> getProductById(@PathVariable int productCode) {

		Optional<Product> prod = productService.getProductByID(productCode);
		
		if(prod.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product found for the given product code: "+ productCode);
		}
		return ResponseEntity.of(prod);
	}
	
	//insert an Product if already an product with the given product_code does not exist
	//(in the payload you can pass nested objects for Category and Classification as well and they will be inserted as well)
	@PostMapping("/product")
	public ResponseEntity insertProduct(@RequestBody Product product) {
		
		Optional<Product> optProd = productService.getProductByID(product.getProductCode());
			
		if( optProd.isEmpty() ) {	
			Optional<Product> prod =  productService.insertProduct(product);
			if(prod.isPresent()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(prod.get());
			}
			
			ProductResponse response = new ProductResponse("The Product payload is invalid, please check the below payload that u've sent and try again", product );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		ProductResponse response = new ProductResponse();
		response.setMessage("Product with product_code : "+ product.getProductCode()+" already exist, here is the existing product" );
		response.setProduct(optProd.get());
		
		
		return ResponseEntity.of(Optional.of(response));
		
	}
	
	@DeleteMapping("product/{productCode}")
	public ResponseEntity deleteProductByProductCode(@PathVariable int productCode) {
		
		Optional<Product> optProd = productService.getProductByID(productCode);

		if(optProd.isEmpty()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product with the given product_code :"+ productCode +" was found to delete");
		}
		
		productService.deleteProduct(productCode);
		return ResponseEntity.status(HttpStatus.OK).body("The product with product_code :"+productCode+ " deleted successfully" );
	}
	
	
	@PatchMapping("product/{productCode}")
	public ResponseEntity<?> patchUpProduct(@PathVariable int productCode, @RequestBody Product product) {
		
			if(product.getClassificationType()!= null && product.getCategory() != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can not modify the category or classification of any existing product");
			}
			
			Optional<Product> prod = productService.updateProduct(product);
			 	if( prod.isEmpty()) {
			 		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Product with product_code :"+product.getProductCode()+" found to be updated");
			 	}
			 	
			 	ProductResponse response = new ProductResponse();
			 	response.setMessage("Product updated successfully");
			 	response.setProduct(prod.get());
		 	
			 	return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
				
	}
	
	
	
	@PutMapping("product")
	public ResponseEntity<?> updateOrCreateProduct(@RequestBody Product product){
		return ResponseEntity.ok( productService.updateOrCreateProduct(product));
		
	}
	
	
	@GetMapping("product/availability")
	public ResponseEntity<?> findProductsByAvailability( @ValidAvailability @RequestParam Availability availability){
		
		List<Product> availableProducts = productService.findByAvailability(availability);

		return ResponseEntity.status(HttpStatus.FOUND).body(availableProducts);
	}
	
	
	
}
