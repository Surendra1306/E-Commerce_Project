package in.surendra.Exceptions;

public class OrderNotFoundException extends RuntimeException{

	private String Code;
	
	public OrderNotFoundException(String code, String msg) {
		super();
		Code = code;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

}
