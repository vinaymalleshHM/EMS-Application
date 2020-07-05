package com.tyss.ems.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sipios.springsearch.anotation.SearchSpec;
import com.tyss.ems.beans.EmployeeBusinessUnitBean;
import com.tyss.ems.beans.EmployeeRegisterInformationBean;
import com.tyss.ems.exception.TYIllegalArgumentException;
import com.tyss.ems.response.EmployeeResponse;
import com.tyss.ems.service.EmployeeManagementService;

/**
 * This class demonstrate the controller logics and Several API's to perform
 * crud operations.
 * 
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeManagementService empService;

	@Autowired
	ServletContext context;

	/**
	 * 
	 * @param employeeBean
	 * @return save Employee register information.
	 */
	@PostMapping(path = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@RequestBody EmployeeRegisterInformationBean employeeBean) {
		EmployeeResponse response = new EmployeeResponse();
		if (empService.saveEmployee(employeeBean)) {
			response.setError(false);
			response.setData("information added successfully");
			if (employeeBean == null) {
				throw new TYIllegalArgumentException("EmployeeRegisterInformationBean cannot be empty");
			}
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setData("failed to add");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param page
	 * @param content
	 * @return the list of all records from EmployeeRegisterInformation
	 */
	@GetMapping(path = "/all-employee/page/{page}/content/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> allEmployeeDetails(@PathVariable("page") int page,
			@PathVariable("content") int content) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeRegisterInformationBean> beans = empService.getAllEmployee(page, content);
		if (beans != null) {
			response.setError(false);
			response.setMessage("information Found");
			response.setData(beans);
			if (empService.getAllEmployee(page, content) == null) {
				throw new TYIllegalArgumentException("No record present for employee");
			}
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("failed to Found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param page
	 * @param content
	 * @return all manager details.
	 */
	@GetMapping(path = "/manager-details/page/{page}/content/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> allManagerDetails(@PathVariable("page") int page,
			@PathVariable(name = "content", required = true) int content) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeRegisterInformationBean> beans = empService.getAllManager(page, content);
		if (beans != null) {
			response.setError(false);
			response.setMessage("information Found");
			response.setData(beans);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("failed to Found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param specs
	 * @return advance search to get register information details.
	 */
	@GetMapping("/searchEmployee")
	public ResponseEntity<Object> searchForEmp(@SearchSpec Specification<EmployeeRegisterInformationBean> specs) {
		List<EmployeeRegisterInformationBean> data = empService.searchForEmp(specs);
		EmployeeResponse response = new EmployeeResponse();
		if (data != null) {
			response.setError(false);
			response.setMessage("information Found");
			response.setData(data);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("failed to Found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param id
	 * @return save manager details.
	 */
	@PostMapping(path = "/manager/id/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveManager(@PathVariable("id") int id) {
		EmployeeResponse response = new EmployeeResponse();
		if (empService.saveManager(id)) {
			response.setError(false);
			response.setData("information added successfully");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setData("information added successfully");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param employeeBusinessBean
	 * @return save business information in db.
	 */
	@PostMapping(path = "/businessUnit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveBusinessUnitInformation(
			@RequestBody EmployeeBusinessUnitBean employeeBusinessBean) {
		EmployeeResponse response = new EmployeeResponse();
		if (empService.saveBusinessUnit(employeeBusinessBean)) {
			if (employeeBusinessBean == null) {
				throw new TYIllegalArgumentException("Employee business unit cant be null");
			}
			response.setError(false);
			response.setData("information added successfully");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setError(true);
			response.setData("information added successfully");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param page
	 * @param content
	 * @param unitName
	 * @param manager
	 * @return get all unit name with manager.
	 */
	@GetMapping(path = "/unit/unitName/{unitName}/page/{page}/content/{content}/manager/{manager}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUnitNameWithManager(@PathVariable("page") int page,
			@PathVariable("content") int content, @PathVariable("unitName")String unitName, @PathVariable("manager") int manager) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBusinessUnitBean> beans = empService.getAllUnitNameWithManager(unitName, page, content, manager);
		if (beans != null) {
			response.setError(false);
			response.setMessage("getAllUnitNameWithManager information Found");
			response.setData(beans);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("getAllUnitNameWithManager information failed to Found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param page
	 * @param content
	 * @return get all unit name.
	 */
	@GetMapping(path = "/unitName/page/{page}/content/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUnitName(@PathVariable("page") int page,
			@PathVariable("content") int content) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBusinessUnitBean> beans = empService.getAllUnitName(page, content);
		if (beans != null) {
			response.setError(false);
			response.setMessage("Get all unit name information Found");
			response.setData(beans);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("Get all unit name information failed to found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param page
	 * @param content
	 * @param unitName
	 * @return get details by unit name.
	 */
	@GetMapping(path = "/unit/unitName/{unitName}/page/{page}/content/{content}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> filterByUnitName(@PathVariable("page") int page,
			@PathVariable("content") int content,  @PathVariable("unitName")String unitName) {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBusinessUnitBean> beans = empService.filterByUnitName(unitName, page, content);
		if (beans != null) {
			response.setError(false);
			response.setMessage("Get details by unit name information Found");
			response.setData(beans);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {

			response.setError(true);
			response.setMessage("Get details by unit name information failed to found");
			return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	public String addRegister(@RequestParam("file") MultipartFile file) throws IOException {
		String modifString = null;
		boolean isExist = new File(context.getRealPath("/EmpAdhar")).exists();
		System.out.println("isExist :"+isExist);
		if (isExist) {
			new File(context.getRealPath("/EmpAdhar")).mkdir();
		}
		String fileName = file.getOriginalFilename();
		 modifString = FilenameUtils.getBaseName(fileName) + "_" + System.currentTimeMillis() + "."
				+ FilenameUtils.getExtension(fileName);
		File serverFile = new File(context.getRealPath("/EmpAdhar" + File.separator + modifString));
		System.out.println("serverFile : "+serverFile);
		System.out.println("serverFile.getAbsolutePath(); "+serverFile.getAbsolutePath());
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
		} catch (Exception e) {
		}
		return serverFile.getAbsolutePath();
		

		
	}
	
	@PostMapping(path = "/image")
	public ResponseEntity<Object> uploadFile(@RequestParam("files") MultipartFile[] files) {
		List<String> list = new LinkedList<String>();
		 List<String> collect = Arrays.asList(files)
                .stream()
                .map(file -> {
					try {
						return addRegister(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				})
                .collect(Collectors.toList());
		 System.out.println("List<String> collect :"+collect);
		return null;
    }
}
