package ch.supsi.dti.tospeech;

import java.io.InputStream;

import com.gtranslate.Audio;
import com.gtranslate.Language;


public class Speech implements Runnable{
	
	private String toSay;
	
	public Speech(String toSay){
		this.toSay = toSay; 
	}

	public void run() {
		Audio audio = Audio.getInstance();
		
		//Translator translate = Translator.getInstance();
		//String text = translate.translate("I am a computer engeneer", Language.ENGLISH, Language.ITALIAN);
		InputStream sound;
		try {
			sound = audio.getAudio(toSay, Language.ENGLISH);
			audio.play(sound);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}