package com.lexicon.library.utilities;

public class LoanAlreadyExistsException extends Exception {
	
	public LoanAlreadyExistsException(String message) {
		super(message);
	}
	
	public LoanAlreadyExistsException() {
		super();
	}

}
