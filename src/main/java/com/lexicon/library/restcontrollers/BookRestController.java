package com.lexicon.library.restcontrollers;

import javax.validation.Valid;

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

/**
 * A REST controller providing functionality for managing Books.
 * 
 * @author Linus Karlbom
 */
@RestController
@RequestMapping("/rest")
public class BookRestController {

	@Autowired
	BookManagementService bookService;
	
	//TODO: validation, error handling and XML support
	
	
	/**
	 * Finds a specific book.
	 * @param id The ID of the sought book.
	 * @return The sought book.
	 * @throws BookNotFoundException
	 */
	@GetMapping("/book/{id}")
	public Book findBookById(@PathVariable String id) throws BookNotFoundException{
		return bookService.findBookById(Long.parseLong(id));
	}
	
	/**
	 * Deletes a specific book.
	 * @param id The ID of the book to delete.
	 * @throws BookNotFoundException
	 */
	@DeleteMapping("book/{id}")
	public void deleteBookById(@PathVariable String id) throws BookNotFoundException{
		Book book = bookService.findBookById(Long.parseLong(id));
		bookService.deleteBook(book);
	}
	
	/**
	 * Replaces a specific book
	 * @param book The book which will replace the old book.
	 * @param id The ID of the book to be replaced.
	 */
	@PutMapping("/book/{id}")
	public void updateBook(@RequestBody Book book, @PathVariable String id){
		bookService.updateBook(book, Long.parseLong(id));
	}
	
	/**
	 * 
	 * @return A list of all books in the library.
	 */
	@GetMapping("/books")
	public BookCollectionRepresentation returnAllBooks() {
		return new BookCollectionRepresentation(bookService.getAllBooks());
	}
	
	/**
	 * Finds all books with a specific ISBN.
	 * @param isbn The ISBN of the sought book.
	 * @return A list of all matching books.
	 */
	@GetMapping("/books/isbn/{isbn}")
	public BookCollectionRepresentation findBooksByIsbn(@PathVariable String isbn) {
		return new BookCollectionRepresentation(bookService.findBooksByIsbn(isbn));
	}
	
	/**
	 * Finds all books with a specific title.
	 * @param title The title of the sought book.
	 * @return A list of all matching books.
	 */
	@GetMapping("/books/title/{title}")
	public BookCollectionRepresentation findBooksByTitle(@PathVariable String title) {
		return new BookCollectionRepresentation(bookService.findBooksByTitle(title));
	}
	
	/**
	 * Finds all books with a specific author.
	 * @param author The author of the sought book.
	 * @return A list of all matching books.
	 */
	@GetMapping("/books/author/{author}")
	public BookCollectionRepresentation findBooksByAuthor(@PathVariable String author) {
		return new BookCollectionRepresentation(bookService.findBooksByAuthor(author));
	}
	
	/**
	 * Finds all books with a specific genre.
	 * @param genre The genre of the sought book.
	 * @return A list of all matching books.
	 */
	@GetMapping("/books/genre/{genre}")
	public BookCollectionRepresentation findBooksByGenre(@PathVariable String genre) {
		return new BookCollectionRepresentation(bookService.findBooksByGenre(genre));
	}
	
	/**
	 * Adds a new book to the library.
	 * @param book The book to add.
	 * @return The added book.
	 * @throws BookAlreadyExistsException If a book with that ID already exists.
	 */
	@PostMapping("/books")
	public ResponseEntity<Book> addNewBook(@Valid @RequestBody Book book) throws BookAlreadyExistsException{
		Book createdBook = bookService.addBook(book);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(BookRestController.class).);
		return new ResponseEntity<Book>(createdBook, headers, HttpStatus.CREATED);
	}
}
