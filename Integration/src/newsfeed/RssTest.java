package newsfeed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
public class RssTest {



	  public static void main(String [] args) {
	    XmlReader parser = new XmlReader("http://students.info.uaic.ro/~andrei.damoc/articles.rss");
	   GetRss feed = parser.readFeed();
	    System.out.println(feed);
	    for (SetRss message : feed.getMessages()) {
	      System.out.println(message);

	    }

	  }
	/*
	  public static void main(String[] args) {
		    // create the rss feed
		   // String copyright = "Copyright hold by Lars Vogel";
		    String title = "Cuvant nou ";
		    String description = "descriere stire";
		   // String language = "en";
		    String link = "http://www.http://students.info.uaic.ro/~andrei.damoc";
		    Calendar cal = new GregorianCalendar();
		    Date creationDate = cal.getTime();
		    SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
		    String pubdate = date_format.format(creationDate);
		    GetRss rssFeeder = new GetRss(title, link, description, 
		         pubdate);

		    // now add one example entry
		    SetRss feed = new SetRss();
		    feed.setTitle("RSSFeed");
		    feed.setDescription("This is a description");
		    feed.setLink("http://students.info.uaic.ro/~andrei.damoc");
		    rssFeeder.getMessages().add(feed);

		    // now write the file
		    XmlWriter writer = new XmlWriter(rssFeeder, "C:\\articles.rss");
		    
		    try {
		      writer.write();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }
*/
	} 
