
// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
package cs6301.g50;

import java.util.ArrayList;

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    static long base = defaultBase;  // Change as needed

    long val;
    Num next;
    boolean negsign;

    /* Start of Level 1 */
    Num(String s) {
        ArrayList<Character> char_s = new ArrayList<Character>();
        for (char c : s.toCharArray()) {
            char_s.add(c);
        }
        negsign = false;
        if(char_s.get(0)=='-'){
            negsign = true;
            char_s.remove(0);
        }
        if(!char_s.isEmpty()){
            this.val = Character.getNumericValue(char_s.get(char_s.size()-1));
            char_s.remove(char_s.size()-1);
            if(!char_s.isEmpty()){
                this.next = new Num(char_s, negsign);
            }
        }
    }

    Num(ArrayList<Character> char_s, boolean negsign) {
        this.val = Character.getNumericValue(char_s.get(char_s.size()-1));
        this.negsign = negsign;
        char_s.remove(char_s.size()-1);
        if(!char_s.isEmpty()){
            this.next = new Num(char_s, negsign);
        }
    }

    Num(long x) {
        if(x<0){
            val = -1*x;
            negsign = true;
        }else{
            val = x;
            negsign = false;
        }
    }

    static Num add(Num a, Num b, long carry) {
        long sum = b.val + a.val + carry;
        Num nextDigit = new Num(sum%base);
        carry = sum/base;

        if((null!=a.next)&&(null!=b.next)){
            nextDigit.next = add(a.next,b.next,carry);
        }else if(null!=a.next){
            nextDigit.next = add(a.next,carry);
        }else if(null!=b.next){
            nextDigit.next = add(b.next,carry);
        }else{
            if(carry>0){
                nextDigit.next = new Num(carry);
            }
        }
        return nextDigit;
    }

    static Num subtract(Num a, Num b, long carry) {
        long sum = a.val - b.val + carry;
        Num nextDigit;
        if(sum<0){
            carry = -1;
            nextDigit = new Num(sum+base);
        }else {
            nextDigit = new Num(sum%base);
            carry = sum/base;
        }

        if((null!=a.next)&&(null!=b.next)){
            nextDigit.next = subtract(a.next,b.next,carry);
            nextDigit.negsign = nextDigit.next.negsign;
        }else if(null!=a.next){
            nextDigit.next = subtract(a.next,carry);
            nextDigit.negsign = nextDigit.next.negsign;
        }else if(null!=b.next){
            nextDigit.next = subtract(b.next,carry);
            nextDigit.negsign = nextDigit.next.negsign;
        }else{
            if(carry<0){
                nextDigit.negsign = true;
            }
        }
        return nextDigit;
    }

    static Num add(Num a, Num b) {
        Num[] padded = padEqual(a,b);
        a = padded[0];
        b = padded[1];
        if(a.negsign&&b.negsign){
            return negate(add(a,b,0));
        }else if(!a.negsign&&!b.negsign){
            return add(a,b,0);
        }else{
            Num numNext;
            if(a.negsign){
                numNext = subtract(b,a,0);
            }else{
                numNext = subtract(a,b,0);
            }
            if(numNext.negsign){
                getComplement(numNext);
                numNext = add(numNext,1);
            }
            return numNext;
        }
    }

    static void getComplement(Num a){
        a.val = base-1-a.val;
        if(null!=a.next){
            getComplement(a.next);
        };
    }

    static Num copy(Num a){
        Num nextNum = new Num(a.val);
        nextNum.negsign = a.negsign;
        if(null!=a.next){
            nextNum.next=copy(a.next);
        }
        return nextNum;
    }

    static Num add(Num a, long carry){
        long sum = a.val + carry;
        Num nextDigit = new Num(sum%base);
        nextDigit.negsign = a.negsign;
        carry = sum/base;

        if(null!=a.next){
            nextDigit.next = add(a.next,carry);
        }else {
            if (carry > 0) {
                nextDigit.next = new Num(carry);
            }
        }

        return nextDigit;
    }

    static Num subtract(Num a, long carry){
        long sum = a.val - carry;
        Num nextDigit = new Num(sum%base);
        carry = sum/base;

        if(null!=a.next){
            nextDigit.next = subtract(a.next,carry);
        }else {
            nextDigit.val = base - 1;
            nextDigit.negsign = true;
        }

        return nextDigit;
    }

    static Num negate(Num a){
        a.negsign = !a.negsign;
        if(null!=a.next){
            a.next = negate(a.next);
            if(null==a.next.next&&a.next.val==0){
                a.next = null;
            }
        }
        return a;
    }

    static Num subtract(Num a, Num b) {
        Num[] padded = padEqual(a,b);
        a = padded[0];
        b = padded[1];
        if(a.negsign&&!b.negsign){
            return negate(add(a,b,0));
        }else if(!a.negsign&&b.negsign){
            return add(a,b,0);
        }else{
            Num numNext;
            if(a.negsign){
                numNext = subtract(b,a,0);
            }else{
                numNext = subtract(a,b,0);
            }
            if(numNext.negsign){
                getComplement(numNext);
                numNext = add(numNext,1);
            }
            return numNext;
        }
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
        Num[] padded = padEqual(a,b);
        if(a.negsign!=b.negsign){
            return negate(productRecursive(copy(padded[0]),copy(padded[1]), padded[2].val));
        }
        return productRecursive(copy(padded[0]),copy(padded[1]), padded[2].val);
    }

    static Num[] kSplit(Num a){
        Num aTrackerFull = a;
        Num aTrackerHalf = a;
        Num aTrackerPrevHalf = a;

        long length = 0;
        while(null!=aTrackerFull){
            length++;
            if(length%2==0){
                aTrackerPrevHalf = aTrackerHalf;
                aTrackerHalf = aTrackerHalf.next;
            }
            aTrackerFull = aTrackerFull.next;
        }
        aTrackerPrevHalf.next = null;

        //Hack that packages the length in the same function call
        return new Num[]{aTrackerHalf,a,new Num(length)};
    }

    static Num productRecursive(Num a, Num b, long length) {

        if(length==1){
            long product = a.val * b.val;
            return convertToBase(product);
        }

        Num[] a_split = kSplit(copy(a));
        Num[] b_split = kSplit(copy(b));
        long length1 = a_split[2].val%2==0?(a_split[2].val/2):(a_split[2].val/2+1);
        long length2 = a_split[2].val/2;;

        Num firstterm = productRecursive(a_split[0],b_split[0],length1);
        Num lastterm = productRecursive(a_split[1],b_split[1],length2);
        Num[] sumproducts = padEqual(add(a_split[0],a_split[1]),add(b_split[0],b_split[1]));

        Num midterm = productRecursive(sumproducts[0],sumproducts[1],sumproducts[2].val);

        Num firsttermPad = padRight(firstterm,2*length2);
        Num secondtermPad = padRight(subtract(subtract(midterm,lastterm),firstterm),length2);

        Num final_res = add(add(firsttermPad,secondtermPad),lastterm);

        return final_res;
    }

    static Num convertToBase(long number){
        long quotient = number/base;
        long remainder = number%base;

        if(quotient == 0){
            return new Num(remainder);
        }else{
            Num res = new Num(remainder);
            res.next = convertToBase(quotient);
            return res;
        }
    }

    static Num[] padEqual(Num a, Num b){
        Num aTrackerFull = a;
        Num bTrackerFull = b;
        long length = 0;

        while(null!=aTrackerFull&&null!=bTrackerFull){
            aTrackerFull = aTrackerFull.next;
            bTrackerFull = bTrackerFull.next;
            length++;
        }

        if(null!=aTrackerFull){
            long padding = 0;
            while (null!=aTrackerFull){
                aTrackerFull = aTrackerFull.next;
                padding++;
                length++;
            }
            b = padLeft(b,padding);
        }

        if(null!=bTrackerFull){
            long padding = 0;
            while (null!=bTrackerFull){
                bTrackerFull = bTrackerFull.next;
                padding++;
                length++;
            }
            a = padLeft(a,padding);
        }

        //Hack for returning length without extra call
        return new Num[] {a,b,new Num(length)};
    }

    static Num padRight(Num a, long padding){
        while (padding>0){
            Num addendum = new Num(0);
            addendum.next = a;
            a = addendum;
            padding --;
        }
        return a;
    }

    static Num padLeft(Num a, long padding){
        Num addendum = a;
        while (null!=addendum.next){
            addendum = addendum.next;
        }
        while (padding>0){
            addendum.next = new Num(0);
            addendum = addendum.next;
            padding --;
        }
        return a;
    }

    // Use divide and conquer
    static Num power(Num a, long n) {
	return null;
    }
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
	return null;
    }

    static Num mod(Num a, Num b) {
	return null;
    }

    // Use divide and conquer
    static Num power(Num a, Num n) {
	return null;
    }

    static Num squareRoot(Num a) {
	return null;
    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
	return 0;
    }

    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    void printList() {
    }

    // Return number to a string in base 10
    public String toString() {
	    Num tracker = this;
	    String initial = "";
	    while(null!=tracker){
            initial = (Long.toString(tracker.val))+initial;
            tracker = tracker.next;
        }
        if(this.negsign){
            initial = "-"+initial;
        }
        return initial;
    }

    public long base() { return base; }

    public static void main(String args[]){
//        Num test = new Num("11");
        Num test = new Num("-111111");
        Num test2 = new Num("100");
//        Num test2 = new Num("0");
//        Num testm = product(test, test2);
        Num testm = subtract(test, test2);
        int a =1;
    }
}