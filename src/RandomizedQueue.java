import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	int n;
	Object[] data;
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	n=0;
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
    	++n;
    	Object[] dataCopy;
    	if(data.length<n) { //Resize
    		int newSize = data.length*2;
    		dataCopy = new Object[newSize];
    		for(int i=0; i<n; i++) {
    			dataCopy[i] = data[i];
    		}
    		data = dataCopy;
    	}
    }

    // remove and return a random item
    public Item dequeue() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        // last element -> n-1
    	StdRandom.shuffle(data, 0, n - 1);
    	Item lastItem = (Item) data[n-1];
    	data[n-1] = null;
    	return lastItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    	StdRandom.shuffle(data, 0, n - 1);
    	Item lastItem = (Item) data[n-1];
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
    	
    }

}