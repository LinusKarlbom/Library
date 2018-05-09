package com.lexicon.library.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lexicon.library.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	public List<Member> findByName(String name);

}
