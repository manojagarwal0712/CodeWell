package com.interview.dp;

import com.sun.org.apache.xalan.internal.xsltc.DOM;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/top-50-dynamic-programming-coding-problems-for-interviews/
public class DPQuestionGeeks {
    public static void main(String[] args) {
        int coins[] = {25, 10, 5}, V = 30;
        System.out.print(findMinCoin_1(coins, V));
    }
/**
 * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 *
 */
    public static int findMinCoin(int coins[], int val){
        return findMinCoinSol(coins, val);

    }
    public static int findMinCoinSol(int[] coins, int val) {
        if (val==0){
            return 0;
        }
        int take =Integer.MAX_VALUE;
        for (int i =0; i<coins.length; i++){
            if (coins[i]<=val){
                take = Math.min(take,1+ findMinCoinSol(coins, val-coins[i]));
            }
        }
        return take;
    }

    public static int findMinCoin_1(int[] coins, int val){
        return findMinCoinSol_1(coins, val, coins.length-1);
    }
    public static int findMinCoinSol_1(int[] coins, int val, int endIndex) {
        if (endIndex==0){
            if (val%coins[endIndex]==0){
                return val/coins[endIndex];
            }
            return Integer.MAX_VALUE; // infinite as, we can not form change wth any number of coins.
        }
        int notTake = findMinCoinSol_1(coins, val, endIndex-1);
        int take = 0;
        if (coins[endIndex]<=val){
            take = 1+findMinCoinSol_1(coins, val-coins[endIndex], endIndex);
        }
        return Math.min(notTake, take);
    }


    /**
     * https://www.geeksforgeeks.org/minimum-steps-to-delete-a-string-after-repeated-deletion-of-palindrome-substrings/
     *
     * IT PASSED ONLY FOR FEW TESTS
     */
    public static int minStepToDelete(String str){
        if (str.length()==0){
            return 0;
        }
        String temp = removeLongestPal(str);
        if (temp.equals(str)){
            return 1;
        }else {
            return 1+minStepToDelete(temp);
        }

    }
    public static String removeLongestPal(String str){
        StringBuilder temp = new StringBuilder();
        int len = Integer.MIN_VALUE;
        int stratPal = -1;
        int endPal = -1;
        for (int i =0;i<str.length(); i++){
            for (int j = i+1; j<str.length(); j++){
                if (isPal(str, i, j)){
                    if (len<(j-i+1)){
                        len = j-i+1;
                        stratPal = i;
                        endPal = j;
                    }
                }
            }
        }
        for (int i =0;i<stratPal; i++){
            temp.append(str.charAt(i));
        }
        for (int i =endPal+1;i<str.length(); i++){
            temp.append(str.charAt(i));
        }
        return temp.toString();
    }

    public static boolean isPal(String str, int start, int end){
        while (start<end){
            if (str.charAt(start)!=str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    /**
     * https://www.geeksforgeeks.org/minimize-steps-to-reach-k-from-0-by-adding-1-or-doubling-at-each-step/
     */
    public static int minOperation(int k){
        if (k==0){
            return 0;
        }
        if (k%2==0){
            return  1+ minOperation(k/2);
        }else {
            return 1+ minOperation(k-1);
        }
    }


    /**
     * https://www.geeksforgeeks.org/program-nth-catalan-number/
     * NEED MORE PRACTICE
     */
    public static int NthCatalanNumber(int n){
        int res = 0;
        if (n<=1){
            return 1;
        }
        for (int i =0; i<n; i++){
            res *=NthCatalanNumber(n) * NthCatalanNumber(n-i-1);
        }
        return res;
    }



}
