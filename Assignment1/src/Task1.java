
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int a,b,q,r; 
        a = scanner.nextInt();
        b = scanner.nextInt();
        q = scanner.nextInt();
        r = scanner.nextInt();
        if ((r>=b) || (b==0) || (a!=q*b+r)) //either one of the required conditions does not hold
        	ans = false;
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}