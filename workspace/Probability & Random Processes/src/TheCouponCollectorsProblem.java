import java.util.Arrays;

public class TheCouponCollectorsProblem {

	public static int RecKNums(int n)
	{
		int count = 0;
		int [] IntArray = new int [n];
		while (true)
		{
			count++;
			int random = (int) (Math.random() * n)+1;
			System.out.println(random);
			for (int j = 0; j < n; j++)
			{
				if (IntArray[j] == 0)
				{
					IntArray [j] = random;
					break;
				}
				else if (IntArray[j] == random)
				{
					break;
				}
			}
			if (IntArray[n-1] != 0)
			{
				break;
			}
		}
		System.out.println("ARRAY: " + Arrays.toString(IntArray));
		return count;
	}
	public static void main (String [] args)
	{
		System.out.println("Number of Draws: " + RecKNums(5));
	}
}
