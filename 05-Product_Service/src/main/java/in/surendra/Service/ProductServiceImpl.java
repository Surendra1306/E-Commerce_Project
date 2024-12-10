package in.surendra.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.surendra.Dto.ProductDto;
import in.surendra.Entity.Category;
import in.surendra.Entity.Product;
import in.surendra.Exceptions.ProductSeviceException;
import in.surendra.Mapper.CategoryMapper;
import in.surendra.Mapper.ProductMapper;
import in.surendra.Repo.CategoryRepo;
import in.surendra.Repo.ProductRepo;
import in.surendra.Utils.FileUtils;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public ProductDto createProdcut(ProductDto productDto, MultipartFile file) throws Exception  {
		String productImg = FileUtils.uploadProductImg(file.getName(), file);
		
		Product productEntity = ProductMapper.convertToEntity(productDto);
		
		Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
		
		productEntity.setImage(productImg);
		productEntity.setCategory(category);
		
		productRepo.save(productEntity);
		
		return ProductMapper.convertToDto(productEntity);
		
	}

	@Override
	public ProductDto updateProduct(Integer pid, ProductDto productDto, MultipartFile file) throws Exception {
		Product productEntity = ProductMapper.convertToEntity(productDto);
		Product product = productRepo.findById(productEntity.getProductId()).orElseThrow();
		String UpdatedProductImg = FileUtils.uploadProductImg(file.getName(), file);
		product.setImage(UpdatedProductImg);
		Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
		product.setCategory(category);
		
		return ProductMapper.convertToDto(product);
	}

	@Override
	public ProductDto getProduct(Integer pid) {
		Optional<Product> byId = productRepo.findById(pid);
		if(!byId.isPresent()) {
			throw new ProductSeviceException("id not found", "EUID500");
		}
		Product product = byId.get();
		return ProductMapper.convertToDto(product);
	}

	@Override
	public List<ProductDto> getAllProducts() {

		List<Product> allProducts = productRepo.findAll();
		List<ProductDto> productDto = new ArrayList<>();
		for(Product products : allProducts) {
			productDto.add(ProductMapper.convertToDto(products));
		}
		
		return productDto;
	}

	@Override
	public ProductDto removeProduct(Integer pid) {
		Optional<Product> optional = productRepo.findById(pid);
		if(!optional.isPresent()) {
			throw new ProductSeviceException("id not found", "HKKJ500");
		}
		Product product = optional.get();
		productRepo.delete(product);
		return ProductMapper.convertToDto(product);
	
	}

	@Override
	public boolean UpdateStock(Integer pid, Integer stockQuantity) {
		Product product = productRepo.findById(pid).orElseThrow();
		product.setStock(stockQuantity);
		productRepo.save(product);
		return true;
		
	}

}
