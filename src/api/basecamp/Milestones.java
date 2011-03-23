package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp Milestone Objects
 * 
 * @author jondavidjohn
 *
 */
public class Milestones extends BaseCampEntity{
	
	private List<Milestone> items = new ArrayList<Milestone>();
	private int milestoneCount = 0;

	/***
	 * 
	 * Get All Milestones for a project
	 * 
	 * @param auth		BCAuth Object
	 * @param projectId	ID of Project
	 */
	public Milestones(BCAuth auth, int projectId) {
		super(auth);
		
		Element milestonesElement = super.get("/milestones.xml");
		//get entry NodeList
		NodeList nl = milestonesElement.getElementsByTagName("milestone");
		
		for (int i=0;i<nl.getLength();i++) {
			Element milestoneElement = (Element) nl.item(i);
			Milestone milestone = new Milestone(auth, milestoneElement);
			this.items.add(milestone);
			this.milestoneCount++;
		}
		
	}
	
	/**
	 * @return List object of Milestones	
	 */
	public List<Milestone> getMilestones() {
		return this.items;
	}
	
	/**
	 * @param 	index	Index of specific Milestone
	 * @return 	Specific Milestone for index
	 */
	public Milestone getMilestone(int index) {
		return this.items.get(index);
	}
	
	/**
	 * @return Count of Milestone Objects
	 */
	public int getMilestoneCount() {
		return this.milestoneCount;
	}
	
}
