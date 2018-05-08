package com.lexicon.library.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexicon.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book> findByIsbn(String isbn);
	public List<Book> findByTitle(String title);
	public List<Book> findByAuthor(String author);
	public List<Book> findByGenre(String genre);

}
