package com.lexicon.library.services.member;

import java.util.List;

import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.BookNotFoundException;
import com.lexicon.library.utilities.MemberAlreadyExistsException;
import com.lexicon.library.utilities.MemberNotFoundException;

/**
 * An interface for services which allows the manipulation of the library's members as well as providing functionality for members to loan and return books.
 * @author Linus Karlbom
 *
 */
public interface MemberManagementService {
	
	/**
	 * Adds a new member for the library.
	 * @param member The member to add.
	 * @return The added member.
	 * @throws MemberAlreadyExistsException If a member with that ID already exists.
	 */
	public Member addMember(Member member) throws MemberAlreadyExistsException;
	
	/**
	 * Replaces a specific member
	 * @param member The member which will replace the old member.
	 * @param id The ID of the member to be replaced.
	 */
	public void updateMember(Member member, long id);
	
	/**
	 * Deletes a specific member.
	 * @param member The member to delete.
	 * @throws MemberNotFoundException
	 */
	public void deleteMember(Member member) throws MemberNotFoundException;
	
	/**
	 * 
	 * @return A list of all the members.
	 */
	public List<Member> getAllMembers();
	
	/**
	 * Finds a specific member.
	 * @param id The ID of the sought member.
	 * @return The sought member.
	 * @throws MemberNotFoundException
	 */
	public Member findMemberById(long id) throws MemberNotFoundException;
	
	/**
	 * Finds all members with a specific name.
	 * @param name The name of the sought members
	 * @return A list of all matching members.
	 */
	public List<Member> findMembersByName(String name);
	
	/**
	 * Creates a loan for a specific member of a specific book.
	 * @param memberId The ID of the member to make the loan.
	 * @param bookId The ID of the book which will be loaned.
	 * @param daysUntilDue The number of days from the time of lending until the book is due to be returned.
	 * @return The created loan.
	 * @throws MemberNotFoundException
	 * @throws BookNotFoundException if no book with bookId exists or if the book is already loaned.
	 */
	public Loan loanBook(Long memberId, Long bookId,  Long daysUntilDue) throws MemberNotFoundException, BookNotFoundException;
	
	/**
	 * Has a specific member return a specific loaned book.
	 * @param memberId The ID of the member to return the book.
	 * @param bookId The ID of the book to be returned
	 * @throws MemberNotFoundException
	 * @throws BookNotFoundException if no book with bookId exists or if it is not currently loaned by the specified member.
	 */
	public void returnBook(Long memberId, Long bookId) throws MemberNotFoundException, BookNotFoundException;

}
