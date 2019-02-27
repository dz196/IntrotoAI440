package recitation;

public class ProblemSet10 {

	public void printGreater(int [] H, int n , int k){
		System.out.println("NVALUE: " + n);
		this.maxNums(H, n, k, 0);
	}
	
	private void maxNums( int [] H, int n, int k, int i)
	{
		if (i > n-1)
			return;
		System.out.println("IVALUE: " + i);
		if (H[i] > k)
		{
			System.out.println(H[i]);
			maxNums( H , n , k , 2*i+1);
			maxNums( H , n , k , 2*i+2);
		}
		else
			return;
	}
	public int factorial(int n)
	{
		if (n == 1)
			return 1;
		int j = n * this.factorial(n-1);
		System.out.println(j);
		return j;
	}
	public static void main (String [] args)
	{
		int [] array = new int[10];
		int j = 10;
		for (int i = 0; i < array.length; i++)
		{
			array[i] = j;
			j--;
		}
		ProblemSet10 object = new ProblemSet10(); 
		System.out.println("ARRAYLENGTH: " + array.length);
		object.printGreater( array, array.length, 5);
	}
}
