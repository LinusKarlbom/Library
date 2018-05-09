package com.lexicon.library.services.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lexicon.library.data.MemberRepository;
import com.lexicon.library.domain.Member;
import com.lexicon.library.utilities.MemberAlreadyExistsException;
import com.lexicon.library.utilities.MemberNotFoundException;

@Service
public class MemberManagementServiceImpl implements MemberManagementService {
	
	@Autowired
	MemberRepository rep;

	@Override
	public Member addMember(Member member) throws MemberAlreadyExistsException {
		if(rep.existsById(member.getId())) {
			throw new MemberAlreadyExistsException();
		}
		return rep.save(member);
	}
	
	@Override
	public void updateMember(Member member, long id) {
		member.setId(id);
		rep.save(member);
	}

	@Override
	public void deleteMember(Member member) throws MemberNotFoundException {
		if (rep.existsById(member.getId())) {
			rep.delete(member);
		} else {
			throw new MemberNotFoundException();
		}
	}

	@Override
	public List<Member> getAllMembers() {
		return rep.findAll();
	}

	@Override
	public Member findMemberById(long id) throws MemberNotFoundException {
		if (rep.existsById(id)) {
			return rep.findById(id).get();
		}
		else {
			throw new MemberNotFoundException();
		}
	}
	
	@Override
	public List<Member> findMembersByName(String name) {
		return rep.findByName(name);
	}
}
