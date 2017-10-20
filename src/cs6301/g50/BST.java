package cs6301.g50;

import java.util.Comparator;
import java.util.Iterator;
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

    class Entry{
        T element;
        Entry left,right;
        Entry(T x){
            this.element=x;
        }
    }

    public Stack<Entry> stack = new Stack<Entry>();

    public Entry root;

    int size;

    public BST() {
        this.root=null;
        this.size=0;
    }
    public BST(Comparator<T> cmp)
    {
        this.root = null;
        this.size=0;
    }

    Entry find(T x){
        stack =new Stack<Entry>();
        stack.push(null);
        return find(root,x);
    }

    Entry find(Entry t,T x){
        //stack.push(null);
        if(t == null) return null;
        if(t.element == x) return t;

        while(!x.equals(t.element)){
            if(x.compareTo(t.element)==-1){
                if(t.left==null){
                    return t;
                }

                stack.push(t);
                t=t.left;
            }
            else if(x.compareTo(t.element)==1){
                if(t.right==null){
                    return t;
                }

                stack.push(t);
                t=t.right;
            }
        }

        return t;
    }

    public boolean contains(T x){
        Entry t=find(x);
        return (t!=null&&t.element.equals(x));
    }

    private T min(){
        if(root==null){
            return null;
        }
        Entry t=root;
        while(t.left!=null){
            t=t.left;
        }
        return t.element;

    }


    private T max(){
        if(root==null){
            return null;
        }
        Entry t=root;
        while(t.right!=null){
            t=t.right;
        }
        return t.element;

    }


    public Boolean add(T x)
    {
        if (root == null) {
            root=new Entry(x);
            size=1;
            return true;
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
        size++;
        return true;
    }

    public T remove(T x){
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
        size--;
        return result;


    }

    public void bypass (Entry t){

        Entry pt= stack.peek();
        System.out.print(pt.element);
        Entry c=  t.left==null?t.right:t.left;
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
