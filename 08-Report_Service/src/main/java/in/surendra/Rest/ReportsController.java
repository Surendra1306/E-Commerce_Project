package in.surendra.Rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.surendra.Dto.OrderReportsDto;
import in.surendra.Service.OrderReportService;
import in.surendra.Util.ExcelGenerator;

@RestController
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private OrderReportService reportService;
	
	@GetMapping("/allOrders")
	public ResponseEntity<InputStreamResource> downloadExcel(){
	
		List<OrderReportsDto> allOrders = reportService.getAllOrders();
		
		if(allOrders!=null) {
			try {
				ByteArrayInputStream in = ExcelGenerator.generateExcel(allOrders);
                InputStreamResource resource = new InputStreamResource(in);

                org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
                headers.add("Content-Disposition", "attachment; filename=orders.xlsx");
                return ResponseEntity.ok()
                					 .headers(headers)
                					 .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                					 .body(resource);
			}
			catch(IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		}
		
		else {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	            }
		}
	}
	
