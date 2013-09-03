package ch.supsi.dti.utils;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;

/**
 * Class which retrieves the informations of the editors in the workspace with
 * static methods
 * 
 * @author Claudio
 * 
 */
public class EditorInformations {

	/**
	 * Return the informations about the editors opened in the workspace, which
	 * are opened, which is focused
	 * 
	 * @return the string with the informations about the editors
	 */
	public static String getGeneralInformations() {
		StringBuilder sb = new StringBuilder();
		IEditorReference[] editorReferences = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (IEditorReference iEditorReference : editorReferences) {
			sb.append(iEditorReference.getName() + ", ");

		}
		sb.append(" " + Messages.areOpenedF + ".");
		sb.append(System.getProperty("line.separator"));
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;

			sb.append(textEditor.getTitle() + " " + Messages.focusedF + ".");
			return sb.toString();
		} else {
			return Messages.noOpenedEditor + ".";
		}
	}

}
