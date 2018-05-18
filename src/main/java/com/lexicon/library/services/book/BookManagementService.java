package com.lexicon.library.services.book;

import java.util.List;

import com.lexicon.library.domain.Book;
import com.lexicon.library.utilities.BookAlreadyExistsException;
import com.lexicon.library.utilities.BookNotFoundException;

/**
 * An interface for services which allows the manipulation of the library's book collection.
 * @author Linus Karlbom
 *
 */
public interface BookManagementService {
	
	/**
	 * Adds a new book to the library.
	 * @param book The book to add.
	 * @return The added book.
	 * @throws BookAlreadyExistsException If a book with that ID already exists.
	 */
	public Book addBook(Book book) throws BookAlreadyExistsException;
	
	/**
	 * Replaces a specific book
	 * @param book The book which will replace the old book.
	 * @param id The ID of the book to be replaced.
	 */
	public void updateBook(Book book, long id);
	
	/**
	 * Deletes a specific book.
	 * @param book The Book to delete.
	 * @throws BookNotFoundException
	 */
	public void deleteBook(Book book) throws BookNotFoundException;
	
	/**
	 * 
	 * @return A list of all books.
	 */
	public List<Book> getAllBooks();
	
	/**
	 * Finds a specific book.
	 * @param id The ID of the sought book.
	 * @return The sought book.
	 * @throws BookNotFoundException
	 */
	public Book findBookById(long id) throws BookNotFoundException;
	
	/**
	 * Finds all books with a specific ISBN.
	 * @param isbn The ISBN of the sought book.
	 * @return A list of all matching books.
	 */
	public List<Book> findBooksByIsbn(String isbn);
	
	/**
	 * Finds all books with a specific title.
	 * @param title The title of the sought book.
	 * @return A list of all matching books.
	 */
	public List<Book> findBooksByTitle(String title);
	
	/**
	 * Finds all books with a specific author.
	 * @param author The author of the sought book.
	 * @return A list of all matching books.
	 */
	public List<Book> findBooksByAuthor(String author);
	
	/**
	 * Finds all books with a specific genre.
	 * @param genre The genre of the sought book.
	 * @return A list of all matching books.
	 */
	public List<Book> findBooksByGenre(String genre);

}
