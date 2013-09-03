package ch.supsi.dti.utils;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;

/**
 * Class which retrieves the informations of the pakages in the workspace with
 * static methods
 * 
 * @author Claudio
 * 
 */
public class PackageInformations {

	/**
	 * Returns the general information about the classes in a given project
	 * 
	 * @param javaProject
	 *            the project in which the packages are
	 * @return the string with the informations about the packages
	 */
	public static String getGeneralInfo(JavaProject javaProject) {
		StringBuilder sb = new StringBuilder();
		StringBuilder first = new StringBuilder();

		int i = 0;

		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();

			for (IPackageFragment thePackage : packages) {
				try {
					if (thePackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
						i++;
						sb.append(i + ", " + thePackage.getElementName() + ".");
						sb.append(System.getProperty("line.separator"));

					}
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (i > 1) {
			first.append(Messages.thereAre + " " + String.valueOf(i) + " "
					+ Messages.packages + ".");
		} else {
			first.append(Messages.thereIs + " " + String.valueOf(i) + " "
					+ Messages.thePackage + ".");
		}

		first.append(System.getProperty("line.separator"));
		return first.toString() + sb.toString();
	}

	/**
	 * Returns the package (IPackageFragment) with the package name and the
	 * project in which it is.
	 * 
	 * @param javaProject
	 *            the project in which the package is
	 * @param packageName
	 *            the package name
	 * @return the package in IPackageFragment object
	 * @throws JavaModelException
	 *             exception thrown if there are problems with the JavaModel
	 */
	public static IPackageFragment getPackage(JavaProject javaProject,
			String packageName) throws JavaModelException {
		IPackageFragment[] packages;
		packages = javaProject.getPackageFragments();

		IPackageFragment thePackage = null;
		for (IPackageFragment thePackageTmp : packages) {
			if (thePackageTmp.getElementName().equals(packageName)) {
				thePackage = thePackageTmp;
				break;
			}
		}
		return thePackage;
	}

	/**
	 * Returns the informations about a given pacakge
	 * 
	 * @param javaProject
	 *            the project in which the package is
	 * @param packageName
	 *            the package name
	 * @return the string with the informations about the pacakge
	 */
	public static String getPunctalInfo(JavaProject javaProject,
			String packageName) {

		IPackageFragment[] packages;
		StringBuilder sb = new StringBuilder();
		try {
			IPackageFragment thePackage = getPackage(javaProject, packageName);

			if (thePackage == null)
				return Messages.packageNotFound;

			if (thePackage.isOpen())
				sb.append(Messages.thePackage + " " + Messages.openedM + ".");
			else
				sb.append(Messages.thePackage + " " + Messages.closedM + ".");

			sb.append(System.getProperty("line.separator"));

			if (thePackage.getCompilationUnits().length > 1) {
				sb.append(Messages.thereAre + " "
						+ thePackage.getCompilationUnits().length + " "
						+ Messages.classes + ".");
			} else {
				sb.append(Messages.thereIs + " "
						+ thePackage.getCompilationUnits().length + " "
						+ Messages.theClass + ".");
			}

			sb.append(System.getProperty("line.separator"));
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
