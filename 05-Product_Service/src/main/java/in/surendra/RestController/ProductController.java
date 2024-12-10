package in.surendra.RestController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import brave.propagation.CurrentTraceContext.Scope;
import in.surendra.Constants.AppConstants;
import in.surendra.Dto.ProductDto;
import in.surendra.PropMessages.PropertyMessages;
import in.surendra.Response.ApiResponse;
import in.surendra.Service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired 
	private PropertyMessages messages;
	
	@PostMapping("/product")
	public ResponseEntity<ApiResponse<ProductDto>> addProduct(@RequestParam("product") String productDto
															 ,@RequestParam("file") MultipartFile file ) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDto productObj = mapper.readValue(productDto, ProductDto.class);
		ProductDto product = productService.createProdcut(productObj, file);
		
		ApiResponse<ProductDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(product!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Succ));
			response.setData(product);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDto>> getProduct(@PathVariable("id") Integer pid){
		ProductDto productById = productService.getProduct(pid);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(productById!=null) {
			response.setStatusCode("201");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Fetch));
			response.setData(productById);
		}
		else {
			response.setStatusCode("501");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<ApiResponse<List<ProductDto>>> getAllProducts(){
		List<ProductDto> allProducts = productService.getAllProducts();
		ApiResponse<List<ProductDto>> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(allProducts!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Fetch_All));
			response.setData(allProducts);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Err));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponse<ProductDto>> deleteProduct(@PathVariable("pid") Integer productId){
		ProductDto removeProduct = productService.removeProduct(productId);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(removeProduct!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Delete));
			response.setData(removeProduct);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Err));
		}
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<ApiResponse<ProductDto>>
												  updateProduct(
										       	  @RequestParam("pid") Integer productId
												 ,@RequestParam("product") String productDto
												 ,@RequestParam("file") MultipartFile file) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		ProductDto productObj = mapper.readValue(productDto, ProductDto.class);
		ProductDto updatedProduct = productService.updateProduct(productId, productObj, file);
		ApiResponse<ProductDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(updatedProduct!=null) {
			response.setStatusCode("200");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Update));
			response.setData(updatedProduct);
		}
		else {
			response.setStatusCode("500");
			response.setStatusMessage(propMessages.get(AppConstants.Prod_Err));
		}
	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("/stock/{pid}")
	public ResponseEntity<ApiResponse<ProductDto>> updateStock(@PathVariable("pid") Integer pid, 
															   @RequestParam("quantity") Integer quantity){
		boolean updatedStock = productService.UpdateStock(pid, quantity);
		
		ApiResponse<ProductDto> response = new ApiResponse<>();
		Map<String, String> propMessages = messages.getMessages();
		if(updatedStock) {
			response.setStatusCode("201");
			response.setStatusMessage(propMessages.get(AppConstants.Stock_Update));
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setStatusCode("505");
			response.setStatusMessage("");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
