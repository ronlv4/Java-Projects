
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int p=1;
        boolean found_d = false;
        while (!(found_d)) { //no "p<n" because assuming d and s will be found for sure because input is correct.
        	p = p * 2; //powers of 2
        	ans1=ans1+1;//saving the number of the power 
        	ans2=-1; //restarting "d" every power of 2
        	while ((p*ans2<n)&&(!(found_d))) {
        		ans2=ans2+2; // checking every odd number starting from 1 until it will be found or it does not exist for this power of 2.
        		if (p*ans2==n-1) {
        			found_d = true;
        		}
        	}
        }
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}