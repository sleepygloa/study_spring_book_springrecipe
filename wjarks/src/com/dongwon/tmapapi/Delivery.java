package com.dongwon.tmapapi;

public class Delivery {
	private String code;
	private String address;
	private double x;
	private double y;

	public Delivery(String code) {
		this.code = code;
	}
	
	public Delivery(String code, String addr) {
		this.code = code;
		this.address = addr;
	}

	public Delivery(String code, String addr, double x, double y) {
		this.code = code;
		this.address = addr;
		this.x = x;
		this.y = y;
	}
	
	public String getCode() {
		return code;
	}
	public void setId(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public String toCSVline() {
		return code+","+address+","+x+","+y;
	}
}
/*
rid: datas[1],
id: datas[3],
name: datas[5],
zip: datas[8],
lat: datas[9],
lon: datas[10].split('\r')[0]
*/