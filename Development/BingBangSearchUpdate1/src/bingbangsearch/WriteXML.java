package bingbangsearch;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class WriteXML {
	
		
	public static String convertToUTF8(String s) {
	        String out = null;
	        try {
	            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
	        } catch (java.io.UnsupportedEncodingException e) {
	            return null;
	        }
	        return out;
	    }
		
	public static String convertFromUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }	

	  public void writeXmlFile(ArrayList<LinkFrequency> list, String query)
	  {
		  try
		  {
			    DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
		        DocumentBuilder build = dFact.newDocumentBuilder();
		        Document doc = (Document) build.newDocument();

		        Element root = doc.createElement("TopLinks");
		        doc.appendChild(root);

		        
		        for(int i=0; i<list.size(); i ++ ) {
		        	
		        	Element Data = doc.createElement("Data");
			        root.appendChild(Data);
			        
			        String utf = new String();
			        
			        Element title = doc.createElement("title");
			        utf = list.get(i).getData().getTitle();
			        utf = convertToUTF8(utf);
		            title.setTextContent(utf);
		            Data.appendChild(title);
		            
		            Element link = doc.createElement("link");
		            utf = list.get(i).getData().getUrl().trim();
		            utf = convertToUTF8(utf);
		            link.setTextContent(utf);
		            Data.appendChild(link);
		            
		            Element frequency = doc.createElement("frequency");
		            utf = String.valueOf(list.get(i).getFrequency());
		            utf = convertToUTF8(utf);
		            frequency.setTextContent(utf);
		            Data.appendChild(frequency);
		        }

		         // Save the document to the disk file
		        TransformerFactory tranFactory = TransformerFactory.newInstance();
		        Transformer aTransformer = tranFactory.newTransformer();

		        // format the XML nicely
		        aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		        aTransformer.setOutputProperty(
		                "{http://xml.apache.org/xslt}indent-amount", "4");
		        aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

		        DOMSource source = new DOMSource(doc);
		        try {
		            FileOutputStream fos = new FileOutputStream(query+".xml");
		            StreamResult result = new StreamResult(fos);
		            aTransformer.transform(source, result);

		        } catch (IOException e) {

		            e.printStackTrace();
		        }
		    } catch (TransformerException ex) {
		        System.out.println("Error outputting document");

		    } catch (ParserConfigurationException ex) {
		        System.out.println("Error building document");
		    }
	  }
	  
	  public ArrayList<LinkFrequency> getXML(String fileName) throws SAXException, IOException, ParserConfigurationException
	  {
		  ArrayList<LinkFrequency> topLinks = new ArrayList<LinkFrequency>();
		  
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = factory.newDocumentBuilder();
		  Document document = builder.parse(new File(fileName));
		  NodeList nodeList = document.getDocumentElement().getChildNodes();
		  
		  for (int i = 0; i < nodeList.getLength(); i++)
		  {
			  if (nodeList.item(i).getNodeName() == "Data")
			  {
				  String textContent = convertFromUTF8(nodeList.item(i).getTextContent().trim());
				  int first = textContent.indexOf('\n');
				  String title = textContent.substring(0, first);
				  String url;
				  int frequency;
				  if(title.startsWith("/url?q="))
				  {
					  title = String.valueOf(' ');
					  url = textContent.substring(0, first);
					  frequency = Integer.valueOf(textContent.substring(first+1, textContent.length()).trim());
					  System.out.println(title);
					  System.out.println(url);
					  System.out.println(frequency);
					  
				  }
				  else
				  {
					  url = textContent.substring(first+1, textContent.length()).trim();
					  first = url.indexOf('\n');
					  frequency = Integer.valueOf(url.substring(first+1,url.length()).trim());
					  url = url.substring(0, first);
					  System.out.println(title);
					  System.out.println(url);
					  System.out.println(frequency);
					  
				  }
				  //System.out.println(title);
				  
				  //String url = convertFromUTF8(datas.item(1).getTextContent());
				  //System.out.println(url);
				  //System.out.println(convertFromUTF8(datas.item(2).getTextContent()));
				  //int frequency = 3;
				  
				  //ResultG res = new ResultG(title, url);
				  //LinkFrequency newLink = new LinkFrequency(res, frequency);
				  
				  //topLinks.add(newLink);
			  }
		  }
		  return topLinks;
	  }
}
