package byrd.product.fmcg_products.projectiondto;

import java.util.Collection;
import java.util.List;


import byrd.product.fmcg_products.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryProductDto {

	protected Collection<ProdDto> products;
	
	
	public CategoryProductDto(Collection<ProdDto> products) {
		this.products = products; 
	}
	
}
