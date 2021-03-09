package lito.demo.service;


import lito.demo.models.Book;
import lito.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public void addBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public Book getBook(Integer bookId) {
		 return bookRepository.findOne(bookId);
	}
	
	@Override
	public Book updateBook(Integer BookId, Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public void deleteBook(Integer bookId) {
		bookRepository.delete(bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books=new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}

}
