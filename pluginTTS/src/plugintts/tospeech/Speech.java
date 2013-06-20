package plugintts.tospeech;

import java.io.InputStream;

import com.gtranslate.Audio;
import com.gtranslate.Language;


public class Speech {
	
	public static void say(String toSay){
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