
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        int i=2; //1 is a trivial divider so we start with 2
        while ((i*i<=n)&&(ans)){ //condition was prooved in class
        	if (n%i==0)
        		ans = false;
        	i=i+1;
        }
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}