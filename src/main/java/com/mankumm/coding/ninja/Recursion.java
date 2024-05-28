package com.stack.com.mankumm.coding.ninja;

import java.util.ArrayList;

public class Recursion {

    public void main(String[] args) {
        int arr[] = {1,2,3,4,5};
        //System.out.print(sum(arr));
        /*String [] str = findSubStrings("abc");
        for (int i =0;i<str.length; i++){
            System.out.println(str[i]);
        }*/

        Student s = new Student(12);
        s.print1();
    }

    /**
     * https://www.geeksforgeeks.org/min-cost-path-dp-6/
     * Given a cost matrix cost[][] and a position (M, N) in cost[][], write a function that returns cost of minimum cost path to
     * reach (M, N) from (0, 0). Each cell of the matrix represents a cost to traverse through that cell. The total cost of a
     * path to reach (M, N) is the sum of all the costs on that path (including both source and destination). You can only traverse down,
     * right and diagonally lower cells from a given cell, i.e., from a given cell (i, j), cells (i+1, j), (i, j+1), and (i+1, j+1)
     * can be traversed.
     *
     * @param input
     * @return
     */

    public static int minCostPathM(int[][] input) {
        //Your code goes here
        int m = input.length;
        int n = input[0].length;
        int storage[][] = new int[m][n];
        for (int i = 0; i<m; i++){
            for (int j = 0; j<n; j++){
                storage[i][j] = -1;
            }
        }
        return minCostPathMHelper(input, 0, 0, storage);
    }

    private static int minCostPathMHelper(int[][] input, int i, int j, int[][]storage) {
        int m = input.length;
        int n = input[0].length;
        if (i==m-1 && j == n-1){
            storage[m-1][n-1] = input[i][j];
            return storage[i][j];
        }
        if (i>m || j> n){
            return Integer.MAX_VALUE;
        }
        if (storage[i][j]!=-1){
            return storage[i][j];
        }
        int op1 = minCostPathMHelper(input, i, j+1, storage); // move to the right
        int op2 = minCostPathMHelper(input, i+1, j+1, storage); // move diagonal
        int op3 = minCostPathMHelper(input, i, j+1, storage); // move to the down
        storage[i][j] = input[i][j] + Math.min(op1, Math.min(op2, op3));
        return storage[i][j];
    }



    public static int minCostPathRecur(int[][] input) {
        //Your code goes here
        return minCostPathHelper(input, 0, 0);
    }

    private static int minCostPathHelper(int[][] input, int i, int j) {
        int m = input.length;
        int n = input[0].length;
        if (i==m-1 && j == n-1){
            return input[i][j];
        }

        int op1 = minCostPathHelper(input, i, j+1); // move to the right
        int op2 = minCostPathHelper(input, i+1, j+1); // move diagonal
        int op3 = minCostPathHelper(input, i, j+1); // move to the down
        if (i>m || j> n){
            return Integer.MAX_VALUE;
        }
        return input[i][j] + Math.min(op1, Math.min(op2, op3));
    }


    class Student{
        String name;
        int rollNo;
        Student(int num){
            rollNo = num;
        }
        public void print1(){
            System.out.print(name +" " + rollNo + " ");
        }
    }

    /**
     * Increasing Numbers
     * Send Feedback
     * Given an integer n, print all n digit increasing numbers in increasing order in one line.
     * Numbers you need to print should be in increasing order and only those numbers should be printed which have digits in increasing order.
     * Input Format :
     * Integer n
     * Output Format :
     *  Numbers in increasing order
     * Constraints :
     * 1 <= n <= 8
     * Sample Input :
     * 2
     * Sample Output :
     * 12 13 14 15 16 17 18 19 23 24 25 26 27 28 29 34 35 36 37 38 39 45 46 47 48 49 56 57 58 59 67 68 69 78 79 89
     * @param n
     */

