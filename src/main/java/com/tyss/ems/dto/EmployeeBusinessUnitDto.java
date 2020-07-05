package com.tyss.ems.dto;

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
 *  This entity class demonstrate, persist the data into ty_emp_business_unit table.
 */
@Data
@Entity
@Table(name="ty_emp_business_unit")
public class EmployeeBusinessUnitDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="unit_id")
	private Integer unitId;
	@Column(name="unit_name")
	private String unitName;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeRegisterInformationDto employee;
}
