package LeetCode;

import java.util.HashMap;

public class Ransom_Note_383 {
	//71ms
    public static boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < magazine.length(); i++){
        	char letter = magazine.charAt(i);
        	if (map.containsKey(letter)){
        		int count = map.get(letter);
        		map.put(letter, count + 1);
        	}
        	else{
        		map.put(letter, 1);
        	}
        }
        for (int i = 0; i < ransomNote.length(); i++){
        	char letter = ransomNote.charAt(i);
        	if (map.containsKey(letter)){
        		int count = map.get(letter);
        		if (count-1 < 0){
        			return false;
        		}
        		map.put(letter, count-1);
        	}
        	else{
        		return false;
        	}
        }
        return true;
    }
    //Much more optimal; 19 ms
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] arr = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if(--arr[ransomNote.charAt(i)-'a'] < 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String [] args){
    	String ransomNote = "aa";
    	String magazine = "aab";
    	System.out.println(canConstruct(ransomNote, magazine));
    	
    }
}
