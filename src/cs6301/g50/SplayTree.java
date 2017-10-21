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

public class SplayTree<T extends Comparable<? super T>> extends BST<T> {
	
	
    SplayTree() {
	    super();
    }
    
    
    
    /* Checks if 'x' is part of the tree */
    public boolean contains(T x) {
    	boolean ret = super.contains(x);
    	splay(x);
	    return ret;
    }
    
    
    
    /* Returns the element in tree that is equal to 'x', null otherwise. */
    public T get(T x) {
    	T ret = super.get(x);
    	splay(ret);
	    return ret;
    }
    
    

    /*  If tree contains a node with same key, replaces element by x. 
      * Returns true if x is a new element added to tree.*/
    public boolean add(T x) {
    	boolean ret = super.add(x);
    	splay(x);
	    return ret;
    }
    
    
    
    /* Removes 'x' from tree. Returns 'x' if found, otherwise returns null */
    public T remove(T x) {
    	T ret = super.remove(x);
    	splay(ret);
	    return ret;
    }
    
    
    /* Returns the maximum element */
    public T max(){
    	T ret = super.max();
    	splay(ret);
	    return ret;
    }
    
    
    /* Returns the minimum element */
    public T min(){
    	T ret = super.max();
    	splay(ret);
	    return ret;
    }
    
    
    /* Rearranges the tree with rotations such that 'x' is at the root of the tree. */
    public void splay(T x){
    	
    }
       
}