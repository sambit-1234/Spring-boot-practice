package byrd.product.fmcg_products.mocker;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMocker {

	   @JsonProperty("product_code")
	    private int productCode;

	    private String availability;
	    private String description;

	    @JsonProperty("packaging_type")
	    private String packagingType;

	    private String name;

	    @JsonProperty("category_id")
	    private int categoryId;

	    @JsonProperty("classification_type_id")
	    private int classificationTypeId;

}
