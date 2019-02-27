package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection_of_Two_Arrays_349 {
	
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> intersection = new HashSet<Integer>();
        for (int i = 0; i < nums1.length; i++){
        	set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++){
        	if (set.contains(nums2[i])){
        		intersection.add(nums2[i]);
        	}
        }
        int [] res = new int[intersection.size()];
        int count = 0;
        for (Integer num : intersection){
        	res[count++] = num;
        }
        return res;
    }
    public static void main(String[] args){
    	int [] nums1 = {1,2,2,1};
    	int [] nums2 = {2,2};
    	System.out.println(Arrays.toString(intersection(nums1,nums2)));
    }
}

