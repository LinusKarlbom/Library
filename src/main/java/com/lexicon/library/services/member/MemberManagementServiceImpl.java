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

@Service
public class MemberManagementServiceImpl implements MemberManagementService {
	
	@Autowired
	MemberRepository memberRep;
	
	@Autowired
	BookRepository bookRep;
	
	@Autowired
	LoanRepository loanRep;

	@Override
	public Member addMember(Member member) throws MemberAlreadyExistsException {
		if(memberRep.existsById(member.getId())) {
			throw new MemberAlreadyExistsException();
		}
		return memberRep.save(member);
	}
	
	@Override
	public void updateMember(Member member, long id) {
		member.setId(id);
		memberRep.save(member);
	}

	@Override
	public void deleteMember(Member member) throws MemberNotFoundException {
		if (memberRep.existsById(member.getId())) {
			memberRep.delete(member);
		} else {
			throw new MemberNotFoundException();
		}
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRep.findAll();
	}

	@Override
	public Member findMemberById(long id) throws MemberNotFoundException {
		if (memberRep.existsById(id)) {
			return memberRep.findById(id).get();
		}
		else {
			throw new MemberNotFoundException();
		}
	}
	
	@Override
	public List<Member> findMembersByName(String name) {
		return memberRep.findByName(name);
	}

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
