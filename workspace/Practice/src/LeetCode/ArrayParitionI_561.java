package LeetCode;

import java.util.Arrays;

public class ArrayParitionI_561 {
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = 0;
        for (int i = 0; i < nums.length; i += 2){
            n += nums[i];
        }
        return n;
    }
    public static void main(String[] args){
    	int [] nums = {1,4,3,2};
    	System.out.println(arrayPairSum(nums));
    }
}
