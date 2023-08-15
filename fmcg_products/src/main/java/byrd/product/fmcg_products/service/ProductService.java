package byrd.product.fmcg_products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repo;
	
//	@Transactional
//	public List<Product> getAllProductsByAvailability(Availability availablity){
//		return repo.findAllByAvailability(availablity);
//	}
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	@Transactional
	public Optional<Product> insertProduct(Product product) {
		return Optional.of(repo.save(product));
	}
}
