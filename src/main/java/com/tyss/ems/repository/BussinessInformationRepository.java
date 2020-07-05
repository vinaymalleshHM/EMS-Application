package com.tyss.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ems.dto.EmployeeBusinessInformationDto;

public interface BussinessInformationRepository extends JpaRepository<EmployeeBusinessInformationDto, Integer> {

}
