package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Categories extends BaseCampEntity{

	private List<Category> items = new ArrayList<Category>();
	private int			   categoryCount;
	
	/***
	 * 
	 * Get All Categories for a project
	 * 
	 * @param auth		BCAuth
	 * @param projectId	ID of Project
	 */
	public Categories(BCAuth auth, int projectId) {
		super(auth);
		
		Element projectsElement = super.get("/projects/"+projectId+"/categories.xml");
		//get entry NodeList
		NodeList nl = projectsElement.getElementsByTagName("category");
		
		for (int i=0;i<nl.getLength();i++) {
			Element catElement = (Element) nl.item(i);
			Category category = new Category(auth, catElement);
			this.items.add(category);
			this.categoryCount++;
		}
	}
	
	/***
	 * 
	 * Get All Categories for a project with Type option
	 * 
	 * @param auth		BCAuth
	 * @param projectId	ID of Project
	 * @param type		[ Category.POST || Category.ATTACH ]
	 */
	public Categories(BCAuth auth, int projectId, boolean type) {
		super(auth);
		
		String typeOption = "?type="+( type ? "post" : "attachment");
		
		Element projectsElement = super.get("/projects/"+projectId+"/categories.xml"+typeOption);
		//get entry NodeList
		NodeList nl = projectsElement.getElementsByTagName("category");
		
		for (int i=0;i<nl.getLength();i++) {
			Element catElement = (Element) nl.item(i);
			Category category = new Category(auth, catElement);
			this.items.add(category);
			this.categoryCount++;
		}
	}
	
	public int getCategoryCount() {
		return this.categoryCount;
	}

	public List<Category> getCategories() {
		return this.items;
	}
	
	public Category getCompany(int id) {
		return this.items.get(id);
	}
}
