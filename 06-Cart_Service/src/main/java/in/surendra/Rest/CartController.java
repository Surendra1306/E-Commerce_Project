package in.surendra.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.surendra.Dto.CartDto;
import in.surendra.Service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/addToCart/{productId}")
	public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto, @PathVariable Integer productId) {
		CartDto productToCart = cartService.addProdcutToCart(cartDto, productId);
		return new ResponseEntity<>(productToCart, HttpStatus.OK);
	}
	
	@PutMapping("/updateCart")
	public ResponseEntity<CartDto> updateCart(@RequestBody CartDto cartDto){
		CartDto updateCart = cartService.updateCart(cartDto);
		return new ResponseEntity<CartDto>(updateCart, HttpStatus.OK);
	}
	
	@GetMapping("cart/{cartId}")
	public ResponseEntity<CartDto> getCartById(@PathVariable Integer cartId){
		CartDto cartByUserId = cartService.getCartByUserId(cartId);
		return new ResponseEntity<CartDto>(cartByUserId, HttpStatus.OK);
	}
	
	public ResponseEntity<CartDto> deleteCarts(@PathVariable Integer cartId){
		CartDto deleteCart = cartService.deleteCartById(cartId);
		return new ResponseEntity<CartDto>(deleteCart, HttpStatus.OK);
	}
	
}