    public static void printIncreasingNumber(int n) {
        printIncreasingNumberSol(1, "", n);
    }
    public static void printIncreasingNumberSol(int start, String out, int n) {
        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         */
        if (n==0){
            System.out.print(out +" ");
            return;
        }

        for (int i =start; i<=9;i++){
            String str = out+Integer.toString(i);
            printIncreasingNumberSol(i+1, str, n-1);
        }

    }

    /**
     * Generate all parenthesis
     * Send Feedback
     * Given n pairs of parentheses, write a function to generate and print all combinations of well-formed parentheses. That is, you need to generate all possible valid set of parenthesis that can be formed with given number of pairs.
     * Input format :
     * Integer n
     * Output format :
     * Print all possible valid parenthesis in different lines
     * Note: Order in which different combinations of well-formed parentheses are printed in different lines doesn't matter.
     * Constraints :
     * 1 <= n <= 10
     * Sample Input :
     * 3
     * Sample Output :
     * ((()))
     * (()())
     * (())()
     * ()(())
     * ()()()
     * @param n
     */

    public static void printWellFormedParanthesis( int n){
        // Write your code here
        ArrayList<String> ans = new ArrayList<>();
        printWellFormedParanthesisSol(n, "", 0, 0, ans);
        for (String str:
             ans) {
            System.out.println(str);
        }
    }
    public static void printWellFormedParanthesisSol( int n, String s, int open, int close, ArrayList<String> ans){
        // Write your code here
        // if the count of both open and close parentheses
        // reaches n, it means we have generated a valid
        // parentheses. So, we add the currently generated
        // string s to the final ans and return.
        if (open == n && close == n) {
            ans.add(s);
            return;
        }

        // At any index i in the generation of the string s,
        // we can put an open parentheses only if its count
        // until that time is less than n.
        if (open < n) {
            printWellFormedParanthesisSol(n, s + "{", open + 1, close, ans);
        }

        // At any index i in the generation of the string s,
        // we can put a closed parentheses only if its count
        // until that time is less than the count of open
        // parentheses.
        if (close < open) {
            printWellFormedParanthesisSol(n, s + "}", open, close + 1, ans);
        }


    }




    /**
     * Strings of Length k
     * Send Feedback
     * Given a string S and an integer k, you need to find and return all the possible strings that can be made of size k using only characters present in string S.
     * The characters can repeat as many times as needed.
     * Note : The number of output strings can be at max 1000.
     * Input format :
     * String S and Integer k (separated by space)
     * Output Format :
     * All possible strings (in different lines)
     * Constraints :
     * 1 <= Length of String S <= 10
     * 1 <= k <= 5
     * Sample Input 1 :
     * abc 2
     * Sample Output 1 :
     * aa
     * ab
     * ac
     * ba
     * bb
     * bc
     * ca
     * cb
     * cc
     * @param charSet
     * @param len
     * @return
     */

    public static String[] allStrings(String charSet, int len){

        // Write your code here
        int k = len;
       ArrayList<String> list =  new ArrayList<>();
       allStringsSol(charSet, len,"", k, list );
       String[] ans = new String[list.size()];
       for (int i =0; i<list.size(); i++){
           ans[i] = list.get(i);
       }
       return ans;

    }
    public static void allStringsSol(String charSet, int len, String pref, int k, ArrayList<String> arrayList){
        // Write your code here
        if (k==0){
            arrayList.add(pref);
            return;
        }
        for (int i =0;i<charSet.length(); ++i){
           String newpref =  pref+charSet.charAt(i);
            allStringsSol(charSet, len, newpref, k-1, arrayList);
        }
    }

    public static  String[] getCode(String input){
        // Write your code here
        ArrayList<String> arrayList = new ArrayList<>();
        getCodeSol(input, "", arrayList);
        String [] arr = new String[arrayList.size()];
        for (int i = 0; i<arrayList.size(); i++){
            arr[i] = arrayList.get(i);
        }
        return arr;
    }

    public static char getChar(int n) {
        return (char) (96 + n);
    }

