
import java.util.ArrayList;
import java.util.List;
public class GetRss {

	

	/*
	 * Stores an RSS feed
	 */


	  final String title;
	  final String link;
	  final String description;

	  final String pubDate;

	  final List<SetRss> entries = new ArrayList<SetRss>();

	  public GetRss(String title, String link, String description, 
	     String pubDate) {
	    this.title = title;
	    this.link = link;
	    this.description = description;

	    this.pubDate = pubDate;
	  }

	  public List<SetRss> getMessages() {
	    return entries;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public String getLink() {
	    return link;
	  }

	  public String getDescription() {
	    return description;
	  }


	  public String getPubDate() {
	    return pubDate;
	  }

	  @Override
	  public String toString() {
		  StringBuilder result = new StringBuilder();
		    String NEW_LINE = System.getProperty("line.separator");

		    result.append("News"+NEW_LINE+
		    		
		    		"Title: "+title+NEW_LINE+
		    		"Description: "+description+NEW_LINE+
		    		
		    		"Link: "+link+NEW_LINE+
		    		"Publication Date"+pubDate+NEW_LINE);
				 
		return result.toString();

	  }

	} 
