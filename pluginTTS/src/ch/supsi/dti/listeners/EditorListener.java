package ch.supsi.dti.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import ch.supsi.dti.tospeech.SpeakerHandler;

public class EditorListener implements KeyListener {

	private StringBuilder wordAccumulator = new StringBuilder();
	private StringBuilder lineAccumulator = new StringBuilder();

	@Override
	public void keyPressed(KeyEvent event) {

		switch (event.keyCode) {
		case SWT.CR:
			checkToSpeechCR();
			break;
		case SWT.SPACE:
			checkToSpeechSPACE();
			break;
		case SWT.BS:
			//TODO: non funziona il backspace!
			wordAccumulator.setLength(wordAccumulator.length()-1);
			break;
		default:
			wordAccumulator.append(event.character);
			break;
		}

	}

	private void checkToSpeechCR() {
		lineAccumulator.append(" " + wordAccumulator.toString());
		SpeakerHandler.getInstance().addToQueue(lineAccumulator.toString());
		wordAccumulator.setLength(0);
		lineAccumulator.setLength(0);		
	}

	private void checkToSpeechSPACE() {
		lineAccumulator.append(" " + wordAccumulator.toString());
		SpeakerHandler.getInstance().addToQueue(wordAccumulator.toString());
		wordAccumulator.setLength(0);
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

}
