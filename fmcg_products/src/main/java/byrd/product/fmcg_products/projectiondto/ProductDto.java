package byrd.product.fmcg_products.projectiondto;

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
public class ProductDto {

	protected String productName;
	protected int productCode;
	protected String description;
	protected Availability availability;
	protected PackagingType packagingType;

	
}
