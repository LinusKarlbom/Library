package com.lexicon.library.services.member;

import java.util.List;

import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.MemberAlreadyExistsException;
import com.lexicon.library.utilities.MemberNotFoundException;

public interface MemberManagementService {
	
	public Member addMember(Member member) throws MemberAlreadyExistsException;
	
	public void updateMember(Member member, long id);
	
	public void deleteMember(Member member) throws MemberNotFoundException;
	
	public List<Member> getAllMembers();
	
	public Member findMemberById(long id) throws MemberNotFoundException;
	
	public List<Member> findMembersByName(String name);

}
