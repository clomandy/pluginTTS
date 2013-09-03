package ch.supsi.dti.tospeech;

import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;

import com.gtranslate.Language;

/**
 * Class acting as a referee for the speaking system. If the speech is running
 * while arriving an other, it is stopped, otherwise it starts the new Speech.
 * 
 * @author Claudio
 * 
 */
public class SpeakingReferee extends Thread {

	/**
	 * The shared instance
	 */
	private static SpeakingReferee instance;

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static SpeakingReferee getInstance() {
		if (instance == null)
			instance = new SpeakingReferee();
		return instance;
	}

	/**
	 * The language in which the sentence is to say
	 */
	private String language;

	/**
	 * The last Speech, if the speech is running while arriving an other, it is
	 * stopped.
	 */
	private Speech lastSpeech;

	private SpeakingQueue queue;

	/**
	 * The constructor, set the language
	 */
	private SpeakingReferee() {
		this.queue = new SpeakingQueue();
		switch (new SpeakingPreferences().getPreferenceStore().getString(
				PreferenceConstants.MULTILANGUAGE)) {
		case "en":
			this.language = Language.ENGLISH;
			break;
		case "it":
			this.language = Language.ITALIAN;
			break;
		}
	}

	/**
	 * Retrurns the queue
	 * 
	 * @return the queue
	 */
	public SpeakingQueue getQueue() {
		return queue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (true) {
			Speech speech = new Speech(queue.getElement(), this.language);
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

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

}
