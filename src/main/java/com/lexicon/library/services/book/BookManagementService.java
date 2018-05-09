package com.lexicon.library.services.book;

import java.util.List;

import com.lexicon.library.domain.Book;
import com.lexicon.library.utilities.BookNotFoundException;

public interface BookManagementService {
	
	public Book addBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book) throws BookNotFoundException;
	
	public List<Book> getAllBooks();
	
	public Book findBookById(long id) throws BookNotFoundException;
	
	public List<Book> findBooksByIsbn(String isbn);
	
	public List<Book> findBooksByTitle(String title);
	
	public List<Book> findBooksByAuthor(String author);
	
	public List<Book> findBooksByGenre(String genre);

}
