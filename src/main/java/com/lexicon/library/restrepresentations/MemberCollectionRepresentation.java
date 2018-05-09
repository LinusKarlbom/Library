package com.lexicon.library.restrepresentations;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lexicon.library.domain.Member;

@XmlRootElement(name="members")
public class MemberCollectionRepresentation {
	
	List<Member> members;
	
	public MemberCollectionRepresentation(List<Member> members) {
		this.members = members;
	}
	
	public MemberCollectionRepresentation() {}

	@XmlElement(name="member")
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
