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
	
	/* Constructor */
    SplayTree() {
	    super();
    }


    /* Returns true if 'x' is part of the tree, false otherwise. */
    public boolean contains(T x) {
    	boolean ret = super.contains(x);
    	if(ret) stack.push(find(x));
    	splay();
	    return ret;
    }
    
    
    /* Returns the element in tree that is equal to 'x', null otherwise. */
    public T get(T x) {
    	T ret = super.get(x);
    	if(ret!=null) stack.push(find(x));
    	splay();
	    return ret;
    }
    

    /*  If tree contains a node with same key, replaces element by 'x'. 
        Returns true if 'x' is a new element added to tree. */
    public boolean add(T x) {
    	boolean ret = super.add(x);
    	stack.push(find(x));
    	splay();
	    return ret;
    }

    
    /* Removes 'x' from tree. Returns 'x' if found, otherwise returns null */
    public T remove(T x) {
    	T ret = super.remove(x);
    	splay();
	    return ret;
    }
    
    
    /* Returns the maximum element */
    public T max(){
    	T ret = super.max();
    	stack.push(find(ret));
    	splay();
	    return ret;
    }
    
    
    /* Returns the minimum element */
    public T min(){
    	T ret = super.max();
    	stack.push(find(ret));
    	splay();
	    return ret;
    }
    
    
    
     /* Utility method to right rotate subtree rooted at 'x' */
     public void rightRotate(Entry x){
         Entry y = x.left;
         x.left = y.right;
         y.right = x;
     }
  
 
 
     /* Utility method to left rotate subtree rooted at 'x' */
     public void leftRotate(Entry x){
         Entry y = x.right;
         x.right = y.left;
         y.left = x;
     }
 
 
    
    /* Rearranges the tree with rotations such that top element of stack is at the root of the tree. */
    public void splay(){
        if(stack.empty()) return;
    	Entry t = stack.pop();
    	while(t != root) {
            if(t==root.left) {rightRotate(root); root = t;}
    		else if(t==root.right) {leftRotate(root); root= t;}
    		else {
    			Entry p_t = stack.pop();
    			Entry g_t = stack.pop();
    			if(g_t.left==p_t && p_t.left==t) {
    			    rightRotate(g_t);
    				rightRotate(g_t);
    			}
    			else if(g_t.right==p_t && p_t.right==t) {
    				leftRotate(g_t);
				    leftRotate(g_t);
				}
    			else if(g_t.left==p_t && p_t.right==t) {
    				rightRotate(g_t);
				    leftRotate(g_t);
    			}
    			else if(g_t.right==p_t && p_t.left==t) {
    				leftRotate(g_t);
				    rightRotate(g_t);
    			}
    			if(g_t==root) root = t;
    			else if(stack.peek().left==g_t) stack.peek().left = t;
    			else stack.peek().right = t;
    		}
    	}
    }
       
}