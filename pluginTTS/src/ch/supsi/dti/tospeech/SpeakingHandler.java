package ch.supsi.dti.tospeech;

/**
 * This class manage the speaking system. It is singleton pattern developed, and
 * it is used to add to queue the sentence for the synthesizer
 * 
 * @author Claudio
 * 
 */
public class SpeakingHandler {

	/**
	 * The shared instance
	 */
	private static SpeakingHandler instance;

	/**
	 * The monospace queue in which is located the sentence
	 */
	private SpeakingQueue queue;
	
	/**
	 * The constructor 
	 */
	private SpeakingHandler() {
		SpeakingReferee referee = SpeakingReferee.getInstance();
		queue = referee.getQueue();
		referee.start();
	}
	
	/**
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static SpeakingHandler getInstance() {
		if (instance == null)
			instance = new SpeakingHandler();
		return instance;
	}

	/**
	 * Add the toSay string in the queue
	 * @param toSay the string to add
	 */
	public void addToQueue(String toSay) {
		queue.add(toSay);
	}

}
