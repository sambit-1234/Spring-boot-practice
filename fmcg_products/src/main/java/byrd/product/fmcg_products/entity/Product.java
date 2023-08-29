package byrd.product.fmcg_products.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import byrd.product.fmcg_products.projectiondto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productCode")
public class Product {

	@Column(name = "name")
	protected String productName;
	@Id
	@Column(name= "product_code")
	protected int productCode;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "classification_type_id")
	@JsonIgnoreProperties("products")

	protected Classification classificationType;
    

	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties("products")
	protected Category category;
    
	protected String description;
	
	@Enumerated(EnumType.STRING)
	protected Availability availability;

	@Enumerated(EnumType.STRING)
	protected PackagingType packagingType;
	
	
	public Product(ProductDto dto) {
		this.productCode = dto.getProductCode();
		this.availability = dto.getAvailability();
		this.description = dto.getDescription();
		this.packagingType = dto.getPackagingType();
		this.productName = dto.getProductName();
	}
}



/*
 * Certainly! I've already used the @Enumerated(EnumType.STRING) annotation in the Product class
 * for the packagingType and availability fields. 
 * This annotation tells JPA to store the enum values as strings in the database rather than their ordinal values. 
 * This is a good practice as it ensures that changes in the enum order won't affect the database records. 
*/




