package com.leanStartupBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import com.leanStartupBarUtils.Queue;



public class Waiter extends Thread {
	private final Queue sharedQ;
	private boolean checkLoop = false;
	private HashMap<Integer, Item> menu;
	private static int count =1;
	
	java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("hh:mm:ss aa");
	public Waiter(Queue sharedQ, HashMap<Integer, Item> menu) {
		super("Waiter");
		this.sharedQ = sharedQ;
		this.menu = menu;
	}

	@Override
	public void run() {

		BufferedReader into = new BufferedReader(new InputStreamReader(System.in));
		String read;
		System.out.println("Welcome...");
		
		while (!checkLoop) {
			try {
				System.out.print("Enter the item id: ");
				read = into.readLine();
				if (read.equals("bye")) {
					checkLoop = true;
					System.out.print("Thank you for visiting");
					return;
				} else {
					int itemId = Integer.parseInt(read);
					Item item = menu.get(itemId);
					if (item != null) {
						synchronized (sharedQ) {
						sharedQ.enQueue(itemId);
						}
						System.out.println("OrderNumber: ORD" + count + " for " + item.getName() + " at "+dateFormat.format(new Date()).toString());
						count++;
					} else {
						System.out.println("Invalid order");
					}
				}
			} catch (IOException e) {
				System.out.println(e);
			}

		}
	}
}
