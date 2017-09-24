// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g50;

public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num("1");
	Num y = new Num("2");
	System.out.println(x.compareTo(y));
	Num z = Num.divide(x, y);
	
	System.out.println(x);	
	System.out.println(y);
	Num a = Num.squareRoot(x);
	System.out.println(a);
	System.out.println(x);	
	System.out.println(y);
	System.out.println(z);
	//Num a = Num.subtract(x, y);
	//Num b = Num.power(x, 23);
	//Num d = Num.mod(x, y);
	//System.out.println(x);	
	//System.out.println(y);
	//Num b = Num.power(x, 234);
	//Num c = Num.power(x, y);
	//Num e = Num.mod(b, y);
	//Num d = Num.divide(y,new Num("2"));
	//System.out.println(x);
	//System.out.println(y);
	//System.out.println(y);
	//System.out.println(z);
	//System.out.println(b);
	// b = Num.power(b, 23);
	 //c = Num.power(c, y);
	//System.out.println(b);
	//System.out.println(c);
	//System.out.println(e);
	//z.printList();
    }
}
