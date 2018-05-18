package com.lexicon.library.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexicon.library.domain.Book;
import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;

/**
 * A repository interface for Loans.
 * 
 * @author Linus Karlbom
 */
public interface LoanRepository extends JpaRepository<Loan, Long>{

	public List<Loan> findByBook(Book book);
	
	public List<Loan> findByMember(Member member);

}
