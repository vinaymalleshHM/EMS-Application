package com.tyss.ems.beans;

import lombok.Data;

/**
 * 
 * @author shubham
 * This class is EmployeeExperianceInformationBean to get and set the Data
 */
@Data
public class EmployeeExperianceInformationBean {
	
	private Integer expId;
	private Double totalYrs;
	private Double relavantExpInYrs;
	private Double currentCtc;
	private Integer noticePeriodInDys;
	
	
	
}
