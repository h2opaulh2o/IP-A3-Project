package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultGTest {

	@Test
	public void testGetTitle() {
		String title="Competitie";
		ResultG instance= new ResultG("com", title);
		instance.getTitle();
		assertEquals(instance.getTitle(),title);
	
	}

	@Test
	public void testGetUrl() {
		String url="www.google.com";
		ResultG instance=new ResultG(url, "notUrl");
		instance.getUrl();
		assertEquals(instance.getUrl(),url);
	}

}
