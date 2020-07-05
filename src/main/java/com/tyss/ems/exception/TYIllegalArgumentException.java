package com.tyss.ems.exception;
/**
 * This class demonstrate the custom  Exception
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TYIllegalArgumentException extends RuntimeException{
	
	private static final long serialVersionUID =1L;
	
	public  TYIllegalArgumentException(String msg) {
		super(msg);
	}

}
