package com.lexicon.library.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lexicon.library.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
