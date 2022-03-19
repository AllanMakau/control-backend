package br.com.mksoftware.control.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Officer;
import br.com.mksoftware.control.repository.OfficerRepository;

@Service
public class OfficerService {
	
	
	@Autowired
	private OfficerRepository officerRespository;

	public List<Officer> getAll() {
		var officers = officerRespository.findAll();
		return officers;
	}

	public Officer getOfficerById(Long id) {
		return officerRespository.findById(id).get();
	}
	
	public Officer saveOfficer(Officer officer) {
		return officerRespository.save(officer);
	}

	public void deleteOfficerById(Long id) {
		officerRespository.deleteById(id);
	}

	public Officer updateOfficer(Officer officer) {
		return officerRespository.save(officer);
	}

	public Officer activate(Long id, Boolean ativo) {
		Officer officerOld = officerRespository.findById(id).get();
		officerOld.setIsActive(ativo);
		Officer officerUpdated = officerRespository.save(officerOld);
		return officerUpdated;
	}

}
