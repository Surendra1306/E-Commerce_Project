package in.surendra.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

	@ExceptionHandler(value = AuthServiceException.class)
	public ResponseEntity<ExResponse> handleException(AuthServiceException ae){
		
		ExResponse response = new ExResponse();
		
		response.setExMsg(ae.getMessage());
		response.setErrCode(ae.getErrCode());
		
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
