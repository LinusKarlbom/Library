package com.lexicon.library.services.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexicon.library.data.BookRepository;
import com.lexicon.library.domain.Book;
import com.lexicon.library.utilities.BookAlreadyExistsException;
import com.lexicon.library.utilities.BookNotFoundException;

/**
 * A service which allows the manipulation of the library's book collection.
 * @author Linus Karlbom
 *
 */
@Service
public class BookManagementServiceImpl implements BookManagementService {

	@Autowired
	BookRepository rep;

	/**
	 * Adds a new book to the library.
	 * @param book The book to add.
	 * @return The added book.
	 * @throws BookAlreadyExistsException If a book with that ID already exists.
	 */
	@Override
	public Book addBook(Book book) throws BookAlreadyExistsException {
		if(rep.existsById(book.getId())) {
			throw new BookAlreadyExistsException();
		}
		return rep.save(book);
	}
	
	/**
	 * Replaces a specific book
	 * @param book The book which will replace the old book.
	 * @param id The ID of the book to be replaced.
	 */
	@Override
	public void updateBook(Book book, long id) {
		book.setId(id);
		rep.save(book);
	}

	/**
	 * Deletes a specific book.
	 * @param book The Book to delete.
	 * @throws BookNotFoundException
	 */
	@Override
	public void deleteBook(Book book) throws BookNotFoundException {
		if (rep.existsById(book.getId())) {
			rep.delete(book);
		} else {
			throw new BookNotFoundException();
		}
	}

	/**
	 * 
	 * @return A list of all books.
	 */
	@Override
	public List<Book> getAllBooks() {
		return rep.findAll();
	}

	/**
	 * Finds a specific book.
	 * @param id The ID of the sought book.
	 * @return The sought book.
	 * @throws BookNotFoundException
	 */
	@Override
	public Book findBookById(long id) throws BookNotFoundException {
		if (rep.existsById(id)) {
			return rep.findById(id).get();
		}
		else {
			throw new BookNotFoundException();
		}
	}
	
	/**
	 * Finds all books with a specific ISBN.
	 * @param isbn The ISBN of the sought book.
	 * @return A list of all matching books.
	 */
	@Override
	public List<Book> findBooksByIsbn(String isbn) {
		return rep.findByIsbn(isbn);
	}

	/**
	 * Finds all books with a specific title.
	 * @param title The title of the sought book.
	 * @return A list of all matching books.
	 */
	@Override
	public List<Book> findBooksByTitle(String title) {
		return rep.findByTitle(title);
	}

	/**
	 * Finds all books with a specific author.
	 * @param author The author of the sought book.
	 * @return A list of all matching books.
	 */
	@Override
	public List<Book> findBooksByAuthor(String author) {
		return rep.findByAuthor(author);
	}

	/**
	 * Finds all books with a specific genre.
	 * @param genre The genre of the sought book.
	 * @return A list of all matching books.
	 */
	@Override
	public List<Book> findBooksByGenre(String genre) {
		return rep.findByGenre(genre);
	}
}
