package com.caio.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.workshopmongo.domain.Post;
import com.caio.workshopmongo.repository.PostRepository;
import com.caio.workshopmongo.services.excepition.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {

		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);

		if (post.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		} else {
			return post.get();
		}

	}

}
