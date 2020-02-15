package com.dongwon.tmapapi.exceptions;

public class NoAPIKeyException extends Exception {
	private static final long serialVersionUID = 7427813593529429907L;
	public NoAPIKeyException() {
		super("API Key가 설정되지 않았습니다. TMapAPI.setAPIKey()");
	}
	public NoAPIKeyException(String msg) {
		super(msg);
	}
}
