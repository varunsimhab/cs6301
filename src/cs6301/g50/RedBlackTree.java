
/** Starter code for Red-Black Tree
 */
package cs6301.g50;

import java.util.NoSuchElementException;
import java.util.Stack;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T>{

    /**
     * Entry class does RB Tree functionality
     */
    class Entry extends BST<T>.Entry {
        private boolean isRed;
        Entry(T x, Entry left, Entry right) {
            super(x, left, right);
            isRed = true;
        }

        Entry(T x) {
            super(x);
            isRed = true;
        }

        Entry getRight(){
            return (Entry) right;
        }

        Entry getLeft(){
            return (Entry) left;
        }
    }


    /**
     * Constructor
     */
    RedBlackTree() {
	super();
    }


    /**
     * Return black if null to mimic sentinel nodes else return isRed
     */
    public boolean isRed(Entry h){
        if(h == null) return false;
        return h.isRed;
    }
    

    /**
     * Checks if isEmpty
     */
    public boolean isEmpty() {
        return root == null;
    }
    

    /**
     * Returns the Entry of Root
     */
    private Entry getRoot(){
        return (Entry) root;
    }
    

    /**
     * Add element to Tree
     */
    public boolean add(T t){
        root = add(getRoot(), t);
        getRoot().isRed = false;
        return true;
    }


    /**
     * Private function to add to Tree
     */
    private Entry add(Entry h, T t) {
        if (h == null) return new Entry(t);

        int cmp = t.compareTo(h.element);
        if      (cmp < 0) h.left  = add(h.getLeft(),  t);
        else if (cmp > 0) h.right = add(h.getRight(), t);
        else              h.element   = t;

        if (isRed(h.getRight()) && !isRed(h.getLeft()))      h = rotateLeft(h);
        if (isRed(h.getLeft()) &&  isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) &&  isRed(h.getRight()))     flipColors(h);

        return h;
    }


    /**
     * Private Function to rotate right
     */
    private Entry rotateRight(Entry h) {
        Entry x = h.getLeft();
        h.left = x.getRight();
        x.right = h;
        x.isRed = x.getRight().isRed;
        x.getRight().isRed = true;
        return x;
    }


    /**
     * Private Function to rotate left
     */
    private Entry rotateLeft(Entry h) {
        Entry x = h.getRight();
        h.right = x.getLeft();
        x.left = h;
        x.isRed = x.getLeft().isRed;
        x.getLeft().isRed = true;
        return x;
    }


    /**
     * Private function to Flip Colors
     */
    private void flipColors(Entry h) {
        h.isRed = !h.isRed;
        h.getLeft().isRed = !h.getLeft().isRed;
        h.getRight().isRed = !h.getRight().isRed;
    }


    /**
     * Private function to move RedRight
     */
    private Entry moveRedRight(Entry h) {
        flipColors(h);
        if (isRed(h.getLeft().getLeft())) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }


    /**
     * Private function to move RedLeft
     */
    private Entry moveRedLeft(Entry h) {
        flipColors(h);
        if (isRed(h.getRight().getLeft())) {
            h.right = rotateRight(h.getRight());
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }


    /**
     * Private function to balance
     */
    private Entry balance(Entry h) {
        if (isRed(h.getRight()))                      h = rotateLeft(h);
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) && isRed(h.getRight()))     flipColors(h);

        return h;
    }


    /**
     * Private function to remove the minimum value for a tree starting at h
     */
    private Entry removeMin(Entry h) {
        if (h.left == null)
            return null;

        if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
            h = moveRedLeft(h);

        h.left = removeMin(h.getLeft());
        return balance(h);
    }


    /**
     * Function to remove an element
     */
    public Entry remove(T key) {
        if (key == null) throw new IllegalArgumentException("argument to remove() is null");
        if (!contains(key)) return null;

        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
            getRoot().isRed = true;

        root = remove(getRoot(), key);
        if (super.size()==0) getRoot().isRed = false;
    }


    /**
     * Private function to remove an element
     */
    private Entry remove(Entry h, T key)
    {
        if (key.compareTo(h.element) < 0)  {
            if (!(isRed(h.getLeft())) && !(isRed(h.getLeft().getLeft())))
                h = moveRedLeft(h);
            h.left = remove(h.getLeft(), key);
        }
        else {
            if ((isRed(h.getLeft())))
                h = rotateRight(h);
            if (key.compareTo(h.element) == 0 && (h.right == null))
                return null;
            if (!(isRed(h.getRight())) && !(isRed(h.getRight().getLeft())))
                h = moveRedRight(h);
            if (key.compareTo(h.element) == 0) {
                Entry x = (Entry) min(h.getRight());
                h.element = x.element;
                h.right = removeMin(h.getRight());
            }
            else h.right = remove(h.getRight(), key);
        }
        return balance(h);
    }

    public static void main( String [ ] args )
    {
        RedBlackTree<Integer> t = new RedBlackTree<>( );
        final int NUMS = 46;

        for( int i = 0; i != NUMS; i++ )
            t.add( i );
    }
}