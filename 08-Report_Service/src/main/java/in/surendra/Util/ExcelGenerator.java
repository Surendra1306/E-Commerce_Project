package in.surendra.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import in.surendra.Dto.OrderReportsDto;

public class ExcelGenerator {
	
	public static ByteArrayInputStream generateExcel(List<OrderReportsDto> orders) throws IOException {
		
		try (Workbook workBook = new XSSFWorkbook()) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Sheet sheet = workBook.createSheet();
				
			String[] headers = {"orderId", "productId", "userId", "quantity", "price", "orderStatus", "orderDate"};
			
			Row headerRow = sheet.createRow(0);
			for(int i=0; i<headers.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(headers[i]);
			}
			
			int rowIdx=1;
			for(OrderReportsDto reportsDto : orders) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(1).setCellValue(reportsDto.getOrderId());	
				row.createCell(2).setCellValue(reportsDto.getProductId() == null ? 4 : reportsDto.getProductId());		
				if(reportsDto.getUserId()!=null) {
					row.createCell(3).setCellValue(reportsDto.getUserId());
				}
				else {
					row.createCell(3).setCellValue("NA");
				}
				row.createCell(4).setCellValue(reportsDto.getQuantity() == null ? 0 : reportsDto.getQuantity());	
				row.createCell(5).setCellValue(reportsDto.getPrice() ==null ? 0 : reportsDto.getPrice());
				row.createCell(6).setCellValue(reportsDto.getOrderStatus() == null ? "N/A" : (String)reportsDto.getOrderStatus().name());
				row.createCell(7).setCellValue(reportsDto.getOrderDate() == null ? new Date() : (Date)reportsDto.getOrderDate());
			}
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}

	
	
	
	
	