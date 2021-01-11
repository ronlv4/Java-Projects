
class Part1_Change{

    public static boolean change(int [] coins, int n){
        // continue to function change(int[],int,int)
        boolean ans = false;
        //Task 2.1
        ans = change(coins,n,0);
        return ans;
    }
    public static boolean change(int[] coins, int n, int i){
        // solving using recursion: dividing into 2 smaller problems:
        // get change with first cell in coins or without it - unlimited coins
        boolean possible = false;
        if (n == 0){
            possible = true;
        }
        else if (n<0 || i>= coins.length){
            return false;
        }
        else{
            return change(coins, n-coins[i], i) || change(coins, n, i+1);

        }
        return possible;
    }

    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){
        // continue to changeLimited(int[], int, int, int)
        boolean ans = false;
        //Task 2.2
        ans = changeLimited(coins, n, numOfCoinsToUse, 0);
        return ans;
    }
    public static boolean changeLimited(int[]coins, int n, int numOfCoinsToUse, int i){
        // same idea - with first cell in coins or without it.
        // this time restrict using "numOfCoinsToUse" coins -
        // every time a coin accounted in solution -> numOfCoinsToUse is decreased by one.
        boolean possible = false;
        if (n == 0 && numOfCoinsToUse == 0){
            possible = true;
        }
        else if (n<0 || i>= coins.length || numOfCoinsToUse < 0){
            return false;
        }
        else{
            return changeLimited(coins, n-coins[i],numOfCoinsToUse-1, i)
                    || changeLimited(coins, n,numOfCoinsToUse, i+1);
        }
        return possible;
    }

    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        // continue  to getChangeLimited(int[], int, int, int, String)
        //Task 2.3
        getChangeLimited(coins, n, numOfCoinsToUse,0, "");
    }
    public static boolean getChangeLimited(int[] coins, int n, int numOfCoinsToUse,int i, String acc){
        // accumulates the right coins in the String "acc" variable and prints it -
        // if a correct set of change was found.
        boolean possible = false;
        if (n == 0 && numOfCoinsToUse == 0){
            possible = true;
            System.out.println(acc.substring(1));
        }
        else if (n<0 || i>= coins.length || numOfCoinsToUse < 0){
            return false;
        }
        else{
            return getChangeLimited(coins, n-coins[i],numOfCoinsToUse-1, i,acc + "," + coins[i]) ||
            getChangeLimited(coins, n,numOfCoinsToUse, i+1, acc);
        }
        return possible;
    }

    public static void main(String[] args){
    }
}
