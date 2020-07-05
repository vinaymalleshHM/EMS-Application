package com.tyss.ems.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tyss.ems.beans.EmployeeBusinessUnitBean;
import com.tyss.ems.beans.EmployeeExperianceInformationBean;
import com.tyss.ems.beans.EmployeeRegisterInformationBean;
import com.tyss.ems.dto.EmployeeBusinessUnitDto;
import com.tyss.ems.dto.EmployeeExperianceInformationDto;
import com.tyss.ems.dto.EmployeeRegisterInformationDto;
import com.tyss.ems.repository.BusinessUnitRepository;
import com.tyss.ems.repository.EmployeeRepositoty;

/**
 * 
 * This class demonstrate the business logic of ems application.
 */
@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

	@Autowired
	private EmployeeRepositoty empRepo;

	@Autowired
	private BusinessUnitRepository unitRepo;

	/**
	 * 
	 * @param empDto
	 * @return transfer employee dto to bean.
	 */
	private EmployeeRegisterInformationBean transverEmpDtoToBean(EmployeeRegisterInformationDto empDto) {

		EmployeeRegisterInformationBean empBean = new EmployeeRegisterInformationBean();
		EmployeeExperianceInformationBean empExpBean = new EmployeeExperianceInformationBean();
		empBean.setEmpId(empDto.getEmpId());
		empBean.setCandidateName(empDto.getCandidateName());
		empBean.setGender(empDto.getGender());
		empBean.setEmailId(empDto.getEmailId());
		empBean.setContactNumber(empDto.getContactNumber());
		empBean.setSkillSet(empDto.getSkillSet());
		empBean.setDateOfJoining(empDto.getDateOfJoining());
		empBean.setAadharImagePath(empDto.getAadharImagePath());
		empBean.setPanCardImagePath(empDto.getPanCardImagePath());
		empBean.setStatus(empDto.getStatus());
		empBean.setActive(empDto.getActive());
		if (empDto.getEmployeeExperianceInformationDto() != null) {
			empExpBean.setExpId(empDto.getEmployeeExperianceInformationDto().getExpId());
			empExpBean.setTotalYrs(empDto.getEmployeeExperianceInformationDto().getTotalYrs());
			empExpBean.setRelavantExpInYrs(empDto.getEmployeeExperianceInformationDto().getRelavantExpInYrs());
			empExpBean.setCurrentCtc(empDto.getEmployeeExperianceInformationDto().getCurrentCtc());
			empExpBean.setNoticePeriodInDys(empDto.getEmployeeExperianceInformationDto().getNoticePeriodInDys());
			empBean.setEmployeeExperianceInformationBean(empExpBean);
		}
		return empBean;

	}

	/**
	 * 
	 * @param bean
	 * @return transfer Dto to Bean
	 */
	private EmployeeRegisterInformationDto transverEmpBeanToDto(EmployeeRegisterInformationBean bean) {

		EmployeeRegisterInformationDto employee = new EmployeeRegisterInformationDto();
		EmployeeExperianceInformationDto employeeExp = new EmployeeExperianceInformationDto();
		if (bean != null) {

			employee.setCandidateName(bean.getCandidateName());
			employee.setGender(bean.getGender());
			employee.setEmailId(bean.getEmailId());
			employee.setContactNumber(bean.getContactNumber());
			employee.setSkillSet(bean.getSkillSet());
			employee.setDateOfJoining(bean.getDateOfJoining());
			employee.setAadharImagePath(bean.getAadharImagePath());
			employee.setPanCardImagePath(bean.getPanCardImagePath());
			employee.setStatus("pending");
			employee.setActive(true);
			if (bean.getEmployeeExperianceInformationBean() != null) {
				employeeExp.setCurrentCtc(bean.getEmployeeExperianceInformationBean().getCurrentCtc());
				employeeExp.setNoticePeriodInDys(bean.getEmployeeExperianceInformationBean().getNoticePeriodInDys());
				employeeExp.setRelavantExpInYrs(bean.getEmployeeExperianceInformationBean().getRelavantExpInYrs());
				employeeExp.setTotalYrs(bean.getEmployeeExperianceInformationBean().getTotalYrs());
				employee.setEmployeeExperianceInformationDto(employeeExp);
			}

		}
		return employee;
	}

	/**
	 * saveEmployee(-) is for data persistence logic.
	 */
	@Override
	public boolean saveEmployee(EmployeeRegisterInformationBean emp) {

		EmployeeRegisterInformationDto beanToDtoTransver = transverEmpBeanToDto(emp);
		if (beanToDtoTransver != null) {
			empRepo.save(beanToDtoTransver);
			return true;

		}

		return false;

	}

	/**
	 * This method will return the details of all employee from
	 * EmployeeRegisterInformation table.
	 */
	@Override
	public List<EmployeeRegisterInformationBean> getAllEmployee(int page, int content) {

		Pageable pageWithElements = PageRequest.of(page, content);
		Page<EmployeeRegisterInformationDto> findAll = empRepo.findAll(pageWithElements);
		List<EmployeeRegisterInformationBean> details = new LinkedList<EmployeeRegisterInformationBean>();

		if (findAll != null) {
			for (EmployeeRegisterInformationDto empDto : findAll) {
				EmployeeRegisterInformationBean dtoToBeanTransver = transverEmpDtoToBean(empDto);
				details.add(dtoToBeanTransver);
			}
		}

		return details;
	}

	/**
	 * to get all manager details
	 */
	@Override
	public List<EmployeeRegisterInformationBean> getAllManager(int page, int content) {
		Pageable pageWithElements = PageRequest.of(page, content);
		List<EmployeeRegisterInformationDto> findByAllManager = empRepo.findByAllManager(pageWithElements);

		List<EmployeeRegisterInformationBean> details = new LinkedList<EmployeeRegisterInformationBean>();

		if (findByAllManager != null) {

			for (EmployeeRegisterInformationDto dto : findByAllManager) {
				EmployeeRegisterInformationBean transverEmpDtoToBean = transverEmpDtoToBean(dto);
				details.add(transverEmpDtoToBean);
			}
		}

		return details;
	}

	/**
	 * search for specific employee in table
	 */
	@Override
	public List<EmployeeRegisterInformationBean> searchForEmp(Specification<EmployeeRegisterInformationBean> specs) {

		List<EmployeeRegisterInformationDto> beans = empRepo.findAll((Sort) Specification.where(specs));
		List<EmployeeRegisterInformationBean> details = new LinkedList<EmployeeRegisterInformationBean>();

		if (beans != null) {
			for (EmployeeRegisterInformationDto empDto : beans) {
				EmployeeRegisterInformationBean dtoToBeanTransver = transverEmpDtoToBean(empDto);
				details.add(dtoToBeanTransver);
			}
		}
		return details;
	}

	@Override
	public boolean saveManager(int id) {

		return false;
	}

	/**
	 * save Business unit information.
	 */
	@Override
	public boolean saveBusinessUnit(EmployeeBusinessUnitBean bean) {
		if (bean != null) {
			EmployeeBusinessUnitDto dto = new EmployeeBusinessUnitDto();
			dto.setUnitName(bean.getUnitName());
			EmployeeRegisterInformationDto registerInformationDto = empRepo.findById(bean.getEmployee().getEmpId()).get();
			dto.setEmployee(registerInformationDto);
			unitRepo.save(dto);
			return true;
		}
		return false;
	}

	/**
	 * filter by unit name details
	 */
	@Override
	public List<EmployeeBusinessUnitBean> filterByUnitName(String unitName, int page, int content) {
		Pageable pageWithElements = PageRequest.of(page, content);

		List<EmployeeBusinessUnitDto> findByUnitName = unitRepo.findByUnitName(unitName, pageWithElements);

		List<EmployeeBusinessUnitBean> listOfBean = new LinkedList<EmployeeBusinessUnitBean>();
		if (findByUnitName != null) {
			for (EmployeeBusinessUnitDto dto : findByUnitName) {

				EmployeeBusinessUnitBean bean = new EmployeeBusinessUnitBean();
				EmployeeRegisterInformationBean transverEmpDtoToBean = transverEmpDtoToBean(dto.getEmployee());

				bean.setUnitId(dto.getUnitId());
				bean.setUnitName(dto.getUnitName());
				bean.setEmployee(transverEmpDtoToBean);
				listOfBean.add(bean);
			}

		}
		return listOfBean;
	}

	/**
	 * get all unit name details
	 */
	@Override
	public List<EmployeeBusinessUnitBean> getAllUnitName(int page, int content) {
		Pageable pageWithElements = PageRequest.of(page, content);
		Page<EmployeeBusinessUnitDto> findAll = unitRepo.findAll(pageWithElements);
		List<EmployeeBusinessUnitBean> listOfBean = new LinkedList<EmployeeBusinessUnitBean>();

		if (findAll != null) {
			for (EmployeeBusinessUnitDto dto : findAll) {

				EmployeeBusinessUnitBean bean = new EmployeeBusinessUnitBean();
				EmployeeRegisterInformationBean transverEmpDtoToBean = transverEmpDtoToBean(dto.getEmployee());
				bean.setUnitId(dto.getUnitId());
				bean.setUnitName(dto.getUnitName());
				bean.setEmployee(transverEmpDtoToBean);
				listOfBean.add(bean);
			}
		}

		return listOfBean;
	}

	/**
	 * get all the unit by manager.
	 */
	@Override
	public List<EmployeeBusinessUnitBean> getAllUnitNameWithManager(String unitName, int page, int content,
			int manager) {
		Pageable pageWithElements = PageRequest.of(page, content);

		List<EmployeeBusinessUnitDto> findByUnitName = unitRepo.findByUnitName(unitName, pageWithElements);

		EmployeeBusinessUnitBean bean = new EmployeeBusinessUnitBean();

		List<EmployeeBusinessUnitBean> listOfBean = new LinkedList<EmployeeBusinessUnitBean>();
		if (findByUnitName != null) {

			for (EmployeeBusinessUnitDto dto : findByUnitName) {
				if (dto.getEmployee() != null) {

					if (dto.getEmployee().getManager() == manager) {
						bean.setUnitId(dto.getUnitId());
						bean.setUnitName(dto.getUnitName());

						EmployeeRegisterInformationBean transverEmpDtoToBean = transverEmpDtoToBean(dto.getEmployee());
						bean.setEmployee(transverEmpDtoToBean);
						listOfBean.add(bean);
					}

				}

			}
		}
		return listOfBean;
	}

}
