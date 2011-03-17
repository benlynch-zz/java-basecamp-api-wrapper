package api.basecamp;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp People Objects
 * 
 * @author jondavidjohn
 *
 */
public class People extends BaseCampEntity {
	
	private List<Person> people = new ArrayList<Person>();
	private int personCount = 0;

	/***
	 * Get All People
	 * 
	 * @param auth	BCAuth
	 */
	public People(BCAuth auth) {
		
		super(auth);
		
		Element peopleElement = super.get("/people.xml");
		
		//get entry NodeList
		NodeList nl = peopleElement.getElementsByTagName("person");
		
		for (int i=0;i<nl.getLength();i++) {
			Element personElement = (Element) nl.item(i);
			Person person = new Person(auth, personElement);
			this.people.add(person);
			this.personCount++;
		}
		
		
	}
	
	//TODO - Enable Getting Company/Project People
	
	public List<Person> getPeople() {
		return this.people;
	}
	
	public Person getEntry(int id) {
		return this.people.get(id);
	}
	
	public int getEntryCount() {
		return this.personCount;
	}
}
