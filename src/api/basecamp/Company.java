package api.basecamp;

import org.w3c.dom.Element;

/***
 * Company Object for use with BaseCamp API
 * 
 * @author jondavidjohn
 *
 */
public class Company extends BaseCampEntity{
	
	private int 	id;
	private String 	name;
	private String 	address1;
	private String 	address2;
	private String 	city;
	private String 	state;
	private int		zip;
	private String 	country;
	private String 	webAddress;
	private String 	phoneOffice;
	private String 	phoneFax;
	private String 	timeZoneId;
	private boolean canSeePrivate;

	/***
	 * Company Constructor
	 * 
	 * Builds Company Object from ID.
	 * 
	 * @param auth		BaseCamp Auth Object
	 * @param companyId	ID of company
	 */
	public Company(BCAuth auth, int companyId) {
		
		super(auth);
		
		Element companyElement = super.get("/companies/"+companyId+".xml");
		
		this.id				= companyId;
		this.name 			= ElementValue.getTextValue(companyElement, "name");
		this.address1 		= ElementValue.getTextValue(companyElement, "address-one");
		this.address2 		= ElementValue.getTextValue(companyElement, "address-two");
		this.city 			= ElementValue.getTextValue(companyElement, "city");
		this.state 			= ElementValue.getTextValue(companyElement, "state");
		this.zip 			= ElementValue.getIntValue(companyElement,  "zip");
		this.country 		= ElementValue.getTextValue(companyElement, "country");
		this.webAddress 	= ElementValue.getTextValue(companyElement, "country");
		this.phoneOffice 	= ElementValue.getTextValue(companyElement, "phone-number-office");
		this.phoneFax 		= ElementValue.getTextValue(companyElement, "phone-number-fax");
		this.timeZoneId 	= ElementValue.getTextValue(companyElement, "time-zone-id");
		this.canSeePrivate 	= ElementValue.getBoolValue(companyElement, "can-see-private");
		
	}
		
	/***
	 * Build Company from XML Element
	 * 
	 * (Internal Use Only)
	 * 
	 * @param auth				BCAuth Object
	 * @param companyElement	XML Element representing Company
	 */
	Company(BCAuth auth, Element companyElement) {
		
		super(auth);
				
		this.id				= ElementValue.getIntValue(companyElement,  "id");
		this.name 			= ElementValue.getTextValue(companyElement, "name");
		this.address1 		= ElementValue.getTextValue(companyElement, "address-one");
		this.address2 		= ElementValue.getTextValue(companyElement, "address-two");
		this.city 			= ElementValue.getTextValue(companyElement, "city");
		this.state 			= ElementValue.getTextValue(companyElement, "state");
		this.zip 			= ElementValue.getIntValue(companyElement,  "zip");
		this.country 		= ElementValue.getTextValue(companyElement, "country");
		this.webAddress 	= ElementValue.getTextValue(companyElement, "country");
		this.phoneOffice 	= ElementValue.getTextValue(companyElement, "phone-number-office");
		this.phoneFax 		= ElementValue.getTextValue(companyElement, "phone-number-fax");
		this.timeZoneId 	= ElementValue.getTextValue(companyElement, "time-zone-id");
		this.canSeePrivate 	= ElementValue.getBoolValue(companyElement, "can-see-private");
		
	}	
	
	
	//--- Getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	public String getCountry() {
		return country;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public String getPhoneFax() {
		return phoneFax;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public boolean canSeePrivate() {
		return canSeePrivate;
	}
	
	

}
