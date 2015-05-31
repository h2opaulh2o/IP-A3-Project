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
			        
			        Attr attr = doc.createAttribute("result");
			        attr.setValue(String.valueOf(i));
			        Data.setAttributeNode(attr);
			        
			        String utf = new String();
			        
			        Element title = doc.createElement("title");
			        utf = list.get(i).getData().getTitle();
			        utf = convertToUTF8(utf);
		            title.setTextContent(utf);
		            Data.appendChild(title);
		            
		            Element link = doc.createElement("link");
		            utf = list.get(i).getData().getUrl();
		            utf = convertToUTF8(utf);
		            link.setTextContent(utf);
		            Data.appendChild(link);
		            
		            Element frequency = doc.createElement("frequency");
		            utf = String.valueOf(list.get(i).getFrequency());
		            utf = convertToUTF8(utf);
		            frequency.setTextContent(utf);
		            Data.appendChild(frequency);
		        }

		        TransformerFactory tranFactory = TransformerFactory.newInstance();
		        Transformer aTransformer = tranFactory.newTransformer();

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
			  Node node = nodeList.item(i);
			  if (node.getNodeType() == Node.ELEMENT_NODE)
			  {
				  Element el = (Element) node;
				  
				  String title = convertFromUTF8(el.getElementsByTagName("title").item(0).getTextContent());
				  String url = convertFromUTF8(el.getElementsByTagName("link").item(0).getTextContent());
				  int frequency = Integer.parseInt(convertFromUTF8(el.getElementsByTagName("frequency").item(0).getTextContent()));
				  
				  ResultG g = new ResultG(url, title);
				  LinkFrequency l = new LinkFrequency(g,frequency);
				  topLinks.add(l);
			  }
		  }
		  return topLinks;
	  }
}