    public static  String [] getCodeSol_1(String input){
        // Write your code here
        if (input.length()==0){
            String ans [] = {""};
            return ans;
        }
        String []smallAns_1 = getCodeSol_1(input.substring(1));

        int firstDigit = input.charAt(0)-'0';
        if (input.length()>1){
            int twogigit = (input.charAt(0)-'0') * 10 * input.charAt(1)-'0';
            if (twogigit>10 && twogigit<=26){
                String []smallAns_2 = getCodeSol_1(input.substring(2));
            }
        }

        return null;



    }

    public static  void getCodeSol(String input, String out, ArrayList<String> arrayList){
        // Write your code here
        if (input.length()==0){
            arrayList.add(out);
            return;
        }
        int firstDigit = input.charAt(0)-'0';
        getCodeSol(input.substring(1), out+getChar(firstDigit), arrayList);
        if (input.length()>1){
            int twogigit = (input.charAt(0)-'0') * 10 * input.charAt(1)-'0';
            if (twogigit>10 && twogigit<=26){
                getCodeSol(input.substring(2), out+getChar(twogigit), arrayList);
            }

        }


    }

    public static long find_Ways(int n)
    {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (n==0){
            return 0;
        }
        return 3*  + find_Ways(n-1);

    }


    public static void interleavings(String first, String second){

        // Write your code here
        printInterLeavingSol(first, 0, second, 0, "");
    }


    static void printInterLeavingSol(String s1, int i,
                                  String s2, int j,
                                  String asf)
    {
        if (i == s1.length() && j == s2.length()) {
            System.out.println(asf);
        }

        // Either we will start with string 1
        if (i < s1.length())
            printInterLeavingSol(s1, i + 1, s2, j,
                    asf + s1.charAt(i));
        // Or with string 2
        if (j < s2.length())
            printInterLeavingSol(s1, i, s2, j + 1,
                    asf + s2.charAt(j));
    }



    /**
     * Replace pi (recursive)
     * Send Feedback
     * Given a string, compute recursively a new string where all appearances of "pi" have been replaced by "3.14".
     * Constraints :
     * 1 <= |S| <= 50
     * where |S| represents the length of string S.
     * Sample Input 1 :
     * xpix
     * Sample Output :
     * x3.14x
     * Sample Input 2 :
     * pipi
     * Sample Output :
     * 3.143.14
     * @param input
     * @return
     */
    public static String replace(String input){
        // Write your code here
        if (input.length()==0 || input.length()==1){
            return input;
        }
        if ("pi".equals(input.substring(0,2))){
            return "3.14"+replace(input.substring(2));
        }else {
            return input.charAt(0)+replace(input.substring(1));
        }
    }


    /**
     * Staircase
     * Send Feedback
     * A child is running up a staircase with N steps, and can hop either 1 step, 2 steps or 3 steps at a time. Implement a method to count how many possible ways the child can run up to the stairs. You need to return number of possible ways W.
     * Input format :
     * Integer N
     * Output Format :
     * Integer W
     * Constraints :
     * 1 <= N <= 30
     * Sample Input 1 :
     * 4
     * Sample Output 1 :
     * 7
     * @param n
     * @return
     */

