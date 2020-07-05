package com.tyss.ems.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * 
 * This demonstrate to set the msg from response.
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponse {
	private boolean error;
	private String message;
	private Object data;

}
