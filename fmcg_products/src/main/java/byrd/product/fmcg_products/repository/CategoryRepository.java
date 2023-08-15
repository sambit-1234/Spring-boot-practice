package byrd.product.fmcg_products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import byrd.product.fmcg_products.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
