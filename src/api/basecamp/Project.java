package api.basecamp;

import java.util.Calendar;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * Project Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class Project extends BaseCampEntity {

	private int 		id;
	private String 		name;
	private Calendar 	createdOn;
	private String 		status;
	private Calendar 	lastChangedOn;
	private int 		companyId;
	private String		companyName;
	
	/***
	 * Build Project from project ID
	 * 
	 * @param auth		BCAuth Object
	 * @param projectId ID of desired project
	 */
	public Project(BCAuth auth, int projectId) {
		
		super(auth);
		
		Element projectElement = super.get("/projects/"+projectId+".xml");
		
		this.id				= ElementValue.getIntValue(projectElement,  "id");
		this.name 			= ElementValue.getTextValue(projectElement, "name");
		this.createdOn 		= ElementValue.getDateTimeValue(projectElement, "created-on");
		this.status 		= ElementValue.getTextValue(projectElement, "status");
		this.lastChangedOn 	= ElementValue.getDateTimeValue(projectElement, "last-changed-on");
		
		//get 'subscription' sub element
		NodeList nl 		   = projectElement.getElementsByTagName("company");
		Element companyElement = (Element)nl.item(0);
		
		this.companyId 	 = ElementValue.getIntValue(companyElement, "id");
		this.companyName = ElementValue.getTextValue(companyElement, "name");

	}
	
	/***
	 * 
	 * Create and Post new Project
	 * 
	 * @param auth			BCAuth Object
	 * @param projectName	String Name of new Project
	 */
	public Project(BCAuth auth, String projectName) {
		super(auth);

		String request;
		
		request  = "<request>";
		request += "<project>";
		request += "<name>"+projectName+"</name>";
		request += "</project>";
		request += "</request>";
		
		int createdId = super.post("/projects.xml", request);
		
		Element projectElement = super.get("/projects/"+createdId+".xml");
		
		this.id				= ElementValue.getIntValue(projectElement,  "id");
		this.name 			= ElementValue.getTextValue(projectElement, "name");
		this.createdOn 		= ElementValue.getDateTimeValue(projectElement, "created-on");
		this.status 		= ElementValue.getTextValue(projectElement, "status");
		this.lastChangedOn 	= ElementValue.getDateTimeValue(projectElement, "last-changed-on");
		
		//get 'subscription' sub element
		NodeList nl 		   = projectElement.getElementsByTagName("company");
		Element companyElement = (Element)nl.item(0);
		
		this.companyId 	 = ElementValue.getIntValue(companyElement, "id");
		this.companyName = ElementValue.getTextValue(companyElement, "name");
		
	}
	
	/***
	 * Build Project from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth				BCAuth Object
	 * @param projectElement	XML Element representation of Project
	 */
	Project(BCAuth auth, Element projectElement) {
		
		super(auth);
				
		this.id				= ElementValue.getIntValue(projectElement,  "id");
		this.name 			= ElementValue.getTextValue(projectElement, "name");
		this.createdOn 		= ElementValue.getDateTimeValue(projectElement, "created-on");
		this.status 		= ElementValue.getTextValue(projectElement, "status");
		this.lastChangedOn 	= ElementValue.getDateTimeValue(projectElement, "last-changed-on");
		
		//get 'subscription' sub element
		NodeList nl 		   = projectElement.getElementsByTagName("company");
		Element companyElement = (Element)nl.item(0);
		
		this.companyId 	 = ElementValue.getIntValue(companyElement, "id");
		this.companyName = ElementValue.getTextValue(companyElement, "name");

	}
	
	//--- Getters

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		return createdOn;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the lastChangedOn
	 */
	public Calendar getLastChangedOn() {
		return lastChangedOn;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	


}
