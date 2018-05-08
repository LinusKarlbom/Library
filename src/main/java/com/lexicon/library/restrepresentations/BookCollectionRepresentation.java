package com.lexicon.library.restrepresentations;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lexicon.library.domain.Book;

@XmlRootElement(name="books")
public class BookCollectionRepresentation {

	List<Book> books;
	
	public BookCollectionRepresentation(List<Book> books) {
		this.books = books;
	}
	
	public BookCollectionRepresentation() {}

	@XmlElement(name="book")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
