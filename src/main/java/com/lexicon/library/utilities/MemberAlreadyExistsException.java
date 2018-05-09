package com.lexicon.library.utilities;

public class MemberAlreadyExistsException extends Exception {
	
	public MemberAlreadyExistsException(String message) {
		super(message);
	}
	
	public MemberAlreadyExistsException() {
		super();
	}
}
