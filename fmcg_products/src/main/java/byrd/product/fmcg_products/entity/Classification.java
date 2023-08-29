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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "classificationCode")
@NoArgsConstructor
@AllArgsConstructor
public class Classification {

	protected String classificationType;
	@Id
	@Column(name = "classification_code" )
	protected int classificationCode;
	
	
    public Classification(String classificationType, int classificationCode) {
		this.classificationType = classificationType;
		this.classificationCode = classificationCode;
	}


	@OneToMany(mappedBy = "classificationType",  fetch = FetchType.LAZY)
	protected List<Product> products;
}
