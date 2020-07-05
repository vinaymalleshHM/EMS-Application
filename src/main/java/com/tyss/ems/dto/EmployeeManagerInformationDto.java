/*
 * package com.tyss.ems.dto;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.Entity; import javax.persistence.GeneratedValue; import
 * javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToOne; import
 * javax.persistence.OneToOne; import javax.persistence.Table;
 * 
 * import lombok.Data;
 * 
 *//**
	 * 
	 * @author shubham
	 *
	 *         This entity class demonstrate, persist the data into
	 *         ty_emp_manager_information table.
	 *//*
		 * @Data
		 * 
		 * @Entity
		 * 
		 * @Table(name = "ty_emp_manager_information") public class
		 * EmployeeManagerInformationDto {
		 * 
		 * @Id
		 * 
		 * @GeneratedValue(strategy = GenerationType.IDENTITY)
		 * 
		 * @Column(name = "id") private Integer id;
		 * 
		 * @ManyToOne(cascade = CascadeType.ALL)
		 * 
		 * @JoinColumn(name = "manager_id", referencedColumnName = "emp_id") private
		 * EmployeeRegisterInformationDto manager;
		 * 
		 * 
		 * @OneToOne(cascade = CascadeType.ALL)
		 * 
		 * @JoinColumn(name = "empId") private EmployeeRegisterInformationDto employee;
		 * 
		 * 
		 * 
		 * }
		 */