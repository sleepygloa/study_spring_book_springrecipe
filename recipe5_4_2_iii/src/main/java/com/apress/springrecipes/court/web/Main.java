package com.apress.springrecipes.court.web;

import org.springframework.web.client.RestTemplate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String uri = "http://localhost:8080/members.json";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
	}

}
