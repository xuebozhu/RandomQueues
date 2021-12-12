import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	public static void main(String[] args) {
		// TEST uniform distribution
		try {
			int n1 = Integer.parseInt(args[0]);

			// Randomized Queue
			RandomizedQueue<String> cola = new RandomizedQueue<String>();

			if (n1 <= 0)
				throw new IllegalArgumentException();

			// testear indice --> Last string is not taken
			while (!StdIn.isEmpty()) {
				String value = StdIn.readString();
//				StdOut.println(value);
				cola.enqueue(value);
			}

			// Dequeue
			int i = 0;
			while (i < n1) {
				String res = cola.dequeue();
				StdOut.println(res);

				i++;
			}

		} catch (NumberFormatException ex) {
//			System.out.println("Error: Not a number in args");
		}
	}
}