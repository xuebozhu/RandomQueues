import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node<Item> first, last;
	int n;
	//Node<Item>[] q;
	
	// helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }

    // construct an empty deque
    public Deque() {
    	first = null;
    	last = null;
    	n =0;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return first == null;
    }

    // return the number of items on the deque
    public int size() {
    	return this.n;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	//new -> first                  null <- last <- element 1 <- element 0 <- first
    	Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        oldfirst.previous = first;
        //isEmpty???
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	//new -> last
    	Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        
        last.previous = oldLast;
        //isEmpty???
        if (isEmpty()) first = last;
        else {          
        	oldLast.next = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        first.previous = null;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        // Last == before, last
        last = last.previous;
        last.next = null;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    // return an iterator over items in order from front to back
   /* public Iterator<Item> iterator(){
    	
    }
*/
    
    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;
        

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }

    
    // unit testing (required)
    public static void main(String[] args) {
    	String item1 = "primero";
    	String item2 = "segundo";
    	String item3 = "tercero";
    	
    	Deque<String> cola = new Deque<String>();
    	
    	cola.addLast(item1);
    	cola.addLast(item2);
    	cola.addLast(item3);
    	
//    	cola.addFirst(item2);
//    	cola.addFirst(item3);
    	
    	Iterator<String> it = cola.iterator();
    	
    	 while (it.hasNext())
         {
             // next() returns the next element in the iteration
             System.out.println(it.next());
         }
    	 
    	 System.out.println(cola.isEmpty());
    	 
    	 //Debugging
    	 System.out.println();
    	 
    	 it = cola.iterator();
    	 cola.removeFirst();
    	 cola.removeLast();
    	
    }

}