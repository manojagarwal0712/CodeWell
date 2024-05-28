package com.interview.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://medium.com/javarevisited/20-dynamic-programming-interview-questions-with-solutions-2a144b1a1e07
 */
public class DPQuestions {
    public static void main(String[] args) {
        //steps(4);
        //longSubSeq("BBABCBCAB");
        //longestCommonSubSequnece("AGGTAB", "GXTXAYB");
        //longestCommonSubSequnece("BBABCBCAB", "BACBCBABB");
        ///longestCommonSubstring("GeeksforGeeks", "GeeksQuiz");
        int []coins = {2, 5, 3, 6};
        int amount = 10;
        coinChange(coins, amount);
    }
    /**
     * The Coin Change Problem
     * https://www.geeksforgeeks.org/coin-change-dp-7/
     */
    public static void coinChange(int arr[], int sum){
        System.out.println(coinChangeRec(arr, sum, arr.length));
    }

    private static int coinChangeRec(int[] arr, int sum, int n) {
        if (sum==0){
            return 1;
        }
        if (sum<0){
            return 0;
        }
        if (n<=0){
            return 0;
        }
        return coinChangeRec(arr, sum, n-1)+ coinChangeRec(arr, sum-arr[n-1], n);
    }

    /**
     * Longest common substring
     */
    public static int longestCommonSubstring(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        int ans = 0;

        int dp[][] = new int[m + 1][n + 1];
        for (int i =0; i<m; i++){
            for (int j =0; j<n; j++){
                if (dp[i-1]==dp[j-1]){
                    dp[i][j] = 1+ dp[i-1][j-1];
                    ans=Math.max(ans, dp[i] [j]);
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans;


    }

    /**
     * Longest common substring
     */
    public static void longestCommonSubSequnece(String s1, String s2){
        System.out.println(longestCommonSubSequneceRec(s1, s2, s1.length()-1, s2.length()-1));
    }

    private static int longestCommonSubSequneceRec(String s1, String s2, int i, int j) {
        if (i<0 || j<0){
            return 0;
        }
        if (s1.charAt(i)==s2.charAt(j)){
            return 1+ longestCommonSubSequneceRec(s1, s2, i-1, j-1);
        }
        return Math.max(longestCommonSubSequneceRec(s1, s2, i, j-1), longestCommonSubSequneceRec(s1, s2, i-1, j));
    }

    /**
     * Longest palindromic subsequence Question
     * https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
     */
    public static void longPalSubSeqBrute(String str){
        List<List<Character>> ans = new ArrayList<>();
        List<Character> ds = new ArrayList<>();
        int len =0;
        longSubSeqRrec(str, ds, ans, 0);
        for (List<Character> l1:
             ans) {
            len = Math.max(len, l1.size());
        }
        System.out.println(len);
    }

    private static void longSubSeqRrec(String str, List<Character> ds, List<List<Character>>ans, int startIndex) {
        if (startIndex==str.length()){
            if (isPal(ds)){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        ds.add(str.charAt(startIndex));
        longSubSeqRrec(str, ds, ans, startIndex+1);
        ds.remove(ds.size()-1);
        longSubSeqRrec(str, ds,  ans,startIndex+1);
    }
    public static boolean isPal(List<Character> ds){
        int i =0;
        int j = ds.size()-1;
        while (i<j){
            if (ds.get(i)!=ds.get(j)){
                return false;
            }
            i++;
            j--;
        }
    return true;
    }

    /**
     * The Climbing Stairs problem
     * This is one of the most popular coding problems which can be solved using the Dynamic Programming technique.
     * In this problem, you are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps.
     * The question is, in how many distinct ways can you climb to the top?
     */
    public static void steps(int n ){
        System.out.println(stepsRec(n));
    }

    private static int stepsRec(int n) {
        if (n==1 || n==2){
            return n;
        }
       return stepsRec(n-1) + stepsRec(n-2);
    }

}
