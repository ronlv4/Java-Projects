import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.ListIterator;

public class BinaryNumber implements Comparable<BinaryNumber>{
    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);

    private BitList bits;

    // Copy constructor
    //Do not change this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    private void checkNull(Object object) {
        if (object == null)
            throw new IllegalArgumentException("Unable to perform operation on null object");
    }
    public BinaryNumber(char c) {
        this(0);
        if (c - '0' < 0 | c - '9' > 0)
            throw new IllegalArgumentException("char must be a digit between 0 and 9");
        int digit = c - '0';
        //constructing BinaryNumber according to decimal to binary conversion
        while (digit > 0) {
            bits.addLast(new Bit(digit % 2));
            digit = digit / 2;
        }
        bits.removeFirst(); // remove first constructed 0
        bits.addLast(Bit.ZERO); // making the nubmer legal positive BinaryNumber
    }

  //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        //BitList toString returns <Bitlist>, this to string trims the outer braces
        return bits.toString().substring(1, bits.toString().length() - 1);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    public boolean equals(Object other) {
        checkNull(other);
        //equal BinaryNumbers are of the same instance
        if (!(other instanceof BinaryNumber))
            return false;
        //equal BinaryNumbers are of the same size
        if (bits.size() != ((BinaryNumber) other).bits.size())
            return false;
        boolean isEqual = true;
        //checking if every bit of this is the same like other and in order
        Iterator<Bit> otherIterator = ((BinaryNumber) other).bits.iterator();
        Iterator<Bit> thisIterator = bits.iterator();
        while (thisIterator.hasNext() && isEqual) {
            isEqual = thisIterator.next().equals(otherIterator.next());
        }
        return isEqual;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    public BinaryNumber add(BinaryNumber addMe) {
        //this function uses fulladder operation on each bit of BinaryNumber to achieve addition
        checkNull(addMe);
        bits.padding(addMe.bits.size());
        addMe.bits.padding(bits.size());
        BinaryNumber result = new BinaryNumber(0);
        Iterator<Bit> bitIterator = bits.iterator();
        Iterator<Bit> addMeIterator = addMe.bits.iterator();
        Bit carry = Bit.ZERO;
        while (bitIterator.hasNext()) {
            Bit bit1 = bitIterator.next();
            Bit bit2 = addMeIterator.next();
            result.bits.addLast(Bit.fullAdderSum(bit1, bit2, carry));
            carry = Bit.fullAdderCarry(bit1, bit2, carry);
        }
        //condition for overflow - taking into account MSB carry
        if (signum() == addMe.signum())
            result.bits.addLast(carry);
        result.bits.removeFirst(); // removing first constructed 0
        //restoring BinaryNumbers to legal representation:
        bits.reduce();
        addMe.bits.reduce();
        result.bits.reduce();
        return result;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    public BinaryNumber negate() {
        //using 2's complement algorithm for negating - complementing each bit and adding ONE.
        BinaryNumber temp = new BinaryNumber(this);
        temp.bits = temp.bits.complement();
        temp.bits.addLast(temp.bits.getLast());
        BinaryNumber negative = temp.add(BinaryNumber.ONE);
        return negative;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    public BinaryNumber subtract(BinaryNumber subtractMe) {
        //A-B = A+(-B)
        checkNull(subtractMe);
        return add(subtractMe.negate());
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    public int signum() {
        //signum is determined by last bit, or zero is zero.
        if (bits.size() == 1)
            return 0;
        if (bits.getLast().toInt() == 1)
            return -1;
        else return 1;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
        //Based on java convention for comparable
        checkNull(other);
        if (equals(other))
            return 0;
        else
            return (subtract(other).signum());
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal())// Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        int decimal = 0;
        int power = 1;
        if (equals(BinaryNumber.ZERO))
            return 0;
        //hadling the case where this represents Integer.MIN_VALUE
        Bit last = bits.removeLast();
        if (bits.size() == 32 & bits.getNumberOfOnes() == 1 & last.equals(Bit.ONE) & bits.getLast().equals(Bit.ONE)) {
            bits.addLast(last);
            return Integer.MIN_VALUE;
        }
        //more than 32+1(last bit was removed before) bits is for sure not legal BinaryNumber that can be int
        if (bits.size() >= 32)
            throw new RuntimeException("Binary Number can not be contained in int type");
        //number can be contained in int (besides 31 bits numbers that will be handled)
        //converting using power series for base 2
        Iterator<Bit> bitIterator = bits.iterator();
        while (bitIterator.hasNext()) {
            decimal = decimal + bitIterator.next().toInt() * power;
            if (decimal < 0) //because of java's cyclic storage for int
                throw new RuntimeException("Binary Number is grater than Integer MAX VALUE");
            power = power * 2;
        }
        decimal = decimal - last.toInt() * power; // last bit has a negative weight
        bits.addLast(last);
        return decimal;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
        //this function will multiply this and multiplyMe
        //sign will be determined by this law: A*B<0 iff (A<0 & B>0) | (A>0 & B<0)
        checkNull(multiplyMe);
        BinaryNumber result;
        BinaryNumber first = new BinaryNumber(this);
        BinaryNumber second = new BinaryNumber(multiplyMe);
        //negate negative numbers in order to use multiplyPositive next
        if (first.signum() == -1)
            first = first.negate();
        if (second.signum() == -1)
            second = second.negate();
        result = first.multiplyPositive(second);
        //determine result sign
        if (signum() + multiplyMe.signum() == 0)
            result = result.negate();
        return result;
    }
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
        //this function uses few field laws:
        // 1. distribution law:
        // A*B = (A/2+A/2 +carry)*(B/2+B/2+carry)
        //for example: 5*9=(2+2+1)*(4+4+1) = 2*4+2*4+2*1+
        //                                   2*4+2*4+2*1+
        //                                   1*4+1*4+1*1
        // 2. 1 * a = a
        // 3. 0 * a = 0
        BinaryNumber mult1 = new BinaryNumber(multiplyMe);
        BinaryNumber mult2 = new BinaryNumber(this);
        if (multiplyMe.equals(ONE)) {
            return this;
        } else if (equals(ONE)) {
            return multiplyMe;
        } else if (multiplyMe.equals(ZERO) || equals(ZERO)) {
            return ZERO;
        } else {
            BinaryNumber carry1 = new BinaryNumber(mult1.bits.shiftRight().toInt());
            BinaryNumber carry2 = new BinaryNumber(mult2.bits.shiftRight().toInt());
            BinaryNumber b1 = mult1.multiplyPositive(mult2).add(mult1.multiplyPositive(mult2).add(mult1.multiplyPositive(carry2)));
            BinaryNumber b2 = mult1.multiplyPositive(mult2).add(mult1.multiplyPositive(mult2).add(mult1.multiplyPositive(carry2)));
            BinaryNumber b3 = carry1.multiplyPositive(mult2).add(carry1.multiplyPositive(mult2).add(carry1.multiplyPositive(carry2)));
            return b1.add(b2).add(b3);
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
        //this function will multiply this and multiplyMe
        //sign will be determined by this law: A/B<0 iff (A<0 & B>0) | (A>0 & B<0) & (B!=0)
        checkNull(divisor);
        // Do not remove or change the next two lines
        if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
        //
        if (this.equals(ZERO))
            return ZERO;
        BinaryNumber result;
        BinaryNumber first = new BinaryNumber(this);
        BinaryNumber second = new BinaryNumber(divisor);
        //negate negative numbers in order to use dividePositive next
        if (first.signum() == -1)
            first = first.negate();
        if (second.signum() == -1)
            second = second.negate();
        result = first.dividePositive(second);
        //determine result sign
        if (signum() + divisor.signum() == 0)
            result = result.negate();
        return result;
    }
    private BinaryNumber dividePositive(BinaryNumber divisor) {
        //this function uses long division
        // let a/b=c, then a = dividend, b = divisor, c = quotient
        BinaryNumber dividend = new BinaryNumber(0);
        BinaryNumber quotient = new BinaryNumber(0);
        ListIterator<Bit> it = bits.listIterator(bits.size()-1);
        while (it.hasPrevious()) {
            dividend.bits.addFirst(it.previous());
            dividend.bits.reduce();
            if (dividend.compareTo(divisor) < 0)
                quotient.bits.addFirst(new Bit(0));
            else {
                quotient.bits.addFirst(new Bit(1));
                dividend = dividend.subtract(divisor);
            }
        }
        quotient.bits.reduce();
        return quotient;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
        //this function converts decimal to binary using power series of base 10
        //iterates for every digit in string, construction appropriate BN, multiplying weight and summing.
        this(0);
        checkNull(s);
        BinaryNumber power = new BinaryNumber(1);
        for (int i = s.length() - 1; i >= 1; i = i - 1) {
            BinaryNumber digit = new BinaryNumber(s.charAt(i));
            bits = add(digit.multiply(power)).bits;
            power = power.multiply(new BinaryNumber('9').add(ONE));
        }
        //handling first char at the string
        char c = s.charAt(0);
        if (c == '-' & s.length() > 1)
            bits = negate().bits;
        else if (c < '0' | c > '9')
            throw new IllegalArgumentException("illegal String");
        else
            bits = add(new BinaryNumber(c).multiply(power)).bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        //
        //using power series of base 10 to convert to decimal
        //arithmetic operations with Strings are handled in different functions
        String result = "0";
        String power = "1";
        Bit last = bits.removeLast();
        for (Bit bit : bits) {
            if (bit.toInt() == 1) {
                result = addString(result, power);
            }
            power = multStringBy2(power);
        }
        bits.addLast(last);
        //adding negative sign to negative nubmer
        if (last.toInt() == 0)
            return result;
        else
            return "-" + subtractString(power, result);
    }
    private String addString(String s1, String s2) {
        //this function adds arithmetically Strings s1 and s2
        //assumes s1 and s2 are legal numbers
        String result = "";
        int carry = 0;
        s1 = padString(s1, s2.length());
        s2 = padString(s2, s1.length());
        for (int i = s1.length() - 1; i >= 0; i = i - 1) {
            int sum = ((s1.charAt(i) - '0') + (s2.charAt(i) - '0') + carry) % 10;
            result = sum + result;
            carry = ((s1.charAt(i) - '0') + (s2.charAt(i) - '0') + carry) / 10;
        }
        if (carry == 1)
            result = carry + result;
        return result;
    }
    private String multStringBy2(String s1) {
        //multiply string Integer value by 2
        return addString(s1, s1);
    }
    private String padString (String s,int length) {
        //padding Strings with "0" to the same length
        for (int i = s.length(); i < length; i = i + 1)
            s = "0" + s;
        return s;
    }
    private String unpad(String s) {
        //unpad strings to represent legal decimal numbers
        int i = 0;
        while (s.charAt(i) == '0' && i < s.length() - 1)
            i = i + 1;
        s = s.substring(i);
        return s;
    }
    private String subtractString(String larger,String smaller) {
        //subtract Integer value of Strings larger ans smaller
        //assumes larger and smaller are of the same length
        String result = "";
        int borrow = 0;
        smaller = padString(smaller, larger.length());
        //using highschool subtraction
        for (int i = larger.length() - 1; i >= 0; i = i - 1) {
            int sum = ((larger.charAt(i) - '0') - (smaller.charAt(i) - '0') - borrow);
            if (sum < 0) {
                sum = sum + 10;
                borrow = 1;
            } else
                borrow = 0;
            result = sum + result;
        }
        result = unpad(result);
        return result;
    }


    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
}
