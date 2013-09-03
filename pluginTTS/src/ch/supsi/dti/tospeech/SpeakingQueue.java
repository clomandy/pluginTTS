package ch.supsi.dti.tospeech;

import java.util.concurrent.LinkedBlockingQueue;

import ch.supsi.dti.multilanguage.Messages;

/**
 * Class used as data structure to storage the strings for the synthesizer. The
 * data structure is a LinkedBlockingQueue, but it contain only one place.
 * 
 * @author Claudio
 * 
 */
public class SpeakingQueue {

	/**
	 * The queue
	 */
	private LinkedBlockingQueue<String> queue;

	/**
	 * The constructor
	 */
	public SpeakingQueue() {
		queue = new LinkedBlockingQueue<>();
	}

	/**
	 * Adds the string toSay in the queue, if the queue isn't empty, remove the
	 * object that is in. Waiting if necessary for space to become available.
	 * 
	 * @param toSay
	 *            the string for the synthesizer
	 */
	public void add(String toSay) {
		if (!queue.isEmpty())
			queue.remove();

		try {
			queue.put(toSay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves and removes the head of this queue, waiting if necessary until
	 * an element becomes available.
	 * 
	 * @return the string in queue
	 */
	public String getElement() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			return Messages.error;
		}
	}

	/**
	 * Returns if the place is empty (true) or full (false)
	 * 
	 * @return the boolean value
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
