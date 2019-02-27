package ctci;
import java.util.Arrays;

public class CTCI {
	
	public static boolean isUnique(String str){
		if (str.length() > 128){
			return false;
		}
		boolean [] arr = new boolean[128];
		for (int i = 0; i < str.length(); i++){
			int charVal = str.charAt(i);
			if (arr[charVal]){
				arr[charVal] = true;
			}
		}
		return true;
		/*If there are no other data structures, we could brute force it with 0(n^2) by comparing each 
		character to every other character. Or we could sort the string using 0(n log n) then linearly
		proceed through both strings comparing using 0(n)*/
	}
	public static String sortString(String inputString){
		char strArray[] = inputString.toCharArray();
		Arrays.sort(strArray);
		return new String(strArray);
	}
	public static boolean checkPermutation(String first, String second){
		if (first.length() != second.length()){
			return false;
		}
		return sortString(first).equals(sortString(second));
	}
	
	public void URLify(char [] str, int truelength){
		int whitespaceCounter = 0;
		for(int i = 0; i < truelength; i++){
			if (str[i] == ' '){
				whitespaceCounter += 1;
			}
		}
		int index = truelength + whitespaceCounter * 2;
		if (truelength < str.length){
			str[truelength] = '\0';
		}
		for (int i = truelength- 1; i >= 0; i--){
			if (str[i] == ' '){
				str[i] = '0';
				str[i-1] = '2';
				str[i-2] = '%';
				index = index - 3;
			}
			else{
				str[index - 1] = str[i];
				index--;
			}
		}
	}
	
	public boolean OneAway(String first, String second){
		if (first.length() == second.length()){
			
		}
	}
	public static void main(String [] args){
		System.out.println(3/2);
		return;
	}
}

