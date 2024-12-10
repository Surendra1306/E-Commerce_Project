package in.surendra.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.surendra.Dto.ProductDto;

public interface ProductService {
	 
	
	public ProductDto createProdcut(ProductDto productDto, MultipartFile file) throws Exception;
	
	public ProductDto updateProduct(Integer pid, ProductDto productDto, MultipartFile file) throws Exception;

	public ProductDto getProduct(Integer pid);
	
	public List<ProductDto> getAllProducts();
	
	public ProductDto removeProduct(Integer pid);
	
	public boolean UpdateStock(Integer pid, Integer stockQuantity);	
	
}
