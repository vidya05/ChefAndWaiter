package com.leanStartupBar;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import com.leanStartupBarUtils.Queue;

public class Chef extends Thread {
	private final Queue sharedQ;
	private HashMap<Integer, Item> menu;
	private File outPutFile;
	private static int count =1;
	
	java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("hh:mm:ss aa");

	public Chef(Queue sharedQ, HashMap<Integer, Item> menu, File outPutFile) {
		super("Chef");
		this.sharedQ = sharedQ;
		this.menu = menu;
		this.outPutFile = outPutFile;
	}

	@Override
	public void run() {
		while (true) {
            
			synchronized (sharedQ) {
				
				while (!sharedQ.isEmpty()) {
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter(outPutFile.getName(), true));
						int itemId = sharedQ.deQueue();
						Item item = menu.get(itemId);
						writer.write("Chef:Picked up ORD"+count+" at " +dateFormat.format(new Date()).toString()+"\n");
						writer.write("Chef:cooking "+ item.getName()+ " ..."+"\n");
						writer.close();
						sharedQ.wait(item.getPrepTime() *60 *1000);
						writer = new BufferedWriter(new FileWriter(outPutFile.getName(), true));
						writer.write("Chef:Finished making "+item.getName()+" for ORD"+count+" at " +dateFormat.format(new Date()).toString()+"\n");
						count ++;
					} catch (IOException | InterruptedException ex) {
						ex.printStackTrace();
					} finally{
						try {
							writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}