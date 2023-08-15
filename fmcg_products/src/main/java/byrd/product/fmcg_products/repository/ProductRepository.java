package byrd.product.fmcg_products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	 @EntityGraph(attributePaths = {"classificationType", "category"})
	    List<Product> findAll();

}
