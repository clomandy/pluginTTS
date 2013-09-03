package ch.supsi.dti.multilanguage;

import org.eclipse.osgi.util.NLS;

import ch.supsi.dti.preferences.PreferenceConstants;
import ch.supsi.dti.preferences.SpeakingPreferences;

import com.gtranslate.Translator;

public class Messages extends NLS {
	public static String areOpenedF;
	public static final String BUNDLE_NAME_EN = "ch.supsi.dti.multilanguage.en";
	public static final String BUNDLE_NAME_IT = "ch.supsi.dti.multilanguage.it";
	public static String classes;
	public static String classNotSelected;
	public static String closedF;
	public static String closedM;
	public static String collapsedF;
	public static String collapsedM;
	public static String commaExpand;
	public static String constuctor;
	public static String cursor;
	public static String cursorIn;
	public static String done;
	public static String editor;
	public static String error;
	public static String expandedF;
	public static String expandedM;
	public static String field;
	public static String focusedF;
	public static String focusedM;
	public static String folder;
	public static String info;
	public static String itHas;
	public static String mainMethod;
	public static String method;
	public static String methodNotFoundInClass;
	public static String methods;
	public static String multipleClass;
	public static String natureProject;
	public static String noClass;
	public static String noJavaElement;
	public static String noOpenedEditor;
	public static String openedF;
	public static String openedM;
	public static String packageExplorer;
	public static String packageNotFound;
	public static String packages;
	public static String project;
	public static String projectNotExist;
	public static String projectNotSelected;
	public static String projects;
	public static String readOnly;
	public static String returnType;
	public static String select;
	public static String selectedF;
	public static String selectedM;
	public static String signature;
	public static String speakingView;
	public static String syntaxError;
	public static String theClass;
	public static String thePackage;
	public static String thereAre;
	public static String thereIs;
	public static String unknownJavaElement;
	public static String wasAddedF;
	public static String wasAddedM;
	public static String wasChangedF;
	public static String wasChangedM;
	public static String wasRemovedF;
	public static String wasRemovedM;

	static {
		// initialize resource bundle
		switch (new SpeakingPreferences().getPreferenceStore().getString(
				PreferenceConstants.MULTILANGUAGE)) {
		case "en":
			NLS.initializeMessages(BUNDLE_NAME_EN, Messages.class);
			break;
		case "it":
			NLS.initializeMessages(BUNDLE_NAME_IT, Messages.class);
			break;
		}

	}

	public static void reinitializeMessages(String str) {
		NLS.initializeMessages(str, Messages.class);
	}

	public static String traduceText(String commandLineText) {

		Translator translate = Translator.getInstance();
		StringBuilder sb = new StringBuilder();
		String[] arrCommands = commandLineText.split(" ");

		for (int i = 0; i < arrCommands.length; i++) {
			switch (arrCommands[i]) {
			case "select":
				sb.append(Messages.select);
				sb.append(" ");
				break;
			case "info":
				sb.append(Messages.info);
				sb.append(" ");
				break;
			case "project":
				sb.append(Messages.project);
				sb.append(" ");
				break;
			case "projects":
				sb.append(Messages.projects);
				sb.append(" ");
				break;
			case "folder":
				sb.append(Messages.folder);
				sb.append(" ");
				break;
			case "package":
				sb.append(Messages.thePackage);
				sb.append(" ");
				break;
			case "packages":
				sb.append(Messages.packages);
				sb.append(" ");
				break;
			case "class":
				sb.append(Messages.theClass);
				sb.append(" ");
				break;
			case "classes":
				sb.append(Messages.classes);
				sb.append(" ");
				break;
			case "method":
				sb.append(Messages.method);
				sb.append(" ");
				break;
			case "methods":
				sb.append(Messages.methods);
				sb.append(" ");
				break;
			case "cursor":
				sb.append(Messages.cursor);
				sb.append(" ");
				break;
			default:
				sb.append(arrCommands[i]);
				sb.append(" ");
				break;
			}
		}
		return sb.toString();

	}

	private Messages() {
	}
}
