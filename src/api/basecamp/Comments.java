package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp Comment Objects
 * 
 * @author jondavidjohn
 *
 */
public class Comments extends BaseCampEntity{
	
	private List<Comment> items = new ArrayList<Comment>();
	private int commentCount = 0;

	/***
	 * 
	 * Get All Comments for given resource (up to 50)
	 * 
	 * @param auth	BCAuth
	 * @param resourceType	String	type of resource example... "posts" , "milestones" , "todo_items"
	 * @param resourceId	int		id of resource
	 */
	public Comments(BCAuth auth, String resourceType, int resourceId) {
		super(auth);
		
		Element commentsElement = super.get("/"+resourceType+"/"+resourceId+"/comments.xml");
		//get NodeList
		NodeList nl = commentsElement.getElementsByTagName("comment");
		
		for (int i=0;i<nl.getLength();i++) {
			Element commentElement = (Element) nl.item(i);
			Comment comment = new Comment(auth, commentElement);
			this.items.add(comment);
			this.commentCount++;
		}
		
	}
	/***
	 * 
	 * @return List<Comment> collection of Comment objects
	 */
	public List<Comment> getComments() {
		return this.items;
	}
	
	/***
	 * Get comment according to index
	 * @param index	index of Comment
	 * @return	Comment
	 */
	public Comment getComment(int index) {
		return this.items.get(index);
	}
	
	/***
	 * 
	 * @return count of Comments in collection
	 */
	public int getCommentCount() {
		return this.commentCount;
	}

}
