package com.tyss.ems.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * 
 * @author shubham
 *
 *  This class demonstrate, persist the data into ty_emp_experiance_information table.
 */
@Data
@Entity
@Table(name="ty_emp_experiance_information")
public class EmployeeExperianceInformationDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exp_id")
	private Integer expId;
	@Column(name="total_yrs")
	private Double totalYrs;
	@Column(name="relavant_exp_in_yrs")
	private Double relavantExpInYrs;
	@Column(name="current_ctc")
	private Double currentCtc;
	@Column(name="notice_period_in_dys")
	private Integer noticePeriodInDys;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private EmployeeRegisterInformationDto employeeRegisterInformationDto;
	
	
}
