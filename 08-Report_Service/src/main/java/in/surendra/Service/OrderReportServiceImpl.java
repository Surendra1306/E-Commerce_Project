package in.surendra.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.surendra.Dto.OrderReportsDto;
import in.surendra.Entity.OrderReports;
import in.surendra.Entity.OrderStatus;
import in.surendra.Mapper.ReportsMapper;
import in.surendra.Repo.ReportsRepo;

@Service
public class OrderReportServiceImpl implements OrderReportService {
	
	@Autowired
	private ReportsRepo reportsRepo;
	
	public List<OrderReportsDto> createReport(OrderReportsDto orderReportsDto){
		OrderReports entity = ReportsMapper.convertToEntity(orderReportsDto);
		OrderReports savedEntity = reportsRepo.save(entity);
		OrderReportsDto reportsDto = ReportsMapper.convertToDto(savedEntity);
		List<OrderReportsDto> list=new ArrayList<>();
		list.add(reportsDto);
		return list;
	}

	@Override
	public List<OrderReportsDto> getOrdersByStatus(OrderStatus orderStatus) {
		List<OrderReports> status = reportsRepo.findByOrderStatus(orderStatus);
		return status.stream().map(ReportsMapper :: convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderReportsDto> getOrdersByDate(LocalDate orderDate) {
		List<OrderReports> orderByDate = reportsRepo.findByOrderDate(orderDate);
		return orderByDate.stream().map(ReportsMapper :: convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<OrderReportsDto> getAllOrders() {
		List<OrderReports> list = reportsRepo.findAll();
		return list.stream().map(ReportsMapper :: convertToDto).collect(Collectors.toList());
	}
}
