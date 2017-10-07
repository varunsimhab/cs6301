// Sample code for Level 2 driver for lp1

// Change following line to your group number
package cs6301.g50;

public class LP1L2 {
    public static void main(String[] args) {
	Num x = new Num(999);
	Num z = Num.squareRoot(x);
	System.out.println(z);
	z.printList();
    }
}
