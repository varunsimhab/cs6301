

import java.util.Iterator;



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

    public static class Sunit implements Comparable<Sunit>{

        int n1;

        int n2;



        public Sunit(int n1,int n2){

            this.n1 = n1;

            this.n2 = n2;

        }



        public int compareTo(Sunit s1){

            if(s1.n1<this.n1){

                return 1;

            } else if(s1.n1>this.n1){

                return -1;

            } else{

                return 0;

            }

        }



        @Override

        public String toString() {

            return String.format("Key:%d Value:%d",n1,n2);

        }

    }



    public static void main (String[] args){

        BST bst= new BST<Integer>();

        Integer[] a = {8,3,10,1,6,14,4,7,13};

        for(int i=0;i<a.length;i++){

            bst.add(a[i]);

        }

        // Adding an element to the BST

        bst.add(20);



        // Removing an element to the BST

        bst.remove(3);

        bst.remove(1);

        bst.remove(14);

        bst.remove(8);

        // Just making sure that the method"Contains" is working. Should return "false" as 3 was recently removed



        // Finally iterating over the entire BST to display all the elements

        Iterator it = bst.iterator();

        while (it.hasNext()){

            System.out.println(it.next());

        }



        System.out.println(bst.contains(3));

        System.out.println(bst.contains(6));

    }

}