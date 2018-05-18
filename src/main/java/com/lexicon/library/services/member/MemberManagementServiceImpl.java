package com.lexicon.library.services.member;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lexicon.library.data.BookRepository;
import com.lexicon.library.data.LoanRepository;
import com.lexicon.library.data.MemberRepository;
import com.lexicon.library.domain.Book;
import com.lexicon.library.domain.Loan;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.BookNotFoundException;
import com.lexicon.library.utilities.MemberAlreadyExistsException;
import com.lexicon.library.utilities.MemberNotFoundException;

/**
 * A service which allows the manipulation of the library's members as well as providing functionality for members to loan and return books.
 * @author Linus Karlbom
 *
 */
@Service
public class MemberManagementServiceImpl implements MemberManagementService {
	
	@Autowired
	MemberRepository memberRep;
	
	@Autowired
	BookRepository bookRep;
	
	@Autowired
	LoanRepository loanRep;

	/**
	 * Adds a new member for the library.
	 * @param member The member to add.
	 * @return The added member.
	 * @throws MemberAlreadyExistsException If a member with that ID already exists.
	 */
	@Override
	public Member addMember(Member member) throws MemberAlreadyExistsException {
		if(memberRep.existsById(member.getId())) {
			throw new MemberAlreadyExistsException();
		}
		return memberRep.save(member);
	}
	
	/**
	 * Replaces a specific member
	 * @param member The member which will replace the old member.
	 * @param id The ID of the member to be replaced.
	 */
	@Override
	public void updateMember(Member member, long id) {
		member.setId(id);
		memberRep.save(member);
	}

	/**
	 * Deletes a specific member.
	 * @param member The member to delete.
	 * @throws MemberNotFoundException
	 */
	@Override
	public void deleteMember(Member member) throws MemberNotFoundException {
		if (memberRep.existsById(member.getId())) {
			memberRep.delete(member);
		} else {
			throw new MemberNotFoundException();
		}
	}

	/**
	 * 
	 * @return A list of all the members.
	 */
	@Override
	public List<Member> getAllMembers() {
		return memberRep.findAll();
	}

	/**
	 * Finds a specific member.
	 * @param id The ID of the sought member.
	 * @return The sought member.
	 * @throws MemberNotFoundException
	 */
	@Override
	public Member findMemberById(long id) throws MemberNotFoundException {
		if (memberRep.existsById(id)) {
			return memberRep.findById(id).get();
		}
		else {
			throw new MemberNotFoundException();
		}
	}
	
	/**
	 * Finds all members with a specific name.
	 * @param name The name of the sought members
	 * @return A list of all matching members.
	 */
	@Override
	public List<Member> findMembersByName(String name) {
		return memberRep.findByName(name);
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
	@Override
	public Loan loanBook(Long memberId, Long bookId, Long daysUntilDue) throws MemberNotFoundException, BookNotFoundException {
		if(!memberRep.findById(memberId).isPresent()) {
			throw new MemberNotFoundException();
		}
		if(!bookRep.findById(bookId).isPresent()) {
			throw new BookNotFoundException();
		}
		
		Member member = memberRep.findById(memberId).get();
		Book book = bookRep.findById(bookId).get();
		if(book.isLoaned()) {
			throw new BookNotFoundException();
		}
		Loan loan = new Loan(member, book, LocalDateTime.now(), LocalDateTime.now().plusDays(daysUntilDue));
		book.setLoaned(true);
		loanRep.save(loan);
		return loan;

	}

	/**
	 * Has a specific member return a specific loaned book.
	 * @param memberId The ID of the member to return the book.
	 * @param bookId The ID of the book to be returned
	 * @throws MemberNotFoundException
	 * @throws BookNotFoundException if no book with bookId exists or if it is not currently loaned by the specified member.
	 */
	@Override
	@Transactional
	public void returnBook(Long memberId, Long bookId) throws MemberNotFoundException, BookNotFoundException {
		if(!memberRep.findById(memberId).isPresent()) {
			throw new MemberNotFoundException();
		}
		if(!bookRep.findById(bookId).isPresent()) {
			throw new BookNotFoundException();
		}
		Iterator<Loan> loanIterator = loanRep.findByMember(memberRep.findById(memberId).get()).iterator();
		while(loanIterator.hasNext()) {
			Loan nextLoan = loanIterator.next();
			if(nextLoan.getBook().getId() == bookId && nextLoan.getReturnDateAndTime() == null) {
				nextLoan.getBook().setLoaned(false);
				nextLoan.setReturnDateAndTime(LocalDateTime.now());
				return;
			}
		}
		throw new BookNotFoundException();

	}
}
