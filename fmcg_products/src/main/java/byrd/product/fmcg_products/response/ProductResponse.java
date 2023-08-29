package byrd.product.fmcg_products.response;

import byrd.product.fmcg_products.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String message;
	private Product product;
}
