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
import java.util.NoSuchElementException;
import java.util.Random;

import SinglyLinkedList;

public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {
	
	 /*Default Constructor */
     public SortableList() {super();}

	 /*Parameterized Constructor */
	 public SortableList(Entry<T> head,Entry<T> tail,int size) {super(head, tail, size);}
	
	
	 /* Splits the List into two and returns them as SortableLists*/
	public void splitList(SortableList<T> l1,SortableList<T> l2) {
		int listSize = size();
		int mid = listSize/2;
		Entry<T> tail0 = head;
	    while(mid-- > 0) {
            tail0 = tail0.next;
	    }
		Entry<T> head1 = tail0.next;
	    tail0.next = null;
		l1 = new SortableList<T>(head.next,tail0,listSize/2);
		l2 = new SortableList<T>(head1,tail,listSize- listSize/2);
    }
	
		
	/*Merge this list with other list*/
	void merge(SortableList<T> otherList) {
		Entry<T> self = head.next;
		Entry<T> other = otherList.head.next;
		Entry<T> prev = null;
	    while(self != null && other != null) {
	        if(self.element.compareTo(other.element) < 0){
				prev = self;
				self = self.next;
			}else{
				Entry<T> temp = other;
				other = other.next;
				if(prev!=null)
				    prev.next = temp;
                temp.next = self;				
			}
	    }
		if(self==null && other != null && prev != null)
			prev.next = other;
    }
	
	
	/*Sort this list*/	
    void mergeSort() {
		int listSize = size();
		if(listSize < 2) return;		
		int mid = listSize/2;
		Entry<T> tail0 = head;
	    while(mid-- > 0) {
            tail0 = tail0.next;
	    }
		Entry<T> head1 = tail0.next;
	    tail0.next = null;
		SortableList<T> l1 = new SortableList<T>(head.next,tail0,listSize/2);
		SortableList<T> l2 = new SortableList<T>(head1,tail,listSize- listSize/2);
        l1.mergeSort();
        l2.mergeSort();
        l1.merge(l2);
    }
	
	
    public static<T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
	    list.mergeSort();
    }
	
	
	public static void main(String[] args) throws NoSuchElementException {
        int n = 10;
        //if(args.length > 0) {
          //  n = Integer.parseInt(args[0]);
        //}
        SortableList<Integer> lst = new SortableList<Integer>();
        Random generator = new Random();
        for(int i=1; i<=n; i++) {
            lst.add(generator.nextInt(100));
        }
		lst.mergeSort();
        lst.printList();
    }
}

/* Sample input:
   4 2 3 1 10 5 6 8 9 7
   
   Sample output:
   1 2 3 4 5 6 7 8 9 10 
*/