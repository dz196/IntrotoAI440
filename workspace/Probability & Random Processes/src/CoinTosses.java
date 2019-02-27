import java.util.Random;

public class CoinTosses {

	
	public static void Prob()
	{
		double probability = (31.0/90)*10000;
		double count = 0.0; 
		double nheads = 0.0;
		for ( int i = 0; i < 100; i++){
			Random r = new Random();
			double randomValue = 10000*r.nextDouble();
			if ( randomValue <= probability){
				count ++; 
				nheads ++;
				System.out.println( nheads/count );
				}
			else {
				count ++;
				System.out.println( nheads/count);
			}
		}
		
	}
	
	public static void main(String [] args){
		Prob();
		
	}
}
