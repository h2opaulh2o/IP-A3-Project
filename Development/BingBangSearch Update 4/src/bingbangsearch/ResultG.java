package bingbangsearch;

public class ResultG {
	
	private String url;
	private String title;
	
	public ResultG(String one, String two)
	{
		this.title = two;
		this.url = one;
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
