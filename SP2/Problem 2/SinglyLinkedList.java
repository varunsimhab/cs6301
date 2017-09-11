/*
 * Created by
 * Group 50
 *
 * Varun Simha Balaraju
 * Venkata Sarath Chandra Prasad Nelapati
 * Jithin Paul
 * Sunit Mathew
 * on 9/10/2017.
 */

//package cs6301.g50;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {

    /* Class Entry holds a single node of the list */
    static class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }

    /* Dummy header is used.  tail stores reference of tail element of list */
    Entry<T> head, tail;
    int size;

    public SinglyLinkedList() {
        head = new Entry<>(null, null);
        tail = head;
        size = 0;
    }

	public SinglyLinkedList(Entry<T> head, Entry<T> tail, int size) { // Parameterized Constructor
        this.head = new Entry<>(null, head);
        this.tail = tail;
        this.size = size;
    }
	
    public Iterator<T> iterator() { return new SLLIterator<>(this); }

    private class SLLIterator<E> implements Iterator<E> {
	    SinglyLinkedList<E> list;
	    Entry<E> cursor, prev;
	    boolean ready;  // is item ready to be removed?

	    SLLIterator(SinglyLinkedList<E> list) {
	        this.list = list;
	        cursor = list.head;
	        prev = null;
	        ready = false;
	    }

	    public boolean hasNext() {
	        return cursor.next != null;
	    }
	
	    public E next() {
	        prev = cursor;
	        cursor = cursor.next;
	        ready = true;
	        return cursor.element;
	    }


	    /* Removes the current element (retrieved by the most recent next())
	    Remove can be called only if next has been called and the element has not been removed*/
	    public void remove() {
	        if(!ready) {
		        throw new NoSuchElementException();
	        }
	        prev.next = cursor.next;
	        // Handle case when tail of a list is deleted
	        if(cursor == list.tail) {
		        list.tail = prev;
	        }
	        cursor = prev;
	        ready = false;  // Calling remove again without calling next will result in exception thrown
	        size--;
	    }
    }

    /* Add new elements to the end of the list */
    public void add(T x) {
	    tail.next = new Entry<>(x, null);
	    tail = tail.next;
	    size++;
    }
	
	/* Return the size of the list */
	public int size() {
	   return this.size;
    }

    public void printList() {
	    System.out.print(this.size + ": ");
	    for(T item: this) {
	        System.out.print(item + " ");
	    }
	    System.out.println();
    }

    // Rearrange the elements of the list by linking the elements at even index
    // followed by the elements at odd index. Implemented by rearranging pointers
    // of existing elements without allocating any new elements.
    public void unzip() {
	    if(size < 3) {  // Too few elements.  No change.
	        return;
	    }

	    Entry<T> tail0 = head.next;
	    Entry<T> head1 = tail0.next;
	    Entry<T> tail1 = head1;
	    Entry<T> c = tail1.next;
	    int state = 0;

	    while(c != null) {
	        if(state == 0) {
		        tail0.next = c;
		        tail0 = c;
		        c = c.next;
	        } 
			else {
		    tail1.next = c;
		    tail1 = c;
		    c = c.next;
	        }
	        state = 1 - state;
	    }
	    tail0.next = head1;
	    tail1.next = null;
    }
}

    