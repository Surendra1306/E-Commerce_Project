package in.surendra.Mapper;

import org.modelmapper.ModelMapper;

import in.surendra.Dto.OrderReportsDto;
import in.surendra.Entity.OrderReports;

public class ReportsMapper {
	
	public static final ModelMapper mapper = new ModelMapper();
	
	public static OrderReportsDto convertToDto(OrderReports orderReports) {
		return mapper.map(orderReports, OrderReportsDto.class);
	}
	
	public static OrderReports convertToEntity(OrderReportsDto orderReportsDto) {
		return mapper.map(orderReportsDto, OrderReports.class);
	}

}
