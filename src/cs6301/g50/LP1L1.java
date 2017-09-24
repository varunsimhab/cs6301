// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g50;

public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num("500");
	Num y = new Num("4");
	Num z = Num.product(x, y);
	
	//System.out.println(x);
	
	System.out.println(x);
	//System.out.println(y);
	Num a = Num.power(x, 5);
	Num c = Num.mod(x, y);
	//Num b = Num.power(x, y);
	System.out.println(x);
	System.out.println(y);
	
	//Num c = Num.mod(x, y);
	//Num d = Num.divide(y,new Num("2"));
	//System.out.println(x);
	//System.out.println(y);
	//System.out.println(z);
	//System.out.println(a);
	//System.out.println(b);
	//System.out.println(c);
	//System.out.println(d);
	//z.printList();
    }
}
