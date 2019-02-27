package LeetCode;

import java.util.Arrays;

public class Move_Zeroes_283 {
    public static void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        for (i = 0; i < nums.length; i ++ ){
        	if (nums[i] != 0){
        		int tmp = nums[j];
        		nums[j] = nums[i];
        		nums[i] = tmp;
        		j++;
        	}
        }
    }
    public static void main(String[] args){
    	int [] nums = {0, 1, 0, 3, 12};
    	moveZeroes(nums);
    	System.out.println(Arrays.toString(nums));
    }
}
