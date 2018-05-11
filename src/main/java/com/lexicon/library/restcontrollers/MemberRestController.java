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

@RestController
@RequestMapping("/rest")
public class MemberRestController {

	@Autowired
	MemberManagementService memberService;
	
	//TODO: validation, error handling and XML support
	
	@GetMapping("/member/{id}")
	public Member findMemberById(@PathVariable String id) throws MemberNotFoundException{
		return memberService.findMemberById(Long.parseLong(id));
	}
	
	@DeleteMapping("member/{id}")
	public void deleteMemberById(@PathVariable String id) throws MemberNotFoundException{
		Member member = memberService.findMemberById(Long.parseLong(id));
		memberService.deleteMember(member);
	}
	
	@PutMapping("/member/{id}")
	public void updateMember(@RequestBody Member member, @PathVariable String id){
		memberService.updateMember(member, Long.parseLong(id));
	}
	
	@PostMapping("/member/{memberId}/loan")
	public ResponseEntity<Loan> loanBook(@PathVariable String memberId, @RequestParam("book") Long bookId, @RequestParam("days") Long daysUntilDue) throws MemberNotFoundException, BookNotFoundException{
		Loan createdLoan = memberService.loanBook(Long.parseLong(memberId), bookId, daysUntilDue);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(MemberRestController.class).);
		return new ResponseEntity<Loan>(createdLoan, headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/members")
	public MemberCollectionRepresentation returnAllMembers() {
		return new MemberCollectionRepresentation(memberService.getAllMembers());
	}
	
	@GetMapping("/members/name/{name}")
	public MemberCollectionRepresentation findMembersByName(@PathVariable String name) {
		return new MemberCollectionRepresentation(memberService.findMembersByName(name));
	}
	
	@PostMapping("/members")
	public ResponseEntity<Member> addNewMember(@RequestBody Member member) throws MemberAlreadyExistsException{
		Member createdMember = memberService.addMember(member);
		HttpHeaders headers = new HttpHeaders();
		//TODO: fix HATEOAS things
		//URI uri = linkTo(methodOn(MemberRestController.class).);
		return new ResponseEntity<Member>(createdMember, headers, HttpStatus.CREATED);
	}
}
