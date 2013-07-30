package ch.supsi.dti.listeners;

import java.util.Properties;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.JavaCore;

import ch.supsi.dti.tospeech.SpeakerHandler;

public class JavaChangeListener implements IElementChangedListener {
	
	private static final String FILENAME = "clean-cache.properties"; //$NON-NLS-1$
	private static JavaChangeListener instance;
	private Properties fTable = new Properties();

	private JavaChangeListener(){}
	
	public static JavaChangeListener getInstance(){
		if (instance == null)
			instance = new JavaChangeListener();
		return instance;
	}
	
	public void start() {
		JavaCore.addElementChangedListener(this);
//		load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jdt.core.IElementChangedListener#elementChanged(org.eclipse
	 * .jdt.core.ElementChangedEvent)
	 */
	public void elementChanged(ElementChangedEvent event) {
		IJavaElementDelta delta = event.getDelta();
		IJavaElementDelta[] children = delta.getAffectedChildren();
        for (int i = 0; i < children.length; i++) {
            traverseAndPrint(children[i]);
        }
	}

	private void traverseAndPrint(IJavaElementDelta delta) {
        switch (delta.getKind()) {
            case IJavaElementDelta.ADDED:
            	SpeakerHandler.getInstance().addToQueue(delta.getElement() + " was added");
               // System.out.println(delta.getElement() + " was added");
                break;
            case IJavaElementDelta.REMOVED:
            	SpeakerHandler.getInstance().addToQueue(delta.getElement() + " was removed");
               // System.out.println(delta.getElement() + " was removed");
                break;
            case IJavaElementDelta.CHANGED:
            	SpeakerHandler.getInstance().addToQueue(delta.getElement() + " was changed");
               // System.out.println(delta.getElement() + " was changed");
                if ((delta.getFlags() & IJavaElementDelta.F_CHILDREN) != 0) {
                    System.out.println("The change was in its children");
                }
                if ((delta.getFlags() & IJavaElementDelta.F_CONTENT) != 0) {
                    System.out.println("The change was in its content");
                }
                /* Others flags can also be checked */
                break;
        }
        IJavaElementDelta[] children = delta.getAffectedChildren();
        for (int i = 0; i < children.length; i++) {
            traverseAndPrint(children[i]);
            System.out.println();
        }
    }

	public void shutdown() {
		JavaCore.removeElementChangedListener(this);
//		save();
	}

}