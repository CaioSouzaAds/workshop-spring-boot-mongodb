package com.caio.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.caio.workshopmongo.domain.User;
import com.caio.workshopmongo.dto.UserDTO;
import com.caio.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO objDto) {
		User user = service.fromDTO(objDto);
		User insertedUser = service.insertUser(user);

		// Crie a URI com base no ID do usuário inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(insertedUser.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable String id) {

		service.deleteUserById(id);

		// Retorne uma resposta com código HTTP 204 - No Content após a exclusão
		// bem-sucedida
		return ResponseEntity.noContent().build();
	}

	// Endpoint para atualizar um usuário pelo ID
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable String id, @RequestBody UserDTO updatedUserDTO) {
		// Chame o serviço para atualizar o usuário
		User obj = service.fromDTO(updatedUserDTO);
		obj.setId(id);

		User updatedUser = service.updateUser(obj);

		// Retorne o usuário atualizado em um DTO
		UserDTO updatedUserDto = new UserDTO(updatedUser);

		return ResponseEntity.ok(updatedUserDto);
	}

}
