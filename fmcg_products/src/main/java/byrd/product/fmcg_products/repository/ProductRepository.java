package byrd.product.fmcg_products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.PackagingType;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.ProductDto;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	 	@EntityGraph(attributePaths = {"classificationType", "category"})
	    List<Product> findAll();

	 
	    @Query("SELECT new byrd.product.fmcg_products.projectiondto.ProductDto(p.productName, p.productCode,p.description, p.availability, p.packagingType) FROM Product p WHERE p.category.categoryCode = :categoryCode")
	    List<ProductDto> findProductDTOsByCategoryCode(@Param("categoryCode") int categoryCode);
	    
	    @Query("SELECT new byrd.product.fmcg_products.projectiondto.ProductDto(p.productName, p.productCode,p.description, p.availability, p.packagingType) FROM Product p WHERE p.productCode = :productCode")
	    List<ProductDto> findProductByProductId(int productCode);
	    
	    @Modifying
	    @Query("UPDATE Product p SET p.category = null, p.classificationType = null WHERE p.productCode = :productCode")
	    void removeAssociationsFromProduct(int productCode);
	    
	    @Modifying
	    @Query("DELETE FROM Product p WHERE p.productCode = :productCode")
	    void deleteProduct(int productCode);
	    
	    
	    @Modifying
	    void deleteByProductCode(int productCode);
	    
	    
	    List<Product> findByAvailability(Availability availability);
	    
}
