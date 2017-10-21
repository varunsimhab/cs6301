/*
 * Created by
 * Group 50
 *
 * Varun Simha Balaraju
 * Venkata Sarath Chandra Prasad Nelapati
 * Jithin Paul
 * Sunit Mathew
 *
 */

package cs6301.g50;
import java.util.Comparator;
import java.util.Iterator;

public class BSTMap<K extends Comparable<? super K>, V> implements Iterable<K> {
	RedBlackTree rbt;
	
	
	
	/* class to store key-value pair*/
	class Pair implements Comparable<Pair>{
		K key;
		V value;
		
		Pair(K key, V value){this.key = key; this.value = value;}
		
		public int compareTo(Pair other) {
			return key.compareTo(other.key);
		}
	};
	
	
	
	/* Constructor of the class */
    BSTMap() {
    	rbt = new RedBlackTree<Pair>();
    }


    
    /*Get the value associated with the key, if present. Else return null*/
    public V get(K key) {
    	V dummy;
    	rbt.get(new Pair(key,dummy));
    }

    
    
    /* Add a new entry into the map */
    public boolean put(K key, V value) {
    	return rbt.put(new Pair(key,value));
    }

    
    
    /* Iterate over the keys stored in the map, in order */
    public Iterator<K> iterator() {
	    return rbt.Iterator();
    }
}
