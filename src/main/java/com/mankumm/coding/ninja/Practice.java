package com.stack.com.mankumm.coding.ninja;

import java.util.*;

public class Practice {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        //System.out.print(countAllSubSequence(arr,3));
        //System.out.print(combinationSumUnique(arr, 7));
        //System.out.print(printSubSetNoDuplicate(arr));
        findSquareCount(4);
    }





    public static void findSquareCount(int n){
        System.out.println(findSquareCountHelper(n));
    }


    private static int findSquareCountHelper(int n) {
        if (n<=3){
            return n;
        }
        int count = n;
        for (int i =1; i<n; i++){
            int temp = i*i;
            if (temp<=n){
                count = Math.min(count, 1+findSquareCountHelper(n-temp));
            }else {
                break;
            }
        }
        return count;
    }

    public static void findAllPermutation(int arr[]){
        Map<Integer, Boolean> map = new HashMap();
        for (Integer a:
             arr) {
            map.put(a, false);
        }
        List<Integer> currentList = new ArrayList<>();
        findAllPermutationHelper(arr, 0, currentList, map);

    }

    private static void findAllPermutationHelper(int[] arr, int startIndex, List<Integer> currentList, Map<Integer, Boolean> map) {
        if (currentList.size()==arr.length){
            System.out.print(currentList);
            return;
        }
        for (int i =0; i <arr.length; i++){
            if (!map.get(arr[i])){
                map.put(arr[i], true);
                currentList.add(arr[i]);
                findAllPermutationHelper(arr, startIndex, currentList, map);
                map.put(arr[i], false);
                currentList.remove(currentList.size()-1);
            }
        }
    }

    public static List<List<Integer>> printSubSetNoDuplicate(int arr[]){
        List<List<Integer>> subSets = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();
        printSubSetNoDuplicateHelper(arr, 0, currentList, subSets);
        return subSets;

    }

    private static void printSubSetNoDuplicateHelper(int[] arr, int startIndex, List<Integer> currentList, List<List<Integer>> subSets) {
        if (startIndex==arr.length){
            if (!subSets.contains(currentList)){
                subSets.add(new ArrayList<>(currentList));
            }
            return;
        }
        currentList.add(arr[startIndex]);
        printSubSetNoDuplicateHelper(arr, startIndex+1, currentList, subSets);
        currentList.remove(currentList.size()-1);
        printSubSetNoDuplicateHelper(arr, startIndex+1, currentList, subSets);
    }

     public static List<Integer> subSetSum(int arr[]){
        List<Integer> currentList = new ArrayList<>();
        List<Integer> sumsAns = new ArrayList<>();
        subSetSumHelper(arr, 0, currentList, sumsAns);
        return sumsAns;

     }

    private static void subSetSumHelper(int[] arr, int startIndex, List<Integer> currentList, List<Integer> sumsAns) {
        if (startIndex==arr.length){
            Integer sum = 0;
            for (Integer a:
                 currentList) {
                sum = sum+a;
            }
            sumsAns.add(sum);
            return;
        }
        currentList.add(arr[startIndex]);
        subSetSumHelper(arr, startIndex+1, currentList, sumsAns);
        currentList.remove(currentList.size()-1);
        subSetSumHelper(arr, startIndex+1, currentList, sumsAns);
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }

    private static void findCombinations(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds) {
        if(ind == arr.length) {
            if(target == 0) {
                ans.add(new ArrayList<Integer>(ds));
            }
            return;
        }

        if(arr[ind] <= target) {
            ds.add(arr[ind]);
            findCombinations(ind, arr, target - arr[ind], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(ind + 1, arr, target, ans, ds);
    }


    public static int countAllSubSequence(int arr[], int k){
        List<Integer> list = new ArrayList<>();
        int end = arr.length;
        return countAllSubSequenceHelper(arr,k,0, 0,end, list, 0);
    }
    private static int countAllSubSequenceHelper(int[] arr, int sum, int currentSum,  int startIndex, int end, List<Integer> list, int currentCount) {
        if (startIndex==end) {
            return 1;
        }
        list.add(arr[startIndex]);
        currentSum = currentSum+arr[startIndex];
        int left =  countAllSubSequenceHelper(arr, sum, currentSum, startIndex+1, end, list, currentCount);
        list.remove(list.size()-1);
        currentSum = currentSum - arr[startIndex];
        int right  =  countAllSubSequenceHelper(arr, sum, currentSum, startIndex+1, end, list, currentCount);
        return left+right;
    }

    public static void printAnyOneSubSequenceWithSumK(int arr[], int k){
        List<Integer> list = new ArrayList<>();
        int end = arr.length;
        printAnyOneSubSequenceWithSumKHelper(arr,k,0, 0,end, list);
    }
    private static boolean printAnyOneSubSequenceWithSumKHelper(int[] arr, int sum, int currentSum,  int startIndex, int end, List<Integer> list) {
        if (startIndex==end) {
            if (sum == currentSum) {
                for (Integer a :
                        list) {
                    System.out.print(a + " ");
                }
                System.out.println();
                return true;
            }
            return false;
        }
        list.add(arr[startIndex]);
        currentSum = currentSum+arr[startIndex];
        if (printAnyOneSubSequenceWithSumKHelper(arr, sum, currentSum, startIndex+1, end, list))
            return  true;
        list.remove(list.size()-1);
        currentSum = currentSum - arr[startIndex];
        if (printAnyOneSubSequenceWithSumKHelper(arr, sum, currentSum, startIndex+1, end, list))
            return true;
        return false;
    }

    public static void printAllSubSequenceWithSumK(int arr[], int k){
        List<Integer> list = new ArrayList<>();
        int end = arr.length;
        printAllSubSequenceWithSumKHelper(arr,k,0, 0,end, list);
    }
    private static void printAllSubSequenceWithSumKHelper(int[] arr, int sum, int currentSum,  int startIndex, int end, List<Integer> list) {
        if (startIndex==end) {
            if (sum == currentSum) {
                for (Integer a :
                        list) {
                    System.out.print(a + " ");
                }
                System.out.println();
            }
            return;
        }
        list.add(arr[startIndex]);
        currentSum = currentSum+arr[startIndex];
        printAllSubSequenceWithSumKHelper(arr, sum, currentSum, startIndex+1, end, list);
        list.remove(list.size()-1);
        currentSum = currentSum - arr[startIndex];
        printAllSubSequenceWithSumKHelper(arr, sum, currentSum, startIndex+1, end, list);
    }


    public static void printAllSubSequence(int arr[]){
        List<Integer> list = new ArrayList<>();
        int end = arr.length;
        printAllSubSequenceHelper(arr,0,end, list);
    }
    private static void printAllSubSequenceHelper(int[] arr, int startIndex, int end, List<Integer> list) {
        if (startIndex==end){
            for (Integer a:
                 list) {
                System.out.print(a+" ");
            }
            System.out.println();
            return;
        }
        list.add(arr[startIndex]);
        printAllSubSequenceHelper(arr, startIndex+1, end, list);
        list.remove(list.size()-1);
        printAllSubSequenceHelper(arr, startIndex+1, end, list);
    }

    public static int printFibn(int n){
        if (n<=1) {
            return n;
        }
        return printFibn(n-1) + printFibn(n-2);
    }

    public static boolean isPal(String str){
        return isPalHelper(str, 0, str.length()-1);
    }
    private static boolean isPalHelper(String str, int start, int end) {
        if (start>end){
            return true;
        }
        if (str.charAt(start) != str.charAt(end)){
            return false;
        }
        return isPalHelper(str, start+1, end-1);
    }

    public static int fact(int n){
        if (n==0){ // smallest Value of n for which I know the result muself and dont need recursion tocall.
            return 1;
        }
        return n * fact(n-1);
    }
    public static int power(int x, int n) {
        if (n==0 ){
            return 1;
        }
        return x*power(x, n-1);
    }

    public static void printNameNTimes(int n){
        if (n==0){
            return;
        }
        System.out.println("Printing Name");
        printNameNTimes(n-1);
    }

    public static void print1toN(int n){
        print1toNHelper(n, 1);
    }
    public static void print1toNHelper(int n, int start){
        if (start>n){
            return;
        }
        System.out.println(start);
        print1toNHelper(n, start+1);
    }

    public static void printNto1(int n){
        if (n<=0){
            return;
        }
        System.out.println(n);
        printNto1(n-1);
    }

    public static void print1toNButWithoutPlusHelper(int n){
        print1toNHelper_2(n, n);
    }
    private static void print1toNHelper_2(int n, int i) {
        if (i<1){
            return;
        }
        print1toNHelper_2(n, i-1);
        System.out.println(i);
    }

    public static void printNto1ButWithoutPlusHelper(int n){
        printNto1Helper_2(n);
    }
    private static void printNto1Helper_2(int n) {
        if (n<1){
            return;
        }
        System.out.println(n);
        printNto1Helper_2(n-1);
    }

    public static int sumOfFirstN_numbers(int n){
        if (n==1){
            return 1;
        }
        return n+sumOfFirstN_numbers(n-1);
    }

    public static void sumOfFirstN_numbers_1(int n){
        int sum =0;
        sumOfFirstN_numbers_1_Helper(n, sum);
    }

    private static void sumOfFirstN_numbers_1_Helper(int n, int sum) {
        if (n<=0){
            System.out.print(sum);
            return;
        }
        sum= sum+n;
        sumOfFirstN_numbers_1_Helper(n-1, sum);
    }

    public static void reverseAnArray(int arr[]){
        int end = arr.length-1;
        reverseAnArrayHelper(arr, 0, end);
        for (int a:
             arr) {
            System.out.println(a);
        }
    }

    private static void reverseAnArrayHelper(int[] arr, int start, int end) {
        if (start>=end){
            return;
        }
        int temp = arr[end];
        arr[end] = arr[start];
        arr[start] = temp;
        reverseAnArrayHelper(arr, start+1, end-1);
    }
}
