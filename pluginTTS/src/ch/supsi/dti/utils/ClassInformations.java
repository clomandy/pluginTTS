package ch.supsi.dti.utils;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

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
						sb.append(i + ", " + unit.getElementName());
						sb.append(System.getProperty("line.separator"));
					}
				}
			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		first.append("There are " + String.valueOf(i) + " classes:");
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
			return "Multiple class founded, reduce the selection!";
		}

		if(theClass == null)
			return "No class with the name "+ className + ", expands the selection!";
		
		if(theClass.isOpen()){
			sb.append("It is Opened.");
			sb.append(System.getProperty("line.separator"));
		}else{
			sb.append("It is Closed.");
			sb.append(System.getProperty("line.separator"));
		}
		
		if(theClass.isReadOnly()){
			sb.append("It is read-only");
			sb.append(System.getProperty("line.separator"));
		}
		
		try {
			int numberOfMethods = 0;
			for (IType type : theClass.getAllTypes()) {
				numberOfMethods += type.getMethods().length;
				
			}
			sb.append("It has " + numberOfMethods + " methods");
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

}
