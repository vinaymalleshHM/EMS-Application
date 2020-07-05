package com.tyss.ems.dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
 * This entity class demonstrate, persist the data into ty_emp_register_information table.
 */
@Data
@Entity
@Table(name="ty_emp_register_information")
public class EmployeeRegisterInformationDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Integer empId;
	
	@Column(name="candidate_name")
	private String candidateName;
	
	@Column(name="gender")
	private String gender;	
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="contact_number")
	private Long contactNumber;
	
	@Column(name="skill_set")
	@ElementCollection
	private List<String> skillSet;
	
	@Column(name="date_of_joining")
	private Date dateOfJoining;
	
	@ElementCollection
	@Column(name="aadhar_image_path")
	private List<String> aadharImagePath;
	
	@ElementCollection
	@Column(name="pan_card_image_path")
	private List<String> panCardImagePath;
	
	@Column(name="status")
	private String status;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name = "manager_id")
	private int manager;
	
	@Column(name = "designation" )
	private String designation;
	
	@Column(name = "employment_Id")
	private String employmentId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeExperianceInformationDto employeeExperianceInformationDto;
	
	
	
	
}
