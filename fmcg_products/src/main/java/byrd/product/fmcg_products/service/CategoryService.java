package byrd.product.fmcg_products.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import byrd.product.fmcg_products.entity.Category;
import byrd.product.fmcg_products.entity.Product;
import byrd.product.fmcg_products.projectiondto.CategoryDTO;
import byrd.product.fmcg_products.projectiondto.CategoryDtoImpl;
import byrd.product.fmcg_products.projectiondto.CategoryProductDto;
import byrd.product.fmcg_products.projectiondto.ProductDto;
import byrd.product.fmcg_products.repository.CategoryRepository;
import byrd.product.fmcg_products.repository.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository repo;

	@Autowired
	ProductRepository prodRepo;

	@Transactional
	public Category saveCategory(Category category) {
		return repo.save(category);
	}
	
	@Transactional
	public List<CategoryDTO> getAllCategories(){
		
		List<CategoryDTO> categoryDtoList =  repo.getAllCatagories();
		
		return categoryDtoList.stream().map(dto->{
				List<ProductDto> prodDto =  findProductDTOsByCategoryCode(dto.getCategoryCode());
				dto.setProducts(prodDto);
				return dto;
			}).toList();
	
	}

	
	public List<ProductDto> findProductDTOsByCategoryCode(int categoryCode){
		return prodRepo.findProductDTOsByCategoryCode(categoryCode);
	}
	
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	
}

