package br.com.mksoftware.control.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.System;
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
		return systemRepository.findById(id).get();
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

	public System activate(Long id, Boolean ativo) {
		System systemOld = systemRepository.findById(id).get();
		systemOld.setIsActive(ativo);
		System systemUpdated = systemRepository.save(systemOld);
		return systemUpdated;
	}
	

}
