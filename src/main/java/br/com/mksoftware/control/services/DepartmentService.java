package br.com.mksoftware.control.services;

import java.io.StringWriter;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.bind.JAXB;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.exceptions.DepartmentNotFoundException;
import br.com.mksoftware.control.repository.DepartamentRespository;

@Service
public class DepartmentService {
	

	private static final Logger logger 
    = LoggerFactory.getLogger(DepartmentService.class);
	
	@Autowired
	private DepartamentRespository departmentRepository;
	

	public List<Department> getAll() {
		var departments = departmentRepository.findAll();
		return departments;
	}

	public Department getDepartmentById(Long id) {
		return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id.toString()));
	}
	
	public Department saveDepartment(Department department) {
		
		JSONObject jsonObject = new JSONObject(department);
		logger.info("User JSON: {}", jsonObject);
		
		StringWriter sw = new StringWriter();
		JAXB.marshal(department, sw);
		String xmlString = sw.toString();
		
		logger.info("User XML: {}", xmlString);
		
		return departmentRepository.save(department);
	}

	public void deleteDepartmentById(Long id) {
		departmentRepository.deleteById(id);
	}

	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Transactional
	public Department activate(Long id) {
		Department department = departmentRepository.findById(id).get();
		department.activate();
		return department;
	}
	
	@Transactional
	public Department inactivate(Long id) {
		Department department = departmentRepository.findById(id).get();
		department.inactivate();
		return department;
	}

}
