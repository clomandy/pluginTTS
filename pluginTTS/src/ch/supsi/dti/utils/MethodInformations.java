package ch.supsi.dti.utils;

import java.lang.reflect.Method;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaProject;

import ch.supsi.dti.multilanguage.Messages;
import ch.supsi.dti.parser.MethodNotFoundException;

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
					sb.append(i + ", " + method.getElementName() + ".");
					sb.append(System.getProperty("line.separator"));
				}

			}
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		first.append(Messages.thereAre + " " + String.valueOf(i) + " " + Messages.methods + ".");
		first.append(System.getProperty("line.separator"));
		return first.toString() + sb.toString();
	}

	public static String getPunctalInfo(ICompilationUnit theClass,
			String methodName) {

		StringBuilder sb = new StringBuilder();
		IMethod method;

		try {
			method = getMethod(theClass, methodName);
		} catch (MethodNotFoundException e) {
			return Messages.methodNotFoundInClass;
		}

		try {
			if(method.isConstructor()){
				sb.append(Messages.constuctor + ".");
				sb.append(System.getProperty("line.separator"));
			}
			
			if(method.isMainMethod()){
				sb.append(Messages.mainMethod + ".");
				sb.append(System.getProperty("line.separator"));
			}
			
			sb.append(Messages.signature + " " + method.getSignature() + ".");
			sb.append(System.getProperty("line.separator"));
			sb.append(Messages.returnType + " " + method.getReturnType() + ".");
			sb.append(System.getProperty("line.separator"));
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
