package com.lexicon.library.restrepresentations;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lexicon.library.domain.Loan;

/**
 * A wrapper object for a list of Loans.
 * @author Linus Karlbom
 *
 */
@XmlRootElement(name="loans")
public class LoanCollectionRepresentation {

	List<Loan> loans;
	
	public LoanCollectionRepresentation(List<Loan> loans) {
		this.loans = loans;
	}
	
	public LoanCollectionRepresentation() {}

	@XmlElement(name="loan")
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
}
