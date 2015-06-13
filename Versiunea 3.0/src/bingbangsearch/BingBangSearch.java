package bingbangsearch;
import pkginterface.JFrame;
 
import java.io.*;
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
	public static String[][] rezultate=new String[100][2];
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
       
    }
    
    public static String[][] search (String x,String z)throws SAXException, IOException, ParserConfigurationException {
                
		// this is the phrase
		String phrase = x;
		//this is the search engine
		String engine = z;
	
		// search the phrase with the engine
		Set<ResultG> phraseResults = getDataFromGoogle(phrase, engine);
		ArrayList<ResultG> res = new ArrayList<ResultG>();
		res.addAll(phraseResults);
		ArrayList<LinkFrequency> lastResults = new ArrayList<LinkFrequency>();
	
		// save the results in lastResults with frequency 1
		for(int i = 0; i < res.size(); i++)
		{
			LinkFrequency li = new LinkFrequency(res.get(i), 1);
			lastResults.add(li);	
		}
	
		// take every word from the phrase
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(phrase);
		String query = new String();
		
		while (matcher.find()) 
		{
		    
		    query = matcher.group();
		    File f = new File(query+".xml");
		    ArrayList<LinkFrequency> topLinks = new ArrayList<LinkFrequency>();
		   
		    // check if the word was previously searched on google
		    if(f.exists())
		    {
		    	WriteXML newFile = new WriteXML();
		    	topLinks = newFile.getXML(query+".xml");
		    }
		    else
		    {    
		    	//if not, query database and create xml file and topLinks for that word
				ArrayList<ResultG> allResults = new ArrayList<ResultG>();
				Database db = new Database(query);
				Synset[] synset = db.getSynset();
				String[] hypoHyperForms = db.getHypoHyperForms();
				int lngth = db.getLngth();
				for(int j = 0; j < synset.length; j++)
				{
					System.out.println("Sense: "+synset[j].toString());
					String[] wordForms = synset[j].getWordForms();
					
					for(int k = 0; k < wordForms.length; k++)
					{	
						System.out.println(wordForms[k]);	
						Set<ResultG> result = getDataFromGoogle(wordForms[k], engine);
						allResults.addAll(result);
					}
				}
				for(int k = 0; k < lngth; k++)
				{	
					System.out.println(hypoHyperForms[k]);	
					Set<ResultG> result = getDataFromGoogle(hypoHyperForms[k], engine);
					allResults.addAll(result);
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
		
					LinkFrequency link = new LinkFrequency(temp1, counter);
					topLinks.add(link);
				}
				topLinks = sortLinks(topLinks);
				WriteXML newFile = new WriteXML();
				newFile.writeXmlFile(topLinks, query);
		    }
		    
		    for(int k = 0; k < topLinks.size(); k++)
			{
				for(int l = 0; l < lastResults.size(); l++)
				{
					if(topLinks.get(k).getData().getUrl() == lastResults.get(l).getData().getUrl())
					{
						lastResults.get(l).setFrequency(lastResults.get(l).getFrequency() + 1);
						l = lastResults.size() + 5;
					}
					else
					{
						if(topLinks.get(k).getFrequency() != 1)
							lastResults.add(topLinks.get(k));
						l = lastResults.size() + 5;
					}
				}
			}
		}
                int y=0;
		lastResults = sortLinks(lastResults);
		for(int g = 0; g < lastResults.size();g++)
		{  
                    rezultate[y][0]=lastResults.get(g).getData().getUrl();
                    rezultate[y][1]=lastResults.get(g).getData().getTitle();
		    
                    y++;
		}
		return rezultate;
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
  
	// get link without google api unique code
    static public String getLink(String link)
    {
	    int end = 0;
	    int begin = 0;
	    char[] li = new char[2000];
	    end = link.indexOf('&');
	    begin = link.indexOf('=');
	    if(end > 0)
	    {
	    	link.getChars(begin+1, end, li, 0);
	    	String l = new String(li);
	    	return l;
	    }
	    return link;
    }
  
    // search on engine the query
    // return the result Set<ResultG>
    static public Set<ResultG> getDataFromGoogle(String query, String engine) {
 
    	Set<ResultG> result = new HashSet<ResultG>();
    	String request = new String();
    	if(engine == "Bing")
    	{
    		request = "https://www.bing.com/search?q="+ query +"&num=10";
    	}
    	else
    	{
    		request = "https://www.google.com/search?q=" + query + "&num=10";
    	}
    	try 
    	{	
    		if(engine == "Bing")
    		{
				Document doc;
				doc = Jsoup.connect(request).timeout(5000).get();
				Elements links = doc.select("a[href]");
				for (Element link : links) 
				{
					String temp = link.attr("href");
					if(temp.startsWith("http"))
					{
						if(!(temp.trim().contains("go.microsoft")||link.text().trim().contains("Ajutor")))
						{
							String temp2 = link.text();
							String c = temp;
							ResultG res = new ResultG(c, temp2);
							result.add(res);
						}
					}
				}
    		}
    		else
    		{
				Document doc;
				doc = Jsoup.connect(request)
				.userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)")
				.timeout(9000).get();
				Elements links = doc.select("a[href]");
				for (Element link : links) 
				{	
					String temp = link.attr("href");
					if(temp.startsWith("/url?q="))
					{
						if(!(temp.trim().contains("googleusercontent")
								||temp.trim().contains("/settings/ads/preferences")
								||link.text().trim().contains("�n cache")
								||link.text().trim().contains("De ce")))
						{
							String temp2 = link.text();
							String c = getLink(temp);
							ResultG res = new ResultG(c, temp2);
							result.add(res);
						}
					}
				}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return result;
    } 
 }