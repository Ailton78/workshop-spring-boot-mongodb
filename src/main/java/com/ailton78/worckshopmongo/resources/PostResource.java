package com.ailton78.worckshopmongo.resources;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ailton78.worckshopmongo.domain.Post;
import com.ailton78.worckshopmongo.resources.util.URL;
import com.ailton78.worckshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/fullSearch")
	public ResponseEntity<List<Post>> fullSearch(
	        @RequestParam(value = "text", defaultValue = "") String text,
	        @RequestParam(value = "minDate", defaultValue = "") String minDateStr,
	        @RequestParam(value = "maxDate", defaultValue = "") String maxDateStr) {

	    text = URL.decodeParam(text);

	    LocalDate minDate = URL.convertDate(minDateStr, LocalDate.of(1970, 1, 1));
	    LocalDate maxDate = URL.convertDate(maxDateStr, LocalDate.now());

	    List<Post> list = service.fullSearch(text, minDate, maxDate);
	    return ResponseEntity.ok().body(list);
	}

}
