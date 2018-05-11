package com.lexicon.library.services.loan;

import java.util.List;

import com.lexicon.library.domain.Book;
import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.LoanAlreadyExistsException;
import com.lexicon.library.utilities.LoanNotFoundException;

public interface LoanManagementService {
	
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException;
	
	public void updateLoan(Loan loan, long id);
	
	public void deleteLoan(Loan loan) throws LoanNotFoundException;
	
	public List<Loan> getAllLoans();
	
	public Loan findLoanById(long id) throws LoanNotFoundException;
	
	public List<Loan> findLoansByBook(Book book);
	
	public List<Loan> findLoansByMember(Member member);

}
