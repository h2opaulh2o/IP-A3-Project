package bingbangsearch;
 
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import edu.smu.tspell.wordnet.*;

public class BingBangSearch {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
   
	int option = 1;
	if (option == 1)
	{
	String phrase = "green apple";
	Pattern pattern = Pattern.compile("\\w+");
	Matcher matcher = pattern.matcher(phrase);
	String query = new String();
	while (matcher.find()) 
	{
		
	    System.out.println(matcher.group());
	    query = matcher.group();
	    
	    ArrayList<LinkFrequency> topLinks = new ArrayList<LinkFrequency>();
		ArrayList<ResultG> allResults = new ArrayList<ResultG>();
		Database db = new Database(query);
		Synset[] synset = db.getSynset();
		
		for(int j = 0; j < synset.length; j++)
		{
			System.out.println("Sense: "+synset[j].toString());
			String[] wordForms = synset[j].getWordForms();
			for(int k = 0; k < wordForms.length; k++)
			{	
				System.out.println(wordForms[k]);	
				Set<ResultG> result = getDataFromGoogle(wordForms[k]);
				allResults.addAll(result);
			}
		}
		for(int i = 0; i < allResults.size() - 1; i++)
		{
			int counter = 1;
			ResultG temp1 = allResults.get(i); 
			for(int j = i + 1; j < allResults.size(); j++)
			{
				ResultG temp2 = allResults.get(j);
				if(temp1.getUrl().compareTo(temp2.getUrl()) == 0)
				{
					counter++;
					allResults.remove(j);
				}
			}
			if(!(temp1.getUrl().trim().contains("googleusercontent")&&temp1.getTitle().trim().contains("În cache")))
			{
				LinkFrequency link = new LinkFrequency(temp1, counter);
				topLinks.add(link);
			}
		}
		
		topLinks = sortLinks(topLinks);
		WriteXML newFile = new WriteXML();
		newFile.writeXmlFile(topLinks, query);
		topLinks = newFile.getXML(query+".xml");
		for(int i = 0; i < topLinks.size(); i++)
		{
			System.out.println(topLinks.get(i).getData().getTitle());
			System.out.println(topLinks.get(i).getData().getUrl());
			System.out.println(topLinks.get(i).getFrequency());
		}
	}
	}
	else
	{
		WriteXML newFile = new WriteXML();
		ArrayList<LinkFrequency> topLinks = new ArrayList<LinkFrequency>();
		topLinks = newFile.getXML("first.xml");
		for(int i = 0; i < topLinks.size(); i++)
		{
			System.out.println(topLinks.get(i).getData().getTitle());
			System.out.println(topLinks.get(i).getData().getUrl());
			System.out.println(topLinks.get(i).getFrequency());
		}
	}
  }

  static public ArrayList<LinkFrequency> sortLinks(ArrayList<LinkFrequency> topLinks)
  {
	  for(int i = 0; i < topLinks.size() - 1; i++)
		  for(int j = i+1; j < topLinks.size(); j++)
		  {
			  LinkFrequency first = topLinks.get(i);
			  LinkFrequency second = topLinks.get(j);
			  if(first.getFrequency()<second.getFrequency())
			  {
				  topLinks.set(i, second);
				  topLinks.set(j, first);
			  }
		  }
	  return topLinks;
  }
  
  static public String getLink(String link)
  {
	  int first = 0;
	  char[] li = new char[2000];
	  first = link.indexOf('&');
	  if(first > 0)
	  {
		link.getChars(0, first, li, 0);
	  	String l = new String(li);
	  	return l;
	  }
	  return link;
  }
 
  static public Set<ResultG> getDataFromGoogle(String query) {
 
	Set<ResultG> result = new HashSet<ResultG>();	
	String request = "https://www.google.com/search?q=" + query + "&num=20";
	System.out.println("Sending request..." + request);
 
	try {
		// need http protocol, set this as a Google bot agent 
		Document doc = Jsoup
			.connect(request)
			//.header("Connection", "keep-alive")
			.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
			.timeout(5000).get();
 
		// get all links
		Elements links = doc.select("a[href]");
		for (Element link : links) {
			
			String temp = link.attr("href");
			
            
			if(temp.startsWith("/url?q=")){
				
				String temp2 = link.text();
				String c = getLink(temp);
				ResultG res = new ResultG(c, temp2);
				result.add(res);
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return result;
  } 
 
}