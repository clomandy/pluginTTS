package ch.supsi.dti.multilanguage;

import org.eclipse.osgi.util.NLS;

import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;
import ch.supsi.dti.tospeech.SpeakingReferee;

import com.gtranslate.Language;

public class Messages extends NLS {
	public static final String BUNDLE_NAME_EN = "ch.supsi.dti.multilanguage.en";
	public static final String BUNDLE_NAME_IT = "ch.supsi.dti.multilanguage.it";
	public static String done;
	public static String field;
	public static String syntaxError;
	public static String thereAre;
	public static String multipleClass;
	public static String noClass;
	public static String commaExpand;
	public static String readOnly;
	public static String itHas;
	public static String CursorInformations_0;
	public static String CursorInformations_1;
	public static String packages;
	public static String openedM;
	public static String closedM;
	public static String openedF;
	public static String closedF;
	public static String classes;
	public static String methods;
	public static String projects;
	public static String natureProject;
	public static String projectNotSelected;
	public static String classNotSelected;
	public static String methodNotFoundInClass;
	public static String projectNotExist;
	public static String packageNotFound;
	public static String wasAdded;
	public static String wasRemoved;
	public static String wasChanged;
	public static String signature;
	public static String returnType;
	public static String constuctor;
	public static String mainMethod;
	public static String PackageExplorerSelectionListener_0;
	public static String PackageExplorerSelectionListener_1;
	public static String unknownJavaElement;
	public static String noJavaElement;
	public static String PackageExplorerSelectionListener_2;
	public static String project;
	public static String folder;
	public static String thePackage;
	public static String theClass;
	public static String method;
	public static String speakingView;
	public static String packageExplorer;
	public static String error;
	public static String editor;
	public static String focusedM;
	public static String focusedF;
	
	static {
		// initialize resource bundle
		switch(new SpeakingPreferences().getPreferenceStore().getString(PreferenceConstants.MULTILANGUAGE)){
		case "en":
			NLS.initializeMessages(BUNDLE_NAME_EN, Messages.class);
			break;
		case "it":
			NLS.initializeMessages(BUNDLE_NAME_IT, Messages.class);
			break;
		}
		
	}
	
	public static void reinitializeMessages(String str){
		NLS.initializeMessages(str, Messages.class);
	}

	private Messages() {
	}
}
