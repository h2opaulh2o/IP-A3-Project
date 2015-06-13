package syn;
/*@author
 * agent47 aka randomusername24 
*/
import java.util.ArrayList;

import edu.smu.tspell.wordnet.*;
public class tz{
	
	
	
	public static void main(String[] args){
		
	 tz g= new tz();
	
  System.setProperty("wordnet.database.dir","./wn3.1.dict.tar/dict/");
  
  ArrayList<String> list_a = new ArrayList<String>();
  
 for(int i=0;i<10;i++){
	 
 }
System.out.println(g.dist("person","land"));

}
	NounSynset[] hypernyms_a;
	NounSynset[] hypernyms_b;
	ArrayList<String> list_a = new ArrayList<String>();
	ArrayList<String> list_b = new ArrayList<String>();

public int dist (String a,String b){
	if(a.equals(b))
		return 0;
	else
	{
		if(this.one(a, b)){
		return 1;
	}
		else{
			list_a=this.mama(a);
			list_b=this.mama(b);		
			list_a = this.go(list_a);
			list_b = this.go(list_b);
			
			for(int q=0;q<4;q++){
				for(int i=0;i<list_a.size();i++){
					for(int j=0;j<list_b.size();j++){
						if(this.one(a, b) || a.equals(b)){
							
							return q;
							
							}
						}
					}
				
				list_a = this.go(list_a);
				list_b = this.go(list_b);
				
			}
		}
	
		return 10;
		}
	
	
}
public ArrayList<String> go(ArrayList<String> list_a){
	
	ArrayList<String> temp_a = new ArrayList<String>(list_a);
	
	ArrayList<String> temp = new ArrayList<String>();
	
	for (int i = 0; i < list_a.size(); i++) {
		
	  temp .addAll( this.hyper(list_a.get(i)));
	  temp .addAll( this.mmero(list_a.get(i)));
	  temp.addAll( this.smero(list_a.get(i)));
	  temp.addAll( this.pmero(list_a.get(i)));	  
	  
	for (int j = 0; j < temp.size(); j++) {
		  temp_a.addAll(this.mama(temp.get(j)));
		  temp_a.add(temp.get(j));
		  }
	}
	return temp_a;
}
public ArrayList<String> mama(String a){//aray list cu synset
	NounSynset nounSynset; 
	
	ArrayList<String> list = new ArrayList<String>();

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets = database.getSynsets(a, SynsetType.NOUN); 
	for (int i = 0; i < synsets.length; i++) { 
	    nounSynset = (NounSynset)(synsets[i]); 
	    list.add(nounSynset.getWordForms()[0] ); 
	}
	return list;
	
	
}
public ArrayList<String> hyper(String a){//aray list c hypernimele
NounSynset nounSynset; 
	
	ArrayList<String> list = new ArrayList<String>();

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets = database.getSynsets(a, SynsetType.NOUN); 
	for (int i = 0; i < synsets.length; i++) { 
	    nounSynset = (NounSynset)(synsets[i]);
	    NounSynset[] hypernyms = nounSynset.getHypernyms(); 
	    for(int k=0;k<hypernyms.length;k++){
	    Synset[] synsetshyper = database.getSynsets(hypernyms[0].getWordForms()[0], SynsetType.NOUN);
	    for(int j=0;j<synsetshyper.length;j++){
	    	nounSynset = (NounSynset)(synsetshyper[j]);
	    	list.add(nounSynset.getWordForms()[0]);
	    }
	    }
	    
	}
	return list;
	
}
public ArrayList<String> mmero(String a){//aray list c hypernimele
NounSynset nounSynset; 
	
	ArrayList<String> list = new ArrayList<String>();

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets = database.getSynsets(a, SynsetType.NOUN); 
	for (int i = 0; i < synsets.length; i++) { 
	    nounSynset = (NounSynset)(synsets[i]);
	    NounSynset[] hypernyms = nounSynset.getMemberMeronyms(); 
	    for(int k=0;k<hypernyms.length;k++){
	    Synset[] synsetshyper = database.getSynsets(hypernyms[0].getWordForms()[0], SynsetType.NOUN);
	    for(int j=0;j<synsetshyper.length;j++){
	    	nounSynset = (NounSynset)(synsetshyper[j]);
	    	list.add(nounSynset.getWordForms()[0]);
	    }
	    }
	    
	}
	return list;
	
}
public ArrayList<String> pmero(String a){//aray list c hypernimele
NounSynset nounSynset; 
	
	ArrayList<String> list = new ArrayList<String>();

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets = database.getSynsets(a, SynsetType.NOUN); 
	for (int i = 0; i < synsets.length; i++) { 
	    nounSynset = (NounSynset)(synsets[i]);
	    NounSynset[] hypernyms = nounSynset.getPartMeronyms(); 
	    for(int k=0;k<hypernyms.length;k++){
	    Synset[] synsetshyper = database.getSynsets(hypernyms[0].getWordForms()[0], SynsetType.NOUN);
	    for(int j=0;j<synsetshyper.length;j++){
	    	nounSynset = (NounSynset)(synsetshyper[j]);
	    	list.add(nounSynset.getWordForms()[0]);
	    }
	    }
	    
	}
	return list;
	
}

public ArrayList<String> smero(String a){//aray list c hypernimele
NounSynset nounSynset; 
	
	ArrayList<String> list = new ArrayList<String>();

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets = database.getSynsets(a, SynsetType.NOUN); 
	for (int i = 0; i < synsets.length; i++) { 
	    nounSynset = (NounSynset)(synsets[i]);
	    NounSynset[] hypernyms = nounSynset.getSubstanceMeronyms(); 
	    for(int k=0;k<hypernyms.length;k++){
	    Synset[] synsetshyper = database.getSynsets(hypernyms[0].getWordForms()[0], SynsetType.NOUN);
	    for(int j=0;j<synsetshyper.length;j++){
	    	nounSynset = (NounSynset)(synsetshyper[j]);
	    	list.add(nounSynset.getWordForms()[0]);
	    }
	    }
	    
	}
	return list;
	
}

public boolean one(String a, String b){//au intersectie pe acelasi nivel(reprezinta acelasi lucru)
	NounSynset nounSynset_a; 
	NounSynset nounSynset_b; 

	WordNetDatabase database = WordNetDatabase.getFileInstance(); 
	Synset[] synsets_a = database.getSynsets(a, SynsetType.NOUN);
	Synset[] synsets_b = database.getSynsets(b, SynsetType.NOUN);
	for (int i = 0; i < synsets_a.length; i++) { 
	    nounSynset_a = (NounSynset)(synsets_a[i]);  
	    for (int j = 0; j < synsets_b.length; j++) { 
		    nounSynset_b = (NounSynset)(synsets_b[j]); 
		    if(nounSynset_b.getWordForms()[0].equals(nounSynset_a.getWordForms()[0])){
		    	return true;
		    	
		    }
		    } 
	    
	}
	return false;
}

}
