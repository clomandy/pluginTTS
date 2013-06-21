package ch.supsi.dti.utils;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.supsi.dti.views.SpeakingView;


public class GetPluginElements {

	public static SpeakingView getSpeakingView(){
		SpeakingView speakingView = null;
		try {
			speakingView = (SpeakingView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("pluginTTS.views.SpeakingView");	
		} catch (PartInitException e) {
			// TODO manage exception: add tab speakingview
			e.printStackTrace();
		}
		return speakingView;
	}
	
}