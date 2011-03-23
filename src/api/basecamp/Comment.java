package api.basecamp;

import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * 
 * Comment Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class Comment extends BaseCampEntity{

	private int			id;
	private int			authorId;
	private String		authorName;
	private int			commentableId;
	private String		commentableType;
	private String		body;
	private	Calendar	createdAt;
	private int			attachmentCount;
	
	/***
	 * Build Comment from comment ID
	 * 
	 * @param auth		BCAuth Object
	 * @param commentId ID of desired comment
	 */
	public Comment(BCAuth auth, int commentId) {
		super(auth);
		
		Element commentElement = super.get("/comments/"+commentId+".xml");
		
		this.id					= ElementValue.getIntValue(commentElement, 		"id");
		this.authorId			= ElementValue.getIntValue(commentElement, 		"author-id");
		this.authorName 		= ElementValue.getTextValue(commentElement, 	"author-name");
		this.commentableId		= ElementValue.getIntValue(commentElement,  	"commentable-id");
		this.commentableType	= ElementValue.getTextValue(commentElement, 	"commentable-type");
		this.body				= ElementValue.getTextValue(commentElement, 	"body");
		this.createdAt			= ElementValue.getDateTimeValue(commentElement, "created-at");
		this.attachmentCount	= ElementValue.getIntValue(commentElement,      "attachments-count");
		
	}
	
	/***
	 * 
	 * Add comment to BaseCamp resource and Build Comment object
	 * 
	 * @param auth			BCAuth
	 * @param resourceType	Resource Type String - example "todo_item", "posts" , "milestones" 
	 * @param resourceId	id of resource
	 * @param body			String body of comment
	 */
	public Comment(BCAuth auth, String resourceType, int resourceId, String body) {
		super(auth);
		
		String request;
		
		this.body = body;
		
		request = this.postString();
		
		int commentId = super.post("/"+resourceType+"/"+resourceId+"/comments.xml", request);
		
		Element commentElement = super.get("/comments/"+commentId+".xml");
		
		this.id					= ElementValue.getIntValue(commentElement, 		"id");
		this.authorId			= ElementValue.getIntValue(commentElement, 		"author-id");
		this.authorName 		= ElementValue.getTextValue(commentElement, 	"author-name");
		this.commentableId		= ElementValue.getIntValue(commentElement,  	"commentable-id");
		this.commentableType	= ElementValue.getTextValue(commentElement, 	"commentable-type");
		this.body				= ElementValue.getTextValue(commentElement, 	"body");
		this.createdAt			= ElementValue.getDateTimeValue(commentElement, "created-at");
		this.attachmentCount	= ElementValue.getIntValue(commentElement,      "attachments-count");
		
	}
	
	/***
	 * Build Comment from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth				BCAuth Object
	 * @param projectElement	XML Element representation of Comment
	 */
	Comment(BCAuth auth, Element commentElement) {
		super(auth);
		
		this.id					= ElementValue.getIntValue(commentElement, 		"id");
		this.authorId			= ElementValue.getIntValue(commentElement, 		"author-id");
		this.authorName 		= ElementValue.getTextValue(commentElement, 	"author-name");
		this.commentableId		= ElementValue.getIntValue(commentElement,  	"commentable-id");
		this.commentableType	= ElementValue.getTextValue(commentElement, 	"commentable-type");
		this.body				= ElementValue.getTextValue(commentElement, 	"body");
		this.createdAt			= ElementValue.getDateTimeValue(commentElement, "created-at");
		this.attachmentCount	= ElementValue.getIntValue(commentElement,      "attachments-count");
	}

	private String postString() {
		
		String request;
		
		request  = "<comment>";
		request += "<body>"+this.body+"</body>";
		request += "</comment>";
		
		return request;
	}
	
	/***
	 * Save Current state of Comment
	 * @return	Success/Fail
	 */
	public boolean save() {
		
		String request = this.postString();
		
		return super.put("/comments/"+this.id+".xml", request);
		
	}
	
	/***
	 * Delete Comment
	 * @return	Success/Fail
	 */
	public boolean delete() {
		
		return super.delete("/comments/"+this.id+".xml");
		
	}
	
	//--- Getters

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the authorId
	 */
	public int getAuthorId() {
		return authorId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @return the commentableId
	 */
	public int getCommentableId() {
		return commentableId;
	}

	/**
	 * @return the commentableType
	 */
	public String getCommentableType() {
		return commentableType;
	}

	/**
	 * @return the createdAt
	 */
	public Calendar getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the attachmentCount
	 */
	public int getAttachmentCount() {
		return attachmentCount;
	}
	
	//--- Setters
	
	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}
	
}
