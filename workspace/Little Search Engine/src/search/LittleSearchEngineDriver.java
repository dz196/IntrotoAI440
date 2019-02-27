package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LittleSearchEngineDriver {

	static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String [] args) throws IOException
	{
		LittleSearchEngine hashtables = new LittleSearchEngine();
		System.out.println("Enter file name with docs: ");
		String docsFile = keyboard.readLine();
		System.out.println("Enter file name with noisewords: ");
		String noiseWordsfile = keyboard.readLine();
		hashtables.makeIndex(docsFile, noiseWordsfile);
		System.out.println("Enter first string: ");
		String first = keyboard.readLine();
		System.out.println("Enter second string: ");
		String second = keyboard.readLine();
		System.out.println(hashtables.top5search(first, second));
		
	}
}
