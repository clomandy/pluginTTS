package ch.supsi.dti.utils;

import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.views.SpeakingView;

public class PluginElements {

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

	public static ITextEditor getActiveTextEditor() {
		return (ITextEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}

	public static PackageExplorerPart getPackageExplorer() {
		PackageExplorerPart part= PackageExplorerPart.getFromActivePerspective();
		if (part == null)
			PackageExplorerPart.openInActivePerspective();
		
		part= PackageExplorerPart.getFromActivePerspective();
		
		return part;
	}
	
	public static IEditorPart getActiveEditor(){
		return PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	}
}
