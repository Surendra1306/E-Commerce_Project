package in.surendra.Exception;

public class CartServiceException extends RuntimeException{
	
	private String errCode;

	public CartServiceException(String errCode,String msg) {
		super(msg);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
