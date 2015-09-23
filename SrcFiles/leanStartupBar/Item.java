package com.leanStartupBar;

public class Item {
	private String name;
	private int prepTime;

	public Item(String name , int prepTime){
		this.name = name;
		this.prepTime = prepTime;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}
	
	

}
