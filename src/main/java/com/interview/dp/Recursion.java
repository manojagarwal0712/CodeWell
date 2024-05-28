package com.interview.dp;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int []arr = {1,2,1};
        printSubsequencesWithSumK(arr, 2);
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * @param str
     */

    public static void longPalRrecursionDPSol(String str){

    }

    /**
     * Given a string, print all possible palindromic partitions
     * https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-partition/
     */
    public static void givenStirngFindAllPossiblePartition(String str){

    }


    public static void printSubsequencesWithSumK(int arr[], int k){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int currentSum =0;
        printSubsequencesWithSumKRec(arr, 0, arr.length, ans,ds, k, currentSum);
        System.out.println(ans);
    }

    private static void printSubsequencesWithSumKRec(int[] arr, int startIndex, int endIndex, List<List<Integer>> ans, List<Integer> ds, int k, int currentSum) {
        if (startIndex==endIndex){
            if (currentSum==k){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        ds.add(arr[startIndex]);
        currentSum = currentSum+arr[startIndex];
        printSubsequencesWithSumKRec(arr, startIndex+1, endIndex, ans,ds, k, currentSum);
        ds.remove(ds.size()-1);
        currentSum=currentSum-arr[startIndex];
        printSubsequencesWithSumKRec(arr, startIndex+1, endIndex,ans,ds,k, currentSum);
    }

    public static void printAllSubSequencesOfString(String str){
        List<List<Character>> ans = new ArrayList<>();
        List<Character> ds = new ArrayList<>();
        subSequneceRec(str, 0, str.length(), ans,ds);
        System.out.println(ans);
    }

    private static void subSequneceRec(String str, int startIndex, int endIndex, List<List<Character>> ans, List<Character> ds) {
        if (startIndex>=endIndex){
            ans.add(new ArrayList<>(ds));
            return;
        }
        ds.add(str.charAt(startIndex));
        subSequneceRec(str, startIndex+1,endIndex, ans, ds);
        ds.remove(ds.size()-1);
        subSequneceRec(str, startIndex+1, endIndex,ans, ds);
    }
}
