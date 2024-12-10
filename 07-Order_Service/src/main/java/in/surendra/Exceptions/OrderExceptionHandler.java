package in.surendra.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {
	
	@ExceptionHandler(value = OrderNotFoundException.class)
	public ResponseEntity<ExResponse> handleOrderSerivice(OrderNotFoundException oe){
		ExResponse response = new ExResponse();
		response.setExcode(oe.getCode());
		response.setExMessage(oe.getMessage());	
		return new ResponseEntity<ExResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
