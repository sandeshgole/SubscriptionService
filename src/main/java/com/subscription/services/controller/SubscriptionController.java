package com.subscription.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subscription.services.entity.Subscription;
import com.subscription.services.service.SubscriptionService;
import com.subscription.services.vo.ResponseTemplateVo;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping("/")
	public ResponseEntity<Subscription> saveSubscription(@RequestBody Subscription subscription) {
		return subscriptionService.saveSubscription(subscription);
	}
	
	@GetMapping("/")
	public List<Subscription> getSubscription() {
		return subscriptionService.getSubscription();
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVo getSubscriberWithBook(@PathVariable("id") Long subscriberId) {
		return subscriptionService.getSubscriberWithBook(subscriberId);
	}
	
}
