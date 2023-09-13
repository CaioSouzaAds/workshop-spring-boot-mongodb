package com.caio.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caio.workshopmongo.domain.Post;
import com.caio.workshopmongo.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll(){
		 
		return repo.findAll();
	}

}
