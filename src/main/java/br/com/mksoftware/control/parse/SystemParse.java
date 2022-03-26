package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.SystemResponse;
import br.com.mksoftware.control.dtos.resquest.SystemRequest;
import br.com.mksoftware.control.entities.System;

@Component
public class SystemParse {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public System toDomainObject(SystemRequest systemRequest) {
		return modelMapper.map(systemRequest, System.class);
	}

	public SystemResponse toModelResponse(System system) {
		return modelMapper.map(system, SystemResponse.class);
	}
	
	public List<SystemResponse> toCollectionModel(List<System> systems) {
		return systems.stream()
				.map(system -> toModelResponse(system))
				.collect(Collectors.toList());  
	}

}
