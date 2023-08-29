package byrd.product.fmcg_products.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.CategoryDTO;
import byrd.product.fmcg_products.projectiondto.CategoryDtoImpl;
import byrd.product.fmcg_products.projectiondto.CategoryProductDto;
import byrd.product.fmcg_products.projectiondto.ProductDto;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    @Query("SELECT new byrd.product.fmcg_products.projectiondto.CategoryDTO (c.categoryCode , c.categoryName) FROM Category c")
	public List<CategoryDTO> getAllCatagories();
    
    public List<Product> findProductsByCategoryCode(int categoryCode);
    
    
    @Query("SELECT new byrd.product.fmcg_products.projectiondto.CategoryProductDto(c.products) FROM Category c where c.categoryCode = :categoryCode")
    public Collection<CategoryDtoImpl> getProductsFromCategoryCode(@Param("categoryCode") int categoryCode);
    
   
}












