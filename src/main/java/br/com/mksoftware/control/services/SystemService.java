package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.exceptions.SystemNotFoundException;
import br.com.mksoftware.control.repository.SystemRepository;

@Service
public class SystemService {

	@Autowired
	private SystemRepository systemRepository;

	public List<System> getAll() {
		var systems = systemRepository.findAll();
		return systems;
	}

	public System getSystemById(Long id) {
		return systemRepository.findById(id).orElseThrow(() -> new SystemNotFoundException(id.toString()));
	}

	public void deleteSystemById(Long id) {
		systemRepository.deleteById(id);
	}
	
	public System saveSystem(System system) {
		return systemRepository.save(system);
	}

	public System updateSystem(System system) {
		return systemRepository.save(system);
	}

	@Transactional
	public System activate(Long id) {
		System system = systemRepository.findById(id).get();
		system.activate();
		return system;
	}
	
	@Transactional
	public System inactivate(Long id) {
		System system = systemRepository.findById(id).get();
		system.inactivate();
		return system;
	}
	

}
