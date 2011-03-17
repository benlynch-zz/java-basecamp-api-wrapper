package api.basecamp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/***
 * 
 * Collection of BaseCamp TimeEntry Objects with the ability to define reports.
 * 
 * @author jondavidjohn
 *
 */
public class TimeReport extends BaseCampEntity {
	
	private List<TimeEntry> entries = new ArrayList<TimeEntry>();
	private int entryCount = 0;

	/***
	 * 
	 * Build TimeReport according to a defined TimeReportSpec Object
	 * 
	 * @param auth	BCAuth Object
	 * @param spec	Defined Specifications for Report
	 */
	public TimeReport(BCAuth auth, TimeReportSpec spec) {
		super(auth);
		
		String options = "?";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		//Set options for Report Query
		
		if (spec.subject_id != 0)     	{ options += "subject_id="+spec.subject_id+"&"; }
		
		if (spec.from != null) 			{ options += "from="+sdf.format(spec.from.getTime())+"&"; }
		
		if (spec.to != null) 			{ options += "to="+sdf.format(spec.to.getTime())+"&"; }
		
		if (spec.filterCompanyId != 0) 	{ options += "filter_company_id="+spec.filterCompanyId+"&"; }
		
		if (spec.filterProjectId != 0) 	{ options += "filter_project_id="+spec.filterProjectId+"&"; }
		
		//Run Query with options appended to request URL
		
		Element reportElement = this.get("/time_entries/report.xml"+options);
		
		//get entry NodeList
		NodeList nl = reportElement.getElementsByTagName("time-entry");
		
		for ( int i=0 ; i<nl.getLength() ; i++ ) {
			Element entryElement = (Element) nl.item(i);
			TimeEntry entry = new TimeEntry(auth, entryElement);
			this.entries.add(entry);
			this.entryCount++;
		}
		
		
	}
	
	/***
	 * 
	 * Report Containing all time entires for a given todo item
	 * 
	 * @param auth		BCAuth Object
	 * @param toDoId	ID of todo item
	 */
	public TimeReport(BCAuth auth, int toDoId) {
		super(auth);
		
		Element reportElement = super.get("/todo_items/"+toDoId+"/time_entries.xml");
		
		//get entry NodeList
		NodeList nl = reportElement.getElementsByTagName("time-entry");
		
		for (int i=0;i<nl.getLength();i++) {
			Element entryElement = (Element) nl.item(i);
			TimeEntry entry = new TimeEntry(auth, entryElement);
			this.entries.add(entry);
			this.entryCount++;
		}
	}
	
	public List<TimeEntry> getEntries() {
		return this.entries;
	}
	
	public TimeEntry getEntry(int id) {
		return this.entries.get(id);
	}
	
	public int getEntryCount() {
		return this.entryCount;
	}
	
	/***
	 * 
	 *  Gets a range of entries within the Report
	 *  
	 * @param start	Calendar	Starting Date
	 * @param end	Calendar	Ending Date
	 * @return	List of TimeEntries within given date range
	 */
	public List<TimeEntry> getRangeOfEntries(Calendar start, Calendar end) {
		List<TimeEntry> returnList = new ArrayList<TimeEntry>();
		
		for (int i=0; i<this.entries.size(); i++) {
			TimeEntry entry = this.entries.get(i);
			Calendar entryCal = entry.getCalendar();
			
			if ((start.equals(entryCal) || start.before(entryCal)) && 
				(end.equals(entryCal) || end.after(entryCal))) {
				returnList.add(entry);
			}
		}
		
		return returnList;
	}
	
}
