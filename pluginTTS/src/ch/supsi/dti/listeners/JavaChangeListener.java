package ch.supsi.dti.listeners;

import java.util.Properties;

import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaModel;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.tospeech.SpeakingHandler;

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
		StringBuilder sb = new StringBuilder();
		
		switch (delta.getElement().getElementType()) {
		case JavaModel.COMPILATION_UNIT:
			sb.append(Messages.theClass + " ");
			break;
		case JavaModel.METHOD:
			sb.append(Messages.method + " ");
			break;
		case JavaModel.FIELD:
			sb.append(Messages.field + " ");
			break;
		default:
			break;
		}
		
        switch (delta.getKind()) {
        	case IJavaElementDelta.CHANGED:
        	//sb.append(delta.getElement().getElementName() + " " + Messages.wasChanged);
            if ((delta.getFlags() & IJavaElementDelta.F_CHILDREN) != 0) {
//                System.out.println("The change was in its children");
            }
            if ((delta.getFlags() & IJavaElementDelta.F_CONTENT) != 0) {
//                System.out.println("The change was in its content");
            }
            /* Others flags can also be checked */
            break;
            case IJavaElementDelta.REMOVED:
            	sb.append(delta.getElement().getElementName() + " " + Messages.wasRemoved);
                break;
            
            case IJavaElementDelta.ADDED:
            	sb.append(delta.getElement().getElementName() + " " + Messages.wasAdded);
                
                break;
        }
        SpeakingHandler.getInstance().addToQueue(sb.toString());
        IJavaElementDelta[] children = delta.getAffectedChildren();
        for (int i = 0; i < children.length; i++) {
            traverseAndPrint(children[i]);
        }
    }

	public void shutdown() {
		JavaCore.removeElementChangedListener(this);
//		save();
	}

}