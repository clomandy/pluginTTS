package ch.supsi.dti.listeners;

import java.util.Properties;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaModel;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.tospeech.SpeakingHandler;

public class JavaChangeListener implements IElementChangedListener {

	private static final String FILENAME = "clean-cache.properties"; //$NON-NLS-1$
	private static JavaChangeListener instance;
	private Properties fTable = new Properties();

	private JavaChangeListener() {
	}

	public static JavaChangeListener getInstance() {
		if (instance == null)
			instance = new JavaChangeListener();
		return instance;
	}

	public void start() {
		JavaCore.addElementChangedListener(this);
		// load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jdt.core.IElementChangedListener#elementChanged(org.eclipse
	 * .jdt.core.ElementChangedEvent)
	 */
	@Override
	public void elementChanged(ElementChangedEvent event) {
		IJavaElementDelta delta = event.getDelta();
		IJavaElementDelta[] children = delta.getAffectedChildren();
		for (int i = 0; i < children.length; i++) {
			traverseAndPrint(children[i]);
		}
	}

	private void traverseAndPrint(IJavaElementDelta delta) {
		StringBuilder sb = new StringBuilder();

		String genre = "";
		boolean check = true;
		int elementType = delta.getElement().getElementType();
		switch (elementType) {
		case IJavaElement.COMPILATION_UNIT:
			sb.append(Messages.theClass + " ");
			genre = "f";
			break;
		case IJavaElement.METHOD:
			sb.append(Messages.method + " ");
			genre = "m";
			break;
		case IJavaElement.FIELD:
			sb.append(Messages.field + " ");
			genre = "m";
			break;
		default:
			check = false;
			break;
		}

		switch (delta.getKind()) {
		case IJavaElementDelta.CHANGED:
			// sb.append(delta.getElement().getElementName() + " " +
			// Messages.wasChanged);
			if ((delta.getFlags() & IJavaElementDelta.F_CHILDREN) != 0) {
				// System.out.println("The change was in its children");
				sb = new StringBuilder();
				check = false;
			}
			if ((delta.getFlags() & IJavaElementDelta.F_CONTENT) != 0) {
				// System.out.println("The change was in its content");
				sb = new StringBuilder();
				check = false;
			}
			/* Others flags can also be checked */
			break;
		case IJavaElementDelta.REMOVED:
			sb.append(delta.getElement().getElementName() + " ");
			if (genre.equals("m"))
				sb.append(Messages.wasRemovedM);
			else
				sb.append(Messages.wasRemovedF);
			break;

		case IJavaElementDelta.ADDED:
			sb.append(delta.getElement().getElementName() + " ");
			if (genre.equals("m"))
				sb.append(Messages.wasAddedM);
			else
				sb.append(Messages.wasAddedF);
			break;
		}

		if (check)
			SpeakingHandler.getInstance().addToQueue(sb.toString());

		IJavaElementDelta[] children = delta.getAffectedChildren();
		for (int i = 0; i < children.length; i++) {
			traverseAndPrint(children[i]);
		}

	}

	public void shutdown() {
		JavaCore.removeElementChangedListener(this);
		// save();
	}

}