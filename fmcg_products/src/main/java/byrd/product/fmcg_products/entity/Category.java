package byrd.product.fmcg_products.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@Column(name = "category_code")
	protected int categoryCode;
	protected String categoryName;
	
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	 @JsonIgnoreProperties("category")

    protected List<Product> products;

}
