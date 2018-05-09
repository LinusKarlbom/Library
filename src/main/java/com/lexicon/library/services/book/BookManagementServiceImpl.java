package com.lexicon.library.services.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexicon.library.data.BookRepository;
import com.lexicon.library.domain.Book;
import com.lexicon.library.utilities.BookNotFoundException;

@Service
public class BookManagementServiceImpl implements BookManagementService {

	@Autowired
	BookRepository rep;

	@Override
	public Book addBook(Book book) {
		return rep.save(book);
	}
	
	@Override
	public void updateBook(Book book) {
		rep.save(book);
	}

	@Override
	public void deleteBook(Book book) throws BookNotFoundException {
		if (rep.existsById(book.getId())) {
			rep.delete(book);
		} else {
			throw new BookNotFoundException();
		}
	}

	@Override
	public List<Book> getAllBooks() {
		return rep.findAll();
	}

	@Override
	public Book findBookById(long id) throws BookNotFoundException {
		System.out.println(id);
		if (rep.existsById(id)) {
			return rep.findById(id).get();
		}
		else {
			throw new BookNotFoundException();
		}
	}
	
	@Override
	public List<Book> findBooksByIsbn(String isbn) {
		return rep.findByIsbn(isbn);
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		return rep.findByTitle(title);
	}

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return rep.findByAuthor(author);
	}

	@Override
	public List<Book> findBooksByGenre(String genre) {
		return rep.findByGenre(genre);
	}
}
