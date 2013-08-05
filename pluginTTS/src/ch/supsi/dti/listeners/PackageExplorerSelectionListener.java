package ch.supsi.dti.listeners;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

import ch.supsi.dti.tospeech.SpeakingHandler;
import ch.supsi.dti.utils.GetPluginElements;

public class PackageExplorerSelectionListener implements ITreeViewerListener,
		ISelectionChangedListener {

	private static PackageExplorerSelectionListener instance;

	private PackageExplorerSelectionListener() {
	}

	public static PackageExplorerSelectionListener getInstance() {
		if (instance == null)
			instance = new PackageExplorerSelectionListener();
		return instance;
	}

	public void start() {
		PackageExplorerPart packageExplorer = GetPluginElements
				.getPackageExplorer();
		TreeViewer treeViewer = packageExplorer.getTreeViewer();
		treeViewer.addTreeListener(this);
		treeViewer.addSelectionChangedListener(this);
		// load();
	}

	@Override
	public void treeCollapsed(TreeExpansionEvent e) {
		SpeakingHandler.getInstance().addToQueue(getTypeSelection() + " collapsed!");
	}

	@Override
	public void treeExpanded(TreeExpansionEvent e) {
		SpeakingHandler.getInstance().addToQueue(getTypeSelection() + " expanded!");
	}

	@Override
	public void selectionChanged(SelectionChangedEvent e) {
		SpeakingHandler.getInstance().addToQueue(getTypeSelection() + " selected!");
	}

	public void shutdown() {
		PackageExplorerPart packageExplorer = GetPluginElements
				.getPackageExplorer();
		TreeViewer treeViewer = packageExplorer.getTreeViewer();
		treeViewer.removeTreeListener(this);
		treeViewer.removeSelectionChangedListener(this);
	}
	
	private String getTypeSelection(){
		ISelectionService service = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getSelectionService();

		TreeSelection structured = (TreeSelection) service
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");

		structured = (TreeSelection) service
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");
		
		Object ob = structured.getFirstElement();
		if(ob instanceof JavaElement){
		JavaElement javaElement = (JavaElement) structured.getFirstElement();
		
		
			StringBuilder sb = new StringBuilder();
			
			sb.append(javaElement.getElementName());
			
			if(javaElement.getElementType() == IJavaElement.JAVA_PROJECT){
				return "Project " + sb.toString();
			}else if(javaElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT){
				return "Folder " + sb.toString();
			}else if(javaElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT){
				return "Package " + sb.toString();
			}else if(javaElement.getElementType() == IJavaElement.COMPILATION_UNIT){
				return "Class " + sb.toString();
			}else if(javaElement.getElementType() == IJavaElement.METHOD){
				return "Method " + sb.toString();
			}else{
				return "Unknown Java element";
			}
			
		}else{
			return "No Java Element";
		}
	}

}