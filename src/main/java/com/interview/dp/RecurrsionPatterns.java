package com.interview.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecurrsionPatterns {
    public static void main(String[] args) {
        int[] arr = {2,3,6,7};
        //System.out.println(myPow(2, 2));
        System.out.println(combinationSumUnique(arr, 7));
    }


    public static HashSet<List<Integer>> combinationSumUnique(int[] nums, int k) {
        HashSet<List<Integer>> ans = new HashSet<>();
        Set<Integer> currentSubSet = new HashSet<>();
        combinationSumUniqueSol(nums, currentSubSet, ans, 0, nums.length-1, k, 0);
        return ans;
    }

    public static void combinationSumUniqueSol(int[] nums, Set<Integer> currentSubSet, HashSet<List<Integer>> ans, int start, int end, int k, int currentSum) {
        if (start>end){
            if (k==0){
                ans.add(new ArrayList<>(currentSubSet));
            }
            return;
        }
        if (nums[start]<=k){
            currentSubSet.add(nums[start]);
            combinationSumUniqueSol(nums, currentSubSet, ans, start, end, k-nums[start], currentSum);
            currentSubSet.remove(currentSubSet.size()-1);
        }
        combinationSumUniqueSol(nums, currentSubSet, ans, start+1, end, k, currentSum);
    }

    /**
     * https://leetcode.com/problems/combination-sum/
     * https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2/
     * @return
     *
     * 1, 2, 3 , k =3
     */

    public static List<List<Integer>> combinationSum(int[] nums, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currentSubSet = new ArrayList<>();
        combinationSumSol(nums, currentSubSet, ans, 0, nums.length-1, k, 0);
        return ans;
    }

    public static void combinationSumSol(int[] nums, List<Integer> currentSubSet, List<List<Integer>> ans, int start, int end, int k, int currentSum) {
        if (start>end){
            if (k==0){
                ans.add(new ArrayList<>(currentSubSet));
            }
            return;
        }
        if (nums[start]<=k){
            currentSubSet.add(nums[start]);
            combinationSumSol(nums, currentSubSet, ans, start, end, k-nums[start], currentSum);
            currentSubSet.remove(currentSubSet.size()-1);
        }
        combinationSumSol(nums, currentSubSet, ans, start+1, end, k, currentSum);
    }


    /**
     * Print subsequence with sum k
     */

    public static void printSubSeqWithSumK(int []nums, int k){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currentSubSet = new ArrayList<>();
        printSubSeqWithSumKSol(nums, currentSubSet, ans, 0, nums.length-1, k, 0);
        System.out.println(ans);
    }

    public static void printSubSeqWithSumKSol(int[] nums, List<Integer> currentSubSet, List<List<Integer>> ans, int start, int end, int k, int currentSum) {
        if (start>end){
            if (currentSum==k){
                ans.add(new ArrayList<>(currentSubSet));
            }
            return;
        }
        currentSubSet.add(nums[start]);
        currentSum+=nums[start];
        printSubSeqWithSumKSol(nums, currentSubSet, ans, start+1, end, k, currentSum);
        currentSum= currentSum-currentSubSet.get(currentSubSet.size()-1);
        currentSubSet.remove(currentSubSet.size()-1);
        printSubSeqWithSumKSol(nums, currentSubSet, ans, start+1, end, k, currentSum);
    }

    /**
     * https://takeuforward.org/data-structure/power-set-print-all-the-possible-subsequences-of-the-string/
     */

    public static void printAllSubSequences(String str){
        List<List<Character>> ans  = new ArrayList<>();
        List<Character> currentSeq = new ArrayList<>();
        printAllSubSequencesSol(str, currentSeq, ans, 0, str.length()-1);
        System.out.println(ans);
    }

    public static void printAllSubSequencesSol(String str, List<Character> currentSeq, List<List<Character>> ans, int start, int end) {
        if (start>end){
            if (!currentSeq.isEmpty()){
                ans.add(new ArrayList<>(currentSeq));
            }
            return;
        }
        currentSeq.add(str.charAt(start));
        printAllSubSequencesSol(str, currentSeq, ans, start+1, end);
        currentSeq.remove(currentSeq.size()-1);
        printAllSubSequencesSol(str, currentSeq, ans, start+1, end);
    }

    /**
     * https://leetcode.com/problems/subsets/
     * Given an integer array nums of unique elements, return all possible
     * subsets
     *  (the power set).
     */

    public static void printAllSubSet(int []nums){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> currentSubSet = new ArrayList<>();
        printAllSubSetSol(nums, currentSubSet, ans, 0, nums.length-1);
        System.out.println(ans);
    }

    public static void printAllSubSetSol(int[] nums, List<Integer> currentSubSet, List<List<Integer>> ans, int start, int end) {
        if (start>end){
            ans.add(new ArrayList<>(currentSubSet));
            return;
        }
        currentSubSet.add(nums[start]);
        printAllSubSetSol(nums, currentSubSet, ans, start+1, end);
        currentSubSet.remove(currentSubSet.size()-1);
        printAllSubSetSol(nums, currentSubSet, ans, start+1, end);
    }


    /**
     * https://leetcode.com/problems/count-good-numbers/
     * https://www.codingninjas.com/studio/problems/good-numbers_625508?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
     *
     * @return
     */
    public static List< Integer > goodNumbers(int a, int b, int digit) {
        List<Integer> ans = new ArrayList<>();
        while (a<=b){
            if (!containsDigit(a, digit)){
                if (isGoodNumber(a)){
                    ans.add(a);
                }
            }
            a++;
        }
        return ans;
    }

    public static boolean isGoodNumber(int a) {
        List<Integer> list = new ArrayList<>();
        int sum = a%10;
        a = a/10;
        while (a>0){
            if (a%10<=sum){
                return false;
            }
            sum = sum+a%10;
            a = a/10;
        }
        return true;
    }

    public static boolean containsDigit(int num, int digit){
        while (num>0){
            int temp = num%10;
            if (temp==digit){
                return true;
            }
            num = num/10;
        }
        return false;
    }


    public static double myPow(double x, int n) {
        if (n==0){
            return 1;
        }
        if (n==1){
            return x;
        }
        return x * myPow(x, n-1);
    }

    public static double myPowBetter(double x, int n) {
        if (n==0){
            return 1;
        }
        else if (n%2==0){
            return myPow(x, n/2) * myPow(x, n/2);
        }else {
            return x * myPow(x, n/2) * myPow(x, n/2);
        }
    }


    //"1234"
    public static int myAtoi(String str, int n ) {
       if (str==null || str.isEmpty()){
           return 0;
       }
       if (n==1){
           return (str.charAt(0)-'0');
       }

       return 10*myAtoi(str, n-1) + str.charAt(n-1)-'0';
    }

}
