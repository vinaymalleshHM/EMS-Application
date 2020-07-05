package com.tyss.ems.beans;

import lombok.Data;

/**
 * 
 * @author shubham
 * This class demonstrate the EmployeeLoginBean to get and set the Data.
 */
@Data
public class EmployeeLoginBean {
	private String generatedId;
	private String emailId;
	private String password;
	
	private EmployeeRegisterInformationBean employeeRegisterInformationBean;
}
