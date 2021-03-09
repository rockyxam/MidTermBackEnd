package lito.demo.repository;


import lito.demo.models.Author;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author, Integer>{

    Author findOne(Integer authorId);

    void delete(Integer authorId);
}
