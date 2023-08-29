package byrd.product.fmcg_products.customvalidations;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import byrd.product.fmcg_products.entity.Availability;

public class ValidAvailabilityValidator implements ConstraintValidator<ValidAvailability, Availability> {

	List<String> validStrings = Stream.of("HIGH","LOW","MEDIUM","NOT_AVAILABLE").toList();
	
	@Override
	public boolean isValid(Availability value, ConstraintValidatorContext context) {
		
		if (value == null)
			return false;
		else if (validStrings.contains(value.toString().toUpperCase())) {
			return true;
		}
		
		return false;
	}

	

}
