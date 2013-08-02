package ch.supsi.dti.tospeech;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SpeakerHandler{

	private static SpeakerHandler instance;
	private ExecutorService queue;

	private SpeakerHandler() {
		queue = Executors.newSingleThreadScheduledExecutor();
	}

	public static SpeakerHandler getInstance() {
		if (instance == null)
			instance = new SpeakerHandler();
		return instance;
	}

	public void addToQueue(String toSay) {
		
		queue.execute(new Speech(toSay));
	}
	
	public void killQueue(){
		
		boolean isTerminated = queue.isTerminated();
		while (!isTerminated) {
		    queue.shutdownNow();
		    isTerminated = queue.isTerminated();
		    
		}
		queue = Executors.newSingleThreadScheduledExecutor();
			
	}

}
