package ch.supsi.dti.utils;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import ch.supsi.dti.multilanguage.Messages;

public class ProjectInformations {
	
	public static String getGeneralInfo(){
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject[] projects = root.getProjects();
		StringBuilder sb = new StringBuilder();
		
		sb.append(Messages.thereAre + " " + projects.length + " " + Messages.projects + ".");
		sb.append(System.getProperty("line.separator"));
		int i = 1;
		for (IProject project : projects) {
			
			sb.append(i + ", " + project.getName() + ".");
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
		
		if(project == null)
			return Messages.natureProject;
		
		if (project.isOpen())
			sb.append(Messages.project + " " + Messages.openedM + ".");
		else
			sb.append(Messages.project + " " + Messages.closedM + ".");
		
		sb.append(System.getProperty("line.separator"));
		
		try {
			
			IProjectDescription projectDescription = project.getDescription();
			
			String[] natures = projectDescription.getNatureIds();
			
			if(natures.length > 0)
				sb.append(Messages.natureProject + " ");
			for (int i = 0; i < natures.length; i++) {
				sb.append(natures[i] + ".");

			}
			
			
			String projectDescriptionComment = projectDescription.getComment();
			if(projectDescriptionComment != null)
				sb.append(projectDescriptionComment);
			
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
			
			
		return sb.toString();
	}
	
}
