package Testing;

import org.junit.Test;

public class LinkFrequencyTest {

	@Test
	public void testGetData() {
		ResultG link =new ResultG("test", "www.google.com");
		LinkFrequency instance=new LinkFrequency(link, 3 );
		instance.getData();
	}

	@Test
	public void testGetFrequency() {
		ResultG link =new ResultG("test", "www.google.com");
		LinkFrequency inst=new LinkFrequency(link, 3);
		inst.getFrequency();
	}

}
