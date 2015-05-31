package bingbangsearch;

//import edu.smu.tspell.*;
import edu.smu.tspell.wordnet.*;
//import edu.smu.tspell.wordnet.impl.file.*;

public class Database {
	
	private Synset[] synsets;
	private String[] synsetsHypoHyperForms = new String[100000];
	private int lngth = 0;
	public Database(String query)
	{
		System.setProperty("wordnet.database.dir","wn3.1.dict.tar/dict/");
		WordNetDatabase db = WordNetDatabase.getFileInstance();
		this.synsets = db.getSynsets(query);
		this.getHypo();
		this.getHyper();
	}
	public Synset[] getSynset()
	{
		return synsets;
	}
	
	public String[] getHypoHyperForms()
	{
		return synsetsHypoHyperForms;
	}
	public int getLngth()
	{
		return lngth;
	}
	
	// 1 hyponym for every synset from synsets
	private void getHypo()
	{
		NounSynset[] hyponyms = null;
		NounSynset nounSynset = null;
		for (int i = 0; i < synsets.length; i++) 
		{ 
			if(synsets[i].getType() == SynsetType.NOUN)
			{	
				nounSynset = (NounSynset)(synsets[i]); 
		    	hyponyms = nounSynset.getHyponyms();
		    	if(hyponyms.length != 0)
		    	{
		    		String[] hyp = hyponyms[0].getWordForms();
		    		for(int j = 0; j < hyp.length; j++)
		    		{
		    			synsetsHypoHyperForms[lngth]=hyp[j];
		    			lngth++;
		    		}
		    	}
			}
		}
	}
	// 1 hypernym for every synset from synsets
	private void getHyper()
	{
		NounSynset[] hypernyms = null;
		VerbSynset[] hyper = null;
		NounSynset nounSynset;
		VerbSynset verbSynset;
		for (int i = 0; i < synsets.length; i++) 
		{
			if(synsets[i].getType() == SynsetType.NOUN)
			{
				nounSynset = (NounSynset)(synsets[i]); 
		    	hypernyms = nounSynset.getHypernyms();
				if(hypernyms.length != 0)
		    	{
		    		String[] hyp = hypernyms[0].getWordForms();
		    		for(int j = 0; j < hyp.length; j++)
		    		{
		    			synsetsHypoHyperForms[lngth]=hyp[j];
		    			lngth++;
		    		}
		    	}
			}
			else
			{
				if(synsets[i].getType() == SynsetType.VERB)
				{
					verbSynset = (VerbSynset)(synsets[i]); 
			    	hyper = verbSynset.getHypernyms();
					if(hyper.length != 0)
			    	{
			    		String[] hyp = hyper[0].getWordForms();
			    		for(int j = 0; j < hyp.length; j++)
			    		{
			    			synsetsHypoHyperForms[lngth]=hyp[j];
			    			lngth++;
			    		}
			    	}
				}
			}
		}
	}
	
}
