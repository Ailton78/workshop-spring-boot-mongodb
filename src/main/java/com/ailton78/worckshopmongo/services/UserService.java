package com.ailton78.worckshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ailton78.worckshopmongo.domain.User;
import com.ailton78.worckshopmongo.dto.UserDTO;
import com.ailton78.worckshopmongo.repository.UserRepository;
import com.ailton78.worckshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User>findAll(){
		  return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User entity) {
		return  repository.insert(entity);
	}
	
	public void delete(String id) {
		if(!repository.existsById(id)) {
			throw new ObjectNotFoundException("Id não encontrado" );
		}
		repository.deleteById(id);		
	}
	
	public User updade(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return newObj;	
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	
	public User fromDTO(UserDTO dto) {
		return new User(dto.getId(), dto.getName(), dto.getEmail());
	}
}
