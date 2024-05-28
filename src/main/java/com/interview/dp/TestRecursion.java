package com.interview.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TestRecursion {
    public static void main(String[] args) {
        //System.out.println(sumWithRec(10));
        //printSumWithRec(3, 0);
        /*int arr[] = {1,2,3,4,5,6};
        reverseArray(arr, 0, 5);
        for (int a: arr){
            System.out.println(a);
        }*/
        //System.out.println(isPalandrome("A", 0));
        //System.out.println(fib(5));
        int arr[] = {1,2,3,5,4};
        List<Integer> list = new ArrayList<>();
        //printSubSeq(arr, 0, list, 3);
        //printSubSeqWithSumK(arr, 0, list, 3, 2, 0);
        //System.out.println(printCountOfSubSeqWithSumK(arr, 0, list, 3, 2, 0));
        //mergeSort(arr, 0, 4);
        //quickSort(arr, 0, arr.length-1);
        permutations(new int[]{1, 2, 3});
        combinations(new int[]{1,2,3}, 2);
    }


    /**
     * Combinations of numbers in an array
     */
    public static void combute(int[] arr, int r, List<Integer> ds, boolean []freq, List<List<Integer>> ans){
        if (ds.size()==r){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i = 0; i< arr.length; i++){
            if (!freq[i]){
                freq[i] = true;
                ds.add(arr[i]);
                combute(arr, r, ds, freq, ans);
                ds.remove(ds.size()-1);
                freq[i] = false;
            }
        }

    }
    public static void combinations(int arr[], int r){
        List<List<Integer>> ans = new ArrayList<>();
        boolean freq[] = new boolean[arr.length];
        List<Integer> ds = new ArrayList<>();
        combute(arr, r, ds, freq, ans);
        System.out.println(ans);

    }

    /**
     * Permutation of number in an array.
     * https://www.youtube.com/watch?v=YK78FU5Ffjw&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=14
     *
     */
    public static void permuteApproach2(int[] arr, int index, List<List<Integer>> ans){
        if (index==arr.length){
            List<Integer> ds = new ArrayList<>();
            for (int i =0; i<arr.length; i++){
                ds.add(arr[i]);
            }
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i =index; i<arr.length; i++){
            swap(arr, i, index);
            permuteApproach2(arr, index+1, ans);
            swap(arr, i, index);
        }
    }
    public static void permute(int[] arr, List<Integer> ds, boolean []freq, List<List<Integer>> ans){
        if (ds.size()==arr.length){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i = 0; i< arr.length; i++){
            if (!freq[i]){
                freq[i] = true;
                ds.add(arr[i]);
                permute(arr, ds, freq, ans);
                ds.remove(ds.size()-1);
                freq[i] = false;
            }
        }

    }
    public static void permutations(int arr[]){
        List<List<Integer>> ans = new ArrayList<>();
        boolean freq[] = new boolean[arr.length];
        List<Integer> ds = new ArrayList<>();
        permute(arr, ds, freq, ans);
        System.out.println(ans);

    }
    /**
     * Sum of all combination: https://www.youtube.com/watch?v=G1fRTGRxXU8&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=11
     */

    public static void findCombinationsAll(int ind, int[] arr, int target, HashSet<HashSet<Integer>> ans, HashSet<Integer> ds){
        if (ind==arr.length){
            if (target==0){
                ans.add(new HashSet<>(ds));
            }
            return;
        }
        if (arr[ind]<=target){
            ds.add(arr[ind]);
            findCombinationsAll(ind+1, arr, target-arr[ind], ans, ds);
            ds.remove(ds.size()-1);
        }
        findCombinationsAll(ind+1, arr, target, ans, ds);
    }
    public static HashSet<HashSet<Integer>> combSumAll(int arr[], int target){
        HashSet<HashSet<Integer>> ans = new HashSet<>();
        findCombinationsAll(0, arr, target, ans, new HashSet());
        return ans;
    }
    /**
     * Combination sum: https://www.youtube.com/watch?v=OyZFFqQtu98&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=10
     *
     */
    public static void findCombinations(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds){
            if (ind==arr.length){
                if (target==0){
                    ans.add(new ArrayList<>(ds));
                }
                return;
            }
            if (arr[ind]<=target){
                ds.add(arr[ind]);
                findCombinations(ind, arr, target-arr[ind], ans, ds);
                ds.remove(ds.size()-1);
            }
            findCombinations(ind+1, arr, target, ans, ds);
    }
    public static List<List<Integer>> combSum(int arr[], int target){
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, arr, target, ans, new ArrayList<>());
        return ans;
    }


    /**
     *Quick sort - sor the array in ascending order.
     * Pick a vivot - any element.
     */
    public static void quickSort(int arr[], int low, int high){
        if (low<high){
            int partision = quickSortF(arr, low, high);
            quickSort(arr, low, partision-1);
            quickSort(arr, partision+1, high);
        }
    }
    public static int quickSortF(int arr[], int low, int high){
        int pivot = low; // pivot could be at any element of the array.
        int i = low; // starting index of array
        int j = high; // end index of array.
        while (i<j){ // till when there are element in b/w i and j
            while (arr[i]<=arr[pivot] && i<=high-1){ // The purpose is to find an element which is greater then pivot element , so that will be a candiadate
                                                        // for us to move it to the right of pivot.
                i++;
            }
            while (arr[j]>arr[pivot] && j>=low+1){// The purpose is to find an element which is lesser then pivot element , so that will be a candiadate
                                                // for us to move it to the left of pivot.
                j--;
            }
            if (i<j){ // once we find an element to the left of pivot which is greater then pivot and an element which is lesser then pivot  the we swap them
                        // this is to ensure that we keep app lesser element to the left and greater element to the right.
                swap(arr, i, j);
            }
        }
        swap(arr, arr[low], arr[j]);
        return j;
    }

    /**
     * Merge sort - Divide and merge
     * [1,3,4,5,1,9,4]
     */
    public static void mergeSort(int[] arr, int low, int high){
        if (low>=high) { // when the array will have only single element after dividing e.g. [3] then we can say that low=1 and high=1 thats why if low and high are equeal that become our base condistion.
            return;
        }
        int mid = (low+high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1, high);
        merge(arr, low, mid, high);
    }
    public static void merge(int[] arr, int low, int mid, int high){
        List<Integer> arrayList = new ArrayList<>();
        int left = low;
        int right = mid+1;
        int i = 0;
        int ans[] = new int[low+high+1];
        while (left<=mid && right<=high){
            if (arr[left]<=arr[right]){
                //arrayList.add(arr[left]);
                ans[i] = arr[left];
                left++;
            }else {
                //arrayList.add(arr[right]);
                ans[i] = arr[right];
                right++;
            }
            i++;
        }
        while (left<=mid){
            //arrayList.add(arr[left]);
            ans[i] = arr[left];
            left++;
            i++;
        }
        while (right<=high){
            //arrayList.add(arr[right]);
            ans[i] = arr[right];
            right++;
            i++;
        }

    }
    /**
     * Print all subsequences:
     * https://www.youtube.com/watch?v=AxNNVECce8c&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=6
     */
    public static int printCountOfSubSeqWithSumK(int arr[], int ind, List<Integer> list, int n, int k, int sum){
        if (ind==n){ // print list once you reach took all indesxex.
            if (sum==k){
                return 1;
            }else{
                return 0;
            }

        }
        //take or pick the particular index into the subsequence
        sum = sum+arr[ind];
        list.add(arr[ind]);
        int l= printCountOfSubSeqWithSumK(arr, ind+1, list, n, k, sum);
        //remove the element you took, so that this element is not added to the sequence.
        list.remove(list.size()-1);//remove the recently inserted element
        sum = sum-arr[ind];
        int r = printCountOfSubSeqWithSumK(arr, ind+1, list, n, k, sum);
        return l+r; // return total count of number of sub sequnecne that have sum==k;
    }

    public static void printSubSeqWithSumK(int arr[], int ind, List<Integer> list, int n, int k, int sum){
        if (ind==n){ // print list once you reach took all indesxex.
            if (sum==k){
                System.out.println(list);
            }
            return;
        }
        //take or pick the particular index into the subsequence
        sum = sum+arr[ind];
        list.add(arr[ind]);
        printSubSeqWithSumK(arr, ind+1, list, n, k, sum);
        //remove the element you took, so that this element is not added to the sequence.
        list.remove(list.size()-1);//remove the recently inserted element
        sum = sum-arr[ind];
        printSubSeqWithSumK(arr, ind+1, list, n, k, sum);
    }


    /**
     * Print all subsequences:
     * https://www.youtube.com/watch?v=AxNNVECce8c&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=6
     */
    public static void printSubSeq(int arr[], int ind, List<Integer> list, int n){
        if (ind==n){ // print list once you reach took all indesxex.
            System.out.println(list);
            return;
        }
        //take or pick the particular index into the subsequence
        list.add(arr[ind]);
        printSubSeq(arr, ind+1, list, n);
        //remove the element you took, so that this element is not added to the sequence.
        list.remove(list.size()-1);//remove the recently inserted element
        printSubSeq(arr, ind+1, list, n);
    }

    /**
     * Fibnaaci series
     */
    public static int fib(int n){
        if (n<=1){
            return n;
        }
        return fib(n-1) + fib(n-2);
    }

    /**
     * Find if a string is palandrom
     */
    public static boolean isPalandrome(String str, int i){
        if (i>=str.length()/2){
            return true;
        }
        if (str.charAt(i)!=str.charAt(str.length()-i-1)){
            return false;
        }
        return isPalandrome(str, i+1);
    }
    /**
     * Reverse an array with recursion.
     */
    public static void reverseArray(int[] arr, int l, int r){
        if (l>=r){
            return;
        }
        swap(arr, l, r);
        reverseArray(arr, l+1, r-1);

    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Sum of first N number with Rec
     */
    public static void printSumWithRec(int i, int sum){
        if (i<1){
            System.out.println(sum);
            return;
        }
        printSumWithRec(i-1,sum+i);
    }

    /**
     * Sum of first N number with Rec
     */
    public static int sumWithRec(int n){
        if (n<1){
            return 0;
        }
       return n + sumWithRec(n-1);
    }
    /**
     * Print numbers from N to 1
     *
     */
    public static void printNumberOpposite(int n){
        if (n<1){
            return;
        }
        System.out.println(n);
        printNumberOpposite(n-1);
    }
    /**
     * Print lineally from 1 to N
     *
     */
    public static void printNumber(int i, int n){
        if (i>n){
            return;
        }
        System.out.println(i);
        printNumber(i+1, n);

    }
    public static void printNumberLinear(int n){
        printNumber(1, n);
    }


    /**
     * Print name n times with recurssion
     */
    public static void printNameNTimes(int i, int n){
        if (i>n){
            return;
        }
        System.out.println("name");
        printNameNTimes(i+1, n);
    }
    public static void printName(int n){
        printNameNTimes(1, n);
    }


}
