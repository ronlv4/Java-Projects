
import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans = 0;

        //---------------write your code BELOW this line only!--------------
        int a,b;        
        a = scanner.nextInt();
        b = scanner.nextInt();
        int range = b-a;
        ans = (int)(Math.random() * (range+1)) + a;
        //ans is the floored integer which is blocked from above by 1*(range+1) + a = b-a+1-a=b+1 (exluded)
        //also ans is blocked from the bottom by 0*(range+1)+a=0+a=a
        scanner.close();
        //---------------write your code ABOVE this line only!--------------
        
        System.out.println(ans);
    }
}