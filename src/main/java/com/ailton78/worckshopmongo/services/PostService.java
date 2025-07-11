package com.ailton78.worckshopmongo.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ailton78.worckshopmongo.domain.Post;
import com.ailton78.worckshopmongo.repository.PostRepository;
import com.ailton78.worckshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle(String text){
		return repository.searchTitle(text);
	}
	
	
	public List<Post>fullSearch (String text, LocalDate minDate, LocalDate maxDate){
		maxDate = maxDate.plusDays(1);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
