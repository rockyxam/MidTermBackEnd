package lito.demo.service;



import lito.demo.models.Author;

import java.util.List;


public interface AuthorService {
	
	public void addAuthor(Author author);
	
	public Author getAuthor(Integer authorId);
	
	public Author updateAuthor(Integer authorId, Author author);
	
	public void deleteAuthor(Integer authorId);
			
	public List<Author> getAllAuthors();
}
