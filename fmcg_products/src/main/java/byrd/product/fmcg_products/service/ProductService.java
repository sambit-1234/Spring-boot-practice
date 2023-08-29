package byrd.product.fmcg_products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Product;
import static byrd.product.fmcg_products.projectiondto.toentity.DtoToEntity.fromDtoToEntity;
//Here we are using Static import so that we can directly use the method within our class 

import byrd.product.fmcg_products.repository.ProductRepository;
import byrd.product.fmcg_products.response.ProductResponse;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
		
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	@Transactional
	public Optional<Product> insertProduct(Product product) {
		return Optional.of(repo.save(product));
	}
	
	
	public Optional<Product> getProductByID(int productId){
		
		if( repo.findProductByProductId(productId).isEmpty()) {
			return Optional.empty();
		}
		return Optional.of (fromDtoToEntity(repo.findProductByProductId(productId).get(0)));

	}
	
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public ProductResponse updateOrCreateProduct(Product product){
					
		 Optional<Product> optProd = getProductByID(product.getProductCode());
			
			if(optProd.isEmpty()) {
				
				Optional<Product> insertedProduct =   insertProduct(product);
				ProductResponse response = new ProductResponse();
				response.setMessage("New Product created Successfully");
				response.setProduct(insertedProduct.get());
				
			}
			
			if(product.getCategory() !=null || product.getClassificationType()!=null) {
				ProductResponse response = new ProductResponse();
				response.setMessage("You can't modify Category or ClassificationType of any existing product");
				response.setProduct(product);
				return response;
			}
			
			Optional<Product> updatedProduct = updateProduct(product);
			ProductResponse response = new ProductResponse();
			response.setMessage("Product updated successfully");
			response.setProduct(updatedProduct.get());
			return response;
			
	}
	
	

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public Optional<Product> updateProduct(Product product){
		
		Optional<Product> optProd = getProductByID(product.getProductCode());
		
		if(optProd.isEmpty()) {
			return Optional.empty();
		}
		
		Product prod = optProd.get();
		if(product.getProductName() != null) {
			prod.setProductName(product.getProductName());
		}
		if(product.getDescription() != null) {
			prod.setDescription(product.getDescription());
		}
		if(product.getAvailability() != null) {
			prod.setAvailability(product.getAvailability());
		}
		if(product.getPackagingType() != null) {
			prod.setPackagingType(product.getPackagingType());
		}

		
		return insertProduct(prod);
	}
	
	
	
	
	

	/* We can not just directly delete the product because it has the association relationship with CLassificationType & Category
	 * So if we directly delete the product then the associated Category and ClassificationType also will be deleted but other products 
	 * are still there associated with those correspondings so now there are 2 ways to achieve this
	 * 
	 * Either we setup the cascading mapping in such a way that if parent is deleted then childs are not effected,
	 * we have cascadeType=ALL set up so all operations done on the parent are reflected to the childs as well so if we delete the 
	 * product then associations are going to be deleted. So we can change the cascading style and make it like,
	 * cascadeType = Merge / Fetch and that will solve the problem but as we already have it set to "ALL" changing it may cause other malfunctions 
	 * 
	 * So what other option is left is, we can detach the Parent from its child first and then we can perform the delete operaiton 
	 *  */
	public void removeProduct(int productCode) {

		repo.removeAssociationsFromProduct(productCode);
		repo.deleteProduct(productCode);
		
//		But we dont need this necessarily because out Product table is structured more nicely as the associations are being 
//		Hold using the Foreign keys and not with the Product Fields so even if we delete the product directly then also the 
//		associations will not be deleted.
	}
	
	
	@Transactional(rollbackFor = Throwable.class , propagation = Propagation.REQUIRED)
	public  void deleteProduct(int productCode) {
		repo.deleteByProductCode(productCode);
	}
	
	
	public List<Product> findByAvailability(Availability availability){
		return repo.findByAvailability(availability);
	}
	
}

