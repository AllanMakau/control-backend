package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.TagPermission;
import br.com.mksoftware.control.exceptions.TagNotFoundException;
import br.com.mksoftware.control.repository.TagPermissionRespository;

@Service
public class TagPermissionService {

	@Autowired
	private TagPermissionRespository tagPermissionRespository;

	public List<TagPermission> getAll() {
		var tagsPermission = tagPermissionRespository.findAll();
		return tagsPermission;
	}

	public TagPermission saveTagPermission(TagPermission tagPermission) {
		return tagPermissionRespository.save(tagPermission);
	}

	public TagPermission getTagPermissionById(Long id) {

		return tagPermissionRespository.findById(id).orElseThrow(() -> new TagNotFoundException(id.toString()));
	}

	public void deleteTagPermissionById(Long id) {
		tagPermissionRespository.deleteById(id);
	}

	public TagPermission updateTagPermission(TagPermission tagPermission) {
		return tagPermissionRespository.save(tagPermission);
	}

	@Transactional
	public TagPermission activate(Long id) {
		TagPermission tagPermission = tagPermissionRespository.findById(id).get();
		tagPermission.activate();
		return tagPermission;
	}
	
	@Transactional
	public TagPermission inactivate(Long id) {
		TagPermission tagPermission = tagPermissionRespository.findById(id).get();
		tagPermission.inactivate();
		return tagPermission;
	}

}
