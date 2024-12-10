package in.surendra.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.surendra.Dto.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto getCategoryById(Integer cid);
	
	public List<CategoryDto> getAllCategories();
	
	public CategoryDto removeCategoryById(Integer cid);
	
	public CategoryDto updateCategory(Integer cid, CategoryDto categoryDto);

}