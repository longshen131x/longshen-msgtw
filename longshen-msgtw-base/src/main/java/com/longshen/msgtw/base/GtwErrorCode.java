package com.longshen.msgtw.base;

public enum GtwErrorCode {
	SYS9999("999999","未知错误"),
	SYS9998("999998","平台错误"),
	SYS9997("999997","路由失败,无法路由");
	
	private String errorCode;
	private String errorDesc;
	
	
	GtwErrorCode(String errorCode,String errorDesc){
		this.errorCode=errorCode;
		this.errorDesc=errorDesc;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public String getErrorDesc() {
		return errorDesc;
	}


	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	


}
