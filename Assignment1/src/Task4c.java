
import java.util.Scanner;

public class Task4c {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int n = scanner.nextInt();
        ans = (int)(Math.random() * (n-2)) + 2;
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}