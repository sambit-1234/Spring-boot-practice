package byrd.product.fmcg_products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.repository.ClassificationRepository;

@Service
public class ClassificationService {

	
	@Autowired
	ClassificationRepository repo;
	
	public Classification saveClassification(Classification classification) {
		return repo.save(classification);
	}
}
