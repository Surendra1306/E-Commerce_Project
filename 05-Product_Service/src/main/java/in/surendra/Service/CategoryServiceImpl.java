package in.surendra.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import in.surendra.Dto.CategoryDto;
import in.surendra.Dto.ProductDto;
import in.surendra.Entity.Category;
import in.surendra.Entity.Product;
import in.surendra.Exceptions.ProductSeviceException;
import in.surendra.Mapper.CategoryMapper;
import in.surendra.Mapper.ProductMapper;
import in.surendra.Repo.CategoryRepo;
import in.surendra.Utils.FileUtils;

public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto)  {
		
		List<Product> productsList = new ArrayList<>();
		List<ProductDto> products = categoryDto.getProducts();
		
		for(ProductDto product: products) {		
			productsList.add(ProductMapper.convertToEntity(product));	
		}
		
		Category categoryObj = CategoryMapper.convertToEntity(categoryDto);
		categoryObj.setCategoryName(categoryDto.getCategoryName());
		categoryObj.setProducts(productsList);
		
		Category saveCategory = categoryRepo.save(categoryObj);
		
		return CategoryMapper.convertToDto(saveCategory);
	}

	@Override
	public CategoryDto getCategoryById(Integer cid) {
	//	Category category = categoryRepo.findById(cid).orElseThrow(() -> new ProductSeviceException("Id not found", "HJAYS500"));
	//	return CategoryMapper.convertToDto(category);
		
		Optional<Category> byId = categoryRepo.findById(cid);
		if(!byId.isPresent()) {
			throw new ProductSeviceException("id not found", "EUID500");
		}
		Category category = byId.get();
		return CategoryMapper.convertToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = categoryRepo.findAll();
		
		List<CategoryDto> categoryDto = new ArrayList<>();
		for(Category category : categories) {
			categoryDto.add(CategoryMapper.convertToDto(category));
		}
		return categoryDto;
		
	}

	@Override
	public CategoryDto removeCategoryById(Integer cid) {
		Optional<Category> optional = categoryRepo.findById(cid);
		if(!optional.isPresent()) {
			throw new ProductSeviceException("id not found", "HKKJ500");
		}
		Category category = optional.get();
		categoryRepo.delete(category);
		return CategoryMapper.convertToDto(category);
	}

	@Override
	public CategoryDto updateCategory(Integer cid, CategoryDto categoryDto) {
		Optional<Category> optional = categoryRepo.findById(cid);
		if(!optional.isPresent()) {
			throw new ProductSeviceException("id not found", "LYBH500");
		}
		Category category = optional.get();
		category.setCategoryName(categoryDto.getCategoryName());
		
		List<Product> productsList = new ArrayList<>();
		List<ProductDto> products = categoryDto.getProducts();	
		for(ProductDto productDto : products) {
			Product productEntity = ProductMapper.convertToEntity(productDto);
			productsList.add(productEntity);
		}
		
		category.setProducts(productsList);
		Category saveUpdated = categoryRepo.save(category);
		return CategoryMapper.convertToDto(saveUpdated);
	}
	
}
