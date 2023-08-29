package byrd.product.fmcg_products.projectiondto.toentity;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.CategoryDTO;
import byrd.product.fmcg_products.projectiondto.ProductDto;

public class DtoToEntity {

	public static Category fromDtoToEntity(CategoryDTO dto) {
		return new Category(dto.getCategoryCode(),dto.getCategoryName());
	}
	
	
	public static Product fromDtoToEntity(ProductDto dto) {
		return new Product(dto);
	}
}
