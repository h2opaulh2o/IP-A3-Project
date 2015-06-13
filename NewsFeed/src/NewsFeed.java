import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class NewsFeed {
	
	private String path;
	
	private ArrayList<News> feed=new ArrayList<News>();
	
	private NewsGetter source = new NewsGetter();
	
	private NewsFilter filter = new NewsFilter();
	
	public NewsFeed(String path)
	{
		if(path!=null)
		{
			this.path=new String(path);
			feed = Deserialize(path);
		}
		source.addSource("http://rss.realitatea.net/stiri.xml", "Realitatea");
		source.addSource("http://www.romaniatv.net/rss/stiri.xml","RomaniaTV");
		source.addSource("http://therealnews.com/rss/therealnewsitunesaudio.rss","TheRealNewsItunes");
	}
	
	public void updateFeed()
	{
		source.updateSources();
		for(NewsSource i : source.sources)
		{
			System.out.println("getting news from "+i.getName());
			feed=filter.filter(feed, i.newsArray);
		}
	}
	
	private ArrayList<News> Deserialize(String path)
	{
		ArrayList<News> result;
		

		try
		{
			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			result = (ArrayList<News>) in.readObject();
			in.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
			return null;
		}
		catch(ClassNotFoundException c)
		{
			System.err.println("Class not found");
			c.printStackTrace();
			return null;
		}
		return result;
	}
	
	public void Serialize(String path)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(feed);
			out.close();
			fileOut.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	
	public ArrayList<News> getFeed()
	{
		ArrayList<News> result = new ArrayList<News>();
		result.addAll(feed);
		return result;
	}
	
	public void close()
	{
		Serialize(path);
	}
}
