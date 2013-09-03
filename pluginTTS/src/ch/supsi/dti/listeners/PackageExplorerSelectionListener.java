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

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.tospeech.SpeakingHandler;
import ch.supsi.dti.utils.PluginElements;

/**
 * Class which allows you to listen the changes in Package Explorer
 * 
 * @author Claudio
 */
public class PackageExplorerSelectionListener implements ITreeViewerListener,
		ISelectionChangedListener {

	/**
	 * The shared instance
	 */
	private static PackageExplorerSelectionListener instance;
	/**
	 * Genre for the speaking coherence
	 */
	private String genre = "";

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static PackageExplorerSelectionListener getInstance() {
		if (instance == null)
			instance = new PackageExplorerSelectionListener();
		return instance;
	}

	/**
	 * The constructor, private for the singleton pattern
	 */
	private PackageExplorerSelectionListener() {
	}

	/**
	 * Return the with the type of element that is selected
	 * 
	 * @return the string with the type
	 */
	private String getTypeSelection() {
		ISelectionService service = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getSelectionService();

		TreeSelection structured = (TreeSelection) service
				.getSelection("org.eclipse.jdt.ui.PackageExplorer");

		Object ob = structured.getFirstElement();
		if (ob instanceof JavaElement) {
			JavaElement javaElement = (JavaElement) structured
					.getFirstElement();

			StringBuilder sb = new StringBuilder();

			sb.append(javaElement.getElementName());

			if (javaElement.getElementType() == IJavaElement.JAVA_PROJECT) {
				this.genre = "m";
				return Messages.project + " " + sb.toString();
			} else if (javaElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT_ROOT) {
				this.genre = "f";
				return Messages.folder + " " + sb.toString();
			} else if (javaElement.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
				this.genre = "m";
				return Messages.thePackage + " " + sb.toString();
			} else if (javaElement.getElementType() == IJavaElement.COMPILATION_UNIT) {
				this.genre = "f";
				return Messages.theClass + " " + sb.toString();
			} else if (javaElement.getElementType() == IJavaElement.METHOD) {
				this.genre = "m";
				return Messages.method + " " + sb.toString();
			} else {
				this.genre = "m";
				return Messages.unknownJavaElement;
			}

		} else {
			this.genre = "m";
			return Messages.noJavaElement;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(
	 * org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent e) {
		if (PluginElements.getPackageExplorer().getTreeViewer().getTree()
				.isFocusControl()) {
			StringBuilder sb = new StringBuilder(getTypeSelection() + " ");
			if (genre.equals("m"))
				sb.append(Messages.selectedM);
			else
				sb.append(Messages.selectedF);
			SpeakingHandler.getInstance().addToQueue(sb.toString());
		}
	}

	/**
	 * This method shutdowns the listeners (Tree and SelectionChanged) from
	 * itself
	 */
	public void shutdown() {
		PackageExplorerPart packageExplorer = PluginElements
				.getPackageExplorer();
		TreeViewer treeViewer = packageExplorer.getTreeViewer();
		treeViewer.removeTreeListener(this);
		treeViewer.removeSelectionChangedListener(this);
	}

	/**
	 * This method starts the listeners (Tree and SelectionChanged) from itself
	 */
	public void start() {
		PackageExplorerPart packageExplorer = PluginElements
				.getPackageExplorer();
		TreeViewer treeViewer = packageExplorer.getTreeViewer();
		treeViewer.addTreeListener(this);
		treeViewer.addSelectionChangedListener(this);
		// load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeViewerListener#treeCollapsed(org.eclipse
	 * .jface.viewers.TreeExpansionEvent)
	 */
	@Override
	public void treeCollapsed(TreeExpansionEvent e) {
		StringBuilder sb = new StringBuilder(getTypeSelection() + " ");
		if (genre.equals("m"))
			sb.append(Messages.collapsedM);
		else
			sb.append(Messages.collapsedF);
		SpeakingHandler.getInstance().addToQueue(sb.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeViewerListener#treeExpanded(org.eclipse
	 * .jface.viewers.TreeExpansionEvent)
	 */
	@Override
	public void treeExpanded(TreeExpansionEvent e) {
		StringBuilder sb = new StringBuilder(getTypeSelection() + " ");
		if (genre.equals("m"))
			sb.append(Messages.expandedM);
		else
			sb.append(Messages.expandedF);
		SpeakingHandler.getInstance().addToQueue(sb.toString());
	}

}