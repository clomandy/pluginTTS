package ch.supsi.dti.utils;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;



public class CursorInformations {

	public static int getCursorColumn() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ISelectionProvider selectionProvider = ((ITextEditor) editor)
					.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				IDocumentProvider provider = ((ITextEditor) editor)
						.getDocumentProvider();
				IDocument document = provider.getDocument(editor
						.getEditorInput());
				int line = textSelection.getStartLine();
				int column = 0;
				try {
					column = textSelection.getOffset()
							- document.getLineOffset(line);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return (column + 1);

			}
		}
		return -1;
	}

	public static int getCursorLine() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ISelectionProvider selectionProvider = ((ITextEditor) editor)
					.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				IDocumentProvider provider = ((ITextEditor) editor)
						.getDocumentProvider();
				int line = textSelection.getStartLine();

				return line+1;

			}
		}
		return -1;
	}

	public static String getCursorLineAndColumn() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		IWorkbenchPage page = win.getActivePage();
		IEditorPart editor = page.getActiveEditor();
		if (editor instanceof ITextEditor) {
			ISelectionProvider selectionProvider = ((ITextEditor) editor)
					.getSelectionProvider();
			ISelection selection = selectionProvider.getSelection();
			if (selection instanceof ITextSelection) {
				ITextSelection textSelection = (ITextSelection) selection;
				IDocumentProvider provider = ((ITextEditor) editor)
						.getDocumentProvider();
				IDocument document = provider.getDocument(editor
						.getEditorInput());
				int line = textSelection.getStartLine();
				int column = 0;
				try {	
					column = textSelection.getOffset()
							- document.getLineOffset(line);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return Messages.CursorInformations_0 + (line + 1) + Messages.CursorInformations_1 + (column + 1);

			}
		}
		return null;
	}

}
