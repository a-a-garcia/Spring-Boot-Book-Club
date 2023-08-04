package com.anthonygarcia.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anthonygarcia.bookclub.models.Book;
import com.anthonygarcia.bookclub.repositories.BookRepository;

@Service
public class BookService {
	    // adding the object repository as a dependency
		@Autowired
	    private BookRepository bookRepository;
	    
	    // returns all the object
	    public List<Book> allBook() {
	        return bookRepository.findAll();
	    }
	    // creates a object
	    public Book createBook(Book object) {
	        return bookRepository.save(object);
	    }
	    // update a object - it's the same as create, however if a primary key already exists, it will update instead of create.
	    public Book updateBook(Book object) {
	        return bookRepository.save(object);
	    }
	    // retrieves a object
	    public Book findBook(Long id) {
	        Optional<Book> optionalBook = bookRepository.findById(id);
	        if(optionalBook.isPresent()) {
	            return optionalBook.get();
	        } else {
	            return null;
	        }
	    }
	    // delete a object
	    public void deleteBook(Long id) {
	    	bookRepository.deleteById(id);
	    }
}