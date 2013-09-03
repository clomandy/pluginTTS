package ch.supsi.dti.utils;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.parser.ClassMultipleException;

public class ClassInformations {

	public static ICompilationUnit getClass(JavaProject javaProject,
			IPackageFragment thePackage, String className)
			throws ClassMultipleException {
		ICompilationUnit theClass = null;

		int i = 0;
		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();

			for (IPackageFragment mypackage : packages) {
				if (mypackage.equals(thePackage) || thePackage == null) {
					for (ICompilationUnit unit : mypackage
							.getCompilationUnits()) {
						if (unit.getElementName().equals(className + ".java")) {
							if (i > 1) {
								throw new ClassMultipleException();
							} else {
								theClass = unit;
								i++;
							}
						}

					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return theClass;
	}

	public static String getGeneralInfo(JavaProject javaProject,
			IPackageFragment thePackage) {
		StringBuilder sb = new StringBuilder();
		StringBuilder first = new StringBuilder();

		int i = 0;

		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();

			for (IPackageFragment mypackage : packages) {
				if (mypackage.equals(thePackage) || thePackage == null) {
					for (ICompilationUnit unit : mypackage
							.getCompilationUnits()) {
						i++;
						sb.append(i + ", " + unit.getElementName() + ".");
						sb.append(System.getProperty("line.separator"));
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (i > 1) {
			first.append(Messages.thereAre + " " + String.valueOf(i) + " "
					+ Messages.classes + ".");
		} else {
			first.append(Messages.thereIs + " " + String.valueOf(i) + " "
					+ Messages.theClass + ".");
		}
		first.append(System.getProperty("line.separator"));
		return first.toString() + sb.toString();
	}

	public static String getPunctalInfo(JavaProject javaProject,
			IPackageFragment thePackage, String className) {

		StringBuilder sb = new StringBuilder();
		ICompilationUnit theClass;
		try {
			theClass = getClass(javaProject, thePackage, className);
		} catch (ClassMultipleException e) {
			return Messages.multipleClass;
		}

		if (theClass == null)
			return Messages.noClass + className + ", " + Messages.expand;

		if (theClass.isOpen()) {
			sb.append(Messages.theClass + " " + Messages.openedF + ".");
			sb.append(System.getProperty("line.separator"));
		} else {
			sb.append(Messages.theClass + " " + Messages.closedF + ".");
			sb.append(System.getProperty("line.separator"));
		}

		if (theClass.isReadOnly()) {
			sb.append(Messages.theClass + " " + Messages.readOnly + ".");
			sb.append(System.getProperty("line.separator"));
		}

		try {
			int numberOfMethods = 0;
			for (IType type : theClass.getAllTypes()) {
				numberOfMethods += type.getMethods().length;

			}
			sb.append(Messages.itHas + " " + numberOfMethods + " "
					+ Messages.methods + ".");
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
