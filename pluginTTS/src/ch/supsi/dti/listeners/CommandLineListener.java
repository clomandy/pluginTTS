package ch.supsi.dti.listeners;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.parser.CommandParser;
import ch.supsi.dti.parser.ParseException;
import ch.supsi.dti.tospeech.SpeakingHandler;
import ch.supsi.dti.utils.PluginElements;
import ch.supsi.dti.views.SpeakingView;

public class CommandLineListener implements KeyListener {

	private int counter;
	private ArrayList<String> commandList;
	private StringBuilder wordAccumulator;
	private StringBuilder lineAccumulator;

	public CommandLineListener() {
		counter = 0;
		commandList = new ArrayList<>();
		wordAccumulator = new StringBuilder();
		lineAccumulator = new StringBuilder();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		SpeakingView speakingView = PluginElements.getSpeakingView();
		switch (event.keyCode) {
		case SWT.CR:
			checkToSpeechCR();
			executeCommand();
			counter = commandList.size();
			break;
		case SWT.SPACE:
			checkToSpeechSPACE();
			break;
		case SWT.ARROW_UP:
			if (counter > 0) {
				counter--;
				speakingView.setCommandLineText(commandList.get(counter));
				SpeakingHandler.getInstance().addToQueue(
						commandList.get(counter));
			}
			break;
		case SWT.ARROW_DOWN:
			if (counter < commandList.size() - 1) {
				counter++;
				speakingView.setCommandLineText(commandList.get(counter));
				SpeakingHandler.getInstance().addToQueue(
						commandList.get(counter));
			}
			break;
		default:
			wordAccumulator.append(event.character);
			break;
		}
		System.err.println(wordAccumulator);
	}

	private void checkToSpeechCR() {
		lineAccumulator = new StringBuilder(PluginElements.getSpeakingView()
				.getCommandLineText());
		wordAccumulator = new StringBuilder();
		lineAccumulator = new StringBuilder();
	}

	private void checkToSpeechSPACE() {
		counter++;
		lineAccumulator = new StringBuilder(PluginElements.getSpeakingView()
				.getCommandLineText());
		SpeakingHandler.getInstance().addToQueue(wordAccumulator.toString());
		wordAccumulator = new StringBuilder();
	}

	@Override
	public void keyReleased(KeyEvent event) {
		SpeakingView speakingView = PluginElements.getSpeakingView();
		switch (event.keyCode) {
		case SWT.BS:
			String tmpStr = PluginElements.getSpeakingView()
					.getCommandLineText();
			wordAccumulator = new StringBuilder(tmpStr.substring(
					tmpStr.lastIndexOf(' ') + 1, tmpStr.length()));
			break;
		}
	}

	private void executeCommand() {
		SpeakingView speakingView = PluginElements.getSpeakingView();
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
				speakingView.addTextOnCommandArea(rensponse);

				speakingView.setCommandAreaStyleRanges(textStyleRanges);
				commandList.add(commandLineText);
				SpeakingHandler.getInstance().addToQueue(
						commandLineText + "."
								+ System.getProperty("line.separator")
								+ rensponse);
			} catch (ParseException e) {
				SpeakingHandler.getInstance().addToQueue(Messages.syntaxError);
			}

		}
	}

}
