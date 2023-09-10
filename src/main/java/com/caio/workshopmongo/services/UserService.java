package com.caio.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.workshopmongo.domain.User;
import com.caio.workshopmongo.dto.UserDTO;
import com.caio.workshopmongo.repository.UserRepository;
import com.caio.workshopmongo.services.excepition.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} else {
			return user.get();
		}

	}
	
	public User insertUser(User user) {
	    User insertedUser = repo.insert(user);

	    if (insertedUser == null) {
	        throw new ObjectNotFoundException("Objeto não inserido");
	    } else {
	        return insertedUser;
	    }
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
