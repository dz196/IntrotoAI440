import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Practice {
	private int x;
	private int y;
	public Practice(int first, int second){
		this.x = first;
		this.y = second;
	}
	public int add(){
		return this.x + this.y;
	}
   
    public static void main(String[] args){
    	Practice practice = new Practice(5, 6);
    	System.out.println(practice.add());
    }
}