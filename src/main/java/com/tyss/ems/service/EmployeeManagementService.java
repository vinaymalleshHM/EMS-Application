package com.tyss.ems.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.tyss.ems.beans.EmployeeBusinessUnitBean;
import com.tyss.ems.beans.EmployeeRegisterInformationBean;

public interface EmployeeManagementService {
	
	public boolean saveEmployee(EmployeeRegisterInformationBean emp);
	public List<EmployeeRegisterInformationBean> getAllEmployee(int page,int content);
	public List<EmployeeRegisterInformationBean> getAllManager(int page,int content);
	public List<EmployeeRegisterInformationBean> searchForEmp(Specification<EmployeeRegisterInformationBean> specs);
	public boolean saveManager(int id);
	
	
	public boolean saveBusinessUnit(EmployeeBusinessUnitBean bean);
	public List<EmployeeBusinessUnitBean> filterByUnitName(String unitName, int page, int content);
	public List<EmployeeBusinessUnitBean> getAllUnitName(int page,int content);
	public List<EmployeeBusinessUnitBean> getAllUnitNameWithManager(String unitName,int page,int content,int manager);
}
