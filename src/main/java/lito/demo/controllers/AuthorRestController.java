package lito.demo.controllers;


import lito.demo.models.Author;
import lito.demo.service.AuthorService;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class AuthorRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	@Autowired
	private AuthorService authorService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list_authors" , method = RequestMethod.GET)
	public@ResponseBody ResponseEntity<Object> getListAuthors() {
		
		List<Author> listAuthors=authorService.getAllAuthors();
		
		List<JSONObject> authors = new ArrayList<JSONObject>();
		
	    for (Author a : listAuthors) {
	        JSONObject author = new JSONObject();
	        author.put("id", a.getId());
	        author.put("name", a.getName());
	        author.put("surname", a.getSurname());
	        author.put("book_name", a.getBook().getBook_name());
	        
	        authors.add(author);
	    }
		return new ResponseEntity<Object>(authors,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/add_authors" , method = RequestMethod.POST)
	public ResponseEntity<Author> addBooks(@RequestBody Author author){
		
		authorService.addAuthor(author);
		HttpHeaders header=new HttpHeaders();
		
		return new ResponseEntity<Author>(header,HttpStatus.CREATED);

	}
}
