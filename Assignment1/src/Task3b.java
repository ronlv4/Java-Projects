
import java.util.Scanner;

public class Task3b {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        ans = 1;
        for (int i=1; i<=n;i++) { 
        	if (ans >=k) { // if intermediate solution past k, no need to keep saving that big number, based on the instruction hint.
        		ans = ans % k;
        	}
        	ans = ans * (2%k); // calculating 2%k to the power of n
        }
        ans = ans % k;
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}