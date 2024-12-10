package in.surendra.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.surendra.Constants.AppConstants;
import in.surendra.Dto.CategoryDto;
import in.surendra.PropMessages.PropertyMessages;
import in.surendra.Response.ApiResponse;
import in.surendra.Service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PropertyMessages messages;
	
	@PostMapping("/category")
	public ResponseEntity<ApiResponse<CategoryDto>> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto category = categoryService.createCategory(categoryDto);
		
		ApiResponse<CategoryDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(category!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Succ));
			response.setData(category);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable("id") Integer cid){
		CategoryDto categoryById = categoryService.getCategoryById(cid);
		ApiResponse<CategoryDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(categoryById!=null) {
			response.setStatusCode("201");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Fetch));
			response.setData(categoryById);
		}
		else {
			response.setStatusCode("501");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories(){
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		ApiResponse<List<CategoryDto>> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(allCategories!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Fetch_All));
			response.setData(allCategories);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponse<CategoryDto>> deleteCategory(@PathVariable("cid") Integer categoryId){
		CategoryDto removeCategory = categoryService.removeCategoryById(categoryId);
		ApiResponse<CategoryDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(removeCategory!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Delete));
			response.setData(removeCategory);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Err));
		}
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(
																@RequestParam("cid") Integer categoryId
																,@RequestBody CategoryDto categoryDto){
		CategoryDto updatedCategory = categoryService.updateCategory(categoryId, categoryDto);
		ApiResponse<CategoryDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(updatedCategory!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Update));
			response.setData(updatedCategory);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Catg_Err));
		}
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
