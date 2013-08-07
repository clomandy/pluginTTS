package ch.supsi.dti.tospeech;

public class SpeakingReferee extends Thread {

	private static SpeakingReferee instance;
	private SpeakingQueue queue;
	private Speech lastSpeech;

	private SpeakingReferee() {
		this.queue = new SpeakingQueue();
	}

	public static SpeakingReferee getInstance() {
		if (instance == null)
			instance = new SpeakingReferee();
		return instance;
	}

	@Override
	public void run() {
		while (true) {
			Speech speech = new Speech(queue.getElement());
			if (lastSpeech != null) {
				if (lastSpeech.isAlive()) {
					lastSpeech.stop();
					lastSpeech.interrupt();
				}
			}
			speech.start();
			lastSpeech = speech;
		}
	}

	public SpeakingQueue getQueue() {
		return queue;
	}

}