    public static int staircase(int n){

        /* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (n<=0){
            return 0;
        }
        if (n==0){
            return 1;
        }

        return staircase(n-1)+ staircase(n-2)+staircase(n-3);
    }

    /**
     * String to Integer
     * Send Feedback
     * Write a recursive function to convert a given string into the number it represents. That is input will be a numeric string that contains only numbers, you need to convert the string into corresponding integer and return the answer.
     * Input format :
     * Numeric string S (string, Eg. "1234")
     * Output format :
     * Corresponding integer N (int, Eg. 1234)
     * Constraints :
     * 0 < |S| <= 9
     * where |S| represents length of string S.
     * Sample Input 1 :
     * 00001231
     * Sample Output 1 :
     * 1231
     * @param input
     * @return
     */
    public static int convertStringToInt(String input){
        // Write your code here
        if (input.length()==1){
            return input.charAt(0)-'0';
        }
        String temp = input.substring(1);
        double tempNum = Math.pow(10, temp.length());
        double currentNum = input.charAt(0) - '0';
        return (int) (tempNum * currentNum) + convertStringToInt(input.substring(1));
    }


    /**
     * Print subsequence
     * @param str
     *
     */
    public static void printSubSequence(String str){
        printSubSequencesol(str, "");
    }
    public static void printSubSequencesol(String str, String output){
        if (str.length()==0){
            System.out.print(output);
            return;
        }
        printSubSequencesol(str.substring(1), output);
        printSubSequencesol(str.substring(1), output+str.charAt(0));
    }

    public static void printKeypad(int n){
        String output = "";
        printKeypadSol(n, output);
    }
    public static void printKeypadSol(int n, String ans){
        if (n==0){
            System.out.print(ans);
            return;
        }
        String [] singleDigitoutPut = helperFunction(n%10);
        for (int i =0; i<singleDigitoutPut.length; i++){
            printKeypadSol(n/10, singleDigitoutPut[i]+ans);
        }
    }
    public static String[] keypad(int n){
        // Write your code here
        if (n==0){
            String ans[] = {""};
            return ans;
        }
        String[] smallAns = keypad(n/10);
        String [] singleDigitoutPut = helperFunction(n%10);
        String[] ans = new String[singleDigitoutPut.length * smallAns.length];
        int k =0;
        for (int i =0; i<smallAns.length; i++){
            for (int j =0; j<singleDigitoutPut.length; j++){
                ans[k] = smallAns[i] + singleDigitoutPut[j];
                k++;
            }
        }
        return ans;
    }

    public static String[] helperFunction(int n) {
        if (n < 2) {
            System.exit(0);
        } else if (n == 2) {
            return new String[]{"a", "b", "c"};
        } else if (n == 3) {
            return new String[]{"d", "e", "f"};
        } else if (n == 4) {
            return new String[]{"g", "h", "i"};
        } else if (n == 5) {
            return new String[]{"j", "k", "l"};
        } else if (n == 6) {
            return new String[]{"m", "n", "o"};
        } else if (n == 7) {
            return new String[]{"p", "q", "r", "s"};
        } else if (n == 8) {
            return new String[]{"t", "u", "v"};
        } else {
            return new String[]{"w", "x", "y", "z"};
        }
        return new String[]{""};
    }


    /**
     * find sub strings of a string
     * @param str
     * @return
     */
    public static String[] findSubStrings(String str){
        if (str.length()<=1){
            String ans[] = {str};
            return ans;
        }
        String [] smallAns = findSubStrings(str.substring(1));
        String []ans = new String[smallAns.length+2];
        for (int i = 0; i<smallAns.length; i++){
            ans[i] = smallAns[i];
        }
        ans[smallAns.length] = ""+str.charAt(0);
        ans[smallAns.length+1] = str.charAt(0)+str.substring(1);
        return ans;
    }

    /**
     * Find Sub sequences fo the string
     * @param str
     * @return
     */
    public static String[] findSubSequences(String str){
        if (str.length()==0){
            String ans[] = {""};
            return ans;
        }
        String [] smallAns = findSubSequences(str.substring(1));
        int size = 2*smallAns.length;
        String ans[] = new String[size];
        int k =0;
        for (int i=0; i<smallAns.length; i++){
            ans[k] = smallAns[i];
            k++;
        }
        for (int i =0; i<smallAns.length; i++){
            ans[k] = str.charAt(0)+smallAns[i];
            k++;
        }
        return ans;
    }

    /**
     *Quick Sort Code
     * Send Feedback
     * Given the 'start' and the 'end'' positions of the array 'input'. Your task is to sort the elements between 'start' and 'end' using quick sort.
     *
     *
     * Note :
     * Make changes in the input array itself.
     * Input format :
     * Line 1 : Integer N i.e. Array size
     * Line 2 : Array elements (separated by space)
     * Output format :
     * Array elements in increasing order (separated by space)
     * Sample Input 1 :
     * 6
     * 2 6 8 5 4 3
     * Sample Output 1 :
     * 2 3 4 5 6 8
     */
    //return the correct location of pivot.

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int partition(int []input, int startIndex, int endInedx){
        // Choosing the pivot
        int pivot = input[endInedx];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (startIndex - 1);

        for (int j = startIndex; j <= endInedx - 1; j++) {

            // If current element is smaller than the pivot
            if (input[j] < pivot) {

                // Increment index of smaller element
                i++;
                swap(input, i, j);
            }
        }
        swap(input, i + 1, endInedx);
        return (i + 1);
    }
    public static void quickSort(int[] input,int startIndex, int endInedx) {
        if (startIndex < endInedx) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(input, startIndex, endInedx);

            // Separately sort elements before
            // partition and after partition
            quickSort(input, startIndex, pi - 1);
            quickSort(input, pi + 1, endInedx);
        }
    }

