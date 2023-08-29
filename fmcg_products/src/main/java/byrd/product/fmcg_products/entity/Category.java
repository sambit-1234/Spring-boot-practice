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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryCode")
@NoArgsConstructor
public class Category {

	@Id
	@Column(name = "category_code")
	protected int categoryCode;
	protected String categoryName;
	
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    protected List<Product> products;

    public Category(int categoryCode, String categoryName) {
    	this.categoryCode=categoryCode;
    	this.categoryName=categoryName;
    }

	public Category(int categoryCode, String categoryName, List<Product> products) {
		
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.products = products;
	}
    
    
}
