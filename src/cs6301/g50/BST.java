package cs6301.g50;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Created by Varun on 10/14/2017.
 */

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
public class BST<T extends Comparable<? super T>> implements Iterable<T> {

    private Stack<Entry> stack = new Stack<Entry>();
    protected Entry root;

    class Entry{
        T element;
        Entry left,right;
        Entry(T x){
            this.element=x;
        }
        Entry(T x, Entry left, Entry right){
            this.left = left;
            this.right = right;
            this.element=x;
        }
    }

    public BST() {
        this.root=null;
    }

    public BST(Comparator<T> cmp)
    {
        this.root = null;
    }

    public int size(){
        return size(root);
    }

    private int size(Entry x){
        if(x == null) return 0;
        else return 1+size(x.left)+size(x.right);
    }

    Entry find(T x){
        stack =new Stack<Entry>();
        if(root == null) return null;
        return find(root,x);
    }

    T get(T x){
        return find(x).element;
    }

    Entry find(Entry t,T x){
        if(t.element == x) return t;
        if(x.compareTo(t.element)==-1){
            if(t.left==null){
                return t;
            }
            stack.push(t);
            return find(t.left, x);
        }else if(x.compareTo(t.element)==1){
            if(t.right==null){
                return t;
            }
            stack.push(t);
            return find(t.right, x);
        }
        return t;
    }

    public boolean contains(T x){
        Entry t=find(x);
        return (t!=null&&t.element.equals(x));
    }

    public T min(){
        if(root==null){
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).element;

    }

    private Entry min(Entry x) {
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public T max(){
        if(root==null){
            return null;
        }
        Entry t=root;
        while(t.right!=null){
            t=t.right;
        }
        return t.element;

    }

    public boolean add(T x){
        if(x == null) throw new IllegalArgumentException("cannot call add() with a null element");

        if (root == null) {
            root=new Entry(x);
        }

        Entry t=find(x);

        if(x.compareTo(t.element)==0){
            t.element=x;
            return false;
        }
        else if(x.compareTo(t.element)==-1) {
            t.left = new Entry(x);
        }
        else{
            t.right= new Entry(x);
        }
        return true;
    }

    public T remove(T x){
        if(x == null) throw new IllegalArgumentException("cannot call remove() with a null element");
        if(root==null){
            return null;
        }

        Entry t= find(x);

        if(t.element!=x){
            return null;
        }

        T result=t.element;

        if(t.left==null||t.right==null){
            bypass(t);
        }
        else{
            stack.push(t);
            Entry minRight=find(t.right,t.element);
            t.element=minRight.element;
            bypass(minRight);
        }

        return result;
    }

    public void bypass (Entry t){
        Entry pt= stack.peek();
        Entry c =  t.left==null?t.right:t.left;
        if(pt==null){
            root=c;
        }
        else if(pt.left==t){
            pt.left=c;
        }
        else{
            pt.right=c;
        }
    }

    public Iterator iterator() {
        return new BSTIterator(root);
    }

    class BSTIterator implements Iterator {

        public BSTIterator(Entry root) {
            stack = new Stack<Entry>();
            traverseLeft(root);
        }

        /** return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         *  returns the next smallest number
         * */

        public T next() {
            Entry t = stack.pop();
            traverseLeft(t.right);
            return t.element;
        }

        private void traverseLeft(Entry t){
            while (t!= null) {
                stack.push(t);
                t = t.left;
            }
        }
    }


}