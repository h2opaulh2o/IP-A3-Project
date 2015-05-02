package bingbangsearch;

//import edu.smu.tspell.*;
import edu.smu.tspell.wordnet.*;
//import edu.smu.tspell.wordnet.impl.file.*;

public class Database {
	
	private Synset[] synsets;
	
	public Database(String query)
	{
		System.setProperty("wordnet.database.dir","wn3.1.dict.tar\\dict\\");
		WordNetDatabase db = WordNetDatabase.getFileInstance();
		synsets = db.getSynsets(query);
	}
	public Synset[] getSynset()
	{
		return synsets;
	}
}
