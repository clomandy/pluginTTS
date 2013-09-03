package ch.supsi.dti.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.supsi.dti.Activator;
import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.tospeech.SpeakingReferee;

import com.gtranslate.Language;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class SpeakingPreferences extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private static SpeakingPreferences instance;

	public SpeakingPreferences() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("The preferences for the Speaking Plugin:");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	@Override
	public void createFieldEditors() {
		addField(new ComboFieldEditor(PreferenceConstants.MULTILANGUAGE,
				"&Select the speaking language:", new String[][] {
						{ "English", "en" }, { "Italian", "it" } },
				getFieldEditorParent()));
		addField(new BooleanFieldEditor(PreferenceConstants.DYNAMIC_READER,
				"&Enable dynamic reader on active editor.",
				getFieldEditorParent()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	public boolean performOk() {
		boolean ret = super.performOk();
		if (ret) {
			switch (this.getPreferenceStore().getString(
					PreferenceConstants.MULTILANGUAGE)) {
			case "en":
				Messages.reinitializeMessages(Messages.BUNDLE_NAME_EN);
				SpeakingReferee.getInstance().setLanguage(Language.ENGLISH);
				break;
			case "it":
				Messages.reinitializeMessages(Messages.BUNDLE_NAME_IT);
				SpeakingReferee.getInstance().setLanguage(Language.ITALIAN);
				break;
			default:
				return false;
			}
		}
		return ret;
	}

}