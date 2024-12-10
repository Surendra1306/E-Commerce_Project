package in.surendra.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartExceptionHandler {
	
	@ExceptionHandler(value = CartServiceException.class)
	public ResponseEntity<ExceptionResponse> handleCartException(CartServiceException cae){
		ExceptionResponse errResponse = new ExceptionResponse();
		
		errResponse.setErrCode(cae.getErrCode());
		errResponse.setErrMessage(cae.getMessage());
		
		return new ResponseEntity<ExceptionResponse>(errResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
