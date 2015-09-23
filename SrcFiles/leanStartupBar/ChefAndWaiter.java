package com.leanStartupBar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.leanStartupBarUtils.Queue;

public class ChefAndWaiter {
	public static void main(String args[]) {

		HashMap<Integer, Item> menu = new HashMap<Integer, Item>();
		Item item1 = new Item("Sandwich", 5);
		Item item2 = new Item("Coffee", 3);
		Item item3 = new Item("Cereal", 3);
		Item item4 = new Item("Pizza", 7);

		menu.put(1, item1);
		menu.put(2, item2);
		menu.put(3, item3);
		menu.put(4, item4);
		
		final File outPut = new File("Output.txt");
		
		try {
			if(outPut.exists()){
				outPut.delete();
			}
			outPut.createNewFile();
			System.out.println(outPut.getCanonicalPath());
			final Queue sharedQ = new Queue();

			Thread waiter = new Waiter(sharedQ, menu);
			Thread chef = new Chef(sharedQ, menu, outPut);

			waiter.start();
			chef.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
