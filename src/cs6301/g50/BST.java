import java.util.Comparator;
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
public class BST<T extends Comparable<T>>{

    class Entry<T>{
        T element;
        Entry<T> left,right;
        Entry(T x){
            this.element=x;
        }
    }
    public Stack s = new Stack<Entry<T>>();
    public Entry<T> root;

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

    private int compare(T one,T two) {
      return one.compareTo(two);
    }

    Entry<T> find(T x){
        s=new Stack<Entry<T>>();
        s.push(null);
        return find(root,x);
    }

    Entry<T> find(Entry t,T x){
        //s.push(null);
        if(t == null) return null;
        if(t.element == x) return t;

        while(isLessThan(x, (T)t.element) || isGreaterThan(x, (T)t.element)){
            if(isLessThan(x, (T)t.element)){
                if(t.left==null){
                    return t;
                }

                s.push(t);
                t=t.left;
            }
            else if(isGreaterThan(x, (T)t.element)){
                if(t.right==null){
                    return t;
                }

                s.push(t);
                t=t.right;
            }
        }

        return t;
    }

    private boolean isLessThan(T firstElement, T otherElement) {
        return compare(firstElement, otherElement) == -1;
    }

    private boolean isGreaterThan(T firstElement, T otherElement) {
        return compare(firstElement, otherElement) == 1;
    }



    public boolean contains(T x){
        Entry t=find(x);
        return (t!=null&&t.element.equals(x));
    }

    private <T extends Entry> T min(){
        if(root==null){
            return null;
        }
        Entry t=root;
        while(t.left!=null){
            t=t.left;
        }
        return (T) t.element;

    }


    private <T extends Entry> T max(){
        if(root==null){
            return null;
        }
        Entry t=root;
        while(t.right!=null){
            t=t.right;
        }
        return (T) t.element;

    }


    public Boolean add(T x)
    {
        if (root == null) {
            root=new Entry<>(x);
            size=1;
            return true;
        }
        Entry t=find(x);
        if(compare(x,(T)t.element)==0){
            t.element=x;
            return false;
        }
        else if(compare(x,(T)t.element)==-1) {
            t.left = new Entry<>(x);
        }
        else{
             t.right= new Entry<>(x);
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
        T result=(T)t.element;
        if(t.left==null||t.right==null){
            bypass(t);
        }
        else{
            s.push(t);
            Entry minRight=find(t.right,(T)t.element);
            t.element=minRight.element;
            bypass(minRight);
        }
        size--;
        return result;


    }

    public void bypass (Entry<T> t){

        Entry pt= (Entry)s.peek();
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

    public void iterator() {
        iterator(root);
    }
    public void iterator(Entry<T> root){
       BSTIterator it= new BSTIterator(root);
        while(it.hasNext()){
            System.out.println(it.next().element);
        }
    }




}
