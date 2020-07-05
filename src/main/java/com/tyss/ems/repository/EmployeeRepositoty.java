package com.tyss.ems.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tyss.ems.dto.EmployeeRegisterInformationDto;
/**
 * 
 * This interface demonstrate Employee repository inheriting the JPA repository
 * for mapping subsystem to map classes onto relational tables.
 *
 */
public interface EmployeeRepositoty extends JpaRepository<EmployeeRegisterInformationDto, Integer>, JpaSpecificationExecutor<EmployeeRegisterInformationDto>{
	
	@Query(value = "SELECT e FROM EmployeeRegisterInformationDto e WHERE e.manager=?l", nativeQuery = true)
	List<EmployeeRegisterInformationDto> findByManager(int id);
	
	@Query(value = "SELECT e FROM EmployeeRegisterInformationDto e WHERE e.manager=e.empId", nativeQuery = true)
	List<EmployeeRegisterInformationDto> findByAllManager(Pageable pageable);
}