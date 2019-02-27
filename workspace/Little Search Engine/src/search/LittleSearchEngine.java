package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next(); 
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			for (Map.Entry<String, Occurrence> entry : kws.entrySet()) {
			    String key = entry.getKey();
			    Occurrence value = entry.getValue();

			    System.out.println ("Key: " + key + " Value: " + value);
			}
			mergeKeyWords(kws);
		}
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeyWords(String docFile) 
	throws FileNotFoundException {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		
		int count = 0;
		HashMap<String, Occurrence> keyWords = new HashMap<String, Occurrence>();
		try 
		{
			Scanner sc = new Scanner(new File(docFile));// SOURCELEAK try using try/catch
			while (sc.hasNext())
			{
				String word = sc.next();
				//System.out.println("PARSED WORDS: " + word);
					String keyword = getKeyWord(word);
					//System.out.println("KEYWORDS: " + keyword);
					if (!(keyword == null || keyword.equals("")))
					{
						if (keyWords.containsKey(keyword))
						{
							keyWords.get(keyword).frequency++;
						}
						else 
						{
							Occurrence docFreq = new Occurrence(docFile, 1);
							keyWords.put(keyword, docFreq);
						}
					}
			
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		return keyWords;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		/*for (String key : kws.keySet())
		{
			System.out.println("KEY: " + key + " value: " + kws.get(key));
		}*/
		//System.out.println("Beginning of MERGEKEYWORDS");
		Iterator <String> iterator = kws.keySet().iterator();
		while (iterator.hasNext())
		{
			//System.out.println("REACHED BEGINNING OF WHILE LOOP");
			String keyword = iterator.next();
			//System.out.println("KEYWORD: " + keyword); 
			ArrayList<Occurrence> occurrences = new ArrayList<Occurrence>();
			Occurrence tmp = kws.get(keyword);
			if (keywordsIndex.containsKey(keyword))
			{
				//System.out.println("Contains Keyword Beginning");
				ArrayList<Occurrence>  list = keywordsIndex.get(keyword);
				list.add(tmp);
				insertLastOccurrence(list);
				//System.out.println("Contains KeyWord");
			}
			else
			{
				//System.out.println("Does not contain keyword beginning");
				occurrences.add(tmp);
				insertLastOccurrence(occurrences);
				keywordsIndex.put(keyword, occurrences);
				//System.out.println("Does Not Contain Keyword");
			}
			//System.out.println("REACHED END OF WHILE LOOP");
		}
		//System.out.println("END OF MERGEKEYWORDS");
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		boolean trail = true;
		word = word.toLowerCase();
		String s = "";
		for (int i = 0; i < word.length(); i++)
		{
			if (!trail)
			{
				if (word.charAt(i)!= '.' && word.charAt(i)!= ','
						&& word.charAt(i)!= '?' &&  word.charAt(i)!= ':' &&
						word.charAt(i)!= ';' && word.charAt(i)!= '!')
				{
					//System.out.println("NULL: " + word);
					return null;
				}
			}
			if (word.charAt(i)!= '.' && word.charAt(i)!= ','
					&& word.charAt(i)!= '?' &&  word.charAt(i)!= ':' &&
					word.charAt(i)!= ';' && word.charAt(i)!= '!')
			{ 
				s += word.charAt(i);
				//System.out.println("s: " + s);
			} 
			else 
			{
				trail  = false;
			}
		}
		for (int i = 0; i < s.length(); i ++)
		{
			if (!Character.isLetter(s.charAt(i)))
			{
				//System.out.println("NULL: " + word);
				return null;
			}
		}
		String value = noiseWords.get(s);
		if (s.equals(value))
		{
			return null;
		}
		//System.out.println("WORD: " + word + " KEYWORD: " + s);
		return s;
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		if (occs.size() == 1)
			return null;
		ArrayList<Integer> midpoints = new ArrayList<Integer>();
		Occurrence justAdded= occs.remove(occs.size() - 1);
		int left = 0 ;
		int right = occs.size()-1;
		//System.out.println("Beginning of insertlastoccurrence");
		insertLastOccurrence(occs, midpoints, left, right, justAdded);
		//System.out.println("INSERTLASTOCCURRENCE: " + occs);
		return midpoints;
	}
	private static void insertLastOccurrence(ArrayList<Occurrence> occs, ArrayList<Integer> midpoints, int left, int right, Occurrence justAdded)
	{
		int mid = (left + right)/2;
		if (left > right)
		{
			occs.add(left, justAdded);
		}
		else
		{
			midpoints.add(mid);
			if (occs.get(mid).frequency > justAdded.frequency)
			{
				insertLastOccurrence(occs, midpoints, mid + 1, right, justAdded);
			}
			else if (occs.get(mid).frequency < justAdded.frequency)
			{
				insertLastOccurrence(occs, midpoints, left, mid -1, justAdded);
			}
			else
			{
				occs.add(mid, justAdded);
			}
		}
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		kw1 = kw1.toLowerCase();
		kw2 = kw2.toLowerCase();
		ArrayList<Occurrence> list = new ArrayList<Occurrence>();
		ArrayList<Occurrence> list2 = new ArrayList<Occurrence>();
		ArrayList<String> results = new ArrayList<String>();
		if (keywordsIndex.get(kw1) != null)
		{
			list = keywordsIndex.get(kw1);
		}
		if (keywordsIndex.get(kw2) != null)
		{
			list2 = keywordsIndex.get(kw2);
		}
		if (list.isEmpty())
		{
			for (int i = 0; i < list2.size(); i ++)
			{
				if (results.size() >= 5)
					break;
				String doc = list2.get(i).document;
				results.add(doc);
			}
		}
		else if (list2.isEmpty())
		{
			for (int i = 0; i < list.size(); i ++)
			{
				if (results.size() >= 5)
					break;
				String doc = list.get(i).document;
				results.add(doc);
			}
		}
		else
		{
			for (int i = 0; i < list.size(); i ++)
			{
				int freq = list.get(i).frequency;
				String doc = list.get(i).document;
				if (results.size() < 5)
				{
					for (int j = 0; j < list2.size(); j ++)
					{
						int freq2 = list2.get(j).frequency;
						String doc2 = list2.get(j).document;
						if (results.size() < 5)
						{
							if (freq >= freq2)
							{
								if (!results.contains(doc))
								{
									results.add(doc);
								}
							}
							else 
							{
								if (!results.contains(doc2))
								{
									results.add(doc2);
								}
							}
							
						}
					}
				}
			}
		}
		return results;
	}
	/*public static void main(String [] args)
	{
		ArrayList<Occurrence> list = new ArrayList<Occurrence>();
		LittleSearchEngine obj = new LittleSearchEngine();
		Occurrence one = new Occurrence("hi", 12);
		list.add(one);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence two = new Occurrence("hi", 8);
		list.add(two);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence three = new Occurrence("hi", 7);
		list.add(three);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence four = new Occurrence("hi", 5);
		list.add(four);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence five = new Occurrence("hi", 3);
		list.add(five);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence six = new Occurrence("hi", 2);
		list.add(six);
		System.out.println(obj.insertLastOccurrence(list));
		Occurrence seven = new Occurrence("hi", 6);
		list.add(seven);
		System.out.println(obj.insertLastOccurrence(list));
	}*/
}