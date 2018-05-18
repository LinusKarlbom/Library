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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.restrepresentations.MemberCollectionRepresentation;
import com.lexicon.library.services.member.MemberManagementService;
import com.lexicon.library.utilities.BookNotFoundException;
import com.lexicon.library.utilities.MemberAlreadyExistsException;
import com.lexicon.library.utilities.MemberNotFoundException;

/**
 * A REST controller providing functionality for managing Members as well as allowing members to loan and return books.
 * 
 * @author Linus Karlbom
 */
@RestController
@RequestMapping("/rest")
public class MemberRestController {

	@Autowired
	MemberManagementService memberService;
	
	//TODO: validation, error handling and XML support
	
	/**
	 * Finds a specific member.
	 * @param id The ID of the sought member.
	 * @return The sought member.
	 * @throws MemberNotFoundException
	 */
	@GetMapping("/member/{id}")
	public Member findMemberById(@PathVariable String id) throws MemberNotFoundException{
		return memberService.findMemberById(Long.parseLong(id));
	}
	
	/**
	 * Deletes a specific member.
	 * @param id The ID of the member to delete.
	 * @throws MemberNotFoundException
	 */
	@DeleteMapping("member/{id}")
	public void deleteMemberById(@PathVariable String id) throws MemberNotFoundException{
		Member member = memberService.findMemberById(Long.parseLong(id));
		memberService.deleteMember(member);
	}
	
	/**
	 * Replaces a specific member
	 * @param member The member which will replace the old member.
	 * @param id The ID of the member to be replaced.
	 */
	@PutMapping("/member/{id}")
	public void updateMember(@RequestBody Member member, @PathVariable String id){
		memberService.updateMember(member, Long.parseLong(id));
	}
	
	/**
	 * Creates a loan for a specific member of a specific book.
	 * @param memberId The ID of the member to make the loan.
	 * @param bookId The ID of the book which will be loaned.
	 * @param daysUntilDue The number of days from the time of lending until the book is due to be returned.
	 * @return The created loan.
	 * @throws MemberNotFoundException
	 * @throws BookNotFoundException if no book with bookId exists or if the book is already loaned.
	 */
	@PostMapping("/member/{memberId}/loan")
	public ResponseEntity<Loan> loanBook(@PathVariable String memberId, @RequestParam("book") Long bookId, @RequestParam("days") Long daysUntilDue) throws MemberNotFoundException, BookNotFoundException{
		Loan createdLoan = memberService.loanBook(Long.parseLong(memberId), bookId, daysUntilDue);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(MemberRestController.class).);
		return new ResponseEntity<Loan>(createdLoan, headers, HttpStatus.CREATED);
	}
	
	/**
	 * Has a specific member return a specific loaned book.
	 * @param memberId The ID of the member to return the book.
	 * @param bookId The ID of the book to be returned
	 * @throws MemberNotFoundException
	 * @throws BookNotFoundException if no book with bookId exists or if it is not currently loaned by the specified member.
	 */
	@PostMapping("/member/{memberId}/returnbook/{bookId}")
	public void returnBook(@PathVariable String memberId, @PathVariable String bookId) throws MemberNotFoundException, BookNotFoundException{
		memberService.returnBook(Long.parseLong(memberId), Long.parseLong(bookId));
	}
	
	/**
	 * 
	 * @return A list of all the members for the library.
	 */
	@GetMapping("/members")
	public MemberCollectionRepresentation returnAllMembers() {
		return new MemberCollectionRepresentation(memberService.getAllMembers());
	}
	
	/**
	 * Finds all members with a specific name.
	 * @param name The name of the sought members
	 * @return A list of all matching members.
	 */
	@GetMapping("/members/name/{name}")
	public MemberCollectionRepresentation findMembersByName(@PathVariable String name) {
		return new MemberCollectionRepresentation(memberService.findMembersByName(name));
	}
	
	/**
	 * Adds a new member for the library.
	 * @param member The member to add.
	 * @return The added member.
	 * @throws MemberAlreadyExistsException If a member with that ID already exists.
	 */
	@PostMapping("/members")
	public ResponseEntity<Member> addNewMember(@RequestBody Member member) throws MemberAlreadyExistsException{
		Member createdMember = memberService.addMember(member);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(MemberRestController.class).);
		return new ResponseEntity<Member>(createdMember, headers, HttpStatus.CREATED);
	}
}
