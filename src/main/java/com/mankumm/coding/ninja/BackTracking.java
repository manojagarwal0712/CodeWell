package com.stack.com.mankumm.coding.ninja;

import com.stack.com.mankumm.oops.A;

import java.util.*;

public class BackTracking {
    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        //int [][] maze = takingInput();
        int [] set = {2,4,6,8};
       //subsetsSumK(set, 2);
        //lexicographicalOrder(20);
        ArrayList<ArrayList<Integer>> ans = combinationSum(set, 8);
        for (ArrayList<Integer> list:
             ans) {
            for (Integer a:
                 list) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }


    /**
     * Sudoku Solver
     * Send Feedback
     * You are given a 9*9 sudoku board, in which some entries are filled, and others are 0 (0 indicates that the cell is empty).
     *
     *
     * You must return true if the Sudoku puzzle can be solved. Else return false.
     * Input Format :
     * 9 Lines where ith line contains ith row elements separated by space
     * Output Format :
     *  You need to return “YES” if the Sudoku puzzle can be solved. Otherwise, return “NO”. (without the inverted commas)
     */
    public static boolean sudokuSolver(int board[][]){

        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Don't print output and return output as specified in the question.
         */
        for (int i =0; i<board.length; i++){
            for (int j =0; j<board[0].length; j++){
                if (board[i][j]==0){
                    for (int k =1; k<=9;k++){
                        if (isValid(board, i, j, k)){
                            board[i][j] = k;
                            if (sudokuSolver(board)){
                                return true;
                            }else {
                                board[i][j] =0;// reset the value to 0
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int k) {
        //verify if the element can be places in this row or not.
        for (int i =0; i<9; i++){
            if (board[i][col]==k){
                return false;
            }
        //verify if the element can be places in this col or not.
            if (board[row][i]==k){
                return false;
            }
            //verify the element can be palced in the 3 * 3 cross matrix
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == k)
                return false;
        }
        return true;
    }


    /**
     * Combinational Sum
     * Send Feedback
     * Given an array of integers A and an integer B. Find and return all unique combinations in A where the sum of elements is equal to B.
     * Elements of input array can be repeated any number of times.
     * One combination should be saved in increasing order. Order of different combinations doesn't matter.
     * Note : All numbers in input array are positive integers.
     * Input Format :
     * Line 1 : Integer n
     * Line 2 : n integers (separated by space)
     * Line 3 : Integer B (i.e. sum)
     * Output Format :
     * Combinations in different lines
     * Constraints :
     * 1<= N <=1000
     * Sample Input 1 :
     * 4
     * 7 2 6 5
     * 16
     * Sample Output 1 :
     * 2 2 2 2 2 2 2 2
     * 2 2 2 2 2 6
     * 2 2 2 5 5
     * 2 2 5 7
     * 2 2 6 6
     * 2 7 7
     * 5 5 6
     * Sample Input 2 :
     * 4
     * 2 4 6 8
     * 8
     * Sample Output 2 :
     * 2 2 2 2
     * 2 2 4
     * 2 6
     * 4 4
     * 8
     * @param arr
     * @param sum
     * @return
     */

    public static ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(candidates);
        combinationSumHelper(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    public static void combinationSumHelper(int[] candidates, int start, int target, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res){
        if(target < 0 || start < 0 || start >= candidates.length)
            return;
        if(target == 0) {
            ArrayList<Integer> result = new ArrayList<Integer>(path);
            res.add(result);
        }else {
            for(int i = start; i < candidates.length && candidates[i] <= target; i++){
                path.add(candidates[i]);
                combinationSumHelper(candidates, i, target - candidates[i], path, res);
                path.remove(path.size() - 1); //reset the variable.
            }
        }
    }


    public static void lexicographicalOrder(int n){
        List<Integer> ans = new ArrayList<>();
        for (int i =1; i<=9; i++){
            dfs(n, i, ans);
        }

        /*for (Integer a:
             ans) {
            System.out.println(a);
        }*/

    }

    private static void dfs(int n, int startNum, List<Integer> ans) {
        if (startNum>n){ // means we reached the last numer
            return;
        }

        System.out.println(startNum);
        for (int j =0;j<10;j++){
            dfs(n, 10*startNum+j, ans);
        }
    }

    public static void lexicographicalOrderIterative(int n){
        Vector<String> s = new Vector<String>();

        for (int i = 1; i <= n; i++)
        {
            s.add(String.valueOf(i));
        }

        Collections.sort(s);
        Vector<Integer> ans = new Vector<Integer>();
        for (int i = 0; i < n; i++)
            ans.add(Integer.valueOf(s.get(i)));

        for (int i = 0; i < n; i++)
            System.out.print(ans.get(i) + " ");
    }
    /**
     * Return subsets sum to K
     * Send Feedback
     * Given an array A of size n and an integer K, return all subsets of A which sum to K.
     * Subsets are of length varying from 0 to n, that contain elements of the array. But the order of elements should remain same as in the input array.
     *
     *
     * Note :
     * The order of subsets are not important.
     *
     *
     * Input format :
     * Line 1 : Integer n, Size of input array
     * Line 2 : Array elements separated by space
     * Line 3 : K
     * Constraints :
     * 1 <= n <= 20
     * Sample Input :
     * 9
     * 5 12 3 17 1 18 15 3 17
     * 6
     * Sample Output :
     * 3 3
     * 5 1
     * @param input
     * @param k
     * @return
     */
    public static void subsetsSumK(int input[], int k) {
        // Write your code here
        List<List<Integer>> res = new ArrayList<>();
        subsetsSumKHelper(input, k,0, new ArrayList<>(), 0, res);
        /*int[][] ans = new int[res.size()][];
        int i =0;
        for (List<Integer> list:
                res) {
            int[] arr = list.stream().mapToInt(j -> j).toArray();
            ans[i] = arr;
            i++;
        }
        return ans;*/
    }
    private static void subsetsSumKHelper(int[] input, int sum, int startIndex, ArrayList<Integer> curerntList, int currentSum , List<List<Integer>> res) {
        if (currentSum==sum){
            //res.add(new ArrayList<>(curerntList));
            for (Integer a:
                 curerntList) {
                System.out.print(a +"");
            }
            System.out.println();
            return;
        }
        for (int i =startIndex; i<input.length; i++){
            curerntList.add(input[i]);
            currentSum = currentSum+input[i];
            subsetsSumKHelper(input, sum, i+1, curerntList, currentSum, res);
            int num = curerntList.get(curerntList.size()-1);
            curerntList.remove(curerntList.size()-1);
            currentSum= currentSum-num;
        }
    }


    public static void subsets_no_dup_5(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();

        Arrays.sort(nums);

        // Start backtracking from the beginning
        backtrack_NoDup(resultList, new ArrayList<>(), nums, 0);
        //return resultList;
        //return resultList;
        System.out.println(resultList);
        for (List<Integer> list:
             resultList) {
            for (Integer a:
                 list) {
                System.out.print(a+" ");
            }
            System.out.println();
        }
    }

    private static void backtrack_NoDup(List<List<Integer>> resultSets, List<Integer> tempSet,
                                  int[] nums, int start) {
        // If the set is already present, just continue
        if (resultSets.contains((tempSet)))
            return;

        resultSets.add(new ArrayList<>(tempSet));

        for (int i = start; i < nums.length; i++) {
            // Case of including the number
            tempSet.add(nums[i]);

            // Backtrack the new subset
            backtrack_NoDup(resultSets, tempSet, nums, i + 1);

            // Case of not-including the number
            tempSet.remove(tempSet.size() - 1);
        }
    }


    /**
     * Return subset of an array
     * Send Feedback
     * Given an integer array (of length n), find and return all the subsets of input array.
     * Subsets are of length varying from 0 to n, that contain elements of the array. But the order of elements should remain same as in the input array.
     *
     *
     * Note :
     * The order of subsets are not important.
     *
     *
     * Input format :
     *
     * Line 1 : Size of array
     *
     * Line 2 : Array elements (separated by space)
     *
     * Sample Input:
     * 3
     * 15 20 12
     * Sample Output:
     * [] (this just represents an empty array, don't worry about the square brackets)
     * 12
     * 20
     * 20 12
     * 15
     * 15 12
     * 15 20
     * 15 20 12
     * @param
     * @return
     */

    public static List<List<Integer>> subsets_4(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        // Start backtracking from the beginning
        backtrack(resultList, new ArrayList<>(), nums, 0);
        return resultList;
    }

    private static void backtrack(List<List<Integer>> resultSets, List<Integer> tempSet,
                                  int[] nums, int start) {
        // Add the set to result set
        resultSets.add(new ArrayList<>(tempSet));
        for (int i = start; i < nums.length; i++) {
            // Case of including the number
            tempSet.add(nums[i]);
            // Backtrack the new subset
            backtrack(resultSets, tempSet, nums, i + 1);
            // Case of not-including the number
            tempSet.remove(tempSet.size() - 1);
        }
    }


    /**
     * Code: N Queens
     * Send Feedback
     * You are given N, and for a given N x N chessboard, find a way to place N queens such that no queen can attack any other queen on the chess board. A queen can be killed when it lies in the same row, or same column, or the same diagonal of any of the other queens. You have to print all such configurations.
     * Input Format :
     * Line 1 : Integer N
     * Output Format :
     * One Line for every board configuration.
     * Every line will have N*N board elements printed row wise and are separated by space
     * Note : Don't print anything if there isn't any valid configuration.
     * Constraints :
     * 1<=N<=10
     * Sample Input 1:
     * 4
     * Sample Output 1 :
     * 0 1 0 0 0 0 0 1 1 0 0 0 0 0 1 0
     * 0 0 1 0 1 0 0 0 0 0 0 1 0 1 0 0
     */

    public static void placeNQueens(int n){

        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         */
        int board[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }
        int row = n;
        int col = 0;
        ArrayList<int[][]> ans = new ArrayList<>();
        placeQueensSolve(board, col, n, ans);
        /*for (int[][] boardAns:
             ans) {
            int size = boardAns.length;
            for (int i =0; i<size; i++){
                for (int j =0; j<size; j++){
                    System.out.print(boardAns[i][j]+" ");
                }
            }
            System.out.println();
        }*/
    }
    public static void placeQueensSolve(int[][] board, int col, int n, ArrayList<int[][]> ans){
        if (col==n){
            // It's a valid configuration
            for (int i =0; i<n; i++){
                for (int j =0; j<n; j++){
                    System.out.print(board[i][j]+" ");
                }
            }
            System.out.println();
            return;
        }

        for (int row =0; row<n; row++){
            if (isSafe(board, row , col, n)){
                board[row][col] = 1;
                placeQueensSolve(board, col+1, n, ans);
                board[row][col] = 0;
            }
        }
    }

    private static boolean isSafe(int[][] board, int row, int col, int n) {
        int dupRow = row;
        int dupCol = col;
        //check diagonal to left side
        while (row>=0 && col>=0){
            if (board[row][col]==1){
                return false;
            }
            row--;
            col--;
        }
        col = dupCol;
        row = dupRow;
        //check left side
        while (col>=0){
            if (board[row][col]==1){
                return false;
            }
            col--;
        }
        row = dupRow;
        col = dupCol;
        //check diagonal downside
        while (row<n && col>=0){
                if (board[row][col]==1){
                return false;
            }
            row++;
            col--;
        }
        return true;
    }


    /**
     * Code: Rat In A Maze
     * Send Feedback
     * You are given a N*N maze with a rat placed at maze[0][0]. Find whether any path exist that rat can follow to reach its destination i.e. maze[N-1][N-1]. Rat can move in any direc­tion ( left, right, up and down).
     * Value of every cell in the maze can either be 0 or 1. Cells with value 0 are blocked means rat can­not enter into those cells and those with value 1 are open.
     * Input Format
     * Line 1: Integer N
     * Next N Lines: Each line will contain ith row elements (separated by space)
     * Output Format :
     * The output line contains true if any path exists for the rat to reach its destination otherwise print false.
     * Sample Input 1 :
     * 3
     * 1 0 1
     * 1 0 1
     * 1 1 1
     * Sample Output 1 :
     * true
     * @param maze
     * @return
     */

    public static boolean solveMaze(int maze[][], int i , int j, int[][] visitedCells){
        // maze the input maze
        // i and j, are the indices to know where we are currently in maze and
        //vistedcell shows, what all cells are visted.
        int n = maze.length;
        //check if the cell i, j is a valid cell or not.
        if (i<0  || i>n || j<0 || j>n ||  maze[i][j]==0 || visitedCells[i][j]==1){
            return false;
        }

        //At this point we can mark the cell a valid cell.
        visitedCells[i][j] = 1;
        //now what if this is the destination cell means we found a path.
       if (i == n-1 && j == n-1){
           return true;
       }
       // now we move in all 3 directon  D, L , R, T

        //move top
        if (solveMaze(maze, i-1, j, visitedCells)){
            return true;
        }

        //right
        if (solveMaze(maze, i, j+1, visitedCells)){
            return true;
        }

        // down
        if (solveMaze(maze, i+1, j, visitedCells)){
            return true;
        }

        // left
        if (solveMaze(maze, i, j-1, visitedCells)){
            return true;
        }

        return false;
    }

    public static void printAllPathInMaze(int maze[][], int i , int j, int[][] visitedCells){
        // maze the input maze
        // i and j, are the indices to know where we are currently in maze and
        //vistedcell shows, what all cells are visted.
        int n = maze.length-1;
        //check if the cell i, j is a valid cell or not.
        if (i<0  || i>n || j<0 || j>n ||  maze[i][j]==0 || visitedCells[i][j]==1){
            return;
        }
        //At this point we can mark the cell a valid cell.
        visitedCells[i][j] = 1;
        //now what if this is the destination cell means we found a path.
        if (i == n-1 && j == n-1){
            for (int r =0; r<n;r++){
                for (int c =0; c<n;c++){
                    System.out.print(visitedCells[r][c]+" ");
                }
            }
            System.out.println();
            visitedCells[i][j] =0;
            return;
        }
        // now we move in all 3 directon  D, L , R, T

        //move top
        printAllPathInMaze(maze, i-1, j, visitedCells);
        //right
        printAllPathInMaze(maze, i, j+1, visitedCells);
        // down
        printAllPathInMaze(maze, i+1, j, visitedCells);
        // left
        printAllPathInMaze(maze, i, j-1, visitedCells);
        visitedCells[i][j] =0;
    }

    public static int[][] takingInput() {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[][] arr = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        return arr;
    }


    public static void solve(int maze[][], int n , String move, int visited[][], ArrayList<String> ans, int i, int j ){

        /*Your class should be named Solution.
         *Don't write main().
         *Don't take input, it is passed as function argument.
         *Don't print output.
         *Taking input and printing output is handled automatically.
         */
        if (i==n-1 && j == n-1){
            ans.add(move);
            return ;
        }
        //move downward
        if (i+1<n && visited[i+1][j]==0 && maze[i+1][j]==1){
            visited[i][j] = 1;
            solve(maze, n, move+'D', visited, ans, i+1, j);
            visited[i][j] = 0;
        }

        //left
        if (j-1>0 && visited[i][j-1]==0 && maze[i][j-1]==1){
            visited[i][j-1] = 1;
            solve(maze, n, move+'L', visited, ans, i, j-1);
            visited[i][j-1] = 0;
        }

        //right
        if (j+1<n && visited[i][j+1]==0 && maze[i][j+1]==1){
            visited[i][j+1] = 1;
            solve(maze, n, move+'R', visited, ans, i, j+1);
            visited[i][j+1] = 0;
        }

        //up
        if (i-1>0 && visited[i-1][j]==0 && maze[i-1][j]==1){
            visited[i-1][j] = 1;
            solve(maze, n, move+'U', visited, ans, i-1, j);
            visited[i-1][j] = 0;
        }
    }
    public static boolean ratInAMaze(int[][] maze, int n) {
        int vis[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = 0;
            }
        }
        ArrayList < String > ans = new ArrayList < > ();
        if (maze[0][0] == 1) solve(maze, n, "", vis, ans, 0, 0);

        return ans.size()>0;
    }


    public static void ratInAMazepath(int[][] maze, int n) {
        int vis[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vis[i][j] = 0;
            }
        }
        printAllPathInMaze(maze, 0, 0, vis);
    }
}
