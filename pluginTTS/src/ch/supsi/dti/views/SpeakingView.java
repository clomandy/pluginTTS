package ch.supsi.dti.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import ch.supsi.dti.listeners.CommandLineListener;

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

	/**
	 * TextArea in which there are written the commands and the answers
	 */
	private StyledText commandArea;
	/**
	 * Field in which the user write the commands
	 */
	private Text commandLine;

	/**
	 * The constructor
	 */
	public SpeakingView() {
		super();
	}

	/**
	 * Adds the text on the command area
	 * 
	 * @param text
	 *            the text to add
	 */
	public void addTextOnCommandArea(String text) {
		commandArea.setText(commandArea.getText()
				+ System.getProperty("line.separator") + text);
		scrollCommandAreaBottom();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		GridLayout gridLay = new GridLayout();
		GridData horizontalFill = new GridData(SWT.FILL, SWT.FILL, true, false);
		GridData fill = new GridData(SWT.FILL, SWT.FILL, true, true);
		parent.setLayout(gridLay);

		commandArea = new StyledText(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);

		commandArea.setText("SpeakingView listening....");
		commandArea.setLayoutData(fill);

		commandLine = new Text(parent, SWT.SINGLE | SWT.BORDER);
		commandLine.setEditable(true);
		commandLine.setText("");
		commandLine.setLayoutData(horizontalFill);
		commandLine.addKeyListener(new CommandLineListener());
	}

	/**
	 * Returns the offset of the command area
	 * 
	 * @return the offset of the command area
	 */
	public int getCommandAreaOffset() {
		return commandArea.getText().length() + 1;
	}

	/**
	 * Returns the styles of the command area
	 * 
	 * @return the styles of the command area
	 */
	public StyleRange[] getCommandAreaStyleRanges() {
		return commandArea.getStyleRanges();
	}

	/**
	 * Returns the string contained in the command line
	 * 
	 * @return the string contained in the command line
	 */
	public String getCommandLineText() {
		return commandLine.getText();
	}

	/**
	 * Resets the command line
	 */
	public void resetCommandLine() {
		commandLine.setText("");
	}

	/**
	 * Scroll the command area to the bottom
	 */
	private void scrollCommandAreaBottom() {
		commandArea.setTopIndex(commandArea.getLineCount() - 1);
	}

	/**
	 * Sets the styles of the text in the command area
	 * 
	 * @param ranges
	 *            styles to set in the command area
	 */
	public void setCommandAreaStyleRanges(StyleRange[] ranges) {
		commandArea.setStyleRanges(ranges);
	}

	/**
	 * Set the text in the command line
	 * 
	 * @param str
	 *            the text to set
	 */
	public void setCommandLineText(String str) {
		commandLine.setText(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		commandLine.setFocus();
	}

	/**
	 * Set the text on the command area
	 * 
	 * @param text
	 *            the text to set
	 */
	public void setTextOnCommandArea(String text) {
		commandArea.setText("Nuovo testo: "
				+ System.getProperty("line.separator") + text);
		scrollCommandAreaBottom();
	}

}