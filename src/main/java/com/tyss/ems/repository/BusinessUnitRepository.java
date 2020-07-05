package com.tyss.ems.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ems.dto.EmployeeBusinessUnitDto;

public interface BusinessUnitRepository extends JpaRepository<EmployeeBusinessUnitDto, Integer> {

	public List<EmployeeBusinessUnitDto> findByUnitName(String unitName, Pageable pageable);
	public List<EmployeeBusinessUnitDto> findByUnitName(String unitName);
}
