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
		
		sb.append(Messages.ProjectInformations_0 + projects.length + Messages.ProjectInformations_1);
		sb.append(System.getProperty(Messages.lineSeparator));
		int i = 1;
		for (IProject project : projects) {
			
			sb.append(i + Messages.comma + project.getName() +Messages.dot);
			sb.append(System.getProperty(Messages.lineSeparator));
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
			return Messages.ProjectInformations_6;
		
		if (project.isOpen())
			sb.append(Messages.ProjectInformations_7);
		else
			sb.append(Messages.ProjectInformations_8);
		
		sb.append(System.getProperty(Messages.lineSeparator));
		
		try {
			
			IProjectDescription projectDescription = project.getDescription();
			
			String[] natures = projectDescription.getNatureIds();
			
			if(natures.length > 0)
				sb.append(Messages.ProjectInformations_10);
			for (int i = 0; i < natures.length; i++) {
				sb.append(natures[i] + Messages.dot);

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
