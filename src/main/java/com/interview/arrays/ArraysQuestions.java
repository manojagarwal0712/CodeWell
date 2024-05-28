package com.interview.arrays;

import java.util.*;

class Pair
{
    long first, second;
    public Pair(long first, long second)
    {
        this.first = first;
        this.second = second;
    }
}

public class ArraysQuestions {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 2};
        int arr2[] = new int[]{3, 5, 6,7};
        int nums1[] = {0,0};
        int nums2[] = {0,0};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    /**
     * https://www.geeksforgeeks.org/find-subarray-with-given-sum/
     * https://leetcode.com/problems/subarray-sum-equals-k/description/
     * @param arr
     * @param k
     */
    public int subarraySumBrute(int[] arr, int k) {
        int count =0;
        for (int i = 0; i<arr.length; i++){
            int sum = arr[i];
            if (sum==k){
                count++;
            }
            for (int j = i+1; j<arr.length; j++){
                sum+=arr[j];
                if (sum==k){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
     * @param arr
     * @param N
     * @param k
     * @return
     */
    public static int lenOfLongSubarr(int arr[], int N, int k) {
        int i =0; int j =0;
        int maxLen = 0;
        int sum =0;
        while (j<arr.length){
            sum+=arr[j];
            if (sum==k){
                maxLen = Math.max(sum, j-i+1);
            }
            if (sum>k){
                while (sum>k){
                    sum-=arr[i];
                    i++;
                }
            }
            j++;
        }
        return maxLen;
    }

    /**
     * Longest sum sub array orray or count of the sub array we shall use: Better Approach(Using Hashing), Sliding window doesnt give optimal soition
     * https://takeuforward.org/data-structure/longest-subarray-with-given-sum-k/
     *
     */
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }


    /**
     * https://www.geeksforgeeks.org/move-negative-numbers-beginning-positive-end-constant-extra-space/
     *
     */
    public static void moveAllNegativeToBegPosToEnd(int arr[]){
        int l = 0;
        int r = arr.length-1;
        while (l<r){
            //when both negative increament the left
            if (arr[l]<0 && arr[r]<0){
                l++;
            }
            //when both are positive
            if (arr[l]>0 && arr[r]>0){
                r--;
            }
            //if left is postive and right negative - swap
            if (arr[l]>0 && arr[r]<0){
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
            // when l negative and right positive
            if (arr[l]<0 && arr[r]>0){
                l++;
                r--;
            }
        }
    }

    public static void moveAllNegAndPos(int arr[]){
        int i =0; int j = 0;
        while (arr[i]<=0){
            i++;
        }
        j = i+1;
        while (i<arr.length && j<arr.length){
            if (arr[j]<=0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j++;
            }else {
                j++;
            }
        }
        for (int a:
                arr) {
            System.out.println(a);
        }

    }

    /**
     * https://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
     * Find a peak element which is not smaller than its neighbours
     * @param arr
     * @param n
     * @return
     */
    public static int findPeak(int arr[], int n){
        // First or last element is peak element
        if (n == 1)
            return 0;
        if (arr[0] >= arr[1]) // this means the array was increasing order from 0 and then started decreasing from arr 1 thats why we reurned arr[0]
            return 0;
        if (arr[n - 1] >= arr[n - 2]) // this means till 2nd last element the array decreasing and now from 2nd last it started increasing so the 2nd last element is peak.
            return n - 1;
        // Check for every other element
        for (int i = 1; i < n - 1; i++) {
            // Check if the neighbors are smaller
            if (arr[i] >= arr[i - 1] && arr[i] >= arr[i + 1])
                return i;
        }
        return 0;
    }
    public static int findPeakBinarySearch(int arr[], int n){
        return findPeakBinarySearchSol(arr, 0, n-1, n);
    }
    public static int findPeakBinarySearchSol(int[] arr, int low, int high, int n) {
        int mid = (low + high)/2;
        if ((mid==0 || arr[mid-1]<=arr[mid]) && (mid==n-1 || arr[mid+1]<=arr[mid])){
            return mid;
        }else if (arr[mid-1]>arr[mid]){
            return findPeakBinarySearchSol(arr, low, mid-1, n);
        }else {
            return findPeakBinarySearchSol(arr, mid+1, high, n);
        }
    }

    /**
     * https://www.geeksforgeeks.org/program-find-minimum-maximum-element-array/
     *
     */
    public Pair findMaxMin(long arr[]){
        long max = Integer.MIN_VALUE;
        long min = Integer.MAX_VALUE;
        for (int i =0; i<arr.length; i++){
            if (max<arr[i]){
                max = arr[i];
            }
            if (min>arr[i]){
                min = arr[i];
            }
        }
        return new Pair(min, max);
    }

    /**
     * Reverse an array
     * https://www.geeksforgeeks.org/write-a-program-to-reverse-an-array-or-string/
     */
    public static void reverseArrayRecu(int arr[]){
        reverseArrayRecuSol(arr, 0, arr.length-1);
    }
    public static void reverseArrayRecuSol(int[] arr, int left, int right) {
        if (left>=right){
            return;
        }
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        reverseArrayRecuSol(arr, left+1, right+1);
    }

    /**
     * https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/
     */
    public static int findFreq(int arr[], int k){
        int count = 0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]==k){
                count++;
            }
        }
        return count;
    }

    /**
     * 1. find the index of the element with Binary search
     * 2. Then from the index see if left is equeal of the value at index found by BS then count all same till the number repeat.
     * 3. else count in right.
     * @param arr
     * @param k
     * @return
     */
    public static int findFreqBinarySearch(int arr[], int k){
        int ind =  findFreqBinarySearchSol(arr, 0, arr.length-1, k, 0);
        if (ind==-1){
            return 0;
        }
        int count = 1;
        int left = ind-1;
        while (left>=0 && arr[left] == k){
            count++;
            left--;
        }
        int right = ind+1;
        while (right<arr.length-1 && arr[right]==k){
            count++;
            right++;
        }
        return count;
    }

    public static int findFreqBinarySearchSol(int[] arr, int low, int high, int k, int count) {
        if (low>high){
            return -1;
        }
        int mid = low + (high-low)/2;
        if (arr[mid] == k){
            return mid;
        }
        if (arr[mid]>k){
            findFreqBinarySearchSol(arr, low, mid-1, k, count);
        }
        return findFreqBinarySearchSol(arr, mid+1, high, k, count);
    }
    /**
     * https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/
     */
    public static void sortArray(int[] arr){
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]==0){
                zeros++;
            }else if (arr[i]==1){
                ones++;
            }else {
                twos++;
            }
        }
        int i =0;
        while (zeros>0){
            arr[i] = 0;
            zeros--;
            i++;
        }
        while (ones>0){
            arr[i] = 0;
            ones--;
            i++;
        }
        while (twos>0){
            arr[i] = 0;
            twos--;
            i++;
        }
    }



    /**
     * https://www.geeksforgeeks.org/find-the-missing-number/
     */
    public static int findMissingInt(int arr[]){
        int n = arr.length;
        int totalSum = (n * (n+1))/2;
        int actualSum = 0;
        for (int i =0; i<arr.length; i++){
            actualSum+=arr[i];
        }
        return totalSum - actualSum;

    }


    /**
     * https://www.geeksforgeeks.org/non-repeating-element/
     */
    public static int findFirstNonRepeatElement(int arr[]){
        Map<Integer, Integer> map  = new LinkedHashMap<>();
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else {
                map.put(arr[i], 1);
            }
        }
        for (int i = 0; i<arr.length; i++){
            if (map.containsKey(arr[i]) && map.get(arr[i])==1){
                return i;
            }
        }
        return -1;
    }

    /**
     * https://www.geeksforgeeks.org/find-first-repeating-element-array-integers/
     */
    public static int findFirstRepeatingCharBrute(int arr[]){
        for (int i =0; i<arr.length; i++){
            for (int j = i+1; j<arr.length; j++){
                if (arr[i]==arr[j]){
                    return i;
                }
            }
        }
        return -1;
    }

    public static int findFirstRepeatingCharBetter(int arr[]){
        Map<Integer, Integer> map  = new LinkedHashMap<>();
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else {
                map.put(arr[i], 1);
            }
        }
        for (int i = 0; i<arr.length; i++){
            if (map.containsKey(arr[i]) && map.get(arr[i])>1){
                return i;
            }
        }
        return -1;
    }


    /**
     * https://www.geeksforgeeks.org/find-common-elements-three-sorted-arrays/
     */

    public static void findCommonElementInThreeSortedArray(int arr1[], int arr2[], int arr3[]){
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arr1.length; i++){
            map.put(arr1[i], map.getOrDefault(map.get(arr1[i]),0)+1);
        }
        for (int i =0; i<arr2.length; i++){
            map.put(arr2[i], map.getOrDefault(map.get(arr2[i]),0)+1);
        }
        for (int i =0; i<arr3.length; i++){
            map.put(arr3[i], map.getOrDefault(map.get(arr3[i]),0)+1);
        }
        for (Map.Entry<Integer, Integer> entry:
                map.entrySet()) {
            if (entry.getValue()==3){
                System.out.println(entry.getKey());
            }
        }
    }
    public static void findCommonElementInThreeSortedArrayBetter(int arr1[], int arr2[], int arr3[]){
        int i =0; int j =0; int k=0;
        while (i<arr1.length && j<arr2.length && k <arr3.length){
            if(arr1[i]==arr2[j] && arr2[j]==arr3[k]){
                System.out.print(arr1[i]);
                i++;
                j++;
                k++;
            }
            else if (arr1[i]<arr2[j]){
                i++;
            }else if (arr2[j]<arr3[k]){
                j++;
            }else {
                k++;
            }

        }
    }



    /**
     * https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
     */
    public static void findDuplicateBrute(int arr[]){
        for (int i =0; i<arr.length; i++){
            for (int j = i+1; j<arr.length; j++){
                if (arr[i]==arr[j]){
                    System.out.print(arr[i]);
                }
            }
        }
    }

    public static void findDuplicate(int arr[]){
        Set<Integer> set = new HashSet<>();
        for (int i =0; i<arr.length; i++){
            if (set.contains(arr[i])){
                System.out.print(arr[i]);
            }
            set.add(arr[i]);
        }
    }
    public static void findDuplicateBetter(int arr[]){
        Map<Integer, Integer> map = new HashMap();
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else {
                map.put(arr[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry:
                map.entrySet()) {
            if (entry.getValue()>1){
                System.out.println(entry.getKey());
            }
        }
    }


    /**
     * https://www.geeksforgeeks.org/count-pairs-with-given-sum/
     */
    public static void countPair(int arr[], int k){
        for (int i =0; i<arr.length; i++){
            for (int j = i+1; j<arr.length; j++){
                if (arr[i]+arr[j] ==k){
                    System.out.println("("+arr[i] + ","+ arr[j] + ")");
                }
            }
        }
    }

    public static void countPairBetter(int arr[], int k){
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(k-arr[i])){
                System.out.println("("+arr[i] + ","+ (k-arr[i]) + ")");
            }
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else {
                map.put(arr[i], 1);
            }
        }
    }

    /**
     * https://www.geeksforgeeks.org/count-subarrays-equal-number-1s-0s/
     */
    public static int countSubArray(int[] arr){
        int zeros = 0;
        int ones = 0;
        int count =0;
        for (int i =0; i<arr.length; i++){
            zeros =0;
            ones = 0;
            if (arr[i]==0){
                zeros +=1;
            }else {
                ones +=1;
            }
            for (int j =i+1; j<arr.length; j++){
                if (arr[j]==0){
                    zeros +=1;
                }else {
                    ones +=1;
                }
                if (zeros == ones && zeros>0){
                    System.out.println("("+ i + ","+ j +")");
                    count++;
                }
            }
        }
        return count;

    }


    /**
     * https://www.geeksforgeeks.org/rearrange-array-alternating-positive-negative-items-o1-extra-space/
     * Sol1 - Extra space seperate out the pos and neg and then fill original arr with pos and neg alternatively.
     * Sol2: Sort the array and then swap postive and negative.
     * Sol3:
     * 1. Ship all negative to the end first.
     * 2. Then arrange postive and negative in alternate fashion.
     */
    public static void arrangeInPositiveNegatievAlternateBrute(int arr[]){
        int pos[] = new int[arr.length];
        int neg[] = new int[arr.length];
        int j =0; int k =0;
        int pIndex = 0;
        int nIndex = 0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]>=0){
                pos[pIndex] = arr[i];
                pIndex++;
            }else {
                neg[nIndex] = arr[i];
                nIndex++;
            }
        }

        for (int i =0; i<arr.length; i++){
            if (i%2==0 && j<arr.length){
                arr[i] = pos[j];
                j++;
            }
            if (i%2!=0 && k<arr.length){
                arr[i] = neg[k];
                k++;
            }
        }
        for (int a:
                arr) {
            System.out.println(a);
        }
    }

    public static void arrangeInPositiveNegatievAlternateBetter(int arr[]){
        List<Integer> list = new ArrayList<>();
        int pIndex =0, nIndex =0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]>=0 && i%2==0){
                list.set(pIndex, arr[i]);
                pIndex+=2;
            }else {
                list.set(nIndex, arr[i]);
                nIndex+=2;
            }
        }
        for (int a:
                list) {
            System.out.println(a);
        }
    }

    /**
     * There’s an array ‘A’ of size ‘N’ with positive and negative elements (not necessarily equal).
     * Without altering the relative order of positive and negative elements, you must return an array of alternately positive and negative
     * values. The leftover elements should be placed at the very end in the same order as in array A.
     * https://takeuforward.org/arrays/rearrange-array-elements-by-sign/
     */
    public static ArrayList<Integer> RearrangebySign(ArrayList<Integer> A, int n) {
        // Define 2 ArrayLists, one for storing positive
        // and other for negative elements of the array.
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        // Segregate the array into positives and negatives.
        for (int i = 0; i < n; i++) {
            if (A.get(i) > 0)
                pos.add(A.get(i));
            else
                neg.add(A.get(i));
        }

        // If positives are lesser than the negatives.
        if (pos.size() < neg.size()) {

            // First, fill array alternatively till the point
            // where positives and negatives are equal in number.
            for (int i = 0; i < pos.size(); i++) {
                A.set(2 * i, pos.get(i));
                A.set(2 * i + 1, neg.get(i));
            }

            // Fill the remaining negatives at the end of the array.
            int index = pos.size() * 2;
            for (int i = pos.size(); i < neg.size(); i++) {
                A.set(index, neg.get(i));
                index++;
            }
        }

        // If negatives are lesser than the positives.
        else {
            // First, fill array alternatively till the point
            // where positives and negatives are equal in number.
            for (int i = 0; i < neg.size(); i++) {
                A.set(2 * i, pos.get(i));
                A.set(2 * i + 1, neg.get(i));
            }

            // Fill the remaining positives at the end of the array.
            int index = neg.size() * 2;
            for (int i = neg.size(); i < pos.size(); i++) {
                A.set(index, pos.get(i));
                index++;
            }
        }
        return A;
    }

    /**
     * https://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
     */
    public static boolean subArrayWithSumZero(int[] arr){
        for (int i =0; i<arr.length; i++){
            int sum = arr[i];
            if (sum==0){
                return true;
            }
            for (int j =i+1; j<arr.length; j++){
                sum += arr[j];
                if (sum==0){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean subArrayWithSumZeroHash(int arr[]){
        Map<Integer, Integer> map = new HashMap<>();
        int sum =0;
        for (int i =0; i<arr.length; i++){
            sum+=arr[i];
            if (sum==0|| arr[i]==0 ||map.containsKey(sum) ){
                return true;
            }
            map.put(sum, i);
        }
        return false;
    }


    /**
     * https://www.geeksforgeeks.org/maximum-product-subarray/
     */
    public static int findMaxProdSubArray(int arr[]){
        int ans = Integer.MIN_VALUE;
        int prod = 1;
        for (int i =0; i<arr.length; i++){
            prod =1;
            prod *= arr[i];
            for (int j =i+1; j<arr.length; j++){
                ans = Math.max(ans, prod);
                prod *=arr[j];
            }
            ans = Math.max(ans, prod);
        }
        return ans;
    }

    /**
     * https://takeuforward.org/data-structure/maximum-product-subarray-in-an-array/
     *
     */
    public static int findMaxProdSubArrayBetter(int arr[]){
        int pre =1;
        int suf = 1;
        int n = arr.length;
        int ans = Integer.MIN_VALUE;
        for (int i =0;i<arr.length; i++){
            if (pre==0){
                pre = 1;
            }
            if (suf==0){
                suf=1;
            }
            pre=pre*arr[i];
            suf = suf*arr[n-i-1];
            ans = Math.max(ans, Math.max(pre, suf));
        }
        return ans;
    }

    /**
     * https://www.geeksforgeeks.org/longest-consecutive-subsequence/
     */
    public static void longestConsecutiveSubSequence(int arr[]){
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        int startIndex =0;
        int endIndex = arr.length-1;
        Arrays.sort(arr);
        longestConsecutiveSubSequenceRec(arr, ans, ds, startIndex, endIndex);
        System.out.println(ans);
    }

    private static void longestConsecutiveSubSequenceRec(int[] arr, List<List<Integer>> ans, List<Integer> ds, int startIndex, int endIndex) {
        if (startIndex==endIndex){
            if (isConsecutive(ds)){
                if (ans.size()==0){
                    ans.add(new ArrayList<>(ds));
                }else if(ans.get(ans.size()-1).size()<ds.size()){
                    ans.remove(ans.size()-1);
                }
            }
            return;
        }
        ds.add(arr[startIndex]);
        longestConsecutiveSubSequenceRec(arr, ans, ds, startIndex+1, endIndex);
        ds.remove(ds.size()-1);
        longestConsecutiveSubSequenceRec(arr, ans, ds, startIndex+1, endIndex);
    }
    public static boolean isConsecutive(List<Integer> ds){
        int arr[] = new int[ds.size()];
        for (int i=0; i<ds.size(); i++){
            arr[i] = ds.get(i);
        }
        Arrays.sort(arr);
        for (int i =0; i<arr.length-1; i++){
            if (arr[i]+1!=arr[i+1]){
                return false;
            }
        }
        return true;
    }

    public static int longestSuccessiveElements(int[] a) {
        int n = a.length;
        if (n == 0)
            return 0;

        int longest = 1;
        Set<Integer> set = new HashSet<>();

        // put all the array elements into set
        for (int i = 0; i < n; i++) {
            set.add(a[i]);
        }

        // Find the longest sequence
        for (int it : set) {
            // if 'it' is a starting number
            if (!set.contains(it - 1)) {
                // find consecutive numbers
                int cnt = 1;
                int x = it;
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }


    /**
     * https://leetcode.com/problems/two-sum/
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     * @param arr
     * @param k
     * @return
     *
     * 2 -> 0
     * 7 -> 1
     * 11-> 2
     * 15 -> 3
     * traget = 9
     *
     */
    public static int[] twoSum(int[] arr, int k) {
        int ans [] = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arr.length; i++){
            if (!map.containsKey(arr[i])){
                map.put(arr[i], i);
            }
        }
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(k-arr[i]) && map.get(k-arr[i])!=i){
                ans[0] = i;
                ans[1] = map.get(k-arr[i]);
            }
        }
        return ans;
    }



    /**
     * https://leetcode.com/problems/move-zeroes/
     * https://takeuforward.org/data-structure/move-all-zeros-to-the-end-of-the-array/
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * Example 1:
     *
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Example 2:
     *
     * Input: nums = [0]
     * Output: [0]
     *
     * [4,2,4,0,0,3,0,5,1,0]
     * @param arr
     */

    public void moveZeroesToEndBruteWithExtraSpace(int[] arr) {
        int temp [] = new int[arr.length];
        int k =0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]!=0){
                temp[k] = arr[i];
                k++;
            }
        }
        while (k<arr.length){
            arr[k] = 0;
            k++;
        }
    }

    public int[] moveZeroesToEnd(int[] arr) {
        int j = -1;
        for (int i =0; i<arr.length; i++){
            if (arr[i]==0){
                j =i;
                break;
            }
        }
        if (j==-1){
            return arr;
        }

        for (int i = j+1; i<arr.length; i++){
            if (arr[i]!=0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        for (int a:
                arr) {
            System.out.println(a);
        }
        return arr;
    }


    /**
     *https://leetcode.com/problems/plus-one/
     * Increment the large integer by one and return the resulting array of digits.
     * Example 1:
     *
     * Input: digits = [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * Incrementing by one gives 123 + 1 = 124.
     * Thus, the result should be [1,2,4].
     * Example 2:
     *
     * Input: digits = [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     * Incrementing by one gives 4321 + 1 = 4322.
     * Thus, the result should be [4,3,2,2].
     * Example 3:
     *
     * Input: digits = [9]
     * Output: [1,0]
     * Explanation: The array represents the integer 9.
     * Incrementing by one gives 9 + 1 = 10.
     * Thus, the result should be [1,0].
     * @param arr
     * @return
     */
    public int[] plusOne(int[] arr) {
        int sum = 0;
        int carry = 1;// // as we need to add 1
        for (int i =arr.length-1; i>=0; i--){
            sum= carry+arr[i];
            carry = sum/10;
            arr[i] = sum%10;
        }
        if (carry>0){
            int [] ans = new int[arr.length+1];
            ans[0] = carry;
            int j =0;
            for (int i =1; i<arr.length; i++){
                ans[i] =arr[j];
                j++;
            }
            return ans;
        }

        return arr;
    }
    /**
     * https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/549/
     * @param nums
     * @return
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     *
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [2,2,1]
     * Output: 1
     *
     */
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i =1; i<nums.length;i++){
            res = res ^ nums[i];
        }
        return res;
    }


    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * @param arr
     * @return
     */
    public static int buyAndSellAp1(int arr[]){
        int profit =0; // initial profit.
        int min = arr[0]; // current min price lets start with arr[0]
        int maxProfit = 0; // max profit can not be less then 0
        for (int stockPrice:arr) {
            min = Math.min(min, stockPrice); // new min price
            profit = stockPrice-min; // profit
            maxProfit = Math.max(profit, maxProfit); //
        }
        return maxProfit;
    }


    /**
     * https://leetcode.com/explore/featured/card/top-interview-questions-easy/92/array/727/
     * Remove Duplicates from Sorted Array
     * @param
     *  nums = [1,1,2]
     * Output: 2, nums = [1,2,_]
     * Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     *
     * Input: nums = [0,0,1,1,1,2,2,3,3,4]
     * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
     * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
     * It does not matter what you leave beyond the returned k (hence they are underscores).
     *
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0 || n == 1)
            return n;
        // To store index of next unique element
        int j = 0;
        // Doing same as done in Method 1
        // Just maintaining another updated index i.e. j
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != nums[i + 1])
                nums[j++] = nums[i];
        }
        nums[j++] = nums[n - 1];
        return j;
    }

    /*
    https://leetcode.com/problems/first-unique-character-in-a-string/description/
    Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
    Example 1:
    Input: s = "leetcode"
    Output: 0
    Example 2:

    Input: s = "loveleetcode"
    Output: 2
    Example 3:

    Input: s = "aabb"
    Output: -1

     */
    public static int firstUniqueChar(String str){
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i =0;i<str.length(); i++) {
            if (map.containsKey(str.charAt(i))){
                map.put(str.charAt(i), map.get(str.charAt(i))+1);
            }else {
                map.put(str.charAt(i),1);
            }
        }
        for (int i =0; i<str.length(); i++){
            if (map.containsKey(str.charAt(i)) && map.get(str.charAt(i))==1){
                return i;
            }
        }
        return -1;
    }



    /**
     * https://leetcode.com/problems/valid-anagram/
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0; i<s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i =0; i<t.length(); i++){
            if (map.containsKey(t.charAt(i))){
                map.put(t.charAt(i), map.get(t.charAt(i))-1);
                if (map.get(t.charAt(i))==0){
                    map.remove(t.charAt(i));
                }
            }else {
                return false;
            }
        }
        if (map.size()==0){
            return true;
        }
        return false;
    }


    /**
     * https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/
     * @param nums
     * @return
     * Find the smallest positive number missing from an unsorted
     */
    public static  int firstMissingPositive(int [] nums) {
        Arrays.sort(nums);
        /**
         * ans = 1, as the first positve number starts from 1. We iterate through array and find if that present if yes, increase it and and go on.
         * Whereever the ans is missing that the answer for us.
         */
        int ans = 1; // lets say smalllest positve number 1 is mising
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == ans)
                ans++; // keep increaseing its present in the array.
        }
        return ans; // this number is not present in the aray
    }



    /**
     * Given a read only array of n + 1 integers between 1 and n, find one number that repeats in
     * linear time using less than O(n) space and traversing the stream sequentially O(1) times.
     *Input: [3 4 1 4 1]
     * OutPut: 1
     * If there are multiple possible answers ( like in the sample case above ), output any one.
     *
     * If there is no duplicate, output -1
     * @param arr
     * @return
     */
    public static int repeatedNumber(int arr[]) {
        Set<Integer> set = new HashSet<>();
        for (int i =0; i<arr.length; i++){
            if (set.contains(arr[i])){
                return arr[i];
            }else {
                set.add(arr[i]);
            }
        }
        return -1;
    }

    /**
     * https://leetcode.com/problems/maximum-gap/
     * @param arr
     * @return
     * Input: nums = [3,6,9,1]
     * Output: 3
     * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
     */
    public int maximumGap(int[] arr) {
        if (arr.length<2){
            return 0;
        }
        Arrays.sort(arr);
        int maxDiff = Integer.MIN_VALUE;
        for (int i =0; i<arr.length-1; i++){
            maxDiff = Math.max(maxDiff, arr[i+1]-arr[i]);
        }
        return maxDiff;
    }

    /**
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
     * Input: 121
     * Output: true
     *
     * Input: -121
     * Output: false
     *
     * Input: 10
     * Output: false
     * https://leetcode.com/problems/palindrome-number/
     */
    public boolean isPalindrome(int num) {
        int revInt = reverseInt1(num);
        if (revInt==num){
            return true;
        }else {
            return false;
        }
    }
    private static int reverseInt1(int num) {
        int ans = 0;
        while (num>0){
            ans = ans*10+(num%10);
            num=num/10;
        }
        return ans;
    }


    /**
     * https://leetcode.com/problems/reverse-integer/description/
     * Given a 32-bit signed integer, reverse digits of an integer.
     * Input: 123
     * Output: 321
     *
     * test cases:
     * Check for -ve number e.g. -213
     * check max number 2,147,483,647
     * Assume we are dealing with an environment which could only store integers within the 32-bit
     * signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0
     * when the reversed integer overflows.
     * Read this: https://medium.com/@Harshit_Raj_14/reverse-integer-leetcode-medium-problem-full-solution-and-approach-explained-1d57b1292ed2
     */
    public static int reverseInt(int num) {
        int ans = 0;
        while (num!=0){
            if (ans>Integer.MAX_VALUE/10 || ans<Integer.MIN_VALUE/10)
                return 0;
            ans = ans*10+(num%10);
            num=num/10;
        }
        return ans;
    }

    /**
     * https://leetcode.com/problems/two-sum/description/
     * Given nums = [2, 7, 11, 15], target = 9,
     * <p>
     * Because nums[0] + nums[1] = 2 + 7 = 9,
     * return [0, 1].
     */

    public int[] twoSumBrute(int[] nums, int target) {
        int[] output = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j <= nums.length - 1; j++) {
                if (nums[i] + nums[j] == target) {
                    output[0] = i;
                    output[1] = j;
                }
            }
        }
        return output;
    }
    public int[] twoSumOptimal(int[] arr, int k) {
        int ans [] = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arr.length; i++){ // keeping all unique int indexes.
            if (!map.containsKey(arr[i])){
                map.put(arr[i], i);
            }
        }
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(k-arr[i]) && map.get(k-arr[i])!=i){ // 2nd condition is to ensure we do not consider the same element twice.
                ans[0] = i;
                ans[1] = map.get(k-arr[i]);
            }
        }
        return ans;
    }

    public static  double findMedianSortedArrays(int arr1[], int arr2[]){
        int arr[] = mergeTwoSortedArray(arr1, arr2);
        if (arr.length==1){
            return arr[0];
        }
        double a = arr[(arr.length)/2]; // if nunber of interger are odd.
        double b = arr[(arr.length/2)-1]; // if number of integer are even.
        if ((arr.length) %2==0){ // if even number of integer
            return (a+b)/2; // medium sum of 2 middle indexed.
        }else {
            return a;
        }
    }

    /**
     * https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
     * @param arr
     * Input: nums = [3,1,-2,-5,2,-4]
     * Output: [3,-2,1,-5,2,-4]
     */

    public static int[] rearrangeArray(int arr[]){
        int [] ans = new int[arr.length];
        int i =0;
        int j=1;
        for (int k = 0; k<arr.length; k++){
            if (arr[k]>0){
                ans[i]=arr[k];
                i=i+2;
            }else {
                ans[j] = arr[k];
                j = j+2;
            }
        }
        return ans;
    }

    /**
     * Reverse an array.
     * @param arr
     */
    public static void reverseArray(int arr[]){
        int start =0;
        int end = arr.length-1;
        while(start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        for (int a:
                arr) {
            System.out.println(a);
        }
    }


    /** <<> ************** CHECK AGAIN ******************</>
     * Search an element in a sorted and rotated array
     * arr[] = {5, 6, 7, 8, 9, 10, 1, 2, 3};
     * key = 3
     * Output : Found at index 8
     * https://leetcode.com/problems/search-in-rotated-sorted-array/
     * https://takeuforward.org/data-structure/search-element-in-a-rotated-sorted-array/
     */
    public static int searchInSortedRotatedArray(int arr[], int start, int end, int key){
        int low = 0;
        int high = end-1;
        while (low<=high){
            int mid = (low+high)/2;
            if (arr[mid]==key){
                return mid;
            }
            //left array is sorted
            if (arr[low]<=arr[mid]){
                if (arr[low]<=key && key<=arr[mid]){ // means target exisit in this sorted half
                    high = mid-1;
                }else {
                    low = mid+1; // else eliminate this sorted half and move to the other half.
                }
                //right half is sorted
            }else {
                if (arr[mid]<=key && key<=arr[high]){ // means element is in this sorted hald
                    low = mid+1;
                }else {
                    high=mid-1; // else remove this sorted half.
                }
            }
        }
        return -1;
    }

    /**
     * in binary search array will be sorted
     * @param arr
     * @param key
     * https://leetcode.com/problems/binary-search/submissions/1258558242/
     */
    public static int binarySearch(int arr[],int start, int end, int key){
        if (end >= start) {
            int mid = (start+end)/2;
            if (key == arr[mid])
                return mid;
            if (key > arr[mid])
                return binarySearch(arr, mid + 1, end, key);
            if (key < arr[mid])
                return binarySearch(arr, start, mid - 1, key);
        }
        return -1;
    }

    /**
     * arr[] = 1,2,3,4,5,6,7
     * output: arr[]= 3,4,5,6,7,1,2
     * Rotate an array.
     * https://leetcode.com/problems/rotate-array/description/
     */
    public static void rotateBrute(int[] arr, int d) {
        for (int i =0; i<d; i++){
            rotateArrayByOne1(arr);
        }
        for (int a: arr){
            System.out.println(a);
        }
    }
    private static void rotateArrayByOne1(int[] arr) {
        int a = arr[arr.length-1];
        for (int i =arr.length-1; i>=1; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = a;
    }

    public static void rotateArrayReversalAlgoOptimal(int arr[], int d){
        d %= arr.length; // if array length is 5 , and we need rotate 8 times , then we dont need to
        reverseArray(arr,0,arr.length-1);
        reverseArray(arr,0,d-1);
        reverseArray(arr,d,arr.length-1);
        for (int a:
                arr) {
            System.out.println(a);
        }
    }
    public static void reverseArray(int []arr, int start, int end){
        while (start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * How to find all pairs on integer array whose sum is equal to given number?
     * <p>
     * Read more: https://javarevisited.blogspot.com/2015/06/top-20-array-interview-questions-and-answers.html#ixzz66qRoTN8t
     */

    /**
     * Sorted order array then this solution will work.
     * @param arr
     * @param key
     */

    public static void findPairWithSumSortedArray(int arr[], int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] == key) {
                System.out.print(arr[left] + "," + arr[right]);
                left++;
                right--;
            } else if (arr[left] + arr[right] > key) {
                right--;
            } else {
                left++;
            }
            System.out.println();
        }
    }

    /**
     * Union of 2 array means all the elements those are in Array-A and Array -B or both.
     */
    public static void findUnionOfTwoArray(int arr1[], int arr2[]) {
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                System.out.println(arr1[i]);
                i++;
            } else if (arr1[i] > arr2[j]) {
                System.out.println(arr2[j]);
                j++;
            } else {
                System.out.println(arr1[i]);
                i++;
                j++;
            }
        }
        while (i < arr1.length) {
            System.out.println(arr1[i]);
            i++;
        }
        while (j < arr2.length) {
            System.out.println(arr2[j]);
            j++;
        }
    }

    /**
     * Intersection is about common elements in both the array
     */
    public static void findIntersectionOfTwoArray(int arr1[], int arr2[]) {
        int i = 0;
        int j = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                System.out.println(arr1[i]);
                i++;
                j++;
            }
        }
    }


    /** <<> NEED TO CHECK THIS AGAIN</>
     * How to find kth smallest element in unsorted array
     * @param arr
     * @param n
     * Sol1 - > Sort the array an return ar[n-1];
     * https://leetcode.com/problems/kth-largest-element-in-an-array/
     *
     */
    public static int kthSmallestElement(int[] arr, int n) {
        Arrays.sort(arr);
        return arr[n-1];
    }
    public static int kthLargestElement(int[] arr, int n) {
        Arrays.sort(arr);
        return arr[arr.length-n];
    }


    /**
     * Combine 2 integer sorted array into a sorted output
     * (Eg array1 = [1,2,5], array 2 = [3,5,6], Expected output - [1,2,3,4,5,6])
     */
    public static int[] mergeTwoSortedArray(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int arr[] = new int[arr1.length + arr2.length];
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                arr[k] = arr1[i];
                i++;
            } else {
                arr[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1.length) {
            arr[k] = arr1[i];
            k++;
            i++;
        }
        while (j < arr2.length) {
            arr[k] = arr2[j];
            k++;
            j++;
        }
        for (int a :
                arr) {
            System.out.println(a);
        }
        return arr;
    }

    /**
     * Given an array of integers, replace every number with the next higher number to its
     * right. If a number can’t be replaced, we leave it as-it is.
     * For example, the list: 5, 2, 1, 4, 6, 7 needs to be changed to 6, 4, 4, 6, 7, 7.
     * https://www.geeksforgeeks.org/next-greater-element/
     */

    public static void replaceWithNextGreatBrute(int arr[]){
        int ans[] = new int[arr.length];
        int k =0;
        for (int i =0;i<arr.length; i++){
            for (int j =i+1; j<arr.length; j++){
                if (arr[j]>arr[i]){
                    ans[k] = arr[j];
                    k++;
                    break;
                }
            }
        }
    }

    public static int[] replaceWithNextGreatOptimal(int arr[]){
        int[] temp = new int[arr.length];
        for (int  i = 0; i<arr.length; i++){
            temp[i] = arr[i];
        }
        Arrays.sort(temp);
        for (int i = 0; i<arr.length; i++){
            int index = binarySearch(temp, 0, temp.length, arr[i]);
            if (index!=-1){
                arr[i] = arr[index+1];
            }else {
                arr[i] = index;
            }
        }
        return arr;
    }
    public static void swapString(String str1, String str2){
        int a=10;
        int b = 5;

        a = a+b; // 15
        b = a-b; // b =10
        a = a-b;

        //concatenate 2 strings
        str1 = str1+str2;  //AnandKumar
        // find the
        str2 = str1.substring(0, str1.length()-str2.length()); // str1.substring(0, 10-5); , str2 = Anand

        str1 = str1.substring(str2.length()); // str1.substring(str2.length()); str1.substring(5) //
        System.out.println(str1+ "---"+str2);
    }

    public static void printPattern(int n)
    {
        int i, j;
        // outer loop to handle number of rows
        for (i = 1; i <= n; i++) {
            // inner loop to print space
            for (j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // inner loop to print star
            for (j = 1; j <= i; j++) {
                System.out.print("*"+ " ");
            }
            // print new line for each row
            System.out.println();
        }
    }

    public static void printPatternRightToLeft(int n){
        for (int i =1; i<=n; i++){
            for (int j = n; j>i; j--){
                System.out.print(' ');
            }
            int num = i;
            while (num>=1){
                System.out.print("*"+ " ");
                num--;
            }
            System.out.println();
        }
    }
    public static void printPatternFromLeftToRight(int n){
        for (int i =1; i<=n; i++){
            int num =i;
            while (num>=1){
                System.out.print("*");
                num--;
            }
            System.out.println();
        }
    }

    public static void printFibSeries(int num){
        int a = 0;
        int b = 1;
        for (int i = 0; i<num; i++){
            System.out.println(a);
            int c = a+b;
            a = b;
            b = c;
        }
    }

    public static void printPrimeNumber(int n){
        for (int i =1; i<=n; i++){
            if (isPrime(i)){
                System.out.println(i);
            }
        }
    }
    public static boolean isPrime(int num ){
        for (int i =2; i<num; i++){
            if (num%i==0){
                return false;
            }
        }
        return true;
    }

    public static void findFirstAndSecondLarge(int arr[]){
        if(arr.length<2){
            throw new IllegalArgumentException("Enter correct array");
        }
        int first = Math.max(arr[0], arr[1]);
        int second = Math.min(arr[0], arr[1]);
        for (int i = 2; i<arr.length; i++){
            if (first<arr[i]){
                second = first;
                first = arr[i];
            }else {
                if (second<arr[i]){
                    second = arr[i];
                }
            }
        }
    }


    public static int findFact(int n){
        if (n==1){
            return 1;
        }
        return n*findFact(n-1);
    }

    public static void maxSumSubArray(int arr[], int size){
        int i =0;
        int j =0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while (j<arr.length){
            sum+=arr[j];
            if ((j-i+1)<size){
                j++;
            }else {
                max = Math.max(sum, max);
                sum=sum-arr[i];
                i++;
                j++;
            }
        }
        System.out.println(max);
    }

    /**
     * https://leetcode.com/problems/merge-intervals/?envType=study-plan-v2&envId=top-interview-150
     * @return
     */
    public List<List<Integer>> merge(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0]-a2[1];
            }
        });

        List<List<Integer>> ans = new ArrayList<>();
        for (int i =0; i<intervals.length; i++){
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (!ans.isEmpty() && end<=ans.get(ans.size()-1).get(1)){
                continue;
            }
            for (int j = i+1; j<intervals.length; j++){
                if (intervals[j][0]<=end){
                    end = Math.max(end, intervals[j][1]);
                }
                else {
                    break;
                }
            }
            ans.add(Arrays.asList(start, end));
        }
        return ans;
    }

    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * @param nums
     * @return https://leetcode.com/problems/3sum/description/?envType=study-plan-v2&envId=top-interview-150
     */
    public List<List<Integer>> threeSumBrute(int[] nums) {
        //use three loops and find the sum of 3 number and if equals to 0 , add them to list and return list at the end.
        return null;
    }

    public List<List<Integer>> threeSumBruteBetter(int[] nums) {

        Set<List<Integer>> st = new HashSet<>();
        List<Integer> ds;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> hashSet = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (hashSet.contains(-(nums[i] + nums[j]))) {
                    ds = new ArrayList<>();
                    ds.add(nums[i]);
                    ds.add(nums[j]);
                    ds.add(-(nums[i] + nums[j]));
                    ds.sort(null);
                    st.add(ds);
                }
                hashSet.add(nums[j]);
            }
        }
        return new ArrayList<>(st);
    }

    /**
     * https://www.geeksforgeeks.org/problems/minimum-platforms-1587115620/1
     */
    public static int minimumPlatform(int arr[], int dep[]){
        Arrays.sort(arr);
        Arrays.sort(dep);
        int plat_needed = 1, result=1;
        int i =0, j =0;
        while (i<arr.length && j<arr.length){
            //
            if (arr[i]<=dep[j]){
                plat_needed++;
                i++;
            }else if (arr[i]>dep[j]){
                plat_needed--;
                j++;
            }
            if (plat_needed>result){
                result = plat_needed;
            }
        }
        System.out.println(result);
        return result;
    }
}
