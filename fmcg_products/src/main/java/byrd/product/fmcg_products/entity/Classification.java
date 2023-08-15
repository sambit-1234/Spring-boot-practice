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

import lombok.Data;

@Entity
@Data
public class Classification {

	protected String classificationType;
	@Id
	@Column(name = "classification_code" )
	protected int classificationCode;
	
    @OneToMany(mappedBy = "classificationType",  fetch = FetchType.LAZY)
	protected List<Product> products;
}
