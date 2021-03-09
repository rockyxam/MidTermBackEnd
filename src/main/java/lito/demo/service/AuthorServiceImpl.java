package lito.demo.service;


import lito.demo.models.Author;
import lito.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public void addAuthor(Author author) {
		authorRepository.save(author);
	}

	@Override
	public Author getAuthor(Integer authorId) {
		return authorRepository.findOne(authorId);
	}

	@Override
	public Author updateAuthor(Integer authorId, Author author) {
		return authorRepository.save(author);
	}
	
	@Override
	public void deleteAuthor(Integer authorId) {
		authorRepository.delete(authorId);
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors=new ArrayList<>();
		authorRepository.findAll().forEach(authors::add);
		return authors;
	}

}
