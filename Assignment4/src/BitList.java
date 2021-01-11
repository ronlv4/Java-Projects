
import com.sun.tools.javac.Main;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

public class BitList extends LinkedList<Bit> {
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    private void checkNull() {
        if (this == null) {
            throw new IllegalArgumentException("Unable to complete operation because BitList is null");
        }
    }
    private void checkNull(Bit element) {
        if (element == null) {
            throw new IllegalArgumentException("Unable to complete operation because Bit is null");
        }
    }

    public void addLast(Bit element) {
        // using LinkedList in order to add to the last place, maintaining numberOfOnes.
        checkNull();
        checkNull(element);
        if (element.toInt()==1)
            numberOfOnes=numberOfOnes+1;
        super.addLast(element);
    }

    public void addFirst(Bit element) {
        // using LinkedList in order to add to the first place, maintaining numberOfOnes.
        checkNull();
        checkNull(element);
        if (element.toInt()==1)
            numberOfOnes=numberOfOnes+1;
        super.addFirst(element);
    }

    public Bit removeLast() {
        // using LinkedList in order to remove the last place, maintaining numberOfOnes.
        checkNullAndSize();
        if (this.getLast().toInt()==1)
            numberOfOnes=numberOfOnes-1;
        return super.removeLast();
    }

    public Bit removeFirst() {
        // using LinkedList in order to remove the first place, maintaining numberOfOnes.
        checkNullAndSize();
        if (getFirst().toInt()==1)
            numberOfOnes=numberOfOnes-1;
        return super.removeFirst();
    }
    private void checkNullAndSize(){
        //throwing exceptions to null or empty BitList
        checkNull();
        if (isEmpty()){
            throw new IllegalArgumentException("can not remove from empty list");
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
        // constructing the string which to be returned using iterator
        checkNull();
        Iterator<Bit> it = listIterator();
        if (! it.hasNext()) //empty list
            return "<>";
        String bitStr = ">";
        for (;;) { //used AbstractCollection.toString implementation with small modifications
            Bit e = it.next();
            bitStr = e.toInt() + bitStr;
            if (! it.hasNext())
                return "<" + bitStr;
        }
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        //Builds an empty BitList, that adding every bit in order like other
        this();
        if (other == null){
            throw new IllegalArgumentException("BitLise input is null");
        }
        for (Bit bit :
                other) {
            addLast(bit);
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        //based on instructions conditions
        checkNull();
        if (!isEmpty())
            return numberOfOnes>1 || getLast().toInt()==0;
        return false;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        //based on instructions conditions
        //see side functions description
        if (isNumber()) {
            if (oneOf3())
                return true;
            if (size() >= 3)
                return  (negatesOrSoleOnes());
        }
        return false;
    }
    private boolean oneOf3() {
        // function returns true iff Bit represents <0>, <01>, <11>
        if (size() == 1 && getFirst().toInt() == 0)
            return true;
        if (size() == 2 && getFirst().toInt() == 1)
            return true;
        return false;
    }
    private boolean negatesOrSoleOnes(){
        //assumes size>=3
        //function returns true iff last two bits are 01 or 10 or
        //last two bits are 11 and are the only ones.
        Bit last = removeLast();
        boolean areNegates = (last.equals(getLast().negate()));
        boolean areOnes = !areNegates && last.toInt()==1;
        addLast(last);
        return areNegates || (numberOfOnes ==2 && areOnes);
    }

    public void reduce() {
        //removing last Bit until BitList in minimal form
        if (!isNumber())
            throw new IllegalArgumentException("Illegal number cannot be reduced");
        while (!isReduced()){
            removeLast();
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
        // function returns new BitList which is a Bit negation of this BitList
        checkNull();
        BitList negateList = new BitList();
        for (Bit bit: this){ //BitList is Iterable, starting from the first Bit, adding to last place in negateList
            negateList.addLast(bit.negate());
        }
        return negateList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
        //shifting right non empty BitList
        //empty BitList remains unchanged
        checkNull();
        if (!isEmpty())
            return removeFirst();
        else
            return null;
    }

    public void shiftLeft() {
        //shifting left BitList
        checkNull();
        addFirst(new Bit(0));
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
        // padding BitList with current last bit until size equals newLength
        checkNull();
        if (!isEmpty()){
            Bit bit = getLast(); // get last bit
            while (size()<newLength){
                addLast(bit); // pad
            }
        }
        else {
            throw new IllegalArgumentException("cannot pad an empty bitList");
        }
    }
    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }


    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
