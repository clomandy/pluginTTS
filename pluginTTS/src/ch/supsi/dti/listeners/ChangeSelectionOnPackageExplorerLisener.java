package ch.supsi.dti.listeners;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.views.contentoutline.ContentOutline;

import ch.supsi.dti.utils.PluginElements;

/**
 * 
 * Class which listen the change in outline and change the selection on the Package Explorer for coherence with the cursor on editor
 * @author Claudio
 *
 */
public class ChangeSelectionOnPackageExplorerLisener implements
		ISelectionChangedListener {

	@Override
	public void selectionChanged(SelectionChangedEvent arg0) {

		ContentOutline outline = PluginElements.getOutline();

		TreeSelection treeSelection = (TreeSelection) outline.getSelection();
		try{
		PluginElements.getPackageExplorer().selectAndReveal(
				treeSelection.getFirstElement());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
