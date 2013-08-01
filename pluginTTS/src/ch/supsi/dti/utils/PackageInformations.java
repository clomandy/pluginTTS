package ch.supsi.dti.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.PackageFragment;

public class PackageInformations {
	
	public static String getGeneralInfo(JavaProject javaProject){
		StringBuilder sb = new StringBuilder();
		try {
			IPackageFragment[] packages = javaProject.getPackageFragments();
		
		
		sb.append("There are "+ packages.length + " packages:");
		sb.append(System.getProperty("line.separator"));
		int i = 1;
		
			
		
		for (IPackageFragment thePackage : packages) {
			try {
				if(thePackage.getKind() == IPackageFragmentRoot.K_SOURCE){
					sb.append(i + ", " + thePackage.getElementName() +".");
					sb.append(System.getProperty("line.separator"));
					i++;
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
		return sb.toString();
	}
	
//	public String getPunctalInfo(String projectName){
//	}

}
