package in.surendra.Service;

import java.time.LocalDate;
import java.util.List;

import in.surendra.Dto.OrderDto;

public interface OrderService {
	
	public List<OrderDto> addOrder(List<OrderDto> orderList);
	
	public OrderDto updateOrder(OrderDto orderDto);
	
	public List<OrderDto> getOrderByUserId(Integer userId);
	
	public List<OrderDto> getOrderByDateAndStatus(LocalDate orderDate, String status);

	public List<OrderDto> getAllOrders();
}