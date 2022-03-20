package br.com.mksoftware.control.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.mksoftware.control.entities.Function;
import br.com.mksoftware.control.entities.System;
import br.com.mksoftware.control.entities.User;
import br.com.mksoftware.control.repository.FunctionRepository;
import br.com.mksoftware.control.repository.SystemRepository;
import br.com.mksoftware.control.repository.UserRespository;

@Service
public class UserService {

	
	@Autowired
	private UserRespository userRepository;
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private SystemRepository systemRepository;
	

	public List<User> getAll() {
		var users = userRepository.findAll();
		return users;
	}

	@Transactional
	public User getUserById(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Transactional
	public User activate(Long id, Boolean ativo) {
		User userOld = userRepository.findById(id).get();
		userOld.setIsActive(ativo);
		User userUpdated = userRepository.save(userOld);
		return userUpdated;
	}

	@Transactional
	public User addSystem(Long id, System system) {
		User user = userRepository.findById(id).get();
		System sys = systemRepository.getById(system.getId());
		user.addSystem(sys);
		return user;
	}
	
	@Transactional
	public User removeSystem(Long id, System system) {
		User user = userRepository.findById(id).get();
		System sys = systemRepository.getById(system.getId());
		user.removeSystem(sys);
		return user;
	}

	@Transactional
	public User addFunction(Long id, Function function) {
		User user = userRepository.findById(id).get();
		Function fun = functionRepository.getById(function.getId());
		user.addFunction(fun);
		return user;
	}

	@Transactional
	public User removeFunction(Long id, Function function) {
		User user = userRepository.findById(id).get();
		Function fun = functionRepository.getById(function.getId());
		user.removeFunction(fun);
		return user;
	}

}
