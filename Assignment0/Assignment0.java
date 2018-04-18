/**
 * 
 */

/**
 * @author redwards
 *Tom Nguyen
 *CS310 TTh 1400-1515
 */
public class Assignment0 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long elapsed, time=0;

		for (int n=100; n < 1000; n *= 2) {
			time = System.nanoTime();


			int [] x=new int[n];
			for (int i=1; i<n; i++) 
				x[i]=x[i-1]*2;

			elapsed = System.nanoTime() - time;
			System.out.print(n + "\t" + elapsed);

			time = System.nanoTime();
			x=new int[n];
			for (int i=1; i<n; i++)
				for (int j=1; j<n; j++)
					x[j]=x[j-1]*2;

			elapsed = System.nanoTime() - time;
			System.out.println("\t" + elapsed);


		}

	}
}
