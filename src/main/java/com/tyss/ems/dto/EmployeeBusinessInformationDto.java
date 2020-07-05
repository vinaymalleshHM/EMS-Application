package com.tyss.ems.dto;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
/**
 * 
 * @author shubham
 *
 *  This entity class demonstrate, persist the data into  ty_emp_business_information table.
 */
@Data
@Entity
@Table(name="ty_emp_business_information")
public class EmployeeBusinessInformationDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="business_id")
	private Integer businessId;
	@Column(name="offered_ctc")
	private Double offeredCtc;
	@Column(name="hike_given_in_percentage")
	private Double hikeGivenInPercentage;
	@Column(name="per_month_client_billing")
	private Double perMonthClientBilling;
	@Column(name="annual_client_billing")
	private Double annualClientBilling;
	@Column(name="gross_margin")
	private Double grossMargin;
	@Column(name="gross_margin_in_percentage")
	private Double grossMarginInPercentage;
	@Column(name="salary_commence_date")
	private Date salaryCommenceDate;
	@Column(name="source")
	private Double source;
	@Column(name="delivery_member")
	private Double deliveryMember;
	@Column(name="date_of_request")
	private Date dateOfRequest;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeRegisterInformationDto employeeRegisterInformationDto;
	
}
