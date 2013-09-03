package ch.supsi.dti.tospeech;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gtranslate.Audio;

/**
 * Thread which is build for the sentences to say with the synthesizer
 * @author Claudio
 *
 */
public class Speech extends Thread {

	/**
	 * Language using to say toSay string
	 */
	private String language;
	/**
	 * String to say with the synthesizer
	 */
	private String toSay;

	/**
	 * The constructor, set the language and the string toSay, set the encoding and replace ".java" string with ""
	 * @param toSay
	 * @param language
	 */
	public Speech(String toSay, String language) {
		this.language = language;
		// In italian doesn't speek the dot.
		toSay = toSay.replace(".java", "");

		try {
			this.toSay = URLEncoder.encode(toSay, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		Audio audio = Audio.getInstance();

		InputStream sound;
		try {
			sound = audio.getAudio(this.toSay, this.language);
			audio.play(sound);
		} catch (Exception e) {
			// TODO gestione degli errori!!!!
			e.printStackTrace();
		}

	}

}