package com.tyss.ems.beans;

import java.sql.Date;

import lombok.Data;

@Data
public class EmployeeBusinessInformationBean {

	private Integer businessInfoId;
	private Double offeredCtc;
	private Double hikeGivenInPercentage;
	private Double perMonthClientBilling;
	private Double annualClientBilling;
	private Double grossMargin;
	private Double grossMarginInPercentage;
	private Date salaryCommenceDate;
	private Double source;
	private Double deliveryMember;
	private Date dateOfRequest;
	
	
	private EmployeeRegisterInformationBean employeeRegisterInformationBean;
	

}
