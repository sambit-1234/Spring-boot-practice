package byrd.product.fmcg_products.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.repository.ClassificationRepository;

@Service
public class ClassificationService {

	
	@Autowired
	ClassificationRepository repo;
	
	@Transactional
	public Classification saveClassification(Classification classification) {
		Optional<Classification> saved = Optional.of(repo.save(classification));
		
		if(saved.isPresent()) {
			return saved.get();
		}
		
		return null;
	}
	
	public Classification getById(int id) {
		Optional<Classification> classification = Optional.of(repo.getReferenceById(id));
		if(classification.isPresent()) {
			return classification.get();
		}
		return null;
	}
	
	
	public List<Classification> findAllClassificaitons(){
		return repo.findAll();
	}
	
}
