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

/**
 * Class which listen to the command line, it says every word (after space),
 * every command (after enter). It executes the command and says the answer of
 * the command. It keeps the entire list of the commands which are executed.
 * 
 * @author Claudio
 * 
 */
public class CommandLineListener implements KeyListener {

	/**
	 * List of commands which are executed
	 */
	private ArrayList<String> commandList;
	/**
	 * Counter for the accumulators
	 */
	private int counter;
	/**
	 * Accumulator for the entire line, for the synthesizer
	 */
	private StringBuilder lineAccumulator;
	/**
	 * Accumulator for the last word, for the synthesizer
	 */
	private StringBuilder wordAccumulator;

	/**
	 * The constructor, reset every field
	 */
	public CommandLineListener() {
		counter = 0;
		commandList = new ArrayList<>();
		wordAccumulator = new StringBuilder();
		lineAccumulator = new StringBuilder();
	}

	/**
	 * It is executed when Enter is pressed, it update the accumulator.
	 */
	private void checkToSpeechCR() {
		lineAccumulator = new StringBuilder(PluginElements.getSpeakingView()
				.getCommandLineText());
		wordAccumulator = new StringBuilder();
		lineAccumulator = new StringBuilder();
	}

	/**
	 * It is executed when Space is pressed, it update the accumulator.
	 */
	private void checkToSpeechSPACE() {
		counter++;
		lineAccumulator = new StringBuilder(PluginElements.getSpeakingView()
				.getCommandLineText());
		SpeakingHandler.getInstance().addToQueue(
				Messages.traduceText(wordAccumulator.toString()));
		wordAccumulator = new StringBuilder();
	}

	/**
	 * It is executed when Enter is pressed, it executes the right command and update the Speacking view. It send the sentences to say to the SpeakingHandler.
	 */
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
						Messages.traduceText(commandLineText) + "."
								// commandLineText + "."
								+ System.getProperty("line.separator")
								+ rensponse);
			} catch (ParseException e) {
				SpeakingHandler.getInstance().addToQueue(Messages.syntaxError);
			}

		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
	 */
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
	 */
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

}
