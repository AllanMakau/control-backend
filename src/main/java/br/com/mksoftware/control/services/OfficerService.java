package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.Officer;
import br.com.mksoftware.control.exceptions.OfficerNotFoundException;
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
		return officerRespository.findById(id).orElseThrow(() -> new OfficerNotFoundException(id.toString()));
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

	@Transactional
	public Officer activate(Long id) {
		Officer officer = officerRespository.findById(id).get();
		officer.activate();
		return officer;
	}
	
	@Transactional
	public Officer inactivate(Long id) {
		Officer officer = officerRespository.findById(id).get();
		officer.inactivate();
		return officer;
	}

}
