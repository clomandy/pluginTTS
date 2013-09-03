package ch.supsi.dti.tospeech;

public class SpeakingHandler {

	private static SpeakingHandler instance;

	public static SpeakingHandler getInstance() {
		if (instance == null)
			instance = new SpeakingHandler();
		return instance;
	}

	private SpeakingQueue queue;

	private SpeakingHandler() {
		SpeakingReferee referee = SpeakingReferee.getInstance();
		queue = referee.getQueue();
		referee.start();
	}

	public void addToQueue(String toSay) {
		queue.add(toSay);
	}

}
