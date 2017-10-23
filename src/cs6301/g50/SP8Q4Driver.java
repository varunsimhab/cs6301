package cs6301.g50;

import java.util.Random;

public class SP8Q4Driver {

    public static void main( String [ ] args )
    {
        Long bst = testTree(new double[]{0.25, 0.25, 0.10, 0.25}, new RedBlackTree());

        System.out.println(bst);
    }

    public static long testTree(double[] probArray, BST t){
        final int NUMS = 10000000;
        final int GAP  =   3;
        Timer timer = new Timer();

        System.out.println( "BST Test has begun" );
        timer.start();

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS)
        {
            Double val = new Random().nextDouble();
            Integer tval = new Random().nextInt(i);

            if(val<probArray[0]){
                t.add( tval );
            }
            if(val<probArray[1]){
                t.contains( tval );
            }
            if(val<probArray[2]){
                t.remove( tval );
            }
        }

        int k = t.size();

        timer.end();
        return timer.elapsedTime;
    }

}
