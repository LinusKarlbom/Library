package com.lexicon.library.utilities;

public class LoanNotFoundException extends Exception {
	
	public LoanNotFoundException(String message) {
		super(message);
	}
	
	public LoanNotFoundException() {
		super();
	}
}
