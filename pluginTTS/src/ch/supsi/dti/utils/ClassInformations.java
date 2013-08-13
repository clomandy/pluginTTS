package ch.supsi.dti.utils;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.parser.ClassMultipleException;

public class ClassInformations {

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
						sb.append(i + Messages.comma + unit.getElementName());
						sb.append(System.getProperty(Messages.lineSeparator));
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		first.append(Messages.ClassInformations_2 + String.valueOf(i) + Messages.ClassInformations_3);
		first.append(System.getProperty(Messages.lineSeparator));
		return first.toString() + sb.toString();
	}

	public static String getPunctalInfo(JavaProject javaProject,
			IPackageFragment thePackage, String className) {

		StringBuilder sb = new StringBuilder();
		ICompilationUnit theClass;
		try {
			theClass = getClass(javaProject, thePackage, className);
		} catch (ClassMultipleException e) {
			return Messages.ClassInformations_5;
		}

		if(theClass == null)
			return Messages.ClassInformations_6+ className + Messages.ClassInformations_7;
		
		if(theClass.isOpen()){
			sb.append(Messages.ClassInformations_8);
			sb.append(System.getProperty(Messages.lineSeparator));
		}else{
			sb.append(Messages.ClassInformations_10);
			sb.append(System.getProperty(Messages.lineSeparator));
		}
		
		if(theClass.isReadOnly()){
			sb.append(Messages.ClassInformations_12);
			sb.append(System.getProperty(Messages.lineSeparator));
		}
		
		try {
			int numberOfMethods = 0;
			for (IType type : theClass.getAllTypes()) {
				numberOfMethods += type.getMethods().length;
				
			}
			sb.append(Messages.ClassInformations_14 + numberOfMethods + Messages.ClassInformations_15);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static ICompilationUnit getClass(JavaProject javaProject,
			IPackageFragment thePackage, String className) throws ClassMultipleException {
		ICompilationUnit theClass = null;

		int i = 0;
		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();

			for (IPackageFragment mypackage : packages) {
				if (mypackage.equals(thePackage) || thePackage == null) {
					for (ICompilationUnit unit : mypackage
							.getCompilationUnits()) {
						if (unit.getElementName().equals(className + Messages.ClassInformations_16)) {
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

}
