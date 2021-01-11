import java.sql.SQLOutput;
import java.util.Arrays;

public class Assignment2 {
    /*-----------------------
     *| Part A - tasks 1-11 |
     * ----------------------*/

    public static int[][] mergeArrays(int [][] arr, int[][] addition){
        if (arr==null | addition == null)
            throw new IllegalArgumentException("Can't merge null array");
        int [][] completeArray=new int[arr.length+addition.length][];
        for (int i=0;i< arr.length;i=i+1){
            completeArray[i]=arr[i];
        }
        for (int i=arr.length;i<completeArray.length;i=i+1){
            completeArray[i]=addition[i-arr.length];
        }
        return completeArray;
    }

    // task 1
    public static boolean isSquareMatrix(boolean[][] matrix) {
        boolean isSquareMatrix = !(matrix==null); // checking if matrix is null or length 0
        if (isSquareMatrix){
            isSquareMatrix=!(matrix.length==0);
        }
        for (int i=0;isSquareMatrix && i<matrix.length ;i=i+1)
            // for every row, checking if it is not null and it's length is like the main column
            isSquareMatrix = !(matrix[i] == null || matrix[i].length != matrix.length);
        return isSquareMatrix;
    }

    // task 2
    public static boolean isSymmetricMatrix(boolean[][] matrix) { // assumes matrix is a square matrix
        boolean isSymmetricMatrix = !(matrix==null); // checking if matrix is null, just in case...
        for (int i=1;isSymmetricMatrix && i<matrix.length;i=i+1) { //i=1 - skipping matrix[0][0] check
            for (int j=0;j<i & isSymmetricMatrix;j=j+1) // j<i - no need to check i=j case
                isSymmetricMatrix= (matrix[i][j] == matrix[j][i]); // matrix is symmetric iff A_ij=A_ji for every i,j
        }
        return isSymmetricMatrix;
    }
    // task 3
    public static boolean isAntiReflexiveMatrix(boolean[][] matrix) { // assumes matrix is a square matrix
        boolean isAntiReflexiveMatrix = !(matrix == null); // checking if matrix is null, just in case...
        for (int i=0;isAntiReflexiveMatrix  && i<matrix.length ;i=i+1) {
            isAntiReflexiveMatrix = !(matrix[i][i]);
        }
        return isAntiReflexiveMatrix;
    }
    // task 4
    public static boolean isLegalInstance(boolean[][] matrix) {
        return (isSquareMatrix(matrix) && (isSymmetricMatrix(matrix)) && (isAntiReflexiveMatrix(matrix)));
    }
    // task 5
    public static boolean isPermutation(int[] array) {
        // this function creates an array PermutationVer
        // it assigns a value within the tested array to it's corconstraintponding index in PermutationVer
        // two cases which array is not a permutation:
        // case 1: one of the values bigger than length or smaller than 0
        // case 2: two or more cells with the same value
        boolean isPermutation = true;
        int[] permutationVer = new int[array.length];
        permutationVer[0]=-1; // because default int array filled with zeros
        for (int i=0;i<array.length & isPermutation;i=i+1) { // empty array is a permutation - loop skipped
            if ((array[i]>=0) && (array[i]<array.length)) { //case 1 - index within bounds
                if (permutationVer[array[i]] != array[i]) // case 2 - tested array value was already assigned before.
                    permutationVer[array[i]] = array[i];
                else
                    isPermutation = false;
            }
            else
                isPermutation = false;
        }
        return isPermutation;
    }
    // task 6
    public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
        for (int i=0;i<tour.length;i=i+1) {
            if (!(flights[tour[i]][tour[(i+1)% tour.length]])) //checks every adjacent cells
                return false;
        }
        return true;
    }
    public static boolean isSolution(boolean[][] flights, int[] tour) {
        // got help with exception names: https://www.protechtraining.com/content/java_fundamentals_tutorial-
        // exceptions#:~:text=Exceptions%20are%20events%20that%20occur,the%20error%20including%20its%20type
        if (tour==null)
            throw new NullPointerException ("tour array is null");
        if (tour.length != flights.length)
            throw new IllegalArgumentException("tour length is inappropriate");
        return ((tour[0]==0) && (isPermutation(tour)) && (hasLegalSteps(flights, tour)));
        // all conditions from definition 2 are met
    }
    // task 8
    public static boolean evaluate(int[][] cnf, boolean[] assign) {
        boolean clauseSAT = true;
        for (int i=0;i<cnf.length && clauseSAT;i=i+1) {
            clauseSAT = false; //assumes at start, for every clause, that it is not satisfied
            for (int j=0;j<cnf[i].length && !clauseSAT;j=j+1) { // if at least one literal is true, clause is satisfied
                if (cnf[i][j]<0) // literal is negative
                    clauseSAT = (!(assign[-cnf[i][j]])); // satisfied if variable is false
                else // literal is positive
                    clauseSAT = (assign[cnf[i][j]]); // satisfied if variable is true
            }
        }
        return (clauseSAT);
    }
    // task 9
    public static int[][] atLeastOne(int[] lits) {
        // at least one is true means all literals posted in a disjunctional form
        // which in cnf means all literals in one and only clause.
        int[][]cnf = new int[1][lits.length];
        cnf[0]=lits;
        return cnf;
    }
    // task 10
    public static int[][] atMostOne(int[] lits) {
        // at most one means (~(1&2))&(~(1&3))&(~(2&3))... for every pair in lits
        // and by De Morgan it becomes to cnf form: (~1|~2)&(~1|~3)&(~2|~3)...
        int n = lits.length;
        int nCk = n * (n-1) / 2; // number of pairs in a group of size n is (n Choose 2)
        int cnfIndex=0;
        int[][]cnf = new int[nCk][2];
        for (int i=0;i<lits.length;i=i+1) {
            for (int j=i+1;j<lits.length;j=j+1, cnfIndex=cnfIndex+1) {
                //assembling a clause:
                cnf[cnfIndex][0]=-lits[i];
                cnf[cnfIndex][1]=-lits[j];
            }
        }
        return cnf;
    }

    // task 11
    public static int[][] exactlyOne(int[] lits) {
        // the principle of the function is: (at least one) and (at most one) is exactly one
        int [][] atMostOne = atMostOne(lits);
        int [][] atLeastOne = atLeastOne(lits);
        return mergeArrays(atLeastOne,atMostOne);
    }
    /*------------------------
     *| Part B - tasks 12-20 |
     * -----------------------*/

    // task 12a
    public static int map(int i, int j, int n) {
        //the map function takes the pair i,j as a number "ij"  written in base n and converts it to decimal + 1
        return (j+i*n+1);
    }
    // task 12b
    public static int[] reverseMap(int k, int n) {
        // this function takes a (number in decimal) + 1 and returns it's base n equal.
        int[]map=new int[2];
        map[0]=(k-1)/n; // first digit
        map[1]=(k-1)%n; // second digit
        return map;
    }


    // task 13
    public static int[][] oneCityInEachStep(int n) {
        // for each step i, only one city j is visited
        int[][]cnf = new int[0][]; // final constraint
        int[][]temp;
        int [][]lits = new int[n][n];
        for (int i=0;i<n;i=i+1){
            for (int j=0;j<n;j=j+1){
                lits[i][j]=map(i,j,n); // get all cities literals represented with map
            }
            temp=exactlyOne(lits[i]); // allow exactly one city to be true for step i
            cnf = mergeArrays(cnf, temp);
        }
        return cnf;
    }

    // task 14
    public static int[][] eachCityIsVisitedOnce(int n) {
        int[][] cnf = new int[0][];
        int[][] temp;
        int[][] lits = new int[n][n];
        for (int i = 0; i < n; i = i + 1) {
            for (int j = 0; j < n; j = j + 1) {
                lits[i][j] = map(j, i, n); // for every city j, assign all the possible steps to get to it
            }
            temp = exactlyOne(lits[i]); // allow exactly one step to reach to a city
            cnf = mergeArrays(cnf, temp);
        }
        return cnf;
    }
    // task 15
    public static int[][] fixSourceCity(int n) {
        int[][]cnf = {{map(0,0,n)}}; // step 0 starts at city 0
        return cnf;
    }

    // task 16
    public static int[][] noIllegalSteps(boolean[][] flights) {
        int n = flights.length;
        int[][] temp;
        int[] lits, lits2;
        int[][] cnf = new int[0][];
        for (int i = 1; i < n; i = i + 1) {
            for (int j = 0; j < i; j = j+1) {
                if (!flights[i][j]) { // focus only when there isn't a flight from i to j or the opposite
                    for (int step=0; step<n;step=step+1){
                        // get the literals of arriving to i and then flying to j for all possible steps
                        lits = new int[]{map(step, i, n), map((step + 1) % n, j, n)};
                        temp = atMostOne(lits); //  allow it to happen at most once, at only one step
                        cnf= mergeArrays(cnf,temp);
                        // next 3 rows, like previous, does the same for arriving to j and then flying to i
                        lits2 = new int[]{map(step, j, n), map((step + 1) % n, i, n)};
                        temp = atMostOne(lits2);
                        cnf = mergeArrays(cnf,temp);
                    }
                }
            }
        }
        return cnf;
    }
    // task 17
    public static int[][] encode(boolean[][] flights) {
        // simply get all constraints and merge them into one "big" cnf
        int[][]constraintA = oneCityInEachStep(flights.length);
        int[][]constraintB = eachCityIsVisitedOnce(flights.length);
        int[][]constraintC = fixSourceCity(flights.length);
        int[][]constraintD = noIllegalSteps(flights);
        int[][]cnf=new int[0][];
        cnf = mergeArrays(cnf,constraintA);
        cnf = mergeArrays(cnf,constraintB);
        cnf = mergeArrays(cnf,constraintC);
        cnf = mergeArrays(cnf,constraintD);
        return cnf;
    }

    // task 18
    // decode a specific assignment to a legal tour array
    public static int[] decode(boolean[] assignment, int n) {
        if (assignment==null)
            throw new NullPointerException("null array");
        if (assignment.length!=(n*n+1))
            throw new IllegalArgumentException("array is not of an appropriate length");
        int []tour=new int[n];
        for (int i=1;i< assignment.length;i=i+1){
            if (assignment[i]){ // whenever a variable got a true assignment
                int [] temp = reverseMap(i,n); // get the variable in [step,city] format
                tour[temp[0]]=temp[1]; // assign city to index step
            }
        }
        return tour;
    }

    // task19
    // encode the problem to cnf, solve with SATSolver, decode and return
    public static int[] solve(boolean[][] flights) {
        if (!isLegalInstance(flights))
            throw new IllegalArgumentException("Illegal flights array");
        int n = flights.length;
        SATSolver.init(n*n);
        int [][]cnf = encode(flights); // get the complete cnf
        SATSolver.addClauses(cnf);
        boolean[]assignment=SATSolver.getSolution();
        if (assignment==null)
            throw new RuntimeException("timed out searching for solution");
        if (assignment.length>0){ // solution is legal
            int[]tour=decode(assignment,n); // get the proposed solution in tour array format
            if (isSolution(flights,tour)) // validate the legal solution is also correct
                return tour;
            else
                throw new IllegalArgumentException("Illegal solution");
        }
        else
            return null;
    }

    // task20
    public static boolean solve2(boolean[][] flights) {
        // this function is going to build an extra constraint
        // it will represent a negation of the current viable solution (if exists) and it's reversed one
        if (isLegalInstance(flights)){
            int [] tour = solve(flights); // get the current viable solution (if exists)
            int n = flights.length;
            int [][]constraintE = new int[2][n];
            int[][]cnf2;
            boolean [] assignment = null;
            if (tour!=null){ // solution exists, otherwise an exception would have been thrown
                for (int i=0;i< tour.length;i=i+1){ // negate all solution, by De Morgan becomes ~A|~B|~C...
                    constraintE[0][i]=-map(i,tour[i],n); // clause handling current solution
                    constraintE[1][i]=-map((n-i)%n,tour[i],n); // clause handling reverse solution
                }
                cnf2 = mergeArrays(encode(flights),constraintE);
                SATSolver.init(n*n);
                SATSolver.addClauses(cnf2);
                assignment = SATSolver.getSolution();
            }
            if (assignment==null || assignment.length == 0)
                return false;
            else { // obtained a second legal solution
                int[] tour2 = decode(assignment, n);
                return (isSolution(flights, tour)); // true if legal second solution is also correct
            }
        }
        else
            return false;
    }
    public static boolean subsetSum (int[] weights, int sum, int i){
        boolean ans = false;
        int[] bits = new int[weights.length];
        if (sum==0){
            ans = true;
            bits[i] = 1;
        }
        else if (sum<0 | i>= weights.length){
            ans = false;
        }
        else{
            ans = subsetSum(weights, sum - weights[i], i+1) || (subsetSum(weights, sum, i+1));
            bits[i] = 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7};
        int sum = 30;
        /*long groups = 1;
        for (int i=1;i<= weights.length;i=i+1){
            groups = groups * (sum-i+1) / i; // groups = sum Choose weights.length-1
        }
        System.out.println(groups);*/
        boolean found = true;
        for (int k = 10;k<=sum & found;k=k+1){
            found = false;
            for (int i=1; i<=sum & !found;i=i+1){
                while (weights[i]<=70 & !found){
                    found = subsetSum(weights,sum, 0);
                    weights[i]=weights[i] +1;
                }
            }
        }
        if (found)
            System.out.println(Arrays.toString(weights));
        else
            System.out.println("didnt found");
    }
}


