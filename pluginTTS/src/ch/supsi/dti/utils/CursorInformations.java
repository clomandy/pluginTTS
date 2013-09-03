package ch.supsi.dti.utils;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.multilanguage.Messages;

/**
 * Class which retrieves the informations of the cursor in the active editor
 * with static methods
 * 
 * @author Claudio
 * 
 */
public class CursorInformations {

	/**
	 * Returns the informations about the cursor located in the active editor,
	 * return in which element is located and the name of this element
	 * 
	 * @return the string with the informations about the cursor
	 */
	public static String getGeneralInfo() {

		ContentOutline outline = PluginElements.getOutline();
		TreeSelection treeSelection = (TreeSelection) outline.getSelection();

		JavaElement element = (JavaElement) treeSelection.getFirstElement();
		StringBuilder sb = new StringBuilder();
		if (element.getElementType() == IJavaElement.TYPE) {
			SourceType theClass = (SourceType) element;
			sb.append(Messages.theClass + " " + theClass.getElementName());

		} else if (element.getElementType() == IJavaElement.METHOD) {

			IMethod method = (IMethod) element;
			sb.append(Messages.method + " " + method.getElementName());

		} else if (element.getElementType() == IJavaElement.FIELD) {

			IField field = (IField) element;
			sb.append(Messages.field + " " + field.getElementName());

		} else {
			sb.append(Messages.unknownJavaElement);
		}
		return Messages.cursorIn + " " + sb.toString();

	}

}
