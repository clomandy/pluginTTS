package ch.supsi.dti.utils;

import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.views.SpeakingView;

public class PluginElements {

	public static IEditorPart getActiveEditor() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage().getActiveEditor();
	}

	public static ITextEditor getActiveTextEditor() {
		return (ITextEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public static ContentOutline getOutline() {

		return (ContentOutline) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.findViewReference(IPageLayout.ID_OUTLINE).getView(true);
	}

	public static PackageExplorerPart getPackageExplorer() {
		PackageExplorerPart part = PackageExplorerPart
				.getFromActivePerspective();
		if (part == null)
			PackageExplorerPart.openInActivePerspective();

		part = PackageExplorerPart.getFromActivePerspective();

		return part;
	}

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
