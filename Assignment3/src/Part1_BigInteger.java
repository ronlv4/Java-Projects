
import java.math.BigInteger;
import java.util.Random;

class Part1_BigInteger{

    public static BigInteger sumSmaller(BigInteger n){
        // summing loop index from i to n
        BigInteger sum =  new BigInteger("0");
        for(BigInteger i = new BigInteger("0");i.compareTo(n)<0;i= i.add(BigInteger.ONE)){
            sum = sum.add(i);
        }
        //Task 1.1
        return sum;
    }

    public static void printRandoms(int n){
        //Task 1.2
        //Generates n random numbers using Random Class
        Random rand = new Random();
        for (int i=1;i<=n;i=i+1){
            System.out.println(rand.nextInt());
        }
    }

    public static boolean isPrime(BigInteger n){
        boolean ans= n.compareTo(BigInteger.ONE)>0;
        //Task 1.3
        //using naive algorithm to determine if prime.
        for (BigInteger i = new BigInteger("2"); ans && i.multiply(i).compareTo(n)<=0;i=i.add(BigInteger.ONE)){
            if (n.remainder(i).compareTo(BigInteger.ZERO)==0)
                ans = false;
        }
        return ans;
    }

    public static BigInteger randomPrime(int n){
        BigInteger randBig = new BigInteger(n, new Random());
        //Task 1.4
        // generating random numbers until conditions are met
        while (randBig.compareTo(BigInteger.ONE)<=0 || !isPrime(randBig))
            randBig = new BigInteger(n, new Random());
        return randBig;
    }
}