package com.shankulk.weatherapp.exception;

public class ErrorInfo {

	//private int errorCode;
	private String errorMessage;
	
	public ErrorInfo(String errorMessage) {
		//this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/*public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
*/
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
