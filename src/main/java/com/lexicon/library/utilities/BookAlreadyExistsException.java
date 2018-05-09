package com.lexicon.library.utilities;

public class BookAlreadyExistsException extends Exception {
	
	public BookAlreadyExistsException(String message) 
	{
		super(message);
	}
	
	public BookAlreadyExistsException() 
	{
		super();
	}

}
