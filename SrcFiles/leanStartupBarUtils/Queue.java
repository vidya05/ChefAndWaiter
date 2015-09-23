package com.leanStartupBarUtils;

public class Queue {
	private Node top;
	
	public Node getTop() {
		return this.top;
	}
	public void setTop(Node top) {
		this.top = top;
	}
	
	public Queue(){
		this.top = null;
	}
	
	public boolean isEmpty(){
		if(this.top == null){
			return true;
		}
		return false;
	}
	
	public void enQueue(int item){
		Node temp = new Node(item);
		temp.setNext(null);
		if(this.isEmpty()){
			this.top = temp;
		}else{
			Node curr = this.getTop();
			while(curr.getNext()!=null){
				curr = curr.getNext();
			}
			curr.setNext(temp);
			
		}
		
	}
	
	public int deQueue(){
		if(this.isEmpty()){
			System.out.println("The given Queue is empty");
			return -1;
		}
		int data =this.top.getData();
		top = top.getNext();
		return data;
	}
	
	public int size(){
		if(this.isEmpty()){
			return 0;
		}
		Node curr = this.top;
		int size = 0;
		while(curr!=null){
			size ++;
			curr = curr.getNext();
		}
		return size;
	}
}
