package ch.supsi.dti.listeners;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.utils.PluginElements;

public class ChangeSelectionOnPackageExplorerLisener implements
		ISelectionChangedListener {

	@Override
	public void selectionChanged(SelectionChangedEvent arg0) {

		ContentOutline outline = PluginElements.getOutline();

		TreeSelection treeSelection = (TreeSelection) outline.getSelection();
		PluginElements.getPackageExplorer().selectAndReveal(
				treeSelection.getFirstElement());
	}

}
