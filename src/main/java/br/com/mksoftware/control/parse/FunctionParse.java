package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.FunctionResponse;
import br.com.mksoftware.control.dtos.resquest.FunctionRequest;
import br.com.mksoftware.control.entities.Function;

@Component
public class FunctionParse {
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Function toDomainObject(FunctionRequest functionRequest) {
		return modelMapper.map(functionRequest, Function.class);
	}
	
	public void toDomainInputOfficer(FunctionRequest functionRequest, Function function) {
		 modelMapper.map(functionRequest, function);
	}

	public FunctionResponse toModelResponse(Function function) {
		return modelMapper.map(function, FunctionResponse.class);
	}
	
	public List<FunctionResponse> toCollectionModel(List<Function> functions) {
		return functions.stream()
				.map(function -> toModelResponse(function))
				.collect(Collectors.toList());  
	}

}
