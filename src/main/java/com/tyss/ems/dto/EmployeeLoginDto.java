package com.tyss.ems.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 
 * @author shubham
 *
 * This entity class demonstrate, persist the data into ty_emp_login_information table.
 */
@Data
@Entity
@Table(name = "ty_emp_login_information")
public class EmployeeLoginDto {

	@Id
	@GenericGenerator(name = "string_based_custom_sequence", strategy = "com.tyss.ems.id.EmployeeIdGenerator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "string_based_custom_sequence")
 
	@Column(name="generated_id")
	private String generatedId;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "password")
	private String password;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeRegisterInformationDto employeeRegisterInformationDto;
}
