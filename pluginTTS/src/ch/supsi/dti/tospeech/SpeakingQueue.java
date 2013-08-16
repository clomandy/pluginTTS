package ch.supsi.dti.tospeech;

import java.util.concurrent.LinkedBlockingQueue;

import ch.supsi.dti.multilanguage.Messages;

public class SpeakingQueue{

	private LinkedBlockingQueue<String> queue;

	public SpeakingQueue() {
		queue = new LinkedBlockingQueue<>();
	}
	
	public void add(String toSay){
		if(!queue.isEmpty())
			queue.remove();
		
		try {
			queue.put(toSay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	public String getElement(){
		try {
			return queue.take();
		} catch (InterruptedException e) {
			return Messages.error;
		}
	}
	
	
	
}
