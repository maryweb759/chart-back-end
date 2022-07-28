package com.example.demo.dto;

public class CountType {

	private Long count;
	private String type;
	
	public CountType(Long count, String type) {
		super();
		this.count = count;
		this.type = type;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	} 
	
	
}
