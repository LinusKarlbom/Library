package com.lexicon.library.services.loan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexicon.library.data.LoanRepository;
import com.lexicon.library.domain.Book;
import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.LoanAlreadyExistsException;
import com.lexicon.library.utilities.LoanNotFoundException;

/**
 * A service which allows the manipulation of the library's loans.
 * @author Linus Karlbom
 *
 */
@Service
public class LoanManagementServiceImpl implements LoanManagementService {

	@Autowired
	LoanRepository rep;
	
	/**
	 * Creates a new loan.
	 * @param loan The loan to create.
	 * @return The created loan.
	 * @throws LoanAlreadyExistsException If a loan with that ID already exists.
	 */
	@Override
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException {
		if(rep.existsById(loan.getId())) {
			throw new LoanAlreadyExistsException();
		}
		return rep.save(loan);
	}

	/**
	 * Replaces a specific loan.
	 * @param loan The loan which will replace the old loan.
	 * @param id The ID of the loan to be replaced.
	 */
	@Override
	public void updateLoan(Loan loan, long id) {
		loan.setId(id);
		rep.save(loan);
	}

	/**
	 * Deletes a specific loan.
	 * @param loan The loan to delete.
	 * @throws LoanNotFoundException
	 */
	@Override
	public void deleteLoan(Loan loan) throws LoanNotFoundException {
		if (rep.existsById(loan.getId())) {
			rep.delete(loan);
		} else {
			throw new LoanNotFoundException();
		}
	}

	/**
	 * 
	 * @return A list of all loans.
	 */
	@Override
	public List<Loan> getAllLoans() {
		return rep.findAll();
	}

	/**
	 * Finds a specific loan.
	 * @param id The ID of the sought loan.
	 * @return The sought loan.
	 * @throws LoanNotFoundException
	 */
	@Override
	public Loan findLoanById(long id) throws LoanNotFoundException {
		if (rep.existsById(id)) {
			return rep.findById(id).get();
		}
		else {
			throw new LoanNotFoundException();
		}
	}

	/**
	 * Finds all loans for a specific book.
	 * @param book The book for which to find loans.
	 * @return A list of all matching loans.
	 */
	@Override
	public List<Loan> findLoansByBook(Book book) {
		return rep.findByBook(book);
	}

	/**
	 * Finds all loans for a specific member.
	 * @param member The member for which to find loans.
	 * @return A list of all matching loans.
	 */
	@Override
	public List<Loan> findLoansByMember(Member member) {
		return rep.findByMember(member);
	}

}
