
public class Fibonacci {
	public static int FibonacciNum(int n)
	{
		if (n==1)
		{
			return 1;
		}
		if (n==2)
		{
			return 1;
		}
		return FibonacciNum(n-1)+FibonacciNum(n-2);
	}
	public static void main(String [] args)
	{
		System.out.print(FibonacciNum(20));
	}
}
