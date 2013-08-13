package ch.supsi.dti.parser;

import java.lang.reflect.Method;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;

public class MethodInformations {

	public static String getGeneralInfo(ICompilationUnit theClass) {
		StringBuilder first = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		try {

			for (IType type : theClass.getAllTypes()) {
				IMethod[] methods = type.getMethods();

				for (IMethod method : methods) {
					i++;
					sb.append(i + Messages.comma + method.getElementName());
					sb.append(System.getProperty(Messages.lineSeparator));
				}

			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		first.append(Messages.MethodInformations_2 + String.valueOf(i) + Messages.MethodInformations_3);
		first.append(System.getProperty(Messages.lineSeparator));
		return first.toString() + sb.toString();
	}

	public static String getPunctalInfo(ICompilationUnit theClass,
			String methodName) {

		StringBuilder sb = new StringBuilder();
		IMethod method;

		try {
			method = getMethod(theClass, methodName);
		} catch (MethodNotFoundException e) {
			return Messages.MethodInformations_5;
		}

		try {
			if(method.isConstructor()){
				sb.append(Messages.MethodInformations_6);
				sb.append(System.getProperty(Messages.lineSeparator));
			}
			
			if(method.isMainMethod()){
				sb.append(Messages.MethodInformations_8);
				sb.append(System.getProperty(Messages.lineSeparator));
			}
			
			sb.append(Messages.MethodInformations_10 + method.getSignature() + Messages.dot);
			sb.append(System.getProperty(Messages.lineSeparator));
			sb.append(Messages.MethodInformations_13 + method.getReturnType() + Messages.dot);
			sb.append(System.getProperty(Messages.lineSeparator));
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return sb.toString();
	}

	public static IMethod getMethod(ICompilationUnit theClass, String methodName)
			throws MethodNotFoundException {
		try {
			for (IType type : theClass.getAllTypes()) {
				IMethod[] methods = type.getMethods();

				for (IMethod tmpMethod : methods) {
					if (tmpMethod.getElementName().equals(methodName))
						return tmpMethod;
				}

			}
		} catch (JavaModelException e) {
			throw new MethodNotFoundException();
		}
		throw new MethodNotFoundException();
	}

}
