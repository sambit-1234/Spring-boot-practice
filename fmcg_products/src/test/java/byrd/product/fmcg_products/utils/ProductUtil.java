package byrd.product.fmcg_products.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import byrd.product.fmcg_products.mocker.ProductMocker;

public class ProductUtil {

	
	
	/*
	 * You can provide the json path where the Products (Json data ) are being stored and then 
	 * the json data will be converted into a List<Product> and will be returned back
	*/
	
	
	 public static List<ProductMocker> parseProductsFromJsonFile(String filePath) {
		    
		 try {
			  	ObjectMapper objectMapper = new ObjectMapper();
		    	InputStream jsonFile = ProductUtil.class.getClassLoader().getResourceAsStream(filePath);
		        
		    	
		    	List<ProductMocker> products = objectMapper.readValue(jsonFile, new TypeReference<List<ProductMocker>>() {});
		        
		        return products; 
		        
		 }catch (IOException ioex) {
			 ioex.printStackTrace();
			return new ArrayList<ProductMocker>(); 
		 }
		  
		    }
		    
}
