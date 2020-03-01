package com.apress.springrecipes.court.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//기본
//		final String uri = "http://localhost:8080/members.json";
//		RestTemplate restTemplate = new RestTemplate();
//		String result = restTemplate.getForObject(uri, String.class);
//		System.out.println(result);
		
		//원하는 파라미터를 uri에 하드코딩하지 않고 가져오는 방법
		final String uri = "http://localhost:8080/member/{memberId}";
		Map<String, String> params = new HashMap<>();
		params.put("memberId",  "1");
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class, params);
		System.out.println(result);
		
		//기본클래스가 아닌 vo를 이용해 매핑하여 가져오기
//		final String uri = "http://localhost:8080/members.json";
//		RestTemplate restTemplate = new RestTemplate();
//		Members result = restTemplate.getForObject(uri, Members.class);
//		System.out.println(result);
		
	}

}
