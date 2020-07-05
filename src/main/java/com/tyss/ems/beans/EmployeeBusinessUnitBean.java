package com.tyss.ems.beans;

import lombok.Data;

/**
 * 
 * @author shubham
 * This class is EmployeeBusinessUnitBean to get and set the Data
 */
@Data
public class EmployeeBusinessUnitBean {
	private Integer unitId;
	private String unitName;
	
	private EmployeeRegisterInformationBean employee;
	
	
}
