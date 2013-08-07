package ch.supsi.dti.utils;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

public class ClassInformations {

	public static String getGeneralInfo(JavaProject javaProject,
			IPackageFragment thePackage) {
		StringBuilder sb = new StringBuilder();
		StringBuilder first = new StringBuilder();

		int i = 0;

		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();

			for (IPackageFragment mypackage : packages) {
				if (mypackage.equals(thePackage) || thePackage == null){
					for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
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


}
