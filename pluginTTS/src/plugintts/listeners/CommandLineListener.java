package plugintts.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

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
		
		
	}

}
