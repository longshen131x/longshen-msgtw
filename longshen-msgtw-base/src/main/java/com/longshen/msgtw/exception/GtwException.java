package com.longshen.msgtw.exception;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 网关异常
 * @author longshen
 *
 */
@Getter
@Setter
public class GtwException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6514691313142220195L;
	
	private String errorCode;
	private String errorParam;
	
	
	public GtwException(String errorCode) {
		super(errorCode);
		this.errorCode=errorCode;
		
	}
	
	public GtwException(String errorCode,String[] errorParams) {
		super(errorCode);
		this.errorCode=errorCode;
		
	}

	public GtwException(String errorCode,String errorParam ,Throwable cause) {
		super(errorCode,cause);
		this.errorCode=errorCode;
		this.errorParam =errorParam;
		
	}
	
	public GtwException(String errorCode,Throwable cause) {
		super(errorCode,cause);
		this.errorCode=errorCode;
		
	}
	
	@Override
	public String getMessage() {
		if(StringUtils.isNoneBlank(errorParam)) {
			return errorParam;
		}else if(StringUtils.isNotBlank(errorCode)) {
			return errorCode;
		}else {
			return super.getMessage();
		}
		
	}
	
}
