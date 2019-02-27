package LeetCode;

import java.util.HashMap;

public class AddTwoNumbers_2 {

    public static int lengthOfLongestSubstring(String s) {
        HashMap <Character, Integer> map = new HashMap<Character, Integer>();
        int count = 0;
        int longest = 0;
        int last = 0;
        for (int i = 0; i < s.length(); i ++ ){
            if (!map.containsKey(s.charAt(i))){
            	System.out.println(s.charAt(i) + " IS NOT IN MAP");
                map.put(s.charAt(i), i);
                count++;
                System.out.println("COUNT: " + count);
            }
            else{
            	System.out.println(s.charAt(i) + " IS IN MAP");
            	last = Math.max(map.get(s.charAt(i)), last);
            	map.remove(s.charAt(i));
            	System.out.println("LAST: "+ last);
                map.put(s.charAt(i), i);
                count = i - last;
                System.out.println("COUNT: " + count);
            }
            if (count > longest)
                longest = count;
        }
        return longest;
    }
    public static void main(String[] args){
    	System.out.println(lengthOfLongestSubstring("dvdf"));
    }
}
