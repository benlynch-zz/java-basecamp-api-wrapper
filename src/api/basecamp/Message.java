package api.basecamp;

import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * 
 * BaseCamp Message Object
 * 
 * @author jon
 *
 */
public class Message extends BaseCampEntity {
	
	private int 		id;
	private String		title;
	private String		body;
	private String		displayBody;
	private Calendar	postedOn;
	private	Calendar	commentedAt;
	private int			projectId;
	private	int			categoryId;
	private	int			authorId;
	private String		authorName;
	private	int			milestoneId;
	private int			commentsCount;
	private	boolean		useTextile;
	private	int			attachmentsCount;
	
	/***
	 * Create new (UNSAVED) BaseCamp message, set appropriate fields and use '.save()' to post the message
	 * @param auth	BCAuth Object
	 */
	public Message(BCAuth auth) {
		super(auth);
		
		this.id = 0;	
	}
	
	/***
	 * 
	 * Get Specific Message according to id	
	 * 
	 * @param auth		BCAuth Object
	 * @param messageId	ID of Message
	 */
	public Message(BCAuth auth, int messageId) {
		super(auth);
		
		Element messageElement = super.get("/posts/"+messageId+".xml");
		
		this.id 			  = ElementValue.getIntValue(messageElement, 	  "id");
		this.title			  = ElementValue.getTextValue(messageElement, 	  "title");
		this.body			  = ElementValue.getTextValue(messageElement, 	  "body");
		this.displayBody	  = ElementValue.getTextValue(messageElement, 	  "display-body");
		this.postedOn		  = ElementValue.getDateTimeValue(messageElement, "posted-on");
		this.commentedAt	  = ElementValue.getDateTimeValue(messageElement, "commented-on");
		this.projectId		  = ElementValue.getIntValue(messageElement, 	  "project-id");
		this.categoryId		  = ElementValue.getIntValue(messageElement, 	  "category-id");
		this.authorId		  = ElementValue.getIntValue(messageElement, 	  "author-id");
		this.authorName		  = ElementValue.getTextValue(messageElement, 	  "author-name");
		this.milestoneId	  = ElementValue.getIntValue(messageElement, 	  "milestone-id");
		this.commentsCount 	  = ElementValue.getIntValue(messageElement, 	  "comments-count");
		this.useTextile		  = ElementValue.getBoolValue(messageElement, 	  "use-textile");
		this.attachmentsCount = ElementValue.getIntValue(messageElement, 	  "attachments-count");
		
	}
	
	Message(BCAuth auth, Element messageElement) {
		super(auth);
		
		this.reconstruct(messageElement);
		
	}
	
	private void reconstruct(Element messageElement) {
		
		this.id 			  = ElementValue.getIntValue(messageElement, 	  "id");
		this.title			  = ElementValue.getTextValue(messageElement, 	  "title");
		this.body			  = ElementValue.getTextValue(messageElement, 	  "body");
		this.displayBody	  = ElementValue.getTextValue(messageElement, 	  "display-body");
		this.postedOn		  = ElementValue.getDateTimeValue(messageElement, "posted-on");
		this.commentedAt	  = ElementValue.getDateTimeValue(messageElement, "commented-on");
		this.projectId		  = ElementValue.getIntValue(messageElement, 	  "project-id");
		this.categoryId		  = ElementValue.getIntValue(messageElement, 	  "category-id");
		this.authorId		  = ElementValue.getIntValue(messageElement, 	  "author-id");
		this.authorName		  = ElementValue.getTextValue(messageElement, 	  "author-name");
		this.milestoneId	  = ElementValue.getIntValue(messageElement, 	  "milestone-id");
		this.commentsCount 	  = ElementValue.getIntValue(messageElement, 	  "comments-count");
		this.useTextile		  = ElementValue.getBoolValue(messageElement, 	  "use-textile");
		this.attachmentsCount = ElementValue.getIntValue(messageElement, 	  "attachments-count");
		
	}
	
	/***
	 * 
	 * Save object to basecamp in current state, or post if new.
	 * 
	 * @param theNotifiedIds	int[] of all ids you wish to notify on post
	 * @return	Boolean
	 */
	public boolean save( int[] theNotifiedIds ) {
		
		String request;
		request = "<request>";
		request += "<post>";
		request += "<category-id>"+this.categoryId+"</category-id>";
		request += "<title>"+this.title+"</title>";
		request += "<body>"+this.body+"</body>";
		request += "<notify-about-changes>0</notify-about-changes>";
		request += "</post>";
		
		for (int i=0 ; i < theNotifiedIds.length ; i++ ) {
			request += "<notify>"+theNotifiedIds[i]+"</notify>";
		}
		
		request += "</request>";
		
		if (this.id == 0) {
		
			int newID = super.post("/projects/"+this.projectId+"/posts.xml", request);

			this.reconstruct(super.get("/projects/"+this.projectId+"/posts/"+newID+".xml"));
			
			return true;
			
		}
		else {
			return super.put("/posts/"+this.id+".xml", request);
		}
	}
	
	/***
	 * 
	 * Save object to basecamp in current state, or post if new.
	 * 
	 * @return	Boolean	Success/Fail
	 */
	public boolean save() {
		
		String request;
		request = "<request>";
		request += "<post>";
		request += "<category-id>"+this.categoryId+"</category-id>";
		request += "<title>"+this.title+"</title>";
		request += "<body>"+this.body+"</body>";
		request += "<notify-about-changes>0</notify-about-changes>";
		request += "</post>";
		request += "</request>";
		
		if (this.id == 0) {
			
			int newID = super.post("/projects/"+this.projectId+"/posts/new.xml", request);
			
			this.reconstruct(super.get("/posts/"+newID+".xml"));
			
			return true;
			
		}
		else {
			return super.put("/posts/"+this.id+".xml", request);
		}
	}
	
	/***
	 * Delete Message from BaseCamp
	 * @return	Boolean	Success/Fail
	 */
	public boolean delete() {
		
		return super.delete("posts/)"+this.id+".xml");
		
	}

	/***
	 * Set Title of Message
	 * @param title	New title content
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/***
	 * Set the Body of the Message
	 * @param body	New body content
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/****
	 * Set the Category ID of the Message
	 * @param categoryId	New category ID
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	/***
	 * 
	 * If project has not been saved to basecamp yet (id == 0) then we will set the proper projectId
	 * to associate this message with upon saving.
	 * 
	 * @param projectId	ID of project you wish to associate this message.
	 */
	public void setProjectId(int projectId) {
		if (this.id == 0) {
			this.projectId = projectId;
		}
	}

	public int getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getBody() {
		return body;
	}


	public String getDisplayBody() {
		return displayBody;
	}


	public Calendar getPostedOn() {
		return postedOn;
	}


	public Calendar getCommentedAt() {
		return commentedAt;
	}


	public int getProjectId() {
		return projectId;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public int getAuthorId() {
		return authorId;
	}


	public String getAuthorName() {
		return authorName;
	}


	public int getMilestoneId() {
		return milestoneId;
	}


	public int getCommentsCount() {
		return commentsCount;
	}


	public boolean isUseTextile() {
		return useTextile;
	}


	public int getAttachmentsCount() {
		return attachmentsCount;
	}

	
	

}
