package com.tyss.ems.beans;

import java.sql.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @author shubham
 * This class is EmployeeRegisterInformationBean to get and set the Data
 */
@Data
public class EmployeeRegisterInformationBean {

	private Integer empId;
	private String candidateName;
	private String gender;	
	private String emailId;
	private Long contactNumber;
	private List<String> skillSet;
	private Date dateOfJoining;
	private List<String> aadharImagePath;
	private List<String> panCardImagePath;
	private String status;
	private int manager;
	private Boolean active;
	private String designation;
	private String employmentId;
	
	
	private EmployeeExperianceInformationBean employeeExperianceInformationBean;
}
