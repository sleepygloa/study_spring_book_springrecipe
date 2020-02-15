package com.dongwon.tmapapi;

public class PathData {
	private long distance;
	private long time;
	private long fare;
	
	public PathData() {
		
	}
	
	public PathData(long distance, long time, long fare) {
		this.distance = distance;
		this.time = time;
		this.fare = fare;
	}
	
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public long getFare() {
		return fare;
	}
	public void setFare(long fare) {
		this.fare = fare;
	}
	
}
