package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp Category Objects
 * 
 * @author jondavidjohn
 *
 */
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
	
	/**
	 * @return Count of Category Objects
	 */
	public int getCategoryCount() {
		return this.categoryCount;
	}
	
	/**
	 * @return List of Project Objects
	 */
	public List<Category> getCategories() {
		return this.items;
	}
	
	/**
	 * @param	index	index of Category Object
	 * @return List of Category Objects
	 */
	public Category getCategory(int index) {
		return this.items.get(index);
	}
}
