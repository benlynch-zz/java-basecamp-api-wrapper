package api.basecamp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * 
 * Comment Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class Milestone extends BaseCampEntity {

	private int			id;
	private String		title;
	private Calendar	deadline;
	private boolean		completed;
	private	int			projectId;
	private Calendar	createdOn;
	private	int			creatorId;
	private	String		creatorName;
	private	int			responsiblePartyId;
	private String		responsiblePartyType;
	private	String		responsiblePartyName;
	private Calendar	commentedAt;
	private int			commentCount;
	
	//---  If Completed
	private Calendar	completedOn;
	private int			completerId;
	private String		completerName;
	
	
	/***
	 * 
	 * New and Unsaved Milestone Object.
	 * 
	 * Make sure to set the following before saving
	 * - title
	 * - deadline
	 * - responsiblePartyId
	 * - projectId
	 * 
	 * @param auth	BCAuth Object
	 */
	public Milestone(BCAuth auth) {
		super(auth);
		
		this.id = 0;
	}
	
	/***
	 * 
	 * Build Milestone object from XML Element
	 * (Internal Use Only, Used by "Milestones" Object)
	 * 
	 * @param auth				BCAuth Object
	 * @param milestoneElement	Milesstone XML Element
	 */
	Milestone(BCAuth auth, Element milestoneElement) {
		super(auth);
		
		this.buildObject(milestoneElement);
	}
	
	private void buildObject(Element milestoneElement) {
		
		this.id						= ElementValue.getIntValue(milestoneElement, 		"id");
		this.title					= ElementValue.getTextValue(milestoneElement, 		"title");
		this.deadline				= ElementValue.getDateValue(milestoneElement, 		"deadline");
		this.completed				= ElementValue.getBoolValue(milestoneElement, 		"completed");
		this.projectId				= ElementValue.getIntValue(milestoneElement, 		"project-id");
		this.createdOn				= ElementValue.getDateTimeValue(milestoneElement, 	"created-on");
		this.creatorId				= ElementValue.getIntValue(milestoneElement, 		"creator-id");
		this.creatorName			= ElementValue.getTextValue(milestoneElement, 		"creator-name");
		this.responsiblePartyId		= ElementValue.getIntValue(milestoneElement, 		"responsible-party-id");
		this.responsiblePartyType	= ElementValue.getTextValue(milestoneElement, 		"responsible-party-type");
		this.responsiblePartyName	= ElementValue.getTextValue(milestoneElement, 		"responsible-party-name");
		this.commentedAt			= ElementValue.getDateTimeValue(milestoneElement, 	"commented-at");
		this.commentCount			= ElementValue.getIntValue(milestoneElement, 		"comments-count");
		
		if (this.completed) {
			this.completedOn 	= ElementValue.getDateTimeValue(milestoneElement, "completed-on");
			this.completerId 	= ElementValue.getIntValue(milestoneElement,      "completer-id)");
			this.completerName	= ElementValue.getTextValue(milestoneElement, "completer-name");
		}
	}
	
	/***
	 * 
	 * Save Milestone in Current form
	 * (Rebuilds/Refreshes upon completion)
	 * 
	 * @param notify	Notify people of update?
	 * @return	Success/Fail
	 */
	public boolean save(boolean notify) {
		
		String request;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		boolean returnVal = true;
		
		request  = "<request>";
		request += "<milestone>";
		request += "<title>"+this.title+"</title>";
		request += "<deadline>"+sdf.format(this.deadline)+"</deadline>";
		request += "<responsible-party>"+this.responsiblePartyId+"</responsible-party>";
		request += "<notify>"+((notify) ? "true" : "false")+"</notify>";
		request += "</milestone>";
		request += "</request>";
		
		if (this.completed && this.completedOn == null) {  // newly completed
			if ( ! super.put("/milestones/complete/"+this.id , null)) {
				returnVal = false;
			}
		}
		else if (this.completed == false && this.completedOn != null) { // newly uncompleted
			if ( ! super.put("/milestones/uncomplete/"+this.id, null )) {
				returnVal = false;
			}
		}
		
		if (this.id == 0) {  // New unsaved milestone, need to "create"
		
			int newInt = super.post("/projects/"+this.projectId+"/milestones/create", request);
			
			if (newInt != 0) {
				this.id = newInt;
			}
			else {
				returnVal = false;
			}
		
		}
		else {
			
			if ( super.post("/milestones/update/"+this.id , request) == 0) {
				returnVal = false;
			}
		
		}
		
		// get fresh copy after changes and rebuild
		this.buildObject(super.get("/milestones/"+this.id+".xml"));
		
		return returnVal;
	}


	//--- Getters
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return the deadline
	 */
	public Calendar getDeadline() {
		return deadline;
	}


	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}


	/**
	 * @return the projectId
	 */
	public int getProjectId() {
		return projectId;
	}


	/**
	 * @return the createdOn
	 */
	public Calendar getCreatedOn() {
		return createdOn;
	}


	/**
	 * @return the creatorId
	 */
	public int getCreatorId() {
		return creatorId;
	}


	/**
	 * @return the creatorName
	 */
	public String getCreatorName() {
		return creatorName;
	}


	/**
	 * @return the responsiblePartyId
	 */
	public int getResponsiblePartyId() {
		return responsiblePartyId;
	}


	/**
	 * @return the responsiblePartyType
	 */
	public String getResponsiblePartyType() {
		return responsiblePartyType;
	}


	/**
	 * @return the responsiblePartyName
	 */
	public String getResponsiblePartyName() {
		return responsiblePartyName;
	}


	/**
	 * @return the commentedAt
	 */
	public Calendar getCommentedAt() {
		return commentedAt;
	}


	/**
	 * @return the commentCount
	 */
	public int getCommentCount() {
		return commentCount;
	}


	/**
	 * @return the completedOn
	 */
	public Calendar getCompletedOn() {
		return completedOn;
	}


	/**
	 * @return the completerId
	 */
	public int getCompleterId() {
		return completerId;
	}


	/**
	 * @return the completerName
	 */
	public String getCompleterName() {
		return completerName;
	}

	//--- Setters

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Calendar deadline) {
		this.deadline = deadline;
	}


	/**
	 * @param responsiblePartyId the responsiblePartyId to set
	 */
	public void setResponsiblePartyId(int responsiblePartyId) {
		this.responsiblePartyId = responsiblePartyId;
	}

	/**
	 * 
	 * Only allows if Milestone ID == 0 (unsaved)
	 * 
	 * @param projectId the projectId to set
	 */
	public void setProjectId(int projectId) {
		if (this.id == 0) {
			this.projectId = projectId;
	
		}
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}

