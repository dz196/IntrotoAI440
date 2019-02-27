package LeetCode;

import java.util.Arrays;

public class Construct_The_Rectangle_492 {
    public static int[] constructRectangle(int area) {
        //int [] res = new int[2];
        int width = (int)Math.sqrt(area);
        while (area % width != 0){
        	width--;
        }
        return new int[]{area/width, width};
    }
    public static void main(String [] args){
    	System.out.println(Arrays.toString(constructRectangle(10)));
    }
}
