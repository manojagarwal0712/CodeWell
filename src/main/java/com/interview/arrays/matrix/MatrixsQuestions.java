package com.interview.arrays.matrix;

import java.util.HashSet;
import java.util.Set;

public class MatrixsQuestions {

    /**
     * https://leetcode.com/problems/transpose-matrix/description/
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix[0].length;
        int n = matrix.length;
        int[][] ans = new int[m][n];
        for (int i =0; i<m; i++){
            for (int j =0; j<n; j++){
                ans[i][j] = matrix[j][i];
            }
        }
        return ans;
    }

    public static void print2DMatricx(int arr[][],int row, int col){
        for (int i =0; i< row;i++){
            for (int j =0; j<col;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * https://www.youtube.com/watch?v=V9dZ-qqCLmc
     *https://leetcode.com/problems/rotate-image/description/
     * @param arr
     * @param row
     * @param col
     */
    public static void rotateBy90Degree(int arr[][], int row, int col){
        // fid transpose ofa matrix and then interchange the columns
        transpose(arr,row,col);
        //reverse the row.
        for (int i=0;i<row;i++){
            for (int j=0;j<col/2;j++){
                int temp = arr[i][j];
                arr[i][j] = arr[i][col-1-j];
                arr[i][col-1-j] = temp;
            }
        }
        print2DMatricx(arr,row,col);
    }

    public static void printSpiral(int arr[][], int row, int col){
        int rowBeg =0;
        int colBeg =0;
        row = row-1;
        col = col-1;

        while (rowBeg<=row && colBeg<=col){

            //row is fixed, col is changing
            for (int i=colBeg; i<col;i++){
                System.out.print(arr[rowBeg][i]);
            }
            System.out.println();
            //print top to bottom , from 2nd row. row is chaging and col is fixed.
            rowBeg++;
            for (int j=rowBeg;j<row;j++){
                System.out.print(arr[j][col]);
            }
            System.out.println();
            //print right to left, where col is readuced , and col is chaging and row is fixed.
            col--;
            if(rowBeg<=row){
                for (int i=col; i>colBeg;i--){
                    System.out.print(arr[col][row]);
                }
                col--;
            }
        }

    }

    public static void transpose(int arr[][], int m, int n){

        for (int i =0;i<n;i++){
            for (int j =i;j<m;j++){
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;

            }
        }
        reverseColumn(arr,n,m);
        for (int i =0;i<n;i++){
            for (int j =0;j<m;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void reverseColumn(int arr[][], int n, int m){

        for (int i =0;i<n/2;i++){
            for (int j =0;j<m;j++){
                int temp =arr[i][j];
                arr[i][j]= arr[n-i-1][j];
                arr[n-i-1][j] = temp;
            }
        }
        /*for (int i =0;i<n;i++){
            for (int j =0;j<m;j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }*/

    }


    static int ROW=0; // its size of 2D arrray m
    static int COL =0; // its size of 2D array n

    public static boolean isSafe(int arr[][], int col, int row, boolean isVisted[][]){

        if (row>0 && row<ROW && col>0 && col<COL && !isVisted[row][col]){
            return true;
        }
        return false;
    }

    public static void DFS(int arr[][], int row, int col, boolean isVisited[][]){

        // These arrays are used to get row and column numbers
        // of 8 neighbors of a given cell

        int rowNbr[] = new int[]{1,0,1,0,-1,-1,1,-1};
        int colNbr[] = new int[]{0,1,1,-1,0,-1,-1,1};
        isVisited[row][col] = true;

        //recur for all connected neighbours
        for (int k =0;k<8;k++){
            if (isSafe(arr,row+rowNbr[k],col+colNbr[k],isVisited)){
                DFS(arr,row+rowNbr[k],col+colNbr[k],isVisited);
            }
        }
    }

    public static void findIsland(int arr[][], int m, int n){
        int count=0;
        boolean isVisited[][] = new boolean[m][n];
        for (int i =0;i<m;i++){
            for (int j =0;j<n;j++){
                if (arr[i][j]==1 && !isVisited[i][j]){
                    DFS(arr,i,j,isVisited);
                    count++;
                }
            }
        }
    }

    /**
     * Find row number of a binary matrix having maximum number of 1s
     *
     */

    public static void findMaxOne(int arr[][], int m, int n){

        int rowNbr=-1;
        int maxcount =-1;

        for (int i =0;i<m;i++){
            int count=0;
            for (int j=0;j<n;j++){
                if (arr[i][j]==1){
                    count++;
                }
            }

            if (count>maxcount){
                maxcount=count;
                rowNbr = i;
            }
        }
        System.out.println(rowNbr +" - "+ maxcount);
    }
    public static void main(String[] args) {
        MatrixsQuestions matrixs = new MatrixsQuestions();

        int arr[][] = { { 0, 0, 1},
                        { 0, 1, 1},
                        { 0, 0, 0} };
        //rotateBy90Degree(arr, 3,3);
        //matrixs.transpose(arr,3,3);
        matrixs.findMaxOne(arr,3,3);
    }

    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     *
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     * @param board
     * @return
     */

    public boolean isValidSudoku(char[][] board) {

        for (int row =0; row<9;row++){
            for (int col=0;col<9;col++){
                boolean res1 = isValidRow(row, board);
                boolean res2 =isValidCol(col, board);
                if (res1==false || res2 == false){
                    return false;
                }
                if (!isValidSubSquare(board)){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isValidRow(int row, char [][]board){
        char[] temp = board[row];
        Set<Character> set = new HashSet<Character>();
        for (char ch:
                temp) {
            if(ch<=48 || ch >= 57){
                return false;
            } else if (set.add(ch)==false) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidCol(int col, char[][]board){
        Set<Character>set = new HashSet<Character>();
        for (int i =0; i< 9;i++){
            if (board[i][col]<=47 || board[i][col]>=57){
                return false;
            }else if (set.add(board[i][col])==false) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidSubSquare(char board[][]){
        for (int row = 0 ; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                Set<Character> set = new HashSet<Character>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (board[r][c] <= 47 || board[r][c] >= 57) {
                            return false;
                        } else if (set.add(board[r][c]) == false) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
