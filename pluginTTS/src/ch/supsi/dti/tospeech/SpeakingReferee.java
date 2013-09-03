package ch.supsi.dti.tospeech;

import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;

import com.gtranslate.Language;

public class SpeakingReferee extends Thread {

	private static SpeakingReferee instance;

	public static SpeakingReferee getInstance() {
		if (instance == null)
			instance = new SpeakingReferee();
		return instance;
	}

	private String language;
	private Speech lastSpeech;

	private SpeakingQueue queue;

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

	public SpeakingQueue getQueue() {
		return queue;
	}

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
