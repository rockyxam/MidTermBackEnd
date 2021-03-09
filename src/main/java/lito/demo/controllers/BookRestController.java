package lito.demo.controllers;


import lito.demo.models.Book;
import lito.demo.service.BookService;
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
public class BookRestController {

    public static final Logger logger = LoggerFactory.getLogger(BookRestController.class);
	
	@Autowired
	private BookService bookService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/list_books" , method = RequestMethod.GET)
	public@ResponseBody ResponseEntity<Object> getListBooks() {
		
		List<Book> listBooks=bookService.getAllBooks();
		
		List<JSONObject> books = new ArrayList<JSONObject>();
		
	    for (Book b: listBooks) {
	        JSONObject book = new JSONObject();
	        book.put("id", b.getId());
	        book.put("book_name", b.getBook_name());
	        book.put("isbn", b.getISBN());
	        book.put("publish_year", b.getPublish_year());
	        book.put("publisher", b.getPublisher());
	        book.put("status", b.getStatus());
	        
	        books.add(book);
	    }
		return new ResponseEntity<Object>(books,HttpStatus.OK);
		

	}
	
	@RequestMapping(value="/add_books" , method = RequestMethod.POST)
	public ResponseEntity<Book> addBooks(@RequestBody Book book){
		
		bookService.addBook(book);
		HttpHeaders header=new HttpHeaders();
		
		return new ResponseEntity<Book>(header,HttpStatus.CREATED);

	}

}
