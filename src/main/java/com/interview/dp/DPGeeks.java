package com.interview.dp;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DPGeeks {
    public static void main(String[] args) {
        int arr[] = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
        //minPartitionCoin(arr, 43);
        longestCommonSubstrBetter("ABCDGH", "ACDGHR");
    }

    /**
     * https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
     * @param str1
     * @param str2
     */
    public static void longestCommonSubstrBrute(String str1, String str2){
        int j =0;
        String ans = "";
        int start = 0;
        String comm = "";
        for (int i =0; i<str1.length() && j<str2.length(); i++){
            if (str1.charAt(i)==str2.charAt(j)){
                comm=comm+str1.charAt(i);
                j++;
            }else {
                if (ans.length()<comm.length()){
                    ans=comm;
                }
                comm="";
            }
        }
        if (ans.length()<comm.length()){
            ans=comm;
        }
        System.out.println(ans);
    }
    public static void longestCommonSubstrBetter(String str1, String str2){
        System.out.println(longestCommonSubstrBetterRec(str1,str2, str1.length()-1, str2.length()-1));
    }

    private static int longestCommonSubstrBetterRec(String str1, String str2, int indx1, int indx2) {
        if (indx1<0 || indx2<0){
            return 0;
        }
        if (str1.charAt(indx1)==str2.charAt(indx2)){
            return 1+ longestCommonSubstrBetterRec(str1, str2, indx1-1, indx2-1);
        }else {
            return Math.max(longestCommonSubstrBetterRec(str1, str2, indx1 -1, indx2), longestCommonSubstrBetterRec(str1, str2, indx1, indx2 - 1));
        }
    }

    /**
     * https://www.geeksforgeeks.org/problems/-minimum-number-of-coins4426/1
     */
    public static void minPartitionCoin(int arr[], int k){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        minPartitionCoinRec(arr, 0, arr.length, ans, ds, k, 0);
        int count =Integer.MAX_VALUE;
        for (List<Integer> list:
             ans) {
            if (count>list.size()){
                count = list.size();
                ds = list;
            }
        }
        System.out.println(ds);
    }

    private static void minPartitionCoinRec(int[] arr, int startIndex, int length, List<List<Integer>> ans, List<Integer> ds, int target, int currentSum) {
        if (startIndex>=arr.length){
            if (currentSum==target){
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        if (currentSum<target){
            ds.add(arr[startIndex]);
            currentSum=currentSum+arr[startIndex];
            minPartitionCoinRec(arr, startIndex, length, ans,  ds, target, currentSum);
            ds.remove(ds.size()-1);
            currentSum= currentSum-arr[startIndex];
        }
        minPartitionCoinRec(arr, startIndex+1, length, ans,  ds, target, currentSum);
    }

    /**
     * https://www.geeksforgeeks.org/problems/find-optimum-operation4504/1
     * @param n
     * Given a number N. Find the minimum number of operations required to reach N starting from 0
     */
    public static void minOperation(int n){
        int count =0;
        while (n>0){
            if (n%2!=0){
                n= n-1;
                count++;
            }else {
                n = n/2;
                count++;
            }
        }
        System.out.println(count);
    }
}
