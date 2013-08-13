package ch.supsi.dti.utils;

import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;

public class PackageInformations {

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
						sb.append(i + Messages.comma + thePackage.getElementName() + Messages.dot);
						sb.append(System.getProperty(Messages.lineSeparator));
				
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
		first.append(Messages.PackageInformations_3 + String.valueOf(i) + Messages.PackageInformations_4);
		first.append(System.getProperty(Messages.lineSeparator));
		return first.toString() + sb.toString();
	}

	public static String getPunctalInfo(JavaProject javaProject,
			String packageName) {
		
		IPackageFragment[] packages;
		StringBuilder sb = new StringBuilder();
		try {
			IPackageFragment thePackage = getPackage(javaProject, packageName);

			
			if(thePackage == null)
				return Messages.PackageInformations_6;
			
			if (thePackage.isOpen())
				sb.append(Messages.PackageInformations_7);
			else
				sb.append(Messages.PackageInformations_8);

			sb.append(System.getProperty(Messages.lineSeparator));
			
			sb.append(Messages.PackageInformations_10 + thePackage.getCompilationUnits().length + Messages.PackageInformations_11);
			sb.append(System.getProperty(Messages.lineSeparator));
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static IPackageFragment getPackage(JavaProject javaProject,
			String packageName) throws JavaModelException {
		IPackageFragment[] packages;
		packages = javaProject.getPackageFragments();

		IPackageFragment thePackage = null;
		for (IPackageFragment thePackageTmp : packages) {
			if (thePackageTmp.getElementName().equals(packageName)){
				thePackage = thePackageTmp;
				break;
			}
		}
		return thePackage;
	}

}
