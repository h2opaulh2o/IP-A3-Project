import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;


public class NewsFeedTest {

	@Test
	public void GetNews() {
		ArrayList<String> result = new ArrayList<String>();
		
		result.add("stire");
		assertEquals(result, NewsFeed.getnews("31-jan-2013"));
	}
	@Test
	public void Check_NG() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes");
		assertEquals(result,NewsGeter.connect());
	}
	@Test
	public void Check_NF() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes");
		assertEquals(result,NewsFilter.connect());
	}
	@Test
	public void Check_C() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes");
		assertEquals(result,Controller.connect());
	}
}
