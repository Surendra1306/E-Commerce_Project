package in.surendra.Service;

import in.surendra.Dto.CartDto;

public interface CartService {

	 public CartDto addProdcutToCart(CartDto cartDto, Integer productId);
	 
	 public CartDto updateCart(CartDto cartDto);
	 
	 public CartDto getCartByUserId(Integer cartId);
	 
	 public CartDto deleteCartById(Integer cartId);
}
