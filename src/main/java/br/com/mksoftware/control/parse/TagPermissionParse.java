package br.com.mksoftware.control.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mksoftware.control.dtos.response.TagPermissionResponse;
import br.com.mksoftware.control.dtos.resquest.SystemRequest;
import br.com.mksoftware.control.dtos.resquest.TagPermissionRequest;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.TagPermission;

@Component
public class TagPermissionParse {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TagPermission toDomainObject(TagPermissionRequest tagPermissionRequest) {
		return modelMapper.map(tagPermissionRequest, TagPermission.class);
	}
	
	public void toDomainInputTagPermission(TagPermissionRequest tagPermissionRequest, TagPermission tagPermission) {
		 modelMapper.map(tagPermissionRequest, tagPermission);
	}

	public TagPermissionResponse toModelResponse(TagPermission tagPermission) {
		return modelMapper.map(tagPermission, TagPermissionResponse.class);
	}
	
	public List<TagPermissionResponse> toCollectionModel(List<TagPermission> tagPermissions) {
		return tagPermissions.stream()
				.map(tagPermission -> toModelResponse(tagPermission))
				.collect(Collectors.toList());  
	}

}
