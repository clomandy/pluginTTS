package ch.supsi.dti.tospeech;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import ch.supsi.dti.multilanguage.Messages;

import com.gtranslate.Audio;
import com.gtranslate.Language;

public class Speech extends Thread {

	private String toSay;

	public Speech(String toSay) {
		try {
			this.toSay = URLEncoder.encode(toSay, Messages.Speech_0);
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
			sound = audio.getAudio(this.toSay, Language.ENGLISH);
			audio.play(sound);
		} catch (Exception e) {
			// TODO gestione degli errori!!!!
			e.printStackTrace();
		}

	}

}