    /**
     * Merge Sort Code
     * Send Feedback
     * You are given the starting 'l' and the ending 'r' positions of the array 'ARR'.
     *
     *
     * You must sort the elements between 'l' and 'r'.
     *
     *
     * Note:
     * Change in the input array itself. So no need to return or print anything.
     * Example:
     * Input: ‘N’ = 7,
     * 'ARR' = [2, 13, 4, 1, 3, 6, 28]
     *
     * Output: [1 2 3 4 6 13 28]
     *
     * Explanation: After applying 'merge sort' on the input array, the output is [1 2 3 4 6 13 28].
     * Input format :
     * The first line contains an integer <em>**'N'**</em> representing the size of the array/list.
     *
     * The second line contains 'N' single space-separated integers representing the elements in the array/list.
     * Output format :
     * You don't need to return anything. In the output, you will see the array after you do the modification.
     * @param arr
     * @param l
     * @param r
     */

    public static void merge(int arr[], int l, int m,  int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    public static void mergeSort(int[] arr, int l, int r){
        // Write your code here
        if (l<r) {
            int mid = (l + r - 1) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }



    /**
     * Remove Duplicates Recursively
     * Send Feedback
     * Given a string S, remove consecutive duplicates from it recursively.
     * Input Format :
     * String S
     * Output Format :
     * Output string
     * Constraints :
     * 1 <= |S| <= 10^3
     * where |S| represents the length of string
     * Sample Input 1 :
     * aabccba
     * Sample Output 1 :
     * abcba
     * @param s
     * @return
     */
    public static String removeConsecutiveDuplicatesSol(String s) {
        // Write your code here
        if (s.length()<=1){
            return s;
        }
        if (s.charAt(0)==s.charAt(1)){
            return removeConsecutiveDuplicatesSol(s.substring(1));
        }else {
            return s.charAt(0)+removeConsecutiveDuplicatesSol(s.substring(1));
        }

    }

    /**
     * Replace Character Recursively
     * Send Feedback
     * Given an input string S and two characters c1 and c2, you need to replace every occurrence of character c1 with character c2 in the given string.
     * Do this recursively.
     * Input Format :
     * Line 1 : Input String S
     * Line 2 : Character c1 and c2 (separated by space)
     * Output Format :
     * Updated string
     * Constraints :
     * 1 <= Length of String S <= 10^6
     * Sample Input :
     * abacd
     * a x
     * Sample Output :
     * xbxcd
     * @param input
     * @param c1
     * @param c2
     * @return
     */

    public static String replaceCharacter(String input, char c1, char c2) {
        return replaceCharacterSol(input, c1,c2, 0);
    }
    public static String replaceCharacterSol(String input, char c1, char c2, int startIndex) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (input.length()==0){
            return input;
        }
        char currentChar = input.charAt(startIndex);
        String ans = "";
        if ((input.length()-1)==startIndex){
            if (currentChar==c1){
                return ans+c2;
            }else {
                return ans+currentChar;
            }
        }
        if (currentChar==c1){
            ans = ans+c2;
        }else {
            ans = ans+currentChar;
        }
        return ans+replaceCharacterSol(input, c1, c2, startIndex+1);

    }

    /**
     * Multiplication (Recursive)
     * Send Feedback
     * Given two integers M & N, calculate and return their multiplication using recursion. You can only use subtraction and addition for your calculation. No other operators are allowed.
     * Input format :
     * Line 1 : Integer M
     * Line 2 : Integer N
     * Output format :
     * M x N
     * Constraints :
     * 0 <= M <= 1000
     * 0 <= N <= 1000
     * Sample Input 1 :
     * 3
     * 5
     * Sample Output 1 :
     * 15
     * @param m
     * @param n
     * @return
     */

    public static int multiplyTwoIntegers(int m, int n){
        // Write your code here
        if (m==0 || n ==0){
            return 0;
        }
        return m+n + multiplyTwoIntegers(m-1, n-1);
    }


    /**
     * Sum of digits (recursive)
     * Send Feedback
     * Write a recursive function that returns the sum of the digits of a given integer.
     * Input format :
     * Integer N
     * Output format :
     * Sum of digits of N
     * Constraints :
     * 0 <= N <= 10^9
     * Sample Input 1 :
     * 12345
     * Sample Output 1 :
     * 15
     * @param input
     * @return
     */
    public static int sumOfDigits(int input){
        // Write your code here
        if (input==0){
            return 0;
        }
        return input%10 + sumOfDigits(input/10);

    }


    /**
     * Check Palindrome (recursive)
     * Send Feedback
     * Check whether a given String 'S' is a palindrome using recursion.
     *
     *
     * Return true or false.
     *
     *
     * Note:
     * You don’t need to print anything. Just implement the given function.
     * Example:
     * Input: s = "racecar"
     * Output: true
     * Explanation: "racecar" is a palindrome.
     * Input Format:
     * The first and only line of the input contains string S.
     * Output format:
     * Return a boolean value True or False.
     * Sample Input 1:
     * abbba
     * Sample Output 1:
     * true
     * @param str
     * @return
     */
    public static boolean isPalindromeSol(String str, int start, int end) {
        if (start==end){
            return true;
        }
        if (str.charAt(start)!=str.charAt(end)){
            return false;
        }
        // If there are more than
        // two characters, check if
        // middle substring is also
        // palindrome or not.
        if (start < end + 1)
            return isPalindromeSol(str, start + 1, end - 1);
        return true;
    }
    public static boolean isPalindrome(String str) {
        // Write your code here.
        return isPalindromeSol(str, 0, str.length()-1);
    }

    /**
     * Geometric Sum
     * Send Feedback
     * Given k, find the geometric sum i.e.
     * 1 + 1/2 + 1/4 + 1/8 + ... + 1/(2^k)
     * using recursion.
     * Input format :
     * Integer k
     * Output format :
     * Geometric sum (upto 5 decimal places)
     * Constraints :
     * 0 <= k <= 1000
     * Sample Input 1 :
     * 3
     * Sample Output 1 :
     * 1.87500
     * @param k
     * @return
     */
    public static double findGeometricSum(int k){
        // Write your code here
        if (k ==0){
            return 1;
        }
        return 1/Math.pow(2, k) + findGeometricSum(k-1);
    }


    /**
     * Count Zeros
     * Send Feedback
     * Given an integer N, count and return the number of zeros that are present in the given integer using recursion.
     * Input Format :
     * Integer N
     * Output Format :
     * Number of zeros in N
     * Constraints :
     * 0 <= N <= 10^9
     * Sample Input 1 :
     * 0
     * Sample Output 1 :
     * 1
     * Sample Input 2 :
     * 00010204
     * Sample Output 2 :
     * 2
     * @param input
     * @return
     * 101;
     */
    public static int countZerosRec(int input){
        // Write your code here

        if(input==0){
            return 1;
        }
        return countZerosRecSol(input);

    }
    public static int countZerosRecSol(int input){
        if (input==0){
            return 0;
        }
        if (input%10==0){
            return 1+ countZerosRecSol(input/10);
        }
        return countZerosRecSol(input/10);

    }


    /**
     * All Indices of Number
     * Send Feedback
     * Given an array of length N and an integer x, you need to find all the indexes where x is present in the input array. Save all the indexes in the output array (in increasing order).
     * Do this recursively. Indexing in the array starts from 0.
     * Hint:
     * Try making a helper function with the required arguments and call the helper function from the allIndexes function.
     * Input Format :
     * Line 1 : An Integer N i.e. size of array
     * Line 2 : N integers which are elements of the array, separated by spaces
     * Line 3 : Integer x
     * Output Format :
     * Return the size of the output array
     * Constraints :
     * 1 <= N <= 10^3
     * Sample Input :
     * 9 8 10 8 8
     * 8
     * @param input
     * @param x
     * @return
     */
    public static int[] allIndexes(int input[], int x) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        allIndexesSol(input, x, 0, list);
        int arr[] = new int[list.size()];
        for (int i =0; i<list.size(); i++){
            arr[i] = list.get(i);
        }
        return arr;

    }

