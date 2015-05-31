package bingbangsearch;

public class ResultG {
	
	private String url;
	private String title;
	
	public ResultG(String link, String t)
	{
		this.title = t;
		this.url = link;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getUrl()
	{
		return this.url;
	}
}
