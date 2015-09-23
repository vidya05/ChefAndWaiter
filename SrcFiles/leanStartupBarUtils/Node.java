package com.leanStartupBarUtils;

public class Node {
	private Integer data;
	private Node next;
	
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	public int getData() {
		return this.data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	public Node(){
		this.setNext(null);
	}
	public Node(int data){
		this.data = data;
		this.setNext(null);
	}
	public Boolean isNull(){
		
		if(this.data==null && this.getNext()==null){
			return true;
		}
		return false;
	}
	public Node addNode(int data){
		Node newNode = new Node(data);
		if(this.isNull()){
			return newNode;
		}
		Node rootNode = this;
		Node curr =rootNode;
		while(curr.getNext()!=null){
			curr = curr.getNext();
		}
		curr.setNext(newNode);
		return rootNode;
	}
	
}
