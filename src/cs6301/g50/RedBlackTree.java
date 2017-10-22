
/** Starter code for Red-Black Tree
 */
package cs6301.g50;

import java.util.NoSuchElementException;
import java.util.Stack;

public class RedBlackTree<T extends Comparable<? super T>> extends BST<T> {
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

    RedBlackTree() {
	super();
    }

    public boolean isRed(Entry h){
        if(h == null) return false;
        return h.isRed;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Entry getRoot(){
        return (Entry) root;
    }

    public T min(){
        if(root==null){
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(getRoot()).element;

    }

    private Entry min(Entry x) {
        if (x.left == null) return x;
        else                return min(x.getLeft());
    }

    public void add(T t){
        root = add(getRoot(), t);
        getRoot().isRed = false;
    }

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

    private Entry rotateRight(Entry h) {
        Entry x = h.getLeft();
        h.left = x.getRight();
        x.right = h;
        x.isRed = x.getRight().isRed;
        x.getRight().isRed = true;
        return x;
    }

    private Entry rotateLeft(Entry h) {
        Entry x = h.getRight();
        h.right = x.getLeft();
        x.left = h;
        x.isRed = x.getLeft().isRed;
        x.getLeft().isRed = true;
        return x;
    }

    private void flipColors(Entry h) {
        h.isRed = !h.isRed;
        h.getLeft().isRed = !h.getLeft().isRed;
        h.getRight().isRed = !h.getRight().isRed;
    }

    private Entry moveRedRight(Entry h) {
        flipColors(h);
        if (isRed(h.getLeft().getLeft())) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Entry moveRedLeft(Entry h) {
        flipColors(h);
        if (isRed(h.getRight().getLeft())) {
            h.right = rotateRight(h.getRight());
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Entry balance(Entry h) {
        if (isRed(h.getRight()))                      h = rotateLeft(h);
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) && isRed(h.getRight()))     flipColors(h);

        return h;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
            getRoot().isRed = true;

        root = deleteMin(getRoot());
        if (!isEmpty()) getRoot().isRed = false;
        // assert check();
    }

    private Entry deleteMin(Entry h) {
        if (h.left == null)
            return null;

        if (!isRed(h.getLeft()) && !isRed(h.getLeft().getLeft()))
            h = moveRedLeft(h);

        h.left = deleteMin(h.getLeft());
        return balance(h);
    }

    public void delete(T key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        if (!isRed(getRoot().getLeft()) && !isRed(getRoot().getRight()))
            getRoot().isRed = true;

        root = delete(getRoot(), key);
        if (super.size()==0) getRoot().isRed = false;
    }

    private Entry delete(Entry h, T key) {

        if (key.compareTo(h.element) < 0)  {
            if (!(isRed(h.getLeft())) && !(isRed(h.getLeft().getLeft())))
                h = moveRedLeft(h);
            h.left = delete(h.getLeft(), key);
        }
        else {
            if ((isRed(h.getLeft())))
                h = rotateRight(h);
            if (key.compareTo(h.element) == 0 && (h.right == null))
                return null;
            if (!(isRed(h.getRight())) && !(isRed(h.getRight().getLeft())))
                h = moveRedRight(h);
            if (key.compareTo(h.element) == 0) {
                Entry x = min(h.getRight());
                h.element = x.element;
                h.right = deleteMin(h.getRight());
            }
            else h.right = delete(h.getRight(), key);
        }
        return balance(h);
    }

    public void printLevels( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else{
            Stack<Entry> stack = new Stack<>();
            stack.push(getRoot());
            printLevels(stack);
        }
    }

    private void printLevels( Stack<Entry> stack )
    {
        Stack<Entry> fstack = new Stack<>();
        while (!stack.empty()){
            Entry node = stack.pop();
            if(node.isRed){
                System.out.print(node.element+" ");
            }else System.out.print(String.format("[%d] ",node.element));
            if(null!=node.right) fstack.push(node.getRight());
            if(null!=node.left) fstack.push(node.getLeft());
        }
        System.out.println();
        if(!fstack.empty()){
            printLevels(fstack);
        }
    }

    public static void main( String [ ] args )
    {
        RedBlackTree<Integer> t = new RedBlackTree<>( );
        final int NUMS = 46;

        for( int i = 0; i != NUMS; i++ )
            t.add( i );

        t.printLevels();
    }
}

