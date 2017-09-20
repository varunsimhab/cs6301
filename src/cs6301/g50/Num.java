
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

    static Num addComplement(Num a, Num b, long carry) {
        long sum = base-1-b.val + a.val + carry;
        Num nextDigit = new Num(sum%base);
        carry = sum/base;

        if((null!=a.next)&&(null!=b.next)){
            nextDigit.next = addComplement(a.next,b.next,carry);
            nextDigit.negsign = nextDigit.next.negsign;
        }else if(null!=a.next){
            nextDigit.next = addComplement(a.next,carry);
        }else if(null!=b.next){
            nextDigit.next = subtractComplement(b.next,carry);
            negate(nextDigit);
        }else{
            if(sum-base+1<0){
                nextDigit.negsign = true;
            }
        }

        return nextDigit;
    }

    static Num add(Num a, Num b) {
        if(a.negsign&&b.negsign){
            return negate(add(a,b,0));
        }else if(!a.negsign&&!b.negsign){
            return add(a,b,0);
        }else{
            Num numNext;
            if(a.negsign){
                numNext = addComplement(b,a,0);
            }else{
                numNext = addComplement(a,b,0);
            }
            if(numNext.negsign){
                numNext = getComplement(numNext);
            }else {
                if(null==numNext.next){
                    numNext = add(numNext,1);
                    numNext.next = null;
                }else{
                    numNext = add(numNext,1);
                }
            }
            return numNext;
        }
    }

    static Num getComplement(Num a){
        a.val = base-1-a.val;
        if(null!=a.next){
            a.next=getComplement(a.next);
        }else if(a.val==0){
            return null;
        }
        return a;
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

    static Num negate(Num a){
        a.negsign = !a.negsign;
        if(null!=a.next){
            a.next = negate(a.next);
        }
        return a;
    }

    static Num addComplement(Num a, long carry){
        long sum =  base -1 + a.val + carry;
        Num nextDigit = new Num(sum%base);
        carry = sum/base;

        if(null!=a.next){
            nextDigit.next = addComplement(a.next,carry);
        }else if(nextDigit.val==0){
            return null;
        }

        return nextDigit;
    }

    static Num subtractComplement(Num a, long carry){
        long sum =  base -1 - a.val + carry;
        Num nextDigit = new Num(sum%base);
        carry = sum/base;

        if(null!=a.next){
            nextDigit.next = subtractComplement(a.next,carry);
        }

        return nextDigit;
    }

    static Num subtract(Num a, Num b) {
        return add(a,negate(copy(b)),0);
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
        Num aTracker = a;
        Num bTracker = b;
        Num finalResult = new Num(0);
        while(null!=a.next&&null!=b.next){
            long x1x2 = a.val*b.val;
            long y1y2 = a.next.val*b.next.val;
            long x1px2y1py2 = (a.val+a.next.val)*(b.val+b.next.val);
        }
        return null;
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
        Num test = new Num("-1111");
        Num test2 = new Num("999");
        Num test3 = test2.subtract(test,test2);
        int a =1;
    }
}