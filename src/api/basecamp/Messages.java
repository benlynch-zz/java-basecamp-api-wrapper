package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp Company Objects
 * 
 * @author jondavidjohn
 *
 */
public class Messages extends BaseCampEntity{
	
	private List<Message> items = new ArrayList<Message>();
	private int messageCount = 0;

	/***
	 * Gets All Category Messages
	 * 
	 * @param auth	BCAuth Object
	 */
	public Messages(BCAuth auth, int projectId, int categoryId) {
		super(auth);
		
		Element messagesElement = super.get("/projects/"+projectId+"/cat/"+categoryId+"/posts.xml");
		
		//get entry NodeList
		NodeList nl = messagesElement.getElementsByTagName("message");
		
		for (int i=0;i<nl.getLength();i++) {
			Element messageElement = (Element) nl.item(i);
			Message message = new Message(auth, messageElement);
			this.items.add(message);
			this.messageCount++;
		}
		
		
	}
	
	/***
	 * Gets Messages associated with given project.
	 * 
	 * @param auth		BCAuth Object
	 * @param projectId	ID of project
	 */
	public Messages(BCAuth auth, int projectId) {
		super(auth);
		
		Element messagesElement = super.get("project/"+projectId+"/posts.xml");
		
		//get entry NodeList
		NodeList nl = messagesElement.getElementsByTagName("message");
		
		for (int i=0;i<nl.getLength();i++) {
			Element messageElement = (Element) nl.item(i);
			Message message = new Message(auth, messageElement);
			this.items.add(message);
			this.messageCount++;
		}
	}
	
	public List<Message> getItems() {
		return this.items;
	}
	
	public Message getMessage(int id) {
		return this.items.get(id);
	}
	
	public int getMessageCount() {
		return this.messageCount;
	}
}

