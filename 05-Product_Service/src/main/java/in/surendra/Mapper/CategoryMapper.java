package in.surendra.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import in.surendra.Dto.CategoryDto;
import in.surendra.Entity.Category;

@Component
public class CategoryMapper {

	private final static ModelMapper mapper = new ModelMapper();
	
	public static CategoryDto convertToDto(Category category) {
		return mapper.map(category, CategoryDto.class);
	}
	
	public static Category convertToEntity(CategoryDto categoryDto) {
		return mapper.map(categoryDto, Category.class);
	}
}
