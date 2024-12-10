package in.surendra.Mapper;

import org.modelmapper.ModelMapper;

import in.surendra.Dto.CartDto;
import in.surendra.Entity.Cart;

public class CartMapper {
	
	private static final ModelMapper mapper = new ModelMapper();
	
	public static Cart convertToEntity(CartDto cartDto) {
		return mapper.map(cartDto, Cart.class);
	}
	
	public static CartDto convertToDto(Cart cart) {
		return mapper.map(cart, CartDto.class);
	}

}
