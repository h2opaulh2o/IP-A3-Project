package bingbangsearch;

//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//
//import com.google.gson.Gson;
//
//public class BingBangSearch 
//{
//	public static void main(String[] args) throws IOException 
//	{	 
//		int counter = 0;
//		String word = "potato";
//		Database forQuery = new Database(word);
//		System.out.println();
//		for (int i = 0; i < 4; i = i + 4) 
//		{
//			String address = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&start="+i+"&q=";
//			String charset = "UTF-8";
//			String query = word;
//			URL url = new URL(address + URLEncoder.encode(query, charset));
//			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), charset));
//			GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
//		 
//			// Show title and URL of each results
//			if(results.getResponseData() != null){
//			for (int m = 0; m < results.getResponseData().getResults().size(); m++) 
//			{
//				System.out.println("Title: " + results.getResponseData().getResults().get(m).getTitle());
//				System.out.println("URL: " + results.getResponseData().getResults().get(m).getUrl() + "\n");
//				counter++;
//			}
//			}
//			else
//			{
//				System.out.println("eroare");
//			}
//		}
//		System.out.println(counter);
//	}
//}
 
import java.io.IOException;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.smu.tspell.wordnet.*;

public class BingBangSearch {

	public static void search(String query) {
   
	Set<LinkFrequency> topLinks = new HashSet<LinkFrequency>();
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
	for(int i = 0; i < allResults.size() - 1; i++){
		int counter = 0;
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
		if(!(temp1.getUrl().trim().contains("googleusercontent")&&temp1.getTitle().trim().contains("�n cache")))
		{
			LinkFrequency link = new LinkFrequency(temp1, counter);
			topLinks.add(link);
			System.out.println(temp1.getTitle());
			System.out.println(temp1.getUrl());
			System.out.println(counter);
		}
	}
	
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
				ResultG res = new ResultG(temp, temp2);
				result.add(res);
			}
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	return result;
  }
 
}