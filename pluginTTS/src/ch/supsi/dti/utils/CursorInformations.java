package ch.supsi.dti.utils;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.multilanguage.Messages;



public class CursorInformations {


	public static String getGeneralInfo() {
		
				ContentOutline outline = PluginElements.getOutline();
				TreeSelection treeSelection = (TreeSelection) outline.getSelection();
				
				JavaElement element = (JavaElement) treeSelection.getFirstElement();
				StringBuilder sb = new StringBuilder();
				if (element.getElementType() == JavaElement.TYPE) {
					SourceType theClass = (SourceType)element;
					sb.append(Messages.theClass + " " +theClass.getElementName());

				}else if(element.getElementType() == JavaElement.METHOD){
					
					IMethod method = (IMethod) element;
					sb.append(Messages.method + " " + method.getElementName());
					
				}else{
					
				}
				return Messages.cursorIn + " " + sb.toString();
		
	}

}
