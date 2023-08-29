package byrd.product.fmcg_products.projectiondto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    
	private int categoryCode;
    private String categoryName;
	private List<ProductDto> products; 
    
    public CategoryDTO(int categoryCode, String categoryName) {
		
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
    
    
    
}
