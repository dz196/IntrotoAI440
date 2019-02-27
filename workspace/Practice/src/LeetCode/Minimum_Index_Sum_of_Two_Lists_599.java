package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Minimum_Index_Sum_of_Two_Lists_599 {
	
    public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> restaurants = new ArrayList<String>();
        int leastIndexSum = 2000;
        for (int i = 0; i < list1.length; i++){
        	if (!map.containsKey(list1[i])){
        		map.put(list1[i], i);
        	}
        }
        for (int i = 0; i < list2.length; i++){
        	if (map.containsKey(list2[i])){
        		if (leastIndexSum >= i + map.get(list2[i])){
        			if (leastIndexSum > i + map.get(list2[i])){
        			leastIndexSum = i + map.get(list2[i]);
        			restaurants.clear();
        			}
        			restaurants.add(list2[i]);
        		}
        	}
        }
        String [] res = new String [restaurants.size()];
        int count = 0;
        for (String restaurant : restaurants){
        	res[count++] = restaurant;
        }
        return res;
    }
    public static void main(String[] args){
    	String [] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
    	String [] list2 = {"KFC", "Shogun", "Burger King"};
    	System.out.println(Arrays.toString(findRestaurant(list1, list2)));
    }
}
