package in.surendra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

	@ExceptionHandler(value = ProductSeviceException.class)
	public ResponseEntity<ExResponse> handleException(ProductSeviceException pse){
		
		ExResponse exResponse = new ExResponse();
		
		exResponse.setErrCode(pse.getErrCode());
		exResponse.setErrMessage(pse.getMessage());
		
		return new ResponseEntity<ExResponse>(exResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
