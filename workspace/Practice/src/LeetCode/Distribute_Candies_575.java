package LeetCode;

import java.util.HashMap;
import java.util.HashSet;

public class Distribute_Candies_575 {
    public int distributeCandies(int[] candies) {
        int half = candies.length/2;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < candies.length; i ++){
            if (!map.containsKey(candies[i])){
            	map.put(candies[i], candies[i]);
            	count++;
            }
        }
        return Math.min(half, count);
    }
    //Sets only add unique elements; seems to be slightly faster
    public int distributeCandies2(int[] candies){
    	HashSet<Integer> set = new HashSet<Integer>();
    	for (int i = 0; i < candies.length; i ++){
    		set.add(candies[i]);
    	}
    	return Math.min(set.size(), candies.length/2);
    }
}
