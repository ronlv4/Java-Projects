/*---------------------------------------
 Genuine author: Ron Levi, I.D.: 205508021
 Date: 02-01-2020
---------------------------------------*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator<Integer> {

    private List<Integer> primes;

    //Complete the following methods
    public PrimeIterator() {
        // task 0
        primes = new DynamicArray<>();
        primes.add(2);
    }

    public boolean hasNext() {
        // task 0
        return primes.get(primes.size() - 1) > 0;
    }

    public Integer next() {
        // task 0
        boolean isPrime = false;
        if (!hasNext())
            throw new NoSuchElementException("No more primes less than 2^31-1");
        int n = primes.get(primes.size() - 1);
        while (!isPrime & n>0) {
            n = n + 1;
            isPrime = true;
            for (int i = 0; i < primes.size() & isPrime; i = i + 1) {
                if (n % primes.get(i) == 0)
                    isPrime = false;
            }
        }
        primes.add(n);
        return primes.get(primes.size() - 2);
    }
}
