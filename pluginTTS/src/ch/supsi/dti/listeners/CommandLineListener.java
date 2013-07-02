package ch.supsi.dti.listeners;

import java.io.ByteArrayInputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;

import ch.supsi.dti.parser.CommandParser;
import ch.supsi.dti.parser.ParseException;
import ch.supsi.dti.tospeech.SpeakerHandler;
import ch.supsi.dti.utils.GetPluginElements;
import ch.supsi.dti.views.SpeakingView;

public class CommandLineListener implements KeyListener {

	private StringBuilder wordAccumulator = new StringBuilder();
	private StringBuilder lineAccumulator = new StringBuilder();

	@Override
	public void keyPressed(KeyEvent event) {

		switch (event.keyCode) {
		case SWT.CR:
			checkToSpeechCR();
			executeCommand();
			break;
		case SWT.SPACE:
			checkToSpeechSPACE();
			break;
		case SWT.BS:
			//TODO: non funziona il backspace!
			wordAccumulator.setLength(wordAccumulator.length()-1);
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

	private void executeCommand() {
		SpeakingView speakingView = GetPluginElements.getSpeakingView();
		String commandLineText = speakingView.getCommandLineText();
		if (!commandLineText.equals("")) {
			
			int actualOffset = speakingView.getCommandAreaOffset();
			Device device = Display.getCurrent();
			Color red = new Color(device, 165, 0, 0);

			StyleRange[] tmpRanges = speakingView.getCommandAreaStyleRanges();
			StyleRange[] ranges = new StyleRange[tmpRanges.length + 1];
			for (int i = 0; i < tmpRanges.length; i++) {
				ranges[i] = tmpRanges[i];
			}
			speakingView.addTextOnCommandArea(commandLineText);

			StyleRange styleRange = new StyleRange();
			styleRange.start = actualOffset;
			styleRange.length = commandLineText.length();
			styleRange.fontStyle = SWT.BOLD;
			styleRange.foreground = red;
			ranges[ranges.length - 1] = styleRange;

			speakingView.setCommandAreaStyleRanges(ranges);
			speakingView.resetCommandLine();

			StyleRange[] textStyleRanges = speakingView
					.getCommandAreaStyleRanges();

			CommandParser parser = new CommandParser(new ByteArrayInputStream(
					commandLineText.getBytes()));
			String rensponse = "";
			try {
				rensponse = parser.parse();
			} catch (ParseException e) {
				SpeakerHandler.getInstance().addToQueue("Syntax error!");
			}

			speakingView.addTextOnCommandArea(rensponse);

			speakingView.setCommandAreaStyleRanges(textStyleRanges);
			SpeakerHandler.getInstance().addToQueue(rensponse);
		}
	}

}
