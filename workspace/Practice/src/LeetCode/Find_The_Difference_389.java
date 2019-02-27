package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Find_The_Difference_389 {
	//Initial Solution
    public char findTheDifference(String s, String t) {
    	Map <Character, Integer> map = new HashMap<Character, Integer>();
    	for (int i = 0; i < s.length(); i++){
    		if (map.containsKey(s.charAt(i))){
    			map.put(s.charAt(i), map.get(s.charAt(i))+1);
    		}
    		else{
        		map.put(s.charAt(i), 1);
    		}
    	}
    	for (int i = 0; i < t.length(); i++){
    		if (map.containsKey(t.charAt(i))){
    			map.put(t.charAt(i), map.get(t.charAt(i))-1);
    			if (map.get(t.charAt(i)) == -1)
    				return t.charAt(i);
    		}
    		else
    			return t.charAt(i);
    	}
    	return 'z';
    }
    //Much More Optimal
    public char findTheDifferenceOptimal(String s, String t){


    }
    public static void main(String [] args){
    	Find_The_Difference_389 obj = new Find_The_Difference_389();
    	System.out.println(obj.findTheDifferenceOptimal("a","aa"));
    }
}

