package LeetCode;

public class Reverse_Words_in_a_String_III_557 {
	//Iteration through String
	public static String reverseWords(String s){
		StringBuilder word = new StringBuilder();
		StringBuilder result = new StringBuilder(); 
		for (int i = 0; i < s.length(); i ++){
			if (s.charAt(i) != ' '){
				word.append(s.charAt(i));
			}
			else{
				result.append(word.reverse());
				result.append(" ");
				word.setLength(0);
			}
		}
		result.append(word.reverse());
		return result.toString();
	}
	//Pointers
	public static String reverseWords2(String s){
		char letters[] = s.toCharArray();
		int i = 0, j = 0;
		for (j = 0; j < letters.length; j ++){
			if (letters[j] == ' '){
				reverse(letters, i , j-1);
				i = j + 1;
			}
		}
		reverse(letters, i, j-1);
		return new String(letters);
	}
	private static void reverse(char s[], int x, int y){
		while (x < y){
			char t = s[x];
			s[x++] = s[y];
			s[y--] = t;
		}
	}
	
	public static void main(String [] args){
		String input = "Let's take LeetCode contest";
		System.out.println(reverseWords2(input));
	}
}
