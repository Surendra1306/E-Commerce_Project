package in.surendra.ClientServie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Product-Service-App")
public interface ProductServiceClient {

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Integer pid);
}
