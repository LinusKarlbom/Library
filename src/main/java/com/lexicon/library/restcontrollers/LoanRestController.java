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
import com.lexicon.library.utilities.LoanAlreadyExistsException;
import com.lexicon.library.utilities.LoanNotFoundException;

@RestController
@RequestMapping("/rest")
public class LoanRestController {
	
	@Autowired
	LoanManagementService loanService;
	
	//TODO: validation, error handling and XML support
	
	@GetMapping("/loan/{id}")
	public Loan findLoanById(@PathVariable String id) throws LoanNotFoundException{
		return loanService.findLoanById(Long.parseLong(id));
	}
	
	@DeleteMapping("loan/{id}")
	public void deleteLoanById(@PathVariable String id) throws LoanNotFoundException{
		Loan loan = loanService.findLoanById(Long.parseLong(id));
		loanService.deleteLoan(loan);
	}
	
	@PutMapping("/loan/{id}")
	public void updateLoan(@RequestBody Loan loan, @PathVariable String id){
		loanService.updateLoan(loan, Long.parseLong(id));
	}
	
	@GetMapping("/loans")
	public LoanCollectionRepresentation returnAllLoans() {
		return new LoanCollectionRepresentation(loanService.getAllLoans());
	}
	
	@PostMapping("/loans")
	public ResponseEntity<Loan> addNewLoan(@RequestBody Loan loan) throws LoanAlreadyExistsException{
		Loan createdLoan = loanService.addLoan(loan);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(LoanRestController.class).);
		return new ResponseEntity<Loan>(createdLoan, headers, HttpStatus.CREATED);
	}

}
