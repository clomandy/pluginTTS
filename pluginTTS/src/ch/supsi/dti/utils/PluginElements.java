package ch.supsi.dti.utils;

import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.views.SpeakingView;

/**
 * Class which retrieves the component of the Plugin
 * 
 * @author Claudio
 * 
 */
public class PluginElements {

	/**
	 * Returns the active editor
	 * 
	 * @return the active editor
	 */
	public static IEditorPart getActiveEditor() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
	}

	/**
	 * Returns the Outline View object
	 * 
	 * @return the Outline View in ContentOutline object
	 */
	public static ContentOutline getOutline() {

		return (ContentOutline) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.findViewReference(IPageLayout.ID_OUTLINE).getView(true);
	}

	/**
	 * Returns the Package Explorer, if it is closed, the method open it.
	 * 
	 * @return the Pacakge Explorer in PackageExplorerPart object
	 */
	public static PackageExplorerPart getPackageExplorer() {
		PackageExplorerPart part = PackageExplorerPart
				.getFromActivePerspective();
		if (part == null)
			PackageExplorerPart.openInActivePerspective();

		part = PackageExplorerPart.getFromActivePerspective();

		return part;
	}

	/**
	 * Returns the Speaking View, if it is closed, the method open it.
	 * 
	 * @return the Speaking View in SpeakingView object
	 */
	public static SpeakingView getSpeakingView() {
		SpeakingView speakingView = null;
		try {
			speakingView = (SpeakingView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.showView("pluginTTS.views.SpeakingView");
		} catch (PartInitException e) {
			// TODO manage exception: add tab speakingview
			e.printStackTrace();
		}
		return speakingView;
	}
}
