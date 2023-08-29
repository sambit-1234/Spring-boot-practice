package byrd.product.fmcg_products.projectiondto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.PackagingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdDto {

	protected String productName;
	protected int productCode;
	protected String description;
	protected Availability availability;
	protected PackagingType packagingType;

	
	
}
