package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.exceptions.FunctionNotFoundException;
import br.com.mksoftware.control.repository.FunctionRepository;
import br.com.mksoftware.control.repository.TagPermissionRespository;

@Service
public class FunctionService {
	

	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private TagPermissionRespository tagPermissionRepository;

	public List<Function> getAll() {
		var functions = functionRepository.findAll();
		return functions;
	}
	
	
	public List<Function> getFunctionsBySystem(System system) {
		var functions = functionRepository.findAll();
		return functions;
	}

	@Transactional
	public Function getFunctionById(Long id) {
		return functionRepository.findById(id).orElseThrow(() -> new FunctionNotFoundException(id.toString()));
	}

	@Transactional
	public void deleteFunctionById(Long id) {
		functionRepository.deleteById(id);
	}
	
	@Transactional
	public Function saveFunction(Function function) {
		return functionRepository.save(function);
	}

	@Transactional
	public Function updateFunction(Function function) {
		return functionRepository.save(function);
	}

	@Transactional
	public Function activate(Long id) {
		Function function = functionRepository.findById(id).get();
		function.activate();
		return function;
	}
	
	@Transactional
	public Function inactivate(Long id) {
		Function function = functionRepository.findById(id).get();
		function.inactivate();
		return function;
	}

	@Transactional
	public Function addTags(Long id, TagPermission tag) {
		
		Function function = functionRepository.findById(id).get();
		
		TagPermission getTag = tagPermissionRepository.getById(tag.getId());
		
		if(ObjectUtils.isEmpty(getTag)) {
			
		}
		
		function.addTag(getTag);

		return function;
	}
	
	
	@Transactional
	public Function removeTags(Long id, TagPermission tag) {
		Function function = functionRepository.findById(id).get();
		
		TagPermission getTag = tagPermissionRepository.getById(tag.getId());
		
		if(ObjectUtils.isEmpty(getTag)) {
			
		}
		
		function.removeTag(getTag);

		return function;
	}
	

}
