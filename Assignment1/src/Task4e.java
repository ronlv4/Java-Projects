
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        boolean first_condition;
        boolean second_condition = true; //assuming it holds for every i until found otherwise
        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();
        int bdmodn=1;
        for (int i=1;i<=d;i=i+1) {// this loop calculate b^d % n based on Task3b
        	if (bdmodn>=n) {
        		bdmodn = bdmodn %n;
        	}
        	bdmodn= bdmodn * (b%n);
        }
        first_condition= (!(bdmodn%n==1));
        int two_i_d, b2idmodn;
        int i=0;
        while ((i<=s-1)&&(second_condition)) { //this loop checks for every i whether second condition holds
        	two_i_d = 1;
        	b2idmodn = 1;
        	for (int j=1;j<=i;j=j+1) { //this loop calculation two to the power of i
        		two_i_d=2*two_i_d;
        	}
        	two_i_d=two_i_d*d; // multiplying by d to set the right power.
        	for (int j=1;j<=two_i_d;j=j+1) { // this loop calculate b^((2^i)*d) % n based on Task3b
        		if (b2idmodn>=n)
        			b2idmodn = b2idmodn %n;
        		b2idmodn = b2idmodn * (b%n);
        	}
        	second_condition = (!(b2idmodn%n==(n-1)));
        	i=i+1;
        }
        if ((first_condition)&&(second_condition)) {
        	ans1=false;
        	ans2=b;
        }
        else {
        	ans1=true;
        	ans2=-1;
        }
        scanner.close();
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans1);
        System.out.println(ans2);
    }
}