package LeetCode;

public class Add_Digits_258 {
    public static int addDigits(int num) {
        if (num == 0)
            return 0;
        if (num % 9 != 0)
        	return num%9;
        else
        	return 9;
    }
    public static void main(String [] args){
    	System.out.println(addDigits(18));
    }
}
