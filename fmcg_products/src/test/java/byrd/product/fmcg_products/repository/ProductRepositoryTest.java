package byrd.product.fmcg_products.repository;

import java.util.List;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import byrd.product.fmcg_products.entity.Availability;
import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Classification;
import byrd.product.fmcg_products.entity.PackagingType;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.mocker.ProductMocker;
import byrd.product.fmcg_products.utils.ProductUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)

/*
 * When we try to create an unit test class using @DataJpaTest then it tries to
 * replace the DataSource with an Embedded database. But by-default we dont
 * provide any Embedded database so it throws an error saying no embedded
 * database is found to be replaced. So we will use this annotation where we can
 * take control over this functionality
 */
/*
 * When we want to run the tests with the real database, then we need to use
 * the @AutoConfigureTestDatabase annotation with replace attribute set to
 * Replace.NONE. This configuration instructs Spring Boot not to replace the
 * application default DataSource.
 */

/*
 * And if we want to use the Embedded database like "h2" then we can just add
 * the dependency in the pom.xml and the @DataJpaTest will take care of
 * replacing the original Datasource with the Embedded database and will perform
 * the Persistence operations on the Embedded database

 *And in that case we need to disable this following annotation
 */


/*
 *  Here we are using the Embedded database instead of the real one and
 * the @DataJpaTest annotation will take care automatically of replacing the
 * original Datasource with the embedded one
 */

//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	ProductRepository repo;
	
	private static String PRODUCTFILE = "product.json" ;
	private static List<ProductMocker> prodList;
	
	@BeforeAll  
	public static void setup() {
		prodList = ProductUtil.parseProductsFromJsonFile(PRODUCTFILE);
	}
	
	@Test
	public void givenProductListWhenLengthThenListLength() {
		
		List<ProductMocker> list = prodList;
		
		assertEquals(65,list.size());
		System.out.println(list.size());
	}
	
	/*
	 * @Test public void givenProductWhenFindWithIdThenReturnProduct() {
	 * 
	 * }
	 */
	
	@Test
	public void givenProductWhenSavedThenReturnSavedProduct() {
		
		//given
		Product testProd = Product.builder()
				.productName("TestProd")
				.description("It is a Product for testing")
				.packagingType(PackagingType.TYPE2)
				.productCode(44)
				.availability(Availability.HIGH)
				.category(new Category(22,"Test category"))
				.classificationType(new Classification("Test Classification",56))
				.build();
		
		
		//when
		Product prod = repo.save(testProd);
		
		//then
		Assertions.assertThat(prod).isNotNull();
		Assertions.assertThat(prod.getProductCode()).isGreaterThan(0);
		
	}
	
}
