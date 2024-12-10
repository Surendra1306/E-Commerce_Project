package in.surendra.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import in.surendra.Dto.OrderDto;
import in.surendra.Entity.Order;
import in.surendra.Mapper.OrderMapper;
import in.surendra.Repo.OrderRepo;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public List<OrderDto> addOrder(List<OrderDto> orderList) {
		List<Order> list = orderList.stream().map(OrderMapper :: convertToEntity).collect(Collectors.toList());
		List<Order> saveAll = orderRepo.saveAll(list);
		return saveAll.stream().map(OrderMapper :: convertToDto).collect(Collectors.toList());
		}

	@Override
	public OrderDto updateOrder(OrderDto orderDto) {
		Order entity = OrderMapper.convertToEntity(orderDto);
		Order order = orderRepo.findById(entity.getOrderId()).orElseThrow();
		if(order!=null) {
			order.setOrderStatus(orderDto.getOrderStatus());
			order.setPaymentType(orderDto.getPaymentType());
			order.setPrice(orderDto.getPrice());
			order.setQuantity(orderDto.getQuantity());
			order.setOrderDate(orderDto.getOrderDate());
			order.setProductId(orderDto.getProductId());
			Order save = orderRepo.save(order);
			return OrderMapper.convertToDto(save);
		}
		else {
			return null;
		}
	}

	@Override
	public List<OrderDto> getOrderByUserId(Integer userId) {
		List<Order> byUserId = orderRepo.findByUserId(userId);
		List<OrderDto> list = byUserId.stream().map(OrderMapper :: convertToDto).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<OrderDto> getOrderByDateAndStatus(LocalDate orderDate, String status) {
		List<Order> orderEntity = orderRepo.findByOrderStatusAndOrderDate(status, orderDate);
		List<OrderDto> list = orderEntity.stream().map(OrderMapper :: convertToDto).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> all = orderRepo.findAll();
		List<OrderDto> list = all.stream().map(OrderMapper :: convertToDto).collect(Collectors.toList());
		return list;
	}

}
