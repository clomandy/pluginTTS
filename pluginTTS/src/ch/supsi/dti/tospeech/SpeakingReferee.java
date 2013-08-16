package ch.supsi.dti.tospeech;

import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;

import com.gtranslate.Language;

public class SpeakingReferee extends Thread {

	private static SpeakingReferee instance;
	private SpeakingQueue queue;
	private Speech lastSpeech;
	private String language;

	private SpeakingReferee() {
		this.queue = new SpeakingQueue();
		switch(new SpeakingPreferences().getPreferenceStore().getString(PreferenceConstants.MULTILANGUAGE)){
		case "en":
			this.language = Language.ENGLISH;
			break;
		case "it":
			this.language = Language.ITALIAN;
			break;
		}
	}

	public static SpeakingReferee getInstance() {
		if (instance == null)
			instance = new SpeakingReferee();
		return instance;
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
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	public SpeakingQueue getQueue() {
		return queue;
	}

}
