///**
// * Created by Varun on 10/14/2017.
// */
//
///*
// * Created by
// * Group 50
// *
// * Varun Simha Balaraju
// * Venkata Sarath Chandra Prasad Nelapati
// * Jithin Paul
// * Sunit Mathew
// *
// */
//import java.util.*;
//
//class BSTIterator <T extends Comparable<T>>  {
//    private Stack<BST.Entry> stack;
//
//    public BSTIterator(BST.Entry root) {
//        stack = new Stack<BST.Entry>();
//        traverseLeft(root);
//    }
//
//    /** return whether we have a next smallest number */
//    public boolean hasNext() {
//        return !stack.isEmpty();
//    }
//
//    /**
//     *  returns the next smallest number
//     * */
//
//    public BST.Entry next() {
//        BST.Entry t = stack.pop();
//        traverseLeft(t.right);
//        return t;
//    }
//
//    private void traverseLeft(BST.Entry t){
//        while (t!= null) {
//            stack.push(t);
//            t = t.left;
//        }
//    }
//
//
//}