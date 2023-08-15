package byrd.product.fmcg_products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import byrd.product.fmcg_products.entity.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Integer>{

}
