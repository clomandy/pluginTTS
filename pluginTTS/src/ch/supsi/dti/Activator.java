package ch.supsi.dti;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import ch.supsi.dti.listeners.ActiveEditorListener;
import ch.supsi.dti.listeners.ChangeSelectionOnPackageExplorerLisener;
import ch.supsi.dti.listeners.JavaChangeListener;
import ch.supsi.dti.listeners.PackageExplorerSelectionListener;
import ch.supsi.dti.utils.PluginElements;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	/**
	 * The shared instance
	 */
	private static Activator plugin;

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "pluginTTS"; //$NON-NLS-1$

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * The constructor, it active the four listeners (JavaChange,
	 * PackageExplorer, ActiveEditor and ChangeSelectionOnPackageExplorer).
	 */
	public Activator() {
		JavaChangeListener.getInstance().start();
		PackageExplorerSelectionListener.getInstance().start();
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.addPostSelectionListener(new ActiveEditorListener());
		PluginElements.getOutline().addSelectionChangedListener(
				new ChangeSelectionOnPackageExplorerLisener());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

}
