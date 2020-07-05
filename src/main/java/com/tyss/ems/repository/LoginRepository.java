package com.tyss.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.ems.dto.EmployeeLoginDto;

public interface LoginRepository extends JpaRepository<EmployeeLoginDto, String>{

}
