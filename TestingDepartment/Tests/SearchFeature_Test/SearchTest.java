import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SearchTest {

	@Test
	public void Test_Search() {
		ArrayList<String> correct_word = new ArrayList<String>();
		correct_word.add("word");
		correct_word.add("word");
		assertEquals(correct_word, Search.search("apple"));
		ArrayList<String> nothing = new ArrayList<String>();
		nothing.add(null);
		assertEquals(nothing, Search.search(null));
		ArrayList<String> sequence = new ArrayList<String>();
		sequence.add("word \n food");
		assertEquals(sequence, Search.search("word \n food"));
	}
	@Test
	public void sort_Search() {
		ArrayList<String> sort_results = new ArrayList<String>();
		sort_results.add("apple fruit food");
		assertEquals(sort_results, Search.search("apple"));
	
	}
	@Test
	public void Test_GS() {
		ArrayList<String> result1 = new ArrayList<String>();
		ArrayList<String> result2 = new ArrayList<String>();
		result1.add((String) Search.search("phone"));
		result2.add((String) Search.search("phone"));
		assertEquals(result1, result2);
		ArrayList<String> right_answer = new ArrayList<String>();
		right_answer.add("fruit");
		assertEquals(right_answer, Search.search("apple"));
		
	}
	@Test
	public void Test_SM() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("yes");
		assertEquals(result,GoogleSlave.connect());
	}
}