    public static ArrayList<Integer> allIndexesSol(int input[], int x, int currentIndex, ArrayList<Integer> list) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (input.length==0){
            return list;
        }
        if(input.length-1==currentIndex){
            if (input[currentIndex]==x){
                list.add(currentIndex);
                return list;
            }else{
                return list;
            }
        }
        if (input[currentIndex]==x){
            list.add(currentIndex);
        }
        return allIndexesSol(input, x, currentIndex+1, list);

    }


    /**
     * Last Index of Number
     * Send Feedback
     * Given an array of length N and an integer x, you need to find and return the last index of integer x present in the array. Return -1 if it is not present in the array.
     * Last index means - if x is present multiple times in the array, return the index at which x comes last in the array.
     * You should start traversing your array from 0, not from (N - 1).
     * Do this recursively. Indexing in the array starts from 0.
     * Input Format :
     * Line 1 : An Integer N i.e. size of array
     * Line 2 : N integers which are elements of the array, separated by spaces
     * Line 3 : Integer x
     * Output Format :
     * last index or -1
     * Constraints :
     * 1 <= N <= 10^3
     * Sample Input :
     * 9 8 10 8
     * 8
     * @param input
     * @param x
     * @param currentIndex
     * @param lastIndex
     * @return
     */
    public static int lastIndexSol(int input[], int x, int currentIndex, int lastIndex ) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (input.length==0){
            return lastIndex;
        }
        if(input.length-1==currentIndex){
            if (input[currentIndex]==x){
                return currentIndex;
            }else{
                return lastIndex;
            }
        }
        if (input[currentIndex]==x){
            lastIndex = currentIndex;
        }
        return lastIndexSol(input, x, currentIndex+1, lastIndex);
    }


    /**
     * First Index of Number
     * Send Feedback
     * Given an array of length N and an integer x, you need to find and return the first index of integer x present in the array. Return -1 if it is not present in the array.
     * First index means, the index of first occurrence of x in the input array.
     * Do this recursively. Indexing in the array starts from 0.
     * Input Format :
     * Line 1 : An Integer N i.e. size of array
     * Line 2 : N integers which are elements of the array, separated by spaces
     * Line 3 : Integer x
     * Output Format :
     * first index or -1
     * Constraints :
     * 1 <= N <= 10^3
     * Sample Input :
     * 9 8 10 8
     * 8
     * Sample Output :
     * 1
     *
     * @param input
     * @param x
     * @return
     */
    public static int firstIndex(int input[], int x, int currentIndex) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        //1,2  2
        if (input.length==0 || currentIndex==input.length-1){
            return -1;
        }
        if (input[currentIndex]==x){
            return currentIndex;
        }
        return firstIndex(input, x, currentIndex+1);

    }



    /**
     * Check Number in Array
     * Send Feedback
     * Given an array of length N and an integer x, you need to find if x is present in the array or not. Return true or false.
     * Do this recursively.
     * Input Format :
     * Line 1 : An Integer N i.e. size of array
     * Line 2 : N integers which are elements of the array, separated by spaces
     * Line 3 : Integer x
     * Output Format :
     * 'true' or 'false'
     * Constraints :
     * 1 <= N <= 10^3
     * Sample Input 1 :
     * 9 8 10
     * 8
     * Sample Output 1 :
     * true
     * @param x
     * @param
     * @return
     */
    public static boolean checkNumber(int input[], int x) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (input.length<=0){
            return false;
        }
        if (input[0]==x){
            return true;
        }
        int[] smallInput = new int[input.length-1];
        for (int i =1; i<input.length; i++){
            smallInput[i-1] = input[i];
        }
        return checkNumber(smallInput, x);
    }


    public static int power(int x, int n) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (n==0){
            return 1;
        }
        return x * power(x, n-1);
    }


    /**
     * Print Numbers
     * Send Feedback
     * Given the number 'n', write a code to print numbers from 1 to n in increasing order recursively.
     * @param n
     * Sample Input 1 :
     *  6
     * Sample Output 1 :
     * 1 2 3 4 5 6
     */
    public static void printNum(int n){
        //Write your code here
        if (n<=1){
            System.out.print(n + " ");
            return;
        }
        printNum(n-1);
        System.out.print(n+" ");
    }

    /**
     * Number of Digits
     * Send Feedback
     * You are given a number 'n'.
     *
     *
     * Return number of digits in ‘n’.
     *
     *
     * Example:
     * Input: 'n' = 123
     *
     * Output: 3
     *
     * Explanation:
     * The 3 digits in ‘123’ are 1, 2 and 3.
     * @param n
     * @return
     */
    public static int countDigits(int n){
        // Write your code here.
        if (n/10==0){
            return 1;
        }
        return 1+countDigits(n/10);
    }

    public static int findFib(int n){
        if (n==1 || n==0){
            return n;
        }
        return findFib(n-1)+findFib(n-2);
    }

    public static boolean checkSortedArray(int arr[]){
        if (arr.length<=1){
            return true;
        }
        int smallInput[] = new int[arr.length-1];
        for (int i = 1; i<arr.length; i++){
            smallInput[i-1] = arr[i];
        }
        boolean smallAns = checkSortedArray(smallInput);
        if (!smallAns){
            return false;
        }
        if (arr[0]<=arr[1]){
            return true;
        }else {
            return false;
        }
    }

    public static boolean checkSortedArray_2(int arr[]){
        if (arr.length<=1){
            return true;
        }
        if (arr[0]>arr[1]){
            return false;
        }
        int smallInput[] = new int[arr.length-1];
        for (int i = 1; i<arr.length; i++){
            smallInput[i-1] = arr[i];
        }
        boolean smallAns = checkSortedArray(smallInput);
        if (smallAns){
            return false;
        }else {
            return true;
        }
    }


    public static boolean checkSortedArray_3(int arr[], int startIndex){
        if (startIndex>=arr.length-1){
            return true;
        }
        if (arr[startIndex]>arr[startIndex+1]){
            return false;
        }
        boolean smallAns = checkSortedArray_3(arr, startIndex+1);
        return smallAns;
    }


    /*
    Sum of Array
        Send Feedback
        Given an array of length N, you need to find and return the sum of all elements of the array.
        Do this recursively.
        Input Format :
        Line 1 : An Integer N i.e. size of array
        Line 2 : N integers which are elements of the array, separated by spaces
        Output Format :
        Sum
        Constraints :
        1 <= N <= 10^3
        Sample Input 1 :
        3
        9 8 9
        Sample Output 1 :
        26
     */
    public static int sum(int[] input) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        if (input.length<0){
            return 0;
        }
        if (input.length==1){
            return input[0];
        }
        int[] smallInput = new int[input.length-1];
        for (int i =1; i<input.length; i++){
            smallInput[i-1] = input[i];
        }
        return input[0]+sum(smallInput);
    }



}
