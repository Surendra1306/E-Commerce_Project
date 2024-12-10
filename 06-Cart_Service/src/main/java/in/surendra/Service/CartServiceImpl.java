package in.surendra.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import in.surendra.ClientServie.ProductDto;
import in.surendra.ClientServie.ProductServiceClient;
import in.surendra.Dto.CartDto;
import in.surendra.Entity.Cart;
import in.surendra.Exception.CartServiceException;
import in.surendra.Mapper.CartMapper;
import in.surendra.Repo.CartRepo;

public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ProductServiceClient productClient;

	@Override
	public CartDto addProdcutToCart(CartDto cartDto, Integer productId) {
		ResponseEntity<ProductDto> product = productClient.getProduct(productId);
		if(product!=null) {
			Cart cartEntity = CartMapper.convertToEntity(cartDto);
			cartEntity.setCartId(cartDto.getCartId());
			cartEntity.setOrderId(cartDto.getOrderId());
			cartEntity.setProductId(cartDto.getProductId());
			cartEntity.setQuantity(cartDto.getQuantity());
			
			Cart cart = cartRepo.save(cartEntity);
			return CartMapper.convertToDto(cart);
		}
		else {
			throw new CartServiceException("HYW628", "product not found");
		}
	}

	@Override
	public CartDto updateCart(CartDto cartDto) {
		Cart cartEntity = CartMapper.convertToEntity(cartDto);
		Optional<Cart> optional = cartRepo.findById(cartEntity.getCartId());
		if(optional.isPresent()) {
			Cart cart = optional.get();
			cart.setProductId(cartDto.getProductId());
			cart.setQuantity(cartDto.getQuantity());
			Cart updatedCart = cartRepo.save(cart);
			return CartMapper.convertToDto(updatedCart);
		}
		else {
			throw new CartServiceException("HIWQ092", "cart not found");
		}
	}
	
	@Override
	public CartDto getCartByUserId(Integer cartId) {
		Cart cart = cartRepo.findByUserId(cartId).orElseThrow();
		return CartMapper.convertToDto(cart);
	}


	@Override
	public CartDto deleteCartById(Integer cartId) {
		Optional<Cart> optional = cartRepo.findById(cartId);
		if(!optional.isPresent()) {
			throw new CartServiceException("DEJF674", "cart not found");
		}
		Cart cart = optional.get();
		cartRepo.delete(cart);
		return CartMapper.convertToDto(cart);
	}

}

