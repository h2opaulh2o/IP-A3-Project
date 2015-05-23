import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
public class RssTest {
	  public static void main(String [] args) {
		  	int ok=1;
			System.out.println("Introduce optiunea");
			
			while(ok==1){
				System.out.println("1- Automat (3 rss-uri - afisate pe ecran)");
				System.out.println("2- Citire de la tastatura( 2 rss-uri - afisate in fisier)");
				System.out.println("3- Compune RSS");
				System.out.println("4- Get Titlu ");
				System.out.println("5- Get Descriere ");
				
				Scanner read= new Scanner(System.in);
				int optiune=read.nextInt();
				
				switch (optiune){
				 case 1: XmlReader parser1 = new XmlReader("http://rss.realitatea.net/stiri.xml");
		 		  XmlReader parser2 = new XmlReader("http://www.romaniatv.net/rss/stiri.xml");
		 		  XmlReader parser3 = new XmlReader("http://therealnews.com/rss/therealnewsitunesaudio.rss");
		   		  
		 		  GetRss feed1 = parser1.readFeed();
		 		  GetRss feed2 = parser2.readFeed();
		 		  GetRss feed3= parser3.readFeed();
		 		  
		 		  System.out.println("1) Rss RealitateaTV");
		   		  System.out.println(feed1);
		   	 		  for (SetRss message1 : feed1.getMessages()) {
		   			  System.out.println(message1);
		   		  }
		   	 		  
		   	 		  System.out.println("2) Rss RomaniaTV");
		   	 		System.out.println(feed2);
		   	 		  for (SetRss message2 : feed2.getMessages()) {
		   			  System.out.println(message2);
		   		  }
		   	 		  
		   	 		  System.out.println("3) RSS The Real News Network");
		   	 		System.out.println(feed3);
		   	 		  for (SetRss message3 : feed3.getMessages()) {
		   			  System.out.println(message3);
		   		  }
	           break;
	           
				 case 2:  String path;
				 		  String path2;

				 		   System.out.println("Introduceti link-urile de unde vor fi citite RSS-urile...");
				 		  Scanner aa =new Scanner(System.in); 
				 		  path= aa.next();

				 		  Scanner aaa =new Scanner(System.in); 
				 		  path2= aaa.next();

					 	  XmlReader parser11 = new XmlReader(path);
				   		  GetRss feed11 = parser11.readFeed();
				   		  System.out.println(feed11);

				   		  XmlReader parser12 = new XmlReader(path2);
				   		  GetRss feed12 = parser12.readFeed();
				   		  System.out.println(feed12);
				   		  String numeFisier1;
						 Scanner numeF1=new Scanner(System.in);
						 System.out.println("Fisierul va fi salvat sub numele de ..");
						 numeFisier1=numeF1.nextLine();
						 try 
						        {
							 FileWriter writer = new FileWriter("D:\\"+numeFisier1); 
							 for(SetRss message : feed11.getMessages()) {
							   writer.write( message.getDescription()+ "     ");
							   writer.append("\r\n");
							 }
							 writer.close();
						 }
							 catch(IOException ex) {
						            System.out.println("Nu am putut scrie in"+ "D:\\"+numeFisier1 + "'");}
					   	 
					   		  for (SetRss message : feed11.getMessages()) {
					   			  System.out.println(message);
					   		  }
					   		  
					   		String numeFisier2;
							 Scanner numeF2=new Scanner(System.in);
							 System.out.println("Fisierul va fi salvat sub numele de ..");
							 numeFisier2=numeF2.nextLine();
							 try 
							        {
								 FileWriter writer = new FileWriter("D:\\"+numeFisier2); 
								 for(SetRss message : feed11.getMessages()) {
								   writer.write( message.getDescription()+ "     ");
								   writer.append("\r\n");
								 }
								 writer.close();
							 }
								 catch(IOException ex) {
							            System.out.println("Nu am putut scrie in"+ "D:\\"+numeFisier2 + "'");}
						   	 
						   		  for (SetRss message : feed12.getMessages()) {
						   			  System.out.println(message);
						   		  }
				   	  break;
				 case 3: String title,description,link;
				 		 System.out.println("Titlu");
				 		 Scanner aa1 =new Scanner(System.in);
				         title =aa1.next();
				         System.out.println("Descriere");
				 		 description=aa1.next();
				 		 System.out.println("Link");
				 		 link= aa1.next();
				 		 Calendar cal = new GregorianCalendar();
						 Date creationDate = cal.getTime();
						 SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
						 String pubdate = date_format.format(creationDate);
						 GetRss rssFeeder = new GetRss(title, link, description,  pubdate);
						 SetRss feed111 = new SetRss();
						 feed111.setTitle("RSSFeed");
						 feed111.setDescription("This is a description");
						 feed111.setLink("http://students.info.uaic.ro/~andrei.damoc");
						 rssFeeder.getMessages().add(feed111);
						 XmlWriter writer = new XmlWriter(rssFeeder, "C:\\articles.rss");
						    
						   try {
						      writer.write();
						      System.out.println("RSS a fost creat in C:/");
						    } catch (Exception e) {
						      e.printStackTrace();
						    }
						 break;
				 case 4: 
					  String path1;
			 		  Scanner aa11 =new Scanner(System.in); 
			 		  System.out.println("Introduceti linkul de unde va fi citit RSS...");
			 		  path1= aa11.next();
					  XmlReader parser4 = new XmlReader(path1);
			   	      GetRss feed4 = parser4.readFeed();
			   		  int i=1;
			   		  for (SetRss message : feed4.getMessages()) {
			   			  System.out.println("Titlu "+i+"   "+message.getTitle());
			   			  i++;
			   		  }
		           break;
				 case 5: 
					  String path11;
			 		  Scanner aa111 =new Scanner(System.in); 
			 		  System.out.println("Introduceti linkul de unde va fi citit RSS...");
			 		  path11= aa111.next();
					  XmlReader parser1111 = new XmlReader(path11);
			   		  GetRss feed1111 = parser1111.readFeed();
			   		  int i1=1;
			   		  for (SetRss message : feed1111.getMessages()) {
			   			  System.out.println("Deascrierea "+i1+"   "+message.getDescription());
			   			  i1++;
			   		  }
		           break;	 
		         default: 
		        	 System.out.println("Invata sa citesti pa!");	
		        	 ok=0;  
				}
			}
	  }
} 
