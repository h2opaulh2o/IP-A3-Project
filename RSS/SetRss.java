
public class SetRss {

	  String title;
	  String description;
	  String link;

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }

	  public String getLink() {
	    return link;
	  }

	  public void setLink(String link) {
	    this.link = link;
	  }

	 
	  @Override
	  public String toString() {
		  StringBuilder result = new StringBuilder();
		    String NEW_LINE = System.getProperty("line.separator");

		    result.append("News"+NEW_LINE+

		    		"Title: "+title+NEW_LINE+
		    		"Description: "+description+NEW_LINE+
		    		
		    		"Link: "+link+NEW_LINE);
				 
		return result.toString();

	  }

	} 
