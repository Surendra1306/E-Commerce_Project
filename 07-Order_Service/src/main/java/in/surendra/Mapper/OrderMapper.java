package in.surendra.Mapper;

import org.modelmapper.ModelMapper;

import in.surendra.Dto.OrderDto;
import in.surendra.Entity.Order;

public class OrderMapper {
	
	private static final ModelMapper mapper = new ModelMapper();
	
	public static OrderDto convertToDto(Order order) {
		return mapper.map(order, OrderDto.class);
	}
	
	public static Order convertToEntity(OrderDto orderDto) {
		return mapper.map(orderDto, Order.class);
	}

}
