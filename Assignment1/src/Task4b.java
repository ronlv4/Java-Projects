
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k=2;
        boolean isPrime;
        for (int i=2;i<=n;i=i+1) { //checking each number up to n
        	isPrime=true;
        	while ((k*k<=i)&&(isPrime)) { //for each n checking whether prime
        		if (i%k==0) {
        			isPrime=false;
        		}
        		k=k+1;
        	}
        	k=2;
        	if (isPrime) 
        		ans=ans+1;
        }	 
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);        
    }
}