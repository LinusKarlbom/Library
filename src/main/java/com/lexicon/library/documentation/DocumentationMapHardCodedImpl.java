package com.lexicon.library.documentation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DocumentationMapHardCodedImpl implements DocumentationMap{

	private final Map<String, String> map;
	public DocumentationMapHardCodedImpl() {
		map = new HashMap<String, String>();
		map.put("book/{id}",
				"GET: gets the book with id equal to {id}. For example, book/3 finds and returns the book with id 3 if such a book exists.");
		map.put("book/{id}", "DELETE: deletes the book with id equal to {id}.");
		map.put("book/{id}", "PUT: updates the book with id equal to {id} with the book provided in the request body.");
		map.put("books", "GET: gives list of all books.");
		map.put("books/isbn/{isbn}", "GET: gives list of all books with ISBN equal to {isbn}.");
		map.put("books/title/{title}", "GET: gives list of all books with title equal to {title}.");
		map.put("books/author/{author}", "GET: gives list of all books with author equal to {author}.");
		map.put("books/genre/{genre}", "GET: gives list of all books with genre equal to {genre}.");
		map.put("books",
				"POST: creates a book equal to the book provided in the request body. The id of the book will be automatically assigned and and do not need to be provided.");

		map.put("loan/{id}", "GET: gets the loan with id equal to {id}.");
		map.put("loan/{id}", "DELETE: deletes the loan with id equal to {id}.");
		map.put("loan/{id}", "PUT: updates the loan with id equal to {id} with the loan provided in the request body.");
		map.put("loans", "GET: gives list of all loans.");
		map.put("books",
				"POST: creates a loan equal to the loan provided in the request body. The id of the loan will be automatically assigned and and do not need to be provided.");

		map.put("member/{id}", "GET: gets the member with id equal to {id}.");
		map.put("member/{id}", "DELETE: deletes the member with id equal to {id}.");
		map.put("member/{id}",
				"PUT: updates the member with id equal to {id} with the member provided in the request body.");
		map.put("members", "GET: gives list of all members.");
		map.put("members/name/{name}", "GET: gives list of all members with name equal to {name}.");
		map.put("members",
				"POST: creates a member equal to the loan provided in the request body. The id of the member will be automatically assigned and and do not need to be provided.");
		map.put("member/{memberId}/loan",
				"POST: loans a book for the member with id equal to {memberId}. The book loaned is determined by the book id provided as a \"book\" parameter and the number of days until "
				+ "the book is due to be returned is provided as a \"days\" parameter. For example, member/{3}/loan?book=12&days=7 would attempt to create a loan for the member with "
				+ "id 3 of the book with id 12 for 7 days");
	}

	public Map<String, String> getMap() {
		return map;
	}
}
