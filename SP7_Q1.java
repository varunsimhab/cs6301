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

public class SP7_Q1 {
    public static void main (String[] args){
        BST bst= new BST();
        Integer[] a = {8,3,10,1,6,14,4,7,13};
        for(int i=0;i<a.length;i++){
            bst.add(a[i]);
        }
        // Adding an element to the BST
        bst.add(20);

        // Removing an element to the BST
        bst.remove(3);
        // Just making sure that the method"Contains" is working. Should return "false" as 3 was recently removed.
        System.out.println(bst.contains(3));

        // Finally iterating over the entire BST to display all the elements
        bst.iterator(bst.root);

    }
}
