package ch.supsi.dti.listeners;

import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;
import ch.supsi.dti.tospeech.SpeakingHandler;

public class ActiveEditorListener implements ISelectionListener {

	/**
	 * Old line, if it is the same of the last one, the synthesizer not speak (it means that the user moved cursor on the same line)
	 */
	private int line;
	/**
	 * Old titleFocused, if it is the same of the last one, the synthesizer not speak (it means that the user not had changed the active editor)
	 */
	private String titleFocused;
	/**
	 * Old string to say, double control, if it is the same of the last one, the synthesizer not speak 
	 */
	private String toSayOld = "";

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		int i = 0;
		int line;
		if (part instanceof CompilationUnitEditor) {
			TextSelection textSelection = (TextSelection) selection;
			if (!part.getTitle().equals(titleFocused)) {
				titleFocused = part.getTitle();
				SpeakingHandler.getInstance().addToQueue(
						titleFocused + " " + Messages.focusedF);
				i = textSelection.getOffset();
			} else {
				if (new SpeakingPreferences().getPreferenceStore().getBoolean(
						PreferenceConstants.DYNAMIC_READER)) {
					if (textSelection.getLength() == 0) {
						CompilationUnitEditor editor = (CompilationUnitEditor) part;
						IDocumentProvider provider = ((ITextEditor) editor)
								.getDocumentProvider();
						IDocument document = provider.getDocument(editor
								.getEditorInput());
						line = textSelection.getStartLine();
						String toSay = "";
						try {
							toSay = document.get(document.getLineOffset(line),
									document.getLineLength(line));
						} catch (BadLocationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (!toSay.equals("") && !toSay.equals(toSayOld)
								&& this.line != line) {
							toSayOld = toSay;
							this.line = line;
							SpeakingHandler.getInstance().addToQueue(toSay);
						}

					}
				}
			}

		}
	}

}
