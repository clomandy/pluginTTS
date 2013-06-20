package plugintts.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;

import plugintts.tospeech.Speech;
import plugintts.utils.GetPluginElements;
import plugintts.views.SpeakingView;

public class CommandLineListener implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.keyCode) {
		case SWT.CR:
			executeCommand();
			break;
		default:
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	private void executeCommand() {
		SpeakingView speakingView = GetPluginElements.getSpeakingView();
		String commandLineText = speakingView.getCommandLineText();
		if(!commandLineText.equals("")){
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
			
			Speech.say(commandLineText);
			
		}
	}

}
