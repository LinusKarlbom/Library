package com.lexicon.library.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.library.domain.Book;
import com.lexicon.library.restrepresentations.BookCollectionRepresentation;
import com.lexicon.library.services.book.BookManagementService;
import com.lexicon.library.utilities.BookAlreadyExistsException;
import com.lexicon.library.utilities.BookNotFoundException;

@RestController
@RequestMapping("/rest")
public class BookRestController {

	@Autowired
	BookManagementService bookService;
	
	//TODO: add updating, validation, error handling and XML support
	
	
	@GetMapping("/book/{id}")
	public Book findBookById(@PathVariable String id) throws BookNotFoundException{
		return bookService.findBookById(Long.parseLong(id));
	}
	
	@DeleteMapping("book/{id}")
	public void deleteBookById(@PathVariable String id) throws BookNotFoundException{
		Book book = bookService.findBookById(Long.parseLong(id));
		bookService.deleteBook(book);
	}
	
	@PutMapping("/book/{id}")
	public void updateBook(@RequestBody Book book, @PathVariable String id){
		bookService.updateBook(book, Long.parseLong(id));
		
	}
	
	@GetMapping("/books")
	public BookCollectionRepresentation returnAllBooks() {
		return new BookCollectionRepresentation(bookService.getAllBooks());
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) throws BookAlreadyExistsException{
		Book createdBook = bookService.addBook(book);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(BookRestController.class).);
		return new ResponseEntity<Book>(createdBook, headers, HttpStatus.CREATED);
	}
}
