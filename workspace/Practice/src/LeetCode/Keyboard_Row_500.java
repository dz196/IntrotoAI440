package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Keyboard_Row_500 {
	
    public static String[] findWords(String[] words) {
        String[] rows = {"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for(int i = 0; i<rows.length; i++){
            for(char c: rows[i].toCharArray()){
                map.put(c, i);//put <char, rowIndex> pair into the map
            }
        }
        LinkedList<String> res = new LinkedList<String>();
        for(String word: words){
            if(word.equals("")) continue;
            int index = map.get(word.toUpperCase().charAt(0));
            for(char letter: word.toUpperCase().toCharArray()){
                if(map.get(letter)!=index){
                    index = -1; //don't need a boolean flag. 
                    break;
                }
            }
            if(index!=-1) res.add(word);//if index != -1, this is a valid string
        }
        return res.toArray(new String[0]);
    }
    public static void main(String[] args){
    	String [] words = {"Hello", "Alaska", "Dad", "Peace"};
    	System.out.println(Arrays.toString(findWords(words)));
    }
}
