package byrd.product.fmcg_products.projectiondto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDtoWithProduct {

	private int categoryCode;
    private String categoryName;
    List<ProductDto> products;
    
}
