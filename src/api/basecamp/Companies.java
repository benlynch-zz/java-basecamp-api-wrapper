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
public class Companies extends BaseCampEntity{
	
	private List<Company> items = new ArrayList<Company>();
	private int companyCount = 0;

	/***
	 * Gets All Companies
	 * 
	 * @param auth	BCAuth Object
	 */
	public Companies(BCAuth auth) {
		super(auth);
		
		Element companiesElement = super.get("/companies.xml");
		
		//get entry NodeList
		NodeList nl = companiesElement.getElementsByTagName("company");
		
		for (int i=0;i<nl.getLength();i++) {
			Element companyElement = (Element) nl.item(i);
			Company company = new Company(auth, companyElement);
			this.items.add(company);
			this.companyCount++;
		}
		
		
	}
	
	/***
	 * Gets Companies associated with given project.
	 * 
	 * @param auth		BCAuth Object
	 * @param projectId	ID of project
	 */
	public Companies(BCAuth auth, int projectId) {
		super(auth);
		
		Element companiesElement = super.get("project/"+projectId+"/companies.xml");
		
		//get entry NodeList
		NodeList nl = companiesElement.getElementsByTagName("company");
		
		for (int i=0;i<nl.getLength();i++) {
			Element companyElement = (Element) nl.item(i);
			Company company = new Company(auth, companyElement);
			this.items.add(company);
			this.companyCount++;
		}
	}
	
	/**
	 * @return List of Category Objects
	 */
	public List<Company> getCompanies() {
		return this.items;
	}
	
	/**
	 * @param	index	index of Category Object
	 * @return List of Category Objects
	 */
	public Company getCompany(int index) {
		return this.items.get(index);
	}
	
	/**
	 * @return Count of Category Objects
	 */
	public int getCompanyCount() {
		return this.companyCount;
	}
}

