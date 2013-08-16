package ch.supsi.dti.parser;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;

public class EditorInformations {

	public static String getGeneralInformations() {
		StringBuilder sb = new StringBuilder();
		IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for (IEditorReference iEditorReference : editorReferences) {
			sb.append(iEditorReference.getName() + Messages.comma);
			
		}
		sb.append(" are opened" + Messages.dot);
		sb.append(System.getProperty(Messages.lineSeparator));
		IEditorPart editor = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;
			
			sb.append(textEditor.getTitle() + " is focused" + Messages.dot);
			return sb.toString();
		}else{
			return "No editor opened" + Messages.dot;
		}
	}

}
