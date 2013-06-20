package plugintts.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import plugintts.listeners.CommandLineListener;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class SpeakingView extends ViewPart {

	private StyledText text;
	private Text commandLine;

	public SpeakingView() {
		super();
	}

	public void setFocus() {
		commandLine.setFocus();
	}

	public void createPartControl(Composite parent) {
		GridLayout gridLay = new GridLayout();
		GridData horizontalFill = new GridData(SWT.FILL, SWT.FILL, true, false);
		GridData fill = new GridData(SWT.FILL, SWT.FILL, true, true);
		parent.setLayout(gridLay);

		text = new StyledText(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);

		text.setText("SpeakingView listening....");
		text.setLayoutData(fill);

		commandLine = new Text(parent, SWT.SINGLE | SWT.BORDER);
		commandLine.setEditable(true);
		commandLine.setText("");
		commandLine.setLayoutData(horizontalFill);
		commandLine.addKeyListener(new CommandLineListener());
	}

	public void setText(String string) {
		text.setText("Nuovo testo: " + System.getProperty("line.separator")
				+ string);
		scrollTextBottom();
	}

	public void addText(String string) {
		text.setText(text.getText() + System.getProperty("line.separator")
				+ string);
		scrollTextBottom();
	}

	public int getTextOffset() {
		return text.getText().length() + 1;
	}

	public StyleRange[] getTextStyleRanges() {
		return text.getStyleRanges();
	}

	public void setTextStyleRanges(StyleRange[] ranges) {
		text.setStyleRanges(ranges);
	}

	public void scrollTextBottom() {
		text.setTopIndex(text.getLineCount() - 1);
	}

	public String getCommandLineText() {
		return commandLine.getText();
	}

	public void resetCommandLine() {
		commandLine.setText("");
	}

}