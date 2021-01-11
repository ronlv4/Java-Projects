
class Part2_Change {

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse) {
        int ans = 0;
        //Task 2.4
        ans = countChangeLimited(coins, n, numOfCoinsToUse, 0, 0);
        return ans;
    }

    public static int countChangeLimited(int[] coins, int n, int numOfCoinsToUse, int i, int count) {
        if (n == 0 && numOfCoinsToUse == 0) {
            return 1;
        } else if (n < 0 || i >= coins.length || numOfCoinsToUse < 0) {
            return 0;
        } else {
            return countChangeLimited(coins, n - coins[i], numOfCoinsToUse - 1, i, count + 1) +
                    countChangeLimited(coins, n, numOfCoinsToUse, i + 1, count);
        }
    }

    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse) {
        //Task 2.5
        printAllChangeLimited(coins, n, numOfCoinsToUse, 0, "");
    }

    public static boolean printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse, int i, String acc) {
        // accumulates the right coins in the String "acc" variable and prints it -
        // if a correct set of change was found.
        // due to the single or operator - "|", the recursion prints all possible options.
        boolean possible = false;
        if (n == 0 && numOfCoinsToUse == 0) {
            possible = true;
            System.out.println(acc.substring(1));
        } else if (n < 0 || i >= coins.length || numOfCoinsToUse < 0) {
            return false;
        } else {
            return printAllChangeLimited(coins, n - coins[i], numOfCoinsToUse - 1, i, acc + "," + coins[i]) |
                    printAllChangeLimited(coins, n, numOfCoinsToUse, i + 1, acc);
        }
        return possible;
    }

    public static int changeInCuba(int cuc) {
        int ans = 0;
        //Task 2.6
        int[] coins = {1, 3, 5, 10, 20, 50, 100,3,9,15,30,60,150,300};
        ans = countChange(coins,cuc*3, 0);
        return ans;
    }
    public static int countChange(int[] coins, int n, int i) {
        if (n == 0) {
            return 1;
        } else if (n < 0 || i >= coins.length) {
            return 0;
        } else {
            return countChange(coins, n - coins[i], i) +
                    countChange(coins, n, i + 1);
        }
    }
}
