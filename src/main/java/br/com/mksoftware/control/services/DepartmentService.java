package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Department;
import br.com.mksoftware.control.exceptions.DepartmentNotFoundException;
import br.com.mksoftware.control.repository.DepartamentRespository;

@Service
public class DepartmentService {
	
	
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
