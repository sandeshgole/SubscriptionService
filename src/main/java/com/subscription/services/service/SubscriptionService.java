package com.subscription.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.subscription.services.entity.Subscription;
import com.subscription.services.repository.SubscriptionRepository;
import com.subscription.services.vo.Book;
import com.subscription.services.vo.ResponseTemplateVo;

@Service
public class SubscriptionService {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private RestTemplate restTemplate;

//	public Subscription saveSubscription(Subscription subscription) {
//		return subscriptionRepository.save(subscription);
//	}

	public List<Subscription> getSubscription() {
		return subscriptionRepository.findAll();
	}

	public ResponseTemplateVo getSubscriberWithBook(Long subscriberId) {
		ResponseTemplateVo responseTemplateVo =new ResponseTemplateVo();
		Subscription subscription = subscriptionRepository.findBySubscriberId(subscriberId);
		
		Book book = restTemplate.getForObject("http://BOOK-SERVICE/books/"+ subscription.getBookId(), Book.class);
		responseTemplateVo.setSubscription(subscription);
		responseTemplateVo.setBook(book);
		return responseTemplateVo;
	}
	
	public int getAvailableBook(String bookCode) {
		Book book = restTemplate.getForObject("http://BOOK-SERVICE/books/"+ bookCode, Book.class);
		return book.getBooksAvailable();
	}

	public ResponseEntity<Subscription> saveSubscription(Subscription subscription) {
		int availableBook = getAvailableBook(subscription.getBookId());
		if(availableBook == 0) {
			return new ResponseEntity<Subscription>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		 if(subscriptionRepository.save(subscription)!=null) {
			 return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
		 } else {
			 return new ResponseEntity<Subscription>(HttpStatus.BAD_REQUEST);
		 }
	}
}
