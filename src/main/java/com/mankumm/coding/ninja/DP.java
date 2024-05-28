package com.stack.com.mankumm.coding.ninja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP {
    public static void main(String[] args) {
        int[]arr = {-1,-2,-3,-4};
        //System.out.print(lis(arr));
        System.out.print(findSum(arr, 4));


    }

    /**
     * Loot Houses
     * Send Feedback
     * A thief wants to loot houses. He knows the amount of money in each house. He cannot loot two consecutive houses. Find the maximum amount of money he can loot.
     * Input format :
     * The first line of input contains an integer value of 'n'. It is the total number of houses.
     *
     * The second line of input contains 'n' integer values separated by a single space denoting the amount of money each house has.
     * Output format :
     * Print the the maximum money that can be looted.
     * Constraints :
     * 0 <= n <= 10 ^ 4
     *
     * Time Limit: 1 sec
     * Sample Input 1 :
     * 6
     * 5 5 10 100 10 5
     * Sample Output 1 :
     * 110
     * Sample Input 2 :
     * 6
     * 10 2 30 20 3 50
     * Sample Output 2 :
     * 90
     * Explanation of Sample Output 2 :
     * Looting first, third, and the last houses([10 + 30 + 50]) will result in the maximum loot, and all the other possible combinations would result in less than 90.
     * @param houses
     * @return
     */
    public static int maxMoneyLooted(int[] houses) {
        //Your code goes here
        return maxMoneyLootedHelper(houses, houses.length-1);
    }

    private static int maxMoneyLootedHelper(int[] houses, int n) {
        if (n<0){
            return 0;
        }
        if (n==0){
            return houses[0];
        }
        int pick = houses[n]+ maxMoneyLootedHelper(houses, n-2);
        int notPick = maxMoneyLootedHelper(houses, n-1);
        return Math.max(pick, notPick);

    }


    /**
     * Code : Max sum sub-array
     * Send Feedback
     * Given an integer array containing both negative and positive integers. Find and return the sum of contiguous sub-array with maximum sum.
     * Input Format :
     * Line 1: Integer N, size of input array
     * Line 2: N integers, elements of input array (separated by space)
     * Output Format :
     * Maximum Sum of the contiguous sub-array
     * Constraints :
     * 1 = N = 1000
     * -100 = A[i] <= 100
     * Sample Input 1:
     * 3
     * 1 2 3
     * Sample Output 1:
     * 6
     * Sample Input 2:
     * 4
     * -1 -2 -3 -4
     * Sample Output 2:
     * -1
     * @param arr
     * @param n
     * @return
     */

    public static int findSum(int arr[],int n){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Don't print the output and return it.
         * Taking input and printing output is handled automatically.
         */

        int max_so_far = Integer.MIN_VALUE, max_ending_here=0;
        for (int i =0; i<arr.length;i++){
            if (max_ending_here<max_ending_here+arr[i]){
                max_ending_here = max_ending_here+arr[i];
            }
            if (max_so_far<max_ending_here){
                max_so_far = max_ending_here;
            }

        }
        return max_so_far;

    }



    /**
     * Given an array with N elements, you need to find the length of the longest subsequence in the given array such that all elements of the subsequence are sorted in strictly increasing order.
     * Input Format
     * The first line of input contains an integer N. The following line contains N space separated integers, that denote the value of elements of array.
     * Output Format
     * The first and only line of output contains the length of longest subsequence.
     * Constraints
     * 1 <= N <= 10^3
     * Time Limit: 1 second
     * Sample Input 1 :
     * 6
     * 5 4 11 1 16 8
     * Sample Output 1 :
     * 3
     * Sample Output Explanation
     * Length of longest subsequence is 3 i.e. (5,11,16) or (4,11,16).
     * Sample Input 2 :
     * 3
     * 1 2 2
     * Sample Output 2 :
     * 2
     * @param arr
     * @return
     */

    public static int lis(int arr[]) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        lisHelper(arr, currentList, ans, 0, arr.length-1, max);
        for (List<Integer> list:
             ans) {
            if(list.size()>max){
                max = list.size();
            }
        }
        return max;
    }

    private static void lisHelper(int[] arr, List<Integer> currentList, List<List<Integer>> ans, int currentIndex, int end, int max) {
        if (currentIndex==end){
            ans.add(new ArrayList<>(currentList));
            return;
        }
        if (currentList.isEmpty() || currentList.get(currentList.size()-1)<arr[currentIndex]){
            currentList.add(arr[currentIndex]);
        }
        lisHelper(arr, currentList, ans, currentIndex+1, end, currentList.size());
        if (!currentList.isEmpty()){
            currentList.remove(currentList.size()-1);
        }
        lisHelper(arr, currentList, ans, currentIndex+1, end, currentList.size());
    }


    /**
     * https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/dynamic-programming/min-squares-official/ojquestion
     * https://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
     * @param n
     * @return
     */
    public static int minCountRec(int n) {
        // base cases
        if (n<=3){
            return 3;
        }

        // getMinSquares rest of the
        // table using recursive
        // formula
        // Maximum squares required is
        int res = n;
        // Go through all smaller numbers
        // to recursively find minimum
        for (int i =1; i<=n; i++){
            int temp = i * i;
            if (temp>n){
                break;
            }else {
                res = Math.min(res, 1+ minCountRec(n-temp));
            }
        }
        return res;
    }
    public static int minCount(int n) {
        int dp[] = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i =2; i<=n; i++){
            int min = Integer.MAX_VALUE;
            for (int j =1; j*j<=i;j++){
                int rem = i- j*j;
                if (dp[rem]<min){
                    min = dp[rem];
                }
            }
            return dp[i] = min+1;
        }
        return dp[n];
    }

    public static int countMinStepsToOne(int n) {
        //Your code goes here
        if (n==1){
            return 0;
        }
        int subtractOne;
        int divideByTwo = Integer.MAX_VALUE;
        int divideByThree = Integer.MAX_VALUE;

        subtractOne = countMinStepsToOne(n - 1);
        if (n%2==0){
            divideByTwo = countMinStepsToOne(n/2);
        }
        if (n%3==0){
            divideByThree = countMinStepsToOne(n/3);
        }
        return 1+ Math.min(subtractOne, Math.min(divideByThree, divideByThree));

    }

    private static int minStepsTo1_DP(int n) {
        if (n==1) return 0;
        int dp[] = new int[n+1];
        dp[1] = 0;

        for (int i =2; i<=n; i++){
            int subtractOne;
            int divideByTwo = Integer.MAX_VALUE;
            int divideByThree = Integer.MAX_VALUE;
            subtractOne = dp[i - 1];

            if (i % 2 == 0)
                divideByTwo = dp[i / 2];

            if (i % 3 == 0)
                divideByThree = dp[i / 3];

            dp[i] = 1 + Math.min(subtractOne, Math.min(divideByTwo, divideByThree));
        }
        return dp[n];
    }
}
