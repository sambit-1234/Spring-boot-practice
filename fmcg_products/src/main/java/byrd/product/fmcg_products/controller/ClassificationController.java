package byrd.product.fmcg_products.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.service.ClassificationService;

@RestController
public class ClassificationController {

	@Autowired
	ClassificationService service;
	
	@PostMapping("classification")
	public ResponseEntity<?> getAllClassificationTypes(@RequestBody Classification classification){
			
		Classification savedEntity =  service.saveClassification(classification);
			
				if(savedEntity != null) 
					return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something is wrong with the payload");
			}
	
	
	@GetMapping("classification/{classificationCode}")
	public ResponseEntity<?> getClassificationById(@PathVariable int classificationCode){
		
		Optional<Classification> entity = Optional.of(service.getById(classificationCode));
		
			if(entity.isPresent()) 
				return ResponseEntity.status(HttpStatus.FOUND).body(entity.get());
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No ClassificationType with given id found");
	}
	
	@GetMapping("classification")
	public ResponseEntity<?> getAllClassifications(){
		List<Classification> entities =  service.findAllClassificaitons();
		
		if(! entities.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(entities);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Categories Found");
	}
	
	
}
