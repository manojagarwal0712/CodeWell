package com.stack.com.mankumm.coding.ninja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DP_TUF {
    static int resLength = Integer.MIN_VALUE;
    public static void main(String[] args) {
        //countWaysRec(4);
        int arr [] = {10,20,30,10};
        int[] arr1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4};
        int hval[] = { 6, 7, 1, 3, 8, 2, 4 };
        //System.out.println(minSquareM(6));
        //System.out.println(minSquareM(10));
        //subSeqLongestIncreasingM(arr);
        findMaxStolenValRec(hval);
        findMaxStolenValM(hval);
    }

    /**
     * https://www.geeksforgeeks.org/find-maximum-possible-stolen-value-houses/
     * There are N houses built in a line, each of which contains some value in it. A thief is going to steal the maximum value of these
     * houses, but he can’t steal in two adjacent houses because the owner of the stolen houses will tell his two neighbors left and
     * right sides. The task is to find what is the maximum stolen value.
     */
    public static void findMaxStolenValRec(int arr[]){
        int end = arr.length-1;
        System.out.println(findMaxStolenValRecHelper(arr, end));
    }
    private static int findMaxStolenValRecHelper(int arr[], int end) {
        if (end <0){
            return 0;
        }
        if (end==0){
            return arr[0];
        }
        int take = arr[end]+ findMaxStolenValRecHelper(arr, end-2); // end-2 , becuase can not loop consecutive houses.
        int notTake = findMaxStolenValRecHelper(arr, end-1);
        return Math.max(take, notTake);
    }
    public static void findMaxStolenValM(int arr[]){
        int end = arr.length-1;
        int[] storage = new int[end+1];
        Arrays.fill(storage, -1);
        System.out.println(findMaxStolenValMHelper(arr, end, storage));
    }
    private static int findMaxStolenValMHelper(int arr[], int end, int[]storage) {
        if (end <0){
            return 0;
        }
        if (end==0){
            return arr[0];
        }
        if (storage[end]!=-1){
            return storage[end];
        }
        int take = arr[end]+ findMaxStolenValRecHelper(arr, end-2);
        int notTake = findMaxStolenValRecHelper(arr, end-1);
        return storage[end] = Math.max(take, notTake);
    }


    /**
     * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
     * Given an array arr[] of size N. The task is to find the sum of the contiguous subarray within a arr[] with the largest sum.
     *
     */
    public static void findMaxSum(int arr[]){
        int maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int i =0;i<arr.length; i++){
            sum = sum + arr[i];
            if (sum>maxi){
                maxi = sum;
            }
            if (sum<0){
                sum =0;
            }
        }
        System.out.println(maxi);
    }
    /**
     * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
     * https://takeuforward.org/data-structure/longest-increasing-subsequence-dp-41/
     * Given an array arr[] of size N, the task is to find the length of the Longest Increasing Subsequence (LIS) i.e.,
     * the longest possible subsequence in which the elements of the subsequence are sorted in increasing order.
     */
    public static void subSeqLongestIncreasing(int arr[]){
        int end = arr.length-1;
        System.out.print(subSeqLongestIncreasingHelper(arr, 0, -1, end));
    }
    private static int subSeqLongestIncreasingHelper(int[] arr, int startIndex, int prev_ind, int end) {
        if (startIndex==end){
            return 0;
        }
        int len = subSeqLongestIncreasingHelper(arr, startIndex+1, prev_ind, end);
        if (prev_ind==-1 || arr[prev_ind]<arr[startIndex]){
            len = Math.max(len, 1+ subSeqLongestIncreasingHelper(arr, startIndex+1, startIndex, end));
        }
        return len;
    }

    public static void subSeqLongestIncreasingM(int arr[]){
        int end = arr.length-1;
        int storage[][] = new int[arr.length][arr.length+1];
        for(int row[]: storage)
            Arrays.fill(row,-1);
        System.out.print(subSeqLongestIncreasingHelperM(arr, 0, -1, end, storage));
    }
    private static int subSeqLongestIncreasingHelperM(int[] arr, int startIndex, int prev_ind, int end, int[][]storage) {
        if (startIndex==end){
            return 0;
        }
        if (storage[startIndex][prev_ind+1]!=-1){
            return storage[startIndex][prev_ind+1];
        }
        int len = subSeqLongestIncreasingHelperM(arr, startIndex+1, prev_ind, end, storage);
        if (prev_ind==-1 || arr[prev_ind]<arr[startIndex]){
            len = Math.max(len, 1+ subSeqLongestIncreasingHelperM(arr, startIndex+1, startIndex, end, storage));
        }
        storage[startIndex][prev_ind+1] = len;
        return storage[startIndex][prev_ind+1];
    }


    public static void subSequence(int arr[]){
        List<Integer> ans = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        int n = arr.length-1;
        subSequenceRecHelper(arr, 0, n, ans, current);
        System.out.print(resLength);
    }

    private static void subSequenceRecHelper(int[] arr, int startIndex, int end, List<Integer> ans, List<Integer> current) {
        if (startIndex==end){
            if (resLength<current.size()){
                resLength = current.size();
            }
            return;
        }
        if (current.isEmpty()){
            current.add(arr[startIndex]);
        }else if (current.get(current.size()-1)<arr[startIndex]){
            current.add(arr[startIndex]);
        }
        subSequenceRecHelper(arr, startIndex+1, end,  ans, current);
        if (current.isEmpty()){
            current.remove(current.size()-1);
        }
        subSequenceRecHelper(arr, startIndex+1, end, ans, current);
    }



    /**
     * Minimum number of squares whose sum equals to given number n
     * https://www.geeksforgeeks.org/minimum-number-of-squares-whose-sum-equals-to-given-number-n/
     * A number can always be represented as a sum of squares of other numbers. Note that 1 is a square and we can always break a
     * number as (1*1 + 1*1 + 1*1 + …). Given a number n, find the minimum number of squares that sum to X.
     *
     */
    public static int minSquare(int n){
        if (n<=3){
            return n;
        }
        int res = n;
        for (int i =1; i<=n; i++){
            int temp = i * i;
            if (temp>n){
                break;
            }else {
                res = Math.min(res, 1+ minSquare(n-temp));
            }
        }
        return res;
    }

    public static int minSquareM(int n){
       int storage[] = new int[n+1];
       Arrays.fill(storage, -1);
        return minSquareMHelper(n, storage);
    }

    public static int minSquareMHelper(int n, int[] storage){
        if (n<=3){
            storage[n] = n;
            return storage[n];
        }
        if (storage[n]!=-1){
            return storage[n];
        }
        storage[n] = n;
        for (int i =1; i<=n; i++){
            int temp = i * i;
            if (temp>n){
                break;
            }else {
                storage[n] = Math.min(storage[n], 1+ minSquareMHelper(n-temp, storage));
            }
        }
        return storage[n];
    }

    /**
     * https://www.geeksforgeeks.org/minimum-steps-minimize-n-per-given-condition/
     * Given a number n, count minimum steps to minimize it to 1 according to the following criteria:
     *
     * If n is divisible by 2 then we may reduce n to n/2.
     * If n is divisible by 3 then you may reduce n to n/3.
     * Decrement n by 1.
     */
    public static int miniSteps(int n){
        if (n<=1){
            return 0;
        }
        int op1 = miniSteps(n-1);
        int minStepsCount = op1;
        if (n%3==0){
            int op2 = miniSteps(n/3);
            if (op2<minStepsCount){
                minStepsCount = op2;
            }
        }
        if (n%2==0){
            int op3 = miniSteps(n/2);
            if (op3<minStepsCount){
                minStepsCount = op3;
            }
        }
        return 1+minStepsCount;
    }
    public static int miniStepsM(int n){
        int[] storage = new int[n+1];
        Arrays.fill(storage, -1);
        return miniStepsMHelper(n, storage);
    }
    public static int miniStepsMHelper(int n, int []storage){
        if (n<=1){
            storage[n] = 0;
            return storage[n];
        }
        if (storage[n]!=-1){
            return storage[n];
        }
        int op1 = miniStepsMHelper(n-1, storage);
        int minStepsCount = op1;
        if (n%3==0){
            int op2 = miniStepsMHelper(n/3, storage);
            if (op2<minStepsCount){
                minStepsCount = op2;
            }
        }
        if (n%2==0){
            int op3 = miniStepsMHelper(n/2, storage);
            if (op3<minStepsCount){
                minStepsCount = op3;
            }
        }
        storage[n] = 1+minStepsCount;
        return storage[n];
    }


    /**
     * Find Fibnaachi number with rec, Memoization, and DP:
     * https://www.geeksforgeeks.org/program-for-nth-fibonacci-number/
     *
     * @param n
     * @return
     */
    public static int findFibRec(int n){
        if (n<=1){
            return n;
        }
        return findFibRec(n-1)+findFibRec(n-2);
    }
    /**
     * @param n
     * @return
     */
    public static int findFibM(int n){
        int []storage = new int[n+1];
        for (int i = 0; i<storage.length; i++){
            storage[i] = -1; // initilizing the storage with -1 , so that we know if its -1 then it has not yet been calculated.
        }
        return findFibMHelper(n, storage);
    }
    public static int findFibMHelper(int n , int[] storage){
        if (n==1 || n ==0){
            storage[n] = n; // In Memoization, we do store the value in storage and then return from storage.
            return storage[n];
        }
        if (storage[n] !=-1){ // before calculating , find out that , if we have already calculate this value, if so , then return then instead of calculating it agai.
            return storage[n];
        }
        storage[n] = findFibMHelper(n-1, storage)+findFibMHelper(n-2, storage); // store the value that we find and then return from storage.
        return storage[n];
    }
    public static int findFibDP(int n){
        int []storage = new int[n+1]; // There is an extra element for when  n =0;
        storage[0] = 0; // we know the base value of fib from bottom 0==0, and 1==1.
        storage[1] = 1;
        for (int i = 2; i<=n; i++){
            storage[i] = storage[i-1] + storage[i-2]; // we know to calculate i, we need to know i-1 and i-2 as per rec sol.
        }
        return storage[n]; // finally after calculating ,we do retrun the fib value at n.
    }
}
