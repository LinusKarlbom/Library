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

@Service
public class LoanManagementServiceImpl implements LoanManagementService {

	@Autowired
	LoanRepository rep;
	
	@Override
	public Loan addLoan(Loan loan) throws LoanAlreadyExistsException {
		if(rep.existsById(loan.getId())) {
			throw new LoanAlreadyExistsException();
		}
		return rep.save(loan);
	}

	@Override
	public void updateLoan(Loan loan, long id) {
		loan.setId(id);
		rep.save(loan);
	}

	@Override
	public void deleteLoan(Loan loan) throws LoanNotFoundException {
		if (rep.existsById(loan.getId())) {
			rep.delete(loan);
		} else {
			throw new LoanNotFoundException();
		}
	}

	@Override
	public List<Loan> getAllLoans() {
		return rep.findAll();
	}

	@Override
	public Loan findLoanById(long id) throws LoanNotFoundException {
		if (rep.existsById(id)) {
			return rep.findById(id).get();
		}
		else {
			throw new LoanNotFoundException();
		}
	}

	@Override
	public List<Loan> findLoansByBook(Book book) {
		return rep.findByBook(book);
	}

	@Override
	public List<Loan> findLoansByMember(Member member) {
		return rep.findByMember(member);
	}

}
