package bingbangsearch;

public class LinkFrequency {
	 
	private ResultG data;
	private int frequency;
	
	public LinkFrequency(ResultG link, int freq) 
	{
		this.data = link;
		this.frequency = freq;
	}
	public ResultG getData()
	{
		return data;
	}
	public int getFrequency()
	{
		return frequency;
	}
	
	public void setFrequency(int f)
	{
		this.frequency = f;
	}
}
