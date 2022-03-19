package br.com.mksoftware.control.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mksoftware.control.entities.TagPermission;
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
		return tagPermissionRespository.findById(id).get();
	}

	public void deleteTagPermissionById(Long id) {
		tagPermissionRespository.deleteById(id);
	}

	public TagPermission updateTagPermission(TagPermission tagPermission) {
		return tagPermissionRespository.save(tagPermission);
	}
	
	public TagPermission activate(Long id, Boolean ativo) {
		TagPermission tagPermissionOld = tagPermissionRespository.findById(id).get();
		tagPermissionOld.setIsActive(ativo);
		TagPermission tagPermissionUpdated = tagPermissionRespository.save(tagPermissionOld);
		return tagPermissionUpdated;
	}
		


}
