package lito.demo.service;



import lito.demo.models.Book;

import java.util.List;


public interface BookService {
	
	public void addBook(Book book);
	
	public Book getBook(Integer bookId);
	
	public Book updateBook(Integer BookId, Book book);

	public void deleteBook(Integer bookId);

	public List<Book> getAllBooks();
}
