package com.lexicon.library.services.loan;

import java.util.List;

import com.lexicon.library.domain.Book;
import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.LoanAlreadyExistsException;
import com.lexicon.library.utilities.LoanNotFoundException;

/**
 * An interface for services which allows the manipulation of the library's loans.
 * @author Linus Karlbom
 *
 */
public interface LoanManagementService {
	
	/**
	 * Creates a new loan.
	 * @param loan The loan to create.
	 * @return The created loan.
	 * @throws LoanAlreadyExistsException If a loan with that ID already exists.
	 */
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException;
	
	/**
	 * Replaces a specific loan.
	 * @param loan The loan which will replace the old loan.
	 * @param id The ID of the loan to be replaced.
	 */
	public void updateLoan(Loan loan, long id);
	
	/**
	 * Deletes a specific loan.
	 * @param loan The loan to delete.
	 * @throws LoanNotFoundException
	 */
	public void deleteLoan(Loan loan) throws LoanNotFoundException;
	
	/**
	 * 
	 * @return A list of all loans.
	 */
	public List<Loan> getAllLoans();
	
	/**
	 * Finds a specific loan.
	 * @param id The ID of the sought loan.
	 * @return The sought loan.
	 * @throws LoanNotFoundException
	 */
	public Loan findLoanById(long id) throws LoanNotFoundException;
	
	/**
	 * Finds all loans for a specific book.
	 * @param book The book for which to find loans.
	 * @return A list of all matching loans.
	 */
	public List<Loan> findLoansByBook(Book book);
	
	/**
	 * Finds all loans for a specific member.
	 * @param member The member for which to find loans.
	 * @return A list of all matching loans.
	 */
	public List<Loan> findLoansByMember(Member member);

}
