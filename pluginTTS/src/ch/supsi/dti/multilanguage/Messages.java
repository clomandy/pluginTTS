package ch.supsi.dti.multilanguage;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	public static final String BUNDLE_NAME_EN = "ch.supsi.dti.multilanguage.en";
	public static final String BUNDLE_NAME_IT = "ch.supsi.dti.multilanguage.it";
	public static String lineSeparator;
	public static String comma;
	public static String dot;
	public static String done;
	public static String syntaxError;
	public static String thereAre;
	public static String spaceClasses;
	public static String multipleClass;
	public static String noClass;
	public static String commaExpand;
	public static String isOpened;
	public static String isClosed;
	public static String isReadOnly;
	public static String itHas;
	public static String spaceMethods;
	public static String CursorInformations_0;
	public static String CursorInformations_1;
	public static String PackageInformations_3;
	public static String PackageInformations_4;
	public static String PackageInformations_6;
	public static String PackageInformations_7;
	public static String PackageInformations_8;
	public static String PackageInformations_10;
	public static String PackageInformations_11;
	public static String ProjectInformations_0;
	public static String ProjectInformations_1;
	public static String ProjectInformations_6;
	public static String ProjectInformations_7;
	public static String ProjectInformations_8;
	public static String ProjectInformations_10;
	public static String projectNotSelected;
	public static String CommandParser_22;
	public static String CommandParser_23;
	public static String CommandParser_24;
	public static String CommandParser_25;
	public static String CommandParser_32;
	public static String CommandParser_36;
	public static String CommandParser_41;
	public static String CommandParser_42;
	public static String CommandParser_45;
	public static String CommandParser_46;
	public static String CommandParser_47;
	public static String CommandParser_48;
	public static String CommandParser_9;
	public static String JavaChangeListener_0;
	public static String JavaChangeListener_1;
	public static String JavaChangeListener_2;
	public static String JavaChangeListener_3;
	public static String JavaChangeListener_4;
	public static String MethodInformations_10;
	public static String MethodInformations_13;
	public static String MethodInformations_2;
	public static String MethodInformations_3;
	public static String MethodInformations_5;
	public static String MethodInformations_6;
	public static String MethodInformations_8;
	public static String PackageExplorerSelectionListener_0;
	public static String PackageExplorerSelectionListener_1;
	public static String PackageExplorerSelectionListener_10;
	public static String PackageExplorerSelectionListener_11;
	public static String PackageExplorerSelectionListener_2;
	public static String PackageExplorerSelectionListener_5;
	public static String PackageExplorerSelectionListener_6;
	public static String PackageExplorerSelectionListener_7;
	public static String PackageExplorerSelectionListener_8;
	public static String PackageExplorerSelectionListener_9;
	public static String speakingViewFocused;
	public static String packageExplorerFocused;
	public static String error;
	
	static {
		// initialize resource bundle
		
		NLS.initializeMessages(BUNDLE_NAME_EN, Messages.class);
	}
	
	public static void reinitializeMessages(String str){
		NLS.initializeMessages(str, Messages.class);
	}

	private Messages() {
	}
}
