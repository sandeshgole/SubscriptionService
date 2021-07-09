package com.subscription.services.vo;

import com.subscription.services.entity.Subscription;

public class ResponseTemplateVo {
	private Subscription subscription;
	
	private Book book;

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
 
}
