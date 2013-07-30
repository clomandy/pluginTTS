package ch.supsi.dti.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

public class ProjectInformations {
	
	public static String getGeneralInfo(){
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		StringBuilder sb = new StringBuilder();
		
		sb.append("There are " + projects.length + " project.");
		sb.append(System.getProperty("line.separator"));
		int i = 1;
		for (IProject project : projects) {
			
			sb.append(i + ", " + project.getName() +".");
			sb.append(System.getProperty("line.separator"));
			i++;
		}
		
		
		return sb.toString();
		
	}
	
	public static String getPunctalInfo(String projectName){
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		StringBuilder sb = new StringBuilder();
		
		
		return sb.toString();
	}

}
