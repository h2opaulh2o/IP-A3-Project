package bingbangsearch;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Andra Maria
 */
public class DatabaseTest {

       
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    private WordNetDatabase db= null;
    
    @Before
    public void setUp() throws Exception {
    db = WordNetDatabase.getFileInstance();
    }

    @After
    public void tearDown() throws Exception {
    db = null;
    }
    
    @Test
    public void testGetDb() {
		Synset[] synsets = db.getSynsets("pipe");
		assertEquals(9,synsets.length);
		Synset[] synsets_n = db.getSynsets("pipe", SynsetType.NOUN);
		assertEquals(5, synsets_n.length);
		String sense = synsets_n[1].getWordForms()[0];
		assertEquals("pipe", sense);
		sense = synsets_n[1].getWordForms()[1];
		assertEquals("pipage", sense);
		sense = synsets_n[1].getWordForms()[2];
		assertEquals("piping", sense);
	}
    
    @Test
    public void testHyponyms() {
		Synset[] synsets = db.getSynsets("pipe", SynsetType.NOUN);
		assertEquals(5, synsets.length);
		String sense = synsets[1].getWordForms()[0];
		assertEquals("pipe", sense);
		NounSynset noun = (NounSynset) synsets[1];
		Synset [] hyponyms = noun.getHyponyms();
		assertEquals(15, hyponyms.length);
	}

    @Test
    public void testHypernyms() {
		Synset[] synsets = db.getSynsets("pipe", SynsetType.NOUN);
		assertEquals(5, synsets.length);
		String sense = synsets[1].getWordForms()[0];
		assertEquals("pipe", sense);
		NounSynset noun = (NounSynset) synsets[1];
		Synset [] hypernyms = noun.getHypernyms();
		assertEquals(1, hypernyms.length);
		sense = hypernyms[0].getWordForms()[0];
		assertEquals("tube", sense);
	}
  
    public static void main(String[] args) {
    System.setProperty("wordnet.database.dir", "C:\\Users\\Andra Maria\\Desktop\\Versiunea 3.0\\wn3.1.dict.tar\\dict");
    }
    
}
