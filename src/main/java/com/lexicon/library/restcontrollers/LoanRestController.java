package com.lexicon.library.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.library.domain.Loan;
import com.lexicon.library.restrepresentations.LoanCollectionRepresentation;
import com.lexicon.library.services.loan.LoanManagementService;
import com.lexicon.library.utilities.BookNotFoundException;
import com.lexicon.library.utilities.LoanAlreadyExistsException;
import com.lexicon.library.utilities.LoanNotFoundException;

/**
 * A REST controller providing functionality for managing Loans.
 * 
 * @author Linus Karlbom
 */
@RestController
@RequestMapping("/rest")
public class LoanRestController {
	
	@Autowired
	LoanManagementService loanService;
	
	//TODO: validation, error handling and XML support
	
	/**
	 * Finds a specific loan.
	 * @param id The ID of the sought loan.
	 * @return The sought loan.
	 * @throws LoanNotFoundException
	 */
	@GetMapping("/loan/{id}")
	public Loan findLoanById(@PathVariable String id) throws LoanNotFoundException{
		return loanService.findLoanById(Long.parseLong(id));
	}
	
	/**
	 * Deletes a specific loan.
	 * @param id The ID of the loan to delete.
	 * @throws LoanNotFoundException
	 */
	@DeleteMapping("loan/{id}")
	public void deleteLoanById(@PathVariable String id) throws LoanNotFoundException{
		Loan loan = loanService.findLoanById(Long.parseLong(id));
		loanService.deleteLoan(loan);
	}
	
	/**
	 * Replaces a specific loan.
	 * @param loan The loan which will replace the old loan.
	 * @param id The ID of the loan to be replaced.
	 */
	@PutMapping("/loan/{id}")
	public void updateLoan(@RequestBody Loan loan, @PathVariable String id){
		loanService.updateLoan(loan, Long.parseLong(id));
	}
	
	/**
	 * 
	 * @return A list of all loans for the library.
	 */
	@GetMapping("/loans")
	public LoanCollectionRepresentation returnAllLoans() {
		return new LoanCollectionRepresentation(loanService.getAllLoans());
	}
	
	/**
	 * Creates a new loan.
	 * @param loan The loan to create.
	 * @return The created loan.
	 * @throws LoanAlreadyExistsException If a loan with that ID already exists.
	 */
	@PostMapping("/loans")
	public ResponseEntity<Loan> addNewLoan(@RequestBody Loan loan) throws LoanAlreadyExistsException{
		Loan createdLoan = loanService.addLoan(loan);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(LoanRestController.class).);
		return new ResponseEntity<Loan>(createdLoan, headers, HttpStatus.CREATED);
	}

}
