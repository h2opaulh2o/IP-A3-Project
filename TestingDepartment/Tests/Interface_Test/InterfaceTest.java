import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class InterfaceTest {

	@Test
	public void GetNews() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("stiri");
		assertEquals(result, NewsFeed.getnews("stiri"));
	}
	
	@Test
	public void ShowNews() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("stiri");
		assertEquals(result, Interface.shownews("stiri"));
	}
	
	@Test
	public void SelectSearchEngineInterface() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("select");
		assertEquals(result, SearchEngine.select_interface());
	}
	
	@Test
	public void SelectNewsFeedInterface() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("select");
		assertEquals(result, NewsFeed.select_interface());
	}
	
	
}
	
	
	
	
	
	
	


