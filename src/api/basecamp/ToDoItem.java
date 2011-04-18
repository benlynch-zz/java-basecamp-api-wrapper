package api.basecamp;

import java.util.Calendar;

import org.w3c.dom.Element;

/***
 * 
 * BaseCamp ToDoItem Object
 * 
 * @author benlynch 
 *
 */
public class ToDoItem extends BaseCampEntity {

	private Calendar	commentedAt;
	private int		commentsCount;
	private boolean		completed;
	private String		content;
	private Calendar	createdAt;
	private int		creatorId;
	private Calendar	dueAt;
	private int 		id;
	private int		position;
	private int		toDoListId;
	private Calendar	updatedAt;
	private String		creatorName;
	private int		responsiblePartyId;
	private String          responsiblePartyName;

	/***
	 * Create new (UNSAVED) BaseCamp ToDo Item, set appropriate fields and use '.save()' to post the item 
	 * @param auth	BCAuth Object
	 */
	public ToDoItem(BCAuth auth) {
		super(auth);
		
		this.id = 0;	
	}
	
	/***
	 * 
	 * Get Specific ToDoItem according to id	
	 * 
	 * @param auth		BCAuth Object
	 * @param toDoItemId	ID of ToDoItem 
	 */
	public ToDoItem(BCAuth auth, int toDoItemId) {
		super(auth);
		
		Element toDoItemElement = super.get("/todo_items/"+toDoItemId+".xml");
	
		this.commentedAt 	  = ElementValue.getDateTimeValue(toDoItemElement, 	  "commented-at");
		this.commentsCount	  = ElementValue.getIntValue(toDoItemElement, 	  "comments-count");
		this.completed		  = ElementValue.getBoolValue(toDoItemElement, 	  "completed");
		this.content		  = ElementValue.getTextValue(toDoItemElement, 	  "content");
		this.createdAt		  = ElementValue.getDateTimeValue(toDoItemElement, "created-at");
		this.creatorId		  = ElementValue.getIntValue(toDoItemElement, "creator-id");
		this.dueAt		  = ElementValue.getDateTimeValue(toDoItemElement, 	  "due-at");
		this.id			  = ElementValue.getIntValue(toDoItemElement, 	  "id");
		this.position		  = ElementValue.getIntValue(toDoItemElement, 	  "position");
		this.toDoListId		  = ElementValue.getIntValue(toDoItemElement, 	  "todo-list-id");
		this.updatedAt		  = ElementValue.getDateTimeValue(toDoItemElement, 	  "updated-at");
		this.creatorName 	  = ElementValue.getTextValue(toDoItemElement, 	  "creator-name");
		this.responsiblePartyId	  = ElementValue.getIntValue(toDoItemElement,     "responsible-party-id");
		this.responsiblePartyName = ElementValue.getTextValue(toDoItemElement,     "responsible-party-name");

	}
	
	ToDoItem(BCAuth auth, Element toDoItemElement) {
		super(auth);
		
		this.reconstruct(toDoItemElement);
		
	}
	
	private void reconstruct(Element toDoItemElement) {

		this.commentedAt          = ElementValue.getDateTimeValue(toDoItemElement,        "commented-at");
                this.commentsCount        = ElementValue.getIntValue(toDoItemElement,     "comments-count");
                this.completed            = ElementValue.getBoolValue(toDoItemElement,    "completed");
                this.content              = ElementValue.getTextValue(toDoItemElement,    "content");
                this.createdAt            = ElementValue.getDateTimeValue(toDoItemElement, "created-at");
                this.creatorId            = ElementValue.getIntValue(toDoItemElement, "creator-id");
                this.dueAt                = ElementValue.getDateTimeValue(toDoItemElement,        "due-at");
                this.id                   = ElementValue.getIntValue(toDoItemElement,     "id");
                this.position             = ElementValue.getIntValue(toDoItemElement,     "position");
                this.toDoListId           = ElementValue.getIntValue(toDoItemElement,     "todo-list-id");
                this.updatedAt            = ElementValue.getDateTimeValue(toDoItemElement,        "updated-at");
                this.creatorName          = ElementValue.getTextValue(toDoItemElement,          "creator-name");
                this.responsiblePartyId   = ElementValue.getIntValue(toDoItemElement,     "responsible-party-id");
                this.responsiblePartyName = ElementValue.getTextValue(toDoItemElement,     "responsible-party-name");
	
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
		request += "<content>"+this.content+"</content>";
		request += "<due-at>"+this.dueAt+"</due-at>";
		request += "<responsible-party-id>"+this.responsiblePartyId+"</responsible-party-id>";
		request += "</post>";
		request += "</request>";
		
		if (this.id == 0) {
			
			int newID = super.post("/todo_lists/"+this.toDoListId+"/todo_items/new.xml", request);
			
			this.reconstruct(super.get("/todo_items/"+newID+".xml"));
			
			return true;
			
		}
		else {
			return super.put("/todo_items/"+this.id+".xml", request);
		}
	}
	
	/***
	 * Delete ToDoItem from BaseCamp
	 * @return	Boolean	Success/Fail
	 */
	public boolean delete() {
		
		return super.delete("todo_items/)"+this.id+".xml");
		
	}

	/***
	 * Set due date of a ToDoItem
	 * @param dueAt New due date content
	 */
	public void setDueAt(Calendar dueAt) {
		this.dueAt = dueAt;
	}

	/***
	 * Set the Responsible Party for a ToDoItem
	 * @param responsible	New responsible party
	 */
	public void setResponsiblePartyId(int responsiblePartyId) {
		this.responsiblePartyId = responsiblePartyId;
	}

	/***
	 * 
	 * If project has not been saved to basecamp yet (id == 0) then we will set the proper projectId
	 * to associate this message with upon saving.
	 * 
	 * @param toDoListId	ID of  ToDoList you wish to associate this message.
	 */
	public void setToDoListId(int toDoListId) {
		if (this.id == 0) {
			this.toDoListId = toDoListId;
		}
	}


	public Calendar getCommentedAt() {
		return commentedAt;
	}


	public int getCommentsCount() {
		return commentsCount;
	}


	public boolean isCompleted() {
		return completed;
	}


	public String getContent() {
		return content;
	}


	public Calendar getCreatedAt() {
		return createdAt;
	}


	public int getCreatorId() {
		return creatorId;
	}


	public Calendar getDueAt() {
		return dueAt;
	}


	public int getId() {
		return id;
	}


	public int getPosition() {
		return position;
	}


	public int getToDoListId() {
		return toDoListId;
	}


	public Calendar getUpdatedAt() {
		return updatedAt;
	}


	public String getCreatorName() {
		return creatorName;
	}


	public int getResponsiblePartyId() {
		return responsiblePartyId; 
	}

	 public String getResponsiblePartyName() {
                return responsiblePartyName;
        }

}

