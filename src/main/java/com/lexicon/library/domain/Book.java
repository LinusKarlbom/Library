package com.lexicon.library.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String isbn;
	private String title;
	private String author;
	private String genre;
	private int shelfNumber;
	@Min(0)
	private int numberOfPages;
	private boolean isLoaned;
	
	public Book(String isbn, String title, String author, String genre, int shelfNumber, int numberOfPages) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.shelfNumber = shelfNumber;
		this.numberOfPages = numberOfPages;
	}
	
	protected Book() {}

	public int getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(int shelfNumber) {
		this.shelfNumber = shelfNumber;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public boolean isLoaned() {
		return isLoaned;
	}

	public void setLoaned(boolean isLoaned) {
		this.isLoaned = isLoaned;
	}
}
