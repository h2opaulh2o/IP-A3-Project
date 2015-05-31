import java.util.ArrayList;


public class NewsGetter {
	public ArrayList<NewsSource> sources = new ArrayList<NewsSource>();
	
	public void addSource(String url, String name)
	{
		sources.add(new NewsSource(url,name));
	}
	
	public void updateSources()
	{
		for(NewsSource i : sources)
			i.UpdateNews();
	}
	public ArrayList<News> getNews(boolean[] options)
	{
		ArrayList<News> result = new ArrayList<News>();
		for(int i=0;i<sources.size();i++)
			if(options[i])
				result.addAll(sources.get(i).newsArray);
		return result;
	}
}
