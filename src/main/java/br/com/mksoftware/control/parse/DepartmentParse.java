package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.DepartamentoResponse;
import br.com.mksoftware.control.dtos.resquest.DepartmentRequest;
import br.com.mksoftware.control.entities.Department;

@Component
public class DepartmentParse {

	@Autowired
	private ModelMapper modelMapper;

	public Department toDomainObject(DepartmentRequest departmentRequest) {
		return modelMapper.map(departmentRequest, Department.class);
	}

	public DepartamentoResponse toModelResponse(Department department) {
		return modelMapper.map(department, DepartamentoResponse.class);
	}
	
	public List<DepartamentoResponse> toCollectionModel(List<Department> departments) {
		return departments.stream()
				.map(department -> toModelResponse(department))
				.collect(Collectors.toList());  
	}

}
