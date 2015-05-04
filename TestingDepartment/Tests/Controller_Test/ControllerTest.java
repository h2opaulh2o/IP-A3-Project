import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class ControllerTest {

	@Test
	public void Update() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("update");
		assertEquals(result,NewsFeed.update());
	}
	
	@Test
	public void SendResultstoInterface() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("send");
		assertEquals(result,Controller.send_results());
	}
	
	@Test
	public void GetResults() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("get");
		assertEquals(result,SearchEngine.send_results());
	}
	 
}
