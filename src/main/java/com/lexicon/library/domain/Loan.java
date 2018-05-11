package com.lexicon.library.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@ManyToOne
	private Member member;
	@OneToOne
	private Book book;
	private LocalDateTime startDateAndTime;
	private LocalDateTime dueDateAndTime;
	private LocalDateTime returnDateAndTime;
	
	public Loan(Member member, Book book, LocalDateTime startDateAndTime, LocalDateTime dueDateAndTime) {
		this.member = member;
		this.book = book;
		this.startDateAndTime = startDateAndTime;
		this.dueDateAndTime = dueDateAndTime;
	}

	public LocalDateTime getDueDateAndTime() {
		return dueDateAndTime;
	}

	public void setDueDateAndTime(LocalDateTime dueDateAndTime) {
		this.dueDateAndTime = dueDateAndTime;
	}

	public LocalDateTime getReturnDateAndTime() {
		return returnDateAndTime;
	}

	public void setReturnDateAndTime(LocalDateTime returnDateAndTime) {
		this.returnDateAndTime = returnDateAndTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public Book getBook() {
		return book;
	}

	public LocalDateTime getStartDateAndTime() {
		return startDateAndTime;
	}
}
