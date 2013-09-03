package ch.supsi.dti.tospeech;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.gtranslate.Audio;

public class Speech extends Thread {

	private String language;
	private String toSay;

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