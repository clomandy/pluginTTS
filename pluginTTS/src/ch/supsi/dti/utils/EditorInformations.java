package ch.supsi.dti.utils;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;

public class EditorInformations {

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
