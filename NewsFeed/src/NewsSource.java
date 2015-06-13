
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class NewsSource {

	public ArrayList<News> newsArray = new ArrayList<News>();
	
	private String name;
	
	public void UpdateNews()
	{
		newsArray=new ArrayList<News>();
		for(SetRss message : feed.getMessages())
		{
			News n=new News();
			n.setDescription(message.getDescription());
			n.setLink(message.getLink());
			n.setTitle(message.getTitle());
			newsArray.add(n);
		}
	}
	
	public NewsSource(String url, String name)
	{
		this.name=name;
		XmlReader parser= new XmlReader(url);
		feed=parser.readFeed();
	}
	
	public String getName() {
		return name;
	}

	private GetRss feed;
	
	private class GetRss {

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
	
	private class SetRss {

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
	
	private class XmlReader {

		 static final String TITLE = "title";
		  static final String DESCRIPTION = "description";
		  static final String CHANNEL = "channel";
		  static final String LINK = "link";
		  static final String ITEM = "item";
		  static final String PUB_DATE = "pubDate";
		  static final String GUID = "guid";

		  final URL url;

		  public XmlReader(String feedUrl) {
		    try {
		      this.url = new URL(feedUrl);
		    } catch (MalformedURLException e) {
		      throw new RuntimeException(e);
		    }
		  }

		  public GetRss readFeed() {
		    GetRss feed = null;
		    try {
		      boolean isFeedHeader = true;
		      String description = "";
		      String title = "";
		      String link = "";
		      String pubdate = "";
		      XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		      InputStream in = read();
		      XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
		      while (eventReader.hasNext()) {
		        XMLEvent event = eventReader.nextEvent();
		        if (event.isStartElement()) {
		          String localPart = event.asStartElement().getName()
		              .getLocalPart();
		          switch (localPart) {
		          case ITEM:
		            if (isFeedHeader) {
		              isFeedHeader = false;
		              feed = new GetRss(title, link, description,  pubdate);
		            }
		            event = eventReader.nextEvent();
		            break;
		          case TITLE:
		            title = getCharacterData(event, eventReader);
		            break;
		          case DESCRIPTION:
		            description = getCharacterData(event, eventReader);
		            break;
		          case LINK:
		            link = getCharacterData(event, eventReader);
		            break;
		          case PUB_DATE:
		            pubdate = getCharacterData(event, eventReader);
		            break;
		          }
		        } else if (event.isEndElement()) {
		          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
		            SetRss message = new SetRss();        
		            message.setDescription(description);
		            message.setLink(link);
		            message.setTitle(title);
		            feed.getMessages().add(message);
		            event = eventReader.nextEvent();
		            continue;
		          }
		        }
		      }
		    } catch (XMLStreamException e) {
		      throw new RuntimeException(e);
		    }
		    return feed;
		  }

		  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
		      throws XMLStreamException {
		    String result = "";
		    event = eventReader.nextEvent();
		    if (event instanceof Characters) {
		      result = event.asCharacters().getData();
		    }
		    return result;
		  }

		  private InputStream read() {
		    try {
		      return url.openStream();
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
		  }
		} 
	
	private class XmlWriter {



		  private String outputFile;
		  private GetRss rssfeed;

		  public XmlWriter(GetRss rssfeed, String outputFile) {
		    this.rssfeed = rssfeed;
		    this.outputFile = outputFile;
		  }

		  public void write() throws Exception {
		    XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

		    XMLEventWriter eventWriter = outputFactory
		        .createXMLEventWriter(new FileOutputStream(outputFile));

		    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		    XMLEvent end = eventFactory.createDTD("\n");

		    StartDocument startDocument = eventFactory.createStartDocument();

		    eventWriter.add(startDocument);

		    eventWriter.add(end);

		    StartElement rssStart = eventFactory.createStartElement("", "", "rss");
		    eventWriter.add(rssStart);
		    eventWriter.add(eventFactory.createAttribute("version", "2.0"));
		    eventWriter.add(end);

		    eventWriter.add(eventFactory.createStartElement("", "", "channel"));
		    eventWriter.add(end);


		    createNode(eventWriter, "title", rssfeed.getTitle());

		    createNode(eventWriter, "link", rssfeed.getLink());

		    createNode(eventWriter, "description", rssfeed.getDescription());


		    for (SetRss entry : rssfeed.getMessages()) {
		      eventWriter.add(eventFactory.createStartElement("", "", "item"));
		      eventWriter.add(end);
		      createNode(eventWriter, "title", entry.getTitle());
		      createNode(eventWriter, "description", entry.getDescription());
		      createNode(eventWriter, "link", entry.getLink());
		      eventWriter.add(end);
		      eventWriter.add(eventFactory.createEndElement("", "", "item"));
		      eventWriter.add(end);

		    }
		    
		    eventWriter.add(end);
		    eventWriter.add(eventFactory.createEndElement("", "", "channel"));
		    eventWriter.add(end);
		    eventWriter.add(eventFactory.createEndElement("", "", "rss"));

		    eventWriter.add(end);

		    eventWriter.add(eventFactory.createEndDocument());
		    eventWriter.close();
		  }

		  private void createNode(XMLEventWriter eventWriter, String name,

		  String value) throws XMLStreamException {
		    XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		    XMLEvent end = eventFactory.createDTD("\n");
		    XMLEvent tab = eventFactory.createDTD("\t");
		    StartElement sElement = eventFactory.createStartElement("", "", name);
		    eventWriter.add(tab);
		    eventWriter.add(sElement);
		    Characters characters = eventFactory.createCharacters(value);
		    eventWriter.add(characters);
		    EndElement eElement = eventFactory.createEndElement("", "", name);
		    eventWriter.add(eElement);
		    eventWriter.add(end);
		  }
		} 
	
	
	
}
