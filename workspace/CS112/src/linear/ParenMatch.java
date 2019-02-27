package linear;

public class ParenMatch {

	public static boolean parenMatch(String in){
		
		Stack<Character> s = new Stack<Character>();
		
		for(int i = 0; i<in.length(); i++){
			
			char c = in.charAt(i);
			if (c == '(' || c == '[' || c == '{'){
				s.push(c);
			}
			else{
				if(s.isEmpty())
					return false;
				
				char x = s.pop();
				
				if ((c == ')' && x=='(') ||
						(c == '[' && x==']') ||
						(c == '}' && x=='{'))
					continue;
				else
					return false;
			}
		}
		return s.isEmpty();
		
	}
	
	public static void main(String[] args){
		System.out.println( parenMatch("()[][]{}{}"));
		System.out.println( parenMatch("([)][{]{}}"));
	}
}
