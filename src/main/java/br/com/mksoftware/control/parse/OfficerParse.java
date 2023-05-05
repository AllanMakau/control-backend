package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.OfficerResponse;
import br.com.mksoftware.control.dtos.resquest.OfficerRequest;
import br.com.mksoftware.control.entities.Officer;

@Component
public class OfficerParse {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Officer toDomainObject(OfficerRequest officerRequest) {
		return modelMapper.map(officerRequest, Officer.class);
	}
	
	public void toDomainInputOfficer(OfficerRequest officerRequest, Officer officer) {
		 modelMapper.map(officerRequest, officer);
	}


	public OfficerResponse toModelResponse(Officer officer) {
		return modelMapper.map(officer, OfficerResponse.class);
	}
	
	public List<OfficerResponse> toCollectionModel(List<Officer> officers) {
		return officers.stream()
				.map(officer -> toModelResponse(officer))
				.collect(Collectors.toList());  
	}
	
	public Officer toOfficerDomain(OfficerRequest officerRequest) {
		
		return modelMapper.map(officerRequest, Officer.class);
	}

}
