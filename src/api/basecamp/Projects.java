package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp Project Objects
 * 
 * @author jondavidjohn
 *
 */
public class Projects extends BaseCampEntity{
	
	private List<Project> items = new ArrayList<Project>();
	private int projectCount = 0;

	/***
	 * 
	 * Get All Projects
	 * 
	 * @param auth	BCAuth
	 */
	public Projects(BCAuth auth) {
		super(auth);
		
		Element projectsElement = super.get("/projects.xml");
		//get entry NodeList
		NodeList nl = projectsElement.getElementsByTagName("project");
		
		for (int i=0;i<nl.getLength();i++) {
			Element projectElement = (Element) nl.item(i);
			Project project = new Project(auth, projectElement);
			this.items.add(project);
			this.projectCount++;
		}
		
	}
	
	public List<Project> getProjects() {
		return this.items;
	}
	
	public Project getProject(int id) {
		return this.items.get(id);
	}
	
	public int getProjectCount() {
		return this.projectCount;
	}
	
	public List<Project> getActive() {
		
		List<Project> returnList = new ArrayList<Project>();
		
		for (int i=0; i<this.items.size(); i++) {
			Project project = this.items.get(i);
			
			if (project.getStatus() == "active") {
				returnList.add(project);
			}
		}
		
		return returnList;
	}
	
	public List<Project> getOnHold() {
		
		List<Project> returnList = new ArrayList<Project>();
		
		for (int i=0; i<this.items.size(); i++) {
			Project project = this.items.get(i);
			
			if (project.getStatus() == "on-hold") {
				returnList.add(project);
			}
		}
		
		return returnList;
	}
	
	public List<Project> getArchived() {
		
		List<Project> returnList = new ArrayList<Project>();
		
		for (int i=0; i<this.items.size(); i++) {
			Project project = this.items.get(i);
			
			if (project.getStatus() == "archived") {
				returnList.add(project);
			}
		}
		
		return returnList;
	}
}
