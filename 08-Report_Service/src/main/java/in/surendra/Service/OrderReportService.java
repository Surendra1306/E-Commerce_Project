package in.surendra.Service;

import java.time.LocalDate;
import java.util.List;

import in.surendra.Dto.OrderReportsDto;
import in.surendra.Entity.OrderStatus;

public interface OrderReportService {
	
	public List<OrderReportsDto> createReport(OrderReportsDto orderReportsDto);
	
	public List<OrderReportsDto> getOrdersByStatus(OrderStatus orderStatus);
	
	public List<OrderReportsDto> getOrdersByDate(LocalDate orderDate);
	
	public List<OrderReportsDto> getAllOrders();

}
