package in.surendra.Rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import in.surendra.Dto.OrderDto;
import in.surendra.Service.OrderService;
import jakarta.ws.rs.QueryParam;

@RestController
public class OrderRest  {

	@Autowired
	private OrderService orderService;
	
	@PutMapping("/addOrder")
	public ResponseEntity<List<OrderDto>> addOrders(List<OrderDto> orderDto){
		List<OrderDto> list = orderService.addOrder(orderDto);
		return new ResponseEntity<List<OrderDto>>(list, HttpStatus.CREATED);	
	}
	
	@PutMapping("/updateOrder")
	public ResponseEntity<OrderDto> updateOrder(OrderDto orderDto){
		OrderDto updateOrder = orderService.updateOrder(orderDto);
		return new ResponseEntity<OrderDto>(updateOrder, HttpStatus.OK);
	}
	
	@GetMapping("/getOrder/{userId}")
	public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable Integer userId){
		List<OrderDto> byUserId = orderService.getOrderByUserId(userId);
		return new ResponseEntity<List<OrderDto>>(byUserId, HttpStatus.OK);
	}
	
	@GetMapping("getOrderByDateAndStatus/{status}")
	public ResponseEntity<List<OrderDto>> getOrderByStatus(@QueryParam(value = "date") LocalDate date, 
														   @PathVariable String status){
		List<OrderDto> orderByDateAndStatus = orderService.getOrderByDateAndStatus(date, status);
		return new ResponseEntity<List<OrderDto>>(orderByDateAndStatus, HttpStatus.OK);
	}
	
	@GetMapping("/allOrders")
	public ResponseEntity<List<OrderDto>> getAllOrders(){
		List<OrderDto> allOrders = orderService.getAllOrders();
		return new ResponseEntity<List<OrderDto>>(allOrders, HttpStatus.OK);
	}
	
}
