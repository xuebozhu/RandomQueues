import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int n;
	private Object[] data;

	// construct an empty randomized queue
	public RandomizedQueue() {
		n = 0;
		data = new Object[1];
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return data[0] == null;
	}

	// return the number of items on the randomized queue
	public int size() {
		return n;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException("Value not valid");
		++n;
		Object[] dataCopy;
		if (data.length < n) { // Resize
			int newSize = data.length * 2;
			dataCopy = new Object[newSize];
			for (int i = 0; i < n - 1; i++) {
				dataCopy[i] = data[i];
			}
			data = dataCopy;
		}
		data[n - 1] = item;

	}

	// remove and return a random item
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
		// last element -> n-1
		StdRandom.shuffle(data, 0, n - 1);
		Item lastItem = (Item) data[n - 1];
		data[n - 1] = null;
		n--;
		return lastItem;
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException("Queue is empty");
		StdRandom.shuffle(data, 0, n - 1);
		Item lastItem = (Item) data[n - 1];
		return lastItem;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		private int current = 0;
		private int[] iterorder;

		public RandomizedQueueIterator() {
			if (n > 0) {
				iterorder = new int[n];
				for (int i = 0; i < n; i++)
					iterorder[i] = i;
				StdRandom.shuffle(iterorder, 0, n - 1);
			}
		}

		public boolean hasNext() {
			return current < n;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (current >= n)
				throw new java.util.NoSuchElementException();
			Item item = (Item) data[iterorder[current]];
			current = current + 1;
			return item;
		}
	}

	// unit testing (required)
	public static void main(String[] args) {
		String item1 = "primero";
		String item2 = "segundo";
		String item3 = "tercero";
		String item4 = "cuarto";
		String item5 = "quinto";
		String item6 = "sexto";

		RandomizedQueue<String> cola = new RandomizedQueue<String>();

		cola.enqueue(item1);
		cola.enqueue(item2);
		cola.enqueue(item3);
		cola.enqueue(item4);
		cola.enqueue(item5);
		cola.enqueue(item6);

		// cola.addFirst(item2);
		// cola.addFirst(item3);

		Iterator<String> it = cola.iterator();
/*
		while (it.hasNext()) {
			// next() returns the next element in the iteration
			// System.out.println(it.next());
		}*/

		// System.out.println(cola.isEmpty());

		// Debugging
		// System.out.println();

		// it = cola.iterator();
		/*
		 * cola.removeFirst(); cola.removeLast();
		 */

	}

}