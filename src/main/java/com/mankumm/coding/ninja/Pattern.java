package com.stack.com.mankumm.coding.ninja;

import java.util.*;

public class Pattern {
    public static void main(String[] args) {
        /* Your class should be named Solution.
         * Read input as specified in the question.
         * Print output as specified in the question.
         */
        /*Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Integer N (Total no. of rows): ");
        int rows = Integer.parseInt(myObj.nextLine());  // Read user input*/
        //int arr[] = {1,3,9,8,9};
        /*int arr[] = {2,5,1,2,3,4,5,3,10,20,30};
        //pattern3(rows);
        sum(arr);*/

       /* Scanner scan = new Scanner(System.in);
        int testCases = scan.nextInt();
        while (testCases != 0) {
            scan = new Scanner(System.in);
            int row = scan.nextInt();
            scan = new Scanner(System.in);
            int col = scan.nextInt();

            int arr [][] = new int[row][col];
            for (int i = 0 ; i<row; i++){
                for (int j = 0; j<col; j++){
                    scan = new Scanner(System.in);
                    arr[i][j] = scan.nextInt();
                }
            }
            wavePrint(arr);
            testCases--;

        }*/
        //breakWords("Helloo world good morniing");
        int [] arr1 ={0310,2329,2358,945};
        int [] arr2 ={0315,2338,2359,1018};
        int [] arr3  = {8,1,2,7,1,5,6,1,6,6 };
        //System.out.print(PairSum(arr, 5));
        //System.out.print(platformsNeeded12(arr1, arr2));
        //printPattern12323(2);

        System.out.print(findMinSubstringContainingString("ik", "i"));
    }

    class Pair{
        int first;
        int second;


        public  Pair(int first, int second){
            this.first = first;
            this.second =second;
        }
    }


    public boolean findPairs(int[] arr){
        Map<Integer, Pair> map = new HashMap<>();
        for (int i = 0; i<arr.length; i++){
            for (int j = i+1; j<arr.length; j++){
                int sum = arr[i]+arr[j];
                if (map.containsKey(sum)){
                    return true;
                }else {
                    Pair pair = new Pair(arr[i], arr[j]);
                    map.put(sum, pair);
                }
            }
        }
        return false;
    }
    public static boolean findPairs1(int[] arr){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return the desired output and don't print anything.
         * Taking input and printing output is handled automatically.
         */

        for (int i =0; i<arr.length; i++){
            for (int j =i+1;j<arr.length;j++){
                for (int k = j+1; k<arr.length; k++){
                    for (int m = k+1; m<arr.length; m++){
                        if (arr[i]+arr[j]==arr[k]+arr[m]){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public static String findMinSubstringContainingString(String large, String small){
        /* Your class should be named solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         * Looks for minimum string
            mums
            *
            * ik
            * i
         */
        int len = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        int j =0;
        String ans = "";
        for (int i =0; i<large.length(); i++){
            if (j <=small.length()-1 && large.charAt(i)==small.charAt(j)){
                if (j==0){
                    start = i;
                }
                if (j==small.length()-1){
                    end=i;
                    int tempSubStrLen = end-start;
                    if (tempSubStrLen<len){
                        len = tempSubStrLen;
                        ans = large.substring(start, end+1);
                        start = -1;
                        end = -1;
                        j=0;
                    }
                }
                j++;
            } else {
                if (large.charAt(i)==small.charAt(0)){
                    start=i;
                    j =0;
                    j++;
                }
            }
        }
        return ans;
    }


    public static int maximumProfit1(int budget[]) {
        Arrays.sort(budget);
        int n = budget.length;
        int price = 0;
        int totalPrice = Integer.MIN_VALUE;
        for (int i = 0;i<budget.length; i++){
            int count = n-i;
            int tempPrice = budget[i] * count;
            if (totalPrice<tempPrice){
                price = budget[i];
                totalPrice = tempPrice;
            }
        }
        return totalPrice;
    }
    public static int maximumProfit(int budget[]) {
        // Write your code here
        int ans =0;
        int price = 0;
        int totalPrice = 0;
        for (int i =0; i<budget.length; i++){
            int tempprice = budget[i];
            int count =0;
            for (int j = 0; j<budget.length; j++){
                if (tempprice<=budget[j]){
                    count++;
                }

            }
            if (totalPrice<(count*tempprice)){
                ans=count;
                price = tempprice;
                totalPrice = ans* price;
            }
        }

        return totalPrice;
    }

    public static int platformsNeeded12(int arrival[], int departure[]) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        Arrays.sort(arrival);
        Arrays.sort(departure);
        int ans = 1;
        int platform_needed = 1, result =1;
        int i =1, j =0;
        while (i<arrival.length && j<arrival.length){
            if (arrival[i] <=departure[j]){
                platform_needed++;
                i++;
            }
            else if (arrival[i]>departure[j]){
                platform_needed--;
                j++;
            }
            if (platform_needed>result){
                result = platform_needed;
            }
        }
        return result;
    }

    public static void printPattern12323(int n){
        // Write your code here
        int top = 0;
        int bottom = n-1;
        int left =0;
        int right = n-1;
        int mat[][] = new int[n][n];
        int numElement = n*n;
        int k =1;
        for (int j =0; j<(n/2)+1 && k<numElement; j++){
            //print top
            for (int i = left; i<=right && k<=numElement; i++){
                //00, 01, 02
                mat[top][i] = k;
                k++;
            }
            top++;
            //print bottom
            for (int i = left; i<=right && k<=numElement; i++){
                //00, 01, 02
                mat[bottom][i] = k;
                k++;
            }
            bottom--;
        }

        for (int i =0; i<mat.length; i++){
            for (int j = 0; j<mat[0].length; j++){
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }

    }


    public static void printPattern111(int n){
        if(n==0){
            return;
        }
        int numElement = (2*n-1) * (2*n-1);
        int top = 0;
        int bottom = 2*n-2;
        int left = 0;
        int right = 2*n-2;
        int count = 0;
        int matrix[][] = new int[2*n-1][2*n-1];
        int row = 2*n-1;
        int col = 2*n-1;
        while (count<numElement && n>=1){
            //print left to right
            for (int i = left; i<=right && count<numElement; i++){
                matrix[top][i] = n;
                count++;
            }
            top++;
            //print top to bottom
            for (int i = top; i<=bottom && count<numElement; i++){

                matrix[i][right] =n;
                count++;
            }
            right--;
            //print right to left
            for (int i = right; i>=left && count<numElement; i--){

                matrix[bottom][i]=n;
                count++;
            }
            bottom--;
            //bottom to top
            for (int i = bottom; i>=top && count<numElement; i--){
                matrix[i][left]=n;
                count++;
            }
            left++;
            n--;
        }

        for (int i = 0; i<=row;i++){
            for (int j = 0; j<=col; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


        public static ArrayList<Integer> longestConsecutiveIncreasingSequence(int[] arr) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        Map<Integer, Boolean> visitedElement = new HashMap<Integer, Boolean>();
        Map<Integer, Integer> elementToIndexMapping = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            elementToIndexMapping.put(num, i);

            if (!visitedElement.containsKey(num)) {
                visitedElement.put(num, false);
            }
        }

        ArrayList<Integer> longestSubsequence = new ArrayList<Integer>();
        int globalMaxSequenceLength = 1;
        int globalMinStartIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            int currentMinStartIndex = i;

            int count = 0;
            int tmpNum = num;

            // Forward
            while (visitedElement.containsKey(tmpNum) && !visitedElement.get(tmpNum)) {
                // Mark visited elements in the array as true
                visitedElement.put(tmpNum, true);
                count++;
                tmpNum++;
            }

            // Backward
            tmpNum = num - 1;
            while (visitedElement.containsKey(tmpNum) && !visitedElement.get(tmpNum)) {
                // Mark visited elements in the array as true
                visitedElement.put(tmpNum, true);
                count++;

                currentMinStartIndex = elementToIndexMapping.get(tmpNum);
                tmpNum--;
            }

            if (count > globalMaxSequenceLength) {
                globalMaxSequenceLength = count;
                globalMinStartIndex = currentMinStartIndex;
            } else if (count == globalMaxSequenceLength) {
                if (currentMinStartIndex < globalMinStartIndex) {
                    globalMinStartIndex = currentMinStartIndex;
                }
            }
        }

        int globalStartNum = arr[globalMinStartIndex];

        longestSubsequence.add(globalStartNum);

        if (globalMaxSequenceLength > 1) {
            longestSubsequence.add(globalStartNum + globalMaxSequenceLength - 1);
        }

        return longestSubsequence;

    }

    public static int max(int[] arr){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        int ans = Integer.MIN_VALUE;
        int czero = 0;
        int cones = 0;
        for (int i =0; i<arr.length; i++){

            for (int j = i;j<arr.length;j++){
                if (arr[j]==0){
                    czero++;
                }else {
                    cones++;
                }
                if ((j-i+1)>ans && czero==cones){
                    ans=j-i+1;
                    System.out.print(i +"******"+ j);
                }
            }

        }
        return ans;
    }


    public static int minDistance(int[] arr){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        int ans = Integer.MAX_VALUE;
        for (int i =0; i<arr.length; i++){
            for (int j = i+1;j<arr.length;j++){
                if (arr[i]==arr[j]){
                    if ((j-i)<ans){
                        ans = j-i;
                    }
                }
            }

        }
        return ans;

    }

    public static int makeAnagram(String s1,String s2){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        Map<Character, Integer> map1 = new HashMap<>();
        for (int i =0; i<s1.length(); i++){
            if (map1.containsKey(s1.charAt(i))){
                map1.put(s1.charAt(i), map1.get(s1.charAt(i))+1);
            }else {
                map1.put(s1.charAt(i), 1);
            }
        }
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i =0; i<s2.length(); i++){
            if (map2.containsKey(s2.charAt(i))){
                map2.put(s2.charAt(i), map2.get(s2.charAt(i))+1);
            }else {
                map2.put(s2.charAt(i), 1);
            }
        }
        int count =0;
        for (Map.Entry<Character, Integer> entry:
                map1.entrySet()) {
            Character key = entry.getKey();
            if (map2.containsKey(key)){
                count+=Math.abs(entry.getValue()-map2.get(key));
                map2.remove(key);
            }else {
                count+=entry.getValue();
            }
        }
        for (Map.Entry<Character, Integer> entry:
                map2.entrySet()) {

            count+= entry.getValue();
        }

        return count;
    }



    public static boolean subarraySumTo0(int[] arr) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        for (int i =0; i<arr.length; i++){
            int sum = arr[i];
            for (int j = i; j<arr.length; j++){
                sum+=arr[j];
                if (sum==0){
                    return true;
                }
            }
        }
        return false;
    }


    public static int getPairsWithDifferenceK(int arr[], int k) {
        //Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            if (map.containsKey(Math.abs(sum-k))) {
                count++;
            }

            // Update the map with the current element
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        return count;

    }

    public static int PairSum(int[] arr, int size) {
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */


        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            if (map.containsKey(-sum)) {
                count += map.get(-sum);
            }

            // Update the map with the current element
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        return count;
    }
    public static int platformsNeeded1(int arrival[], int departure[]){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arrival.length; i++){
                for (Map.Entry<Integer, Integer> entry :
                        map.entrySet()) {
                    if (entry.getValue() < departure[i]) {
                        map.remove(arrival[i]);
                    }
                }

            map.put(arrival[i], departure[i]);
        }

        return map.size();

    }


    public static void printPattern1(int n){

		/* Don't write main().
		 Don't read input, it is passed as function argument.
		 Print output as per the question.
		*/

        int top = 0;
        int bottom = n*2-1;
        int left = 0;
        int right = n*2 -1;
        int count = 0;
        int numElement = bottom*right;
        while (count<numElement) {
            for (int i = left; i < right && count<numElement; i++) {

                System.out.print(n + " ");
                count++;
            }

            top++;

            for (int i = top; i <= bottom && count<numElement; i++) {
                System.out.println(n + " ");
                count++;
            }
            right--;

            for (int i = right; i >= left && count<numElement; i--) {
                System.out.print(n);
                count++;
            }
            bottom--;
            for (int i = bottom; i >= top && count<numElement; i--) {
                System.out.print(n);
                count++;
            }
            left++;
            n--;
        }

    }


    public static void intersection(int[] arr1, int[] arr2) {
        //Your code goes here
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arr1.length;i++){
            if (map.containsKey(arr1[i])){
                map.put(arr1[i], map.get(arr1[i])+1);
            }else {
                map.put(arr1[i], 1);
            }
        }

        for (int i =0; i<arr2.length;i++){
            if (map.containsKey(arr2[i])){
                System.out.print(arr2[i]);
                map.put(arr2[i], map.get(arr2[i])-1);
                if (map.get(arr2[i])==0){
                    map.remove(arr2[i]);
                }
            }else {
                map.put(arr2[i], 1);
            }
        }
    }

    public static int maxFrequencyNumber(int[] arr){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int count = Integer.MIN_VALUE;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i =0; i<arr.length; i++){
            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }else {
                map.put(arr[i], 1);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entryset:
             map.entrySet()) {
            if (entryset.getValue()>count){
                count=entryset.getValue();
                ans = entryset.getKey();
            }
        }
        return ans;
    }


    public static String breakWords(String input){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */

        String []str = input.split(" ");
        String ans = "";
        for (int i =0; i<input.length();i++){
            String temp = str[i];
            if (temp.length()>=4 && temp.length()%2==0){
                ans+= placeSpace(temp)+" ";
            }
        }
        return ans;
    }

    public static String placeSpace(String str){
        String ans ="";
        int count =0;
        for (int i =0; i<str.length(); i++){
            if (count==str.length()/2){
                ans+=" ";
            }
            ans+=str.charAt(i);
            count++;
        }
        return ans;
    }

    public static int countPalindromeSubstrings(String str) {
        // Write your code here
        int count =0;
        for (int i = 0; i<str.length(); i++){
            for (int j = i; j < str.length(); j++) {
                String subStr = str.substring(i, j+1);
                if (isPal(subStr)){
                    count++;
                }
            }
        }
        return count;

    }
    public static boolean isPal(String str){
        String strRev = "";
        for (int i = str.length()-1; i>=0; i--){
            strRev+=str.charAt(i);
        }
        return strRev.equals(str);
    }
    public static String findLargestUniqueSubstring(String str){
        /* Don't write main().
         * Don't read input, it is passed as function argument.
         * Return the answer.
         * Taking input and printing output is handled automatically.
         */
        int len =0;
        String ans = "";
        for (int i = 0; i<str.length(); i++){
            for (int j = i; j < str.length(); j++) {
                String subStr = str.substring(i, j+1);
                if (subStr.length()>len && areUnique(subStr)){
                    len = subStr.length();
                    ans = subStr;
                }
            }
        }
        return ans;
    }
    public static boolean areUnique(String str){
        Set<Character> set = new HashSet<>();
        for (int i =0; i<str.length(); i++){
            set.add(str.charAt(i));
        }
        return set.size()==str.length();
    }

    public static String reverseWordWise1(String input) {
        // Write your code here
        String rev = null;
        String [] str = input.split(" ");
        for (int i = str.length; i>=0; i--){
            rev+=str[i]+" ";
        }
        return rev;

    }


    public static void spiralPrint3(int matrix[][]){
        //Your code goes here
        int numElement = matrix.length * matrix[0].length;
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        int count = 0;


        while (count<numElement){
            //print left to right
            for (int i = left; i<=right && count<numElement; i++){
                System.out.print(matrix[top][i]+ " ");
                count++;
            }
            top++;
            //print top to bottom
            for (int i = top; i<=bottom && count<numElement; i++){
                System.out.print(matrix[i][right]+ " ");
                count++;
            }
            right--;
            //print right to left
            for (int i = right; i>=left && count<numElement; i--){
                System.out.print(matrix[bottom][i]+ " ");
                count++;
            }
            bottom--;

            for (int i = bottom; i>=top && count<numElement; i--){
                System.out.print(matrix[i][left]+ " ");
                count++;
            }
            left++;
        }


    }


    public static void wavePrint2(int mat[][]){
        //Your code goes here
        int count = 0;
        int numElement = mat.length * mat[0].length;
        int top = 0;
        int bottom = mat[0].length-1;
        int col =0;
        while (count<numElement){
            for (int i = top; i<=bottom && count<numElement; i++){
                System.out.print(mat[i][col]);
                count++;
            }
            col++;
            for (int i = bottom; i>=top && count<numElement; i--){
                System.out.print(mat[i][col]);
                count++;
            }
            col++;
        }
    }

    public static void leaders(int[] arr) {
        for (int i=0; i<arr.length; i++){
            boolean isLeader = true;
            for (int j = i+1; j<arr.length; j++){
                if (arr[j]>arr[i]){
                    isLeader = false;
                    break;
                }
            }
            if (isLeader){
                System.out.print(arr[i]);
            }
        }

    }

    public static int platformsNeeded(int arrival[], int departure[]){
        /* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         */
        int x = 0;


        return 0;

    }


    public static void pushZerosAtEnd(int[] arr) {
        //Your code goes here
        int k =0;
        for (int i = 0; i<arr.length; i++){
            if (arr[i]!=0 ){
                arr[k] = arr[i];
                if(i!=k) {
                    arr[i] = 0;
                }
                k++;
            }
        }
    }

    public static void sort012(int[] arr){
        //Your code goes here
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int i =0; i<arr.length; i++){
            if (arr[i]==0){
                count0++;
            }else if (arr[i]==1){
                count1++;
            }else {
                count2++;
            }
        }
       for (int i =0; i<count0; i++){
           arr[i] = 0;
       }
       for (int i = count0; i<count0+count1; i++){
           arr[i] = 1;
       }
        for (int i = count0+count1; i<count0+count1+count2; i++){
            arr[i] = 2;
        }

    }


    public static void wavePrint1(int mat[][]){

        //Your code goes here
        int row = mat.length;
        if(row==0){
            return;
        }
        int col = mat[0].length;
        int top = 0;
        int bottom = row -1;
        int curCol = 0;
        int count = 0;
        int numElement = row*col;
        while (count<numElement) {
            for (int i = top; i <= bottom && count<numElement; i++) {
                System.out.print(mat[i][curCol]);
                count++;
            }
            curCol++;
            for (int i = bottom; i >= top && count<numElement; i--) {
                System.out.print(mat[i][curCol]);
                count++;
            }
            curCol++;
        }

    }


    public static void spiralPrint2(int matrix[][]) {
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        int count = 0 ;
        int elementNum = bottom+right;
        while (count<elementNum){
            for (int i = left; i<=right && count<elementNum; i++){
                System.out.print(matrix[top][i]+ " ");
                count++;
            }
            top++;
            for (int i = top; i<=bottom && count<elementNum; i++){
                System.out.print(matrix[i][right]+ " ");
                count++;
            }
            right--;

            for (int i = right; i>=left && count<elementNum; i--){
                System.out.print(matrix[bottom][i]+ " ");
                count++;
            }
            bottom--;
            for (int i = bottom; i>=top && count<elementNum; i--){
                System.out.print(matrix[i][top] + " ");
                count++;
            }
            left++;
        }
    }

    public static void spiralPrint1(int matrix[][]) {
        int numRow = matrix.length; // number of total rows
        int numCol = matrix[0].length; // number of total column
        int elementCount = numRow + numCol;
        int count = 0;
        int colStart = 0, rowStart = 0;
        while (count < elementCount) {
            //print row
            for (int i = colStart; i<numCol && count<elementCount; i++){
                System.out.print(matrix[rowStart][i]);
                count++;
            }
            rowStart++;

            //print col
            for (int i = rowStart; i<numRow && count<elementCount; i++){
                System.out.print(matrix[i][numCol]);
                count++;
            }
            numCol--;

            //print row
            for (int i = numCol; i>=colStart && count<elementCount; i--){
                System.out.print(matrix[numRow][i]);
                count++;
            }
            numRow--;

            //print col
            for (int i =numRow-1; i>=rowStart && count<elementCount; --i){
                System.out.print(matrix[numRow][i]);
                count++;
            }
            colStart++;

        }
    }

    public static void spiralPrint(int matrix[][]){
        int numRow = matrix.length; // number of total rows
        int numCol = matrix[0].length; // number of total column
        int elementCount = numRow+numCol;
        int count = 0;
        int colStart = 0, rowStart=0;
        while (count<elementCount){
            //print row left to right, so the row is fixed and colmumn will change
            //colStart = 0, rowStart = 0;
            for (int i = colStart; i<numCol && count<elementCount; i++){
                System.out.print(matrix[rowStart][i]);
                count++;
            }
            rowStart++;

            //print colum - thus colum is fixed and row is changed.
            for (int i = rowStart; i<numRow && count<elementCount; i++){
                System.out.print(matrix[i][numCol-1]);
                count++;
            }
            numCol--;

            //print bottom row - thus row is fixed and col is changing.
            for (int i = numCol-1; i>=colStart && count<elementCount; i--){
                System.out.print(matrix[rowStart][i]);
                count++;
            }
            numRow--;

            //print col - col is fixed and row is changed
            for (int i = numRow-1;count<elementCount && i>rowStart; --i){
                System.out.print(matrix[i][colStart]);
                count++;
            }
            colStart++;

        }

    }

    public static void wavePrint(int[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        for (int i =0; i<col; i++){
            if (i%2==0) {
                for (int j = 0; j < row; j++) {
                    System.out.print(mat[j][i]);
                }
            }else {
                for (int k = row-1; k >= 0; k--) {
                    System.out.print(mat[k][i]);
                }
            }
        }
    }

    public static void findLargest(int mat[][]){
        //Your code goes here
        int sumRow = Integer.MIN_VALUE;
        int indexRow = 0;
        for (int i = 0; i<mat.length; i++){
            int temp =0;
            for (int j =0; j<mat[0].length; j++){
                temp+=mat[i][j];
            }
            if (temp>sumRow){
                sumRow = temp;
                indexRow = i;
            }
        }
        int sumCol = Integer.MIN_VALUE;
        int indexCol = 0;
        for (int i = 0; i<mat[0].length; i++){
            int temp =0;
            for (int j =0; j<mat.length; j++){
                temp+=mat[i][j];
            }
            if (temp>sumCol){
                sumCol = temp;
                indexCol = i;
            }
        }
        if (sumRow>sumCol){
            System.out.println("row " + indexRow + " "+ sumRow);
        }else {
            System.out.println("column " + indexCol + " "+ sumCol);
        }
    }


    public static String reverseWordWise(String input) {
        // Write your code here
        String [] str = input.split(" ");
        String rev = "";
        for (int i = str.length-1; i>=0; i--){
            rev.concat(str[i]).concat(" ");
        }
        input = rev;
        return input;
    }

    public static void printSubstrings(String str) {
        //Your code goes here
        for (int i =0; i <str.length(); i++){
            for (int j =i; j<str.length(); j++){
                System.out.println(str.substring(i,j+1));
            }
        }
    }

    public static boolean isPalindrome(String str) {
        //Your code goes here
        int start = 0;
        int end = str.length()-1;
        while (start<=end){
            if (str.charAt(start)!=str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    public static int countWords(String str) {
        //Your code goes here
        int count =0;
        for (int i = 0; i<str.length(); i++){
            if (str.charAt(i)==' '){
                count++;
            }
        }
        return count;

    }

    public static void swapAlternate(int arr[]) {
        //Your code goes here
        for (int i =0; i<arr.length-1;){
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
            i = i+2;
        }
        for (int i =0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }



    public static int[] arrange(int arr[], int n){
        int start =0;
        int end = n;
        int num =1;
        for (int i  =0; i<arr.length; i++){
            if (num%2 == 0){
                arr[start] = num;
                start++;
            }else {
                arr[end] = num;
                end++;
            }
            num++;
        }
        return arr;
    }

    public static int leanerSearch(int arr[], int k){
        for (int i =0; i<arr.length;i++){
            if (arr[i]==k){
                return i;
            }
        }
        return -1;
    }

    public static void sum(int[] arr) {
        int numOfQuery = arr[0];
        int sum =0;
        int k =0;
        int index = 1;
        for (int i = 1; i<=numOfQuery; i++){
            int num = index;
            for (k = 1; k <=num; k++){
                    sum+=arr[index];
                    num++;
                    index++;
                }
                System.out.println(sum);
                sum =0;
            }
    }

    public static int sumOfElementsInArray(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }
    public static int[] takingInput() {
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = scan.nextInt();
        }
        return arr;
    }

    public static void pattern3(int n){
        if (n%2!=0){
            return;
        }
        int firstHalf = (n+1)/2;
        int secondHalf = n/2;
        //print fist half
        for (int i = 1; i<=firstHalf; i++){
            //print spaces
            for (int j = 1; j<=i-1; j++){
                System.out.print(" ");
            }
            //print stars
            for (int k = 1; k<=i;k++){
                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println();
        }
        //pritn 2nd half
        for (int i =1; i<=secondHalf; i++){
            //print spaces
            for (int j = 1; j <= secondHalf-i; j++){
                System.out.print(" ");
            }

            for (int k = 1; k <= secondHalf-i+1; k++){
                System.out.print("*");
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    public static void pattern2(int n){
        for (int i = 1; i<=n; i++){
            //print number
            int temp = 1;
            for (int k =1; k<=i; k++){
                System.out.print(temp);
                temp++;
            }
            //print spaces
            for (int j = 1; j<=(n-i); j++){
                System.out.print("X");
            }

            //print spaces
            for (int j = 1; j<= n-i; j++){
                System.out.print("X");
            }
            //print numbers
            temp = i;
            for (int k = 1; k<=i;k++){
                System.out.print(temp);
                temp--;
            }
            System.out.println();
        }
    }

    public static void printPatter1(int n){
        for (int i = 1; i<=n; i++){
            //print spaces
            for (int j = 1; j<= n-i; j++){
                System.out.print("X");
            }
            //print numbers
            int temp = i;
            for (int k = 1; k<=i;k++){
                System.out.print(temp);
                temp++;
            }
            System.out.println();
        }
    }
    public static void printPattern(int n){
        for (int i =1; i<=n; i++){
            //print space
            for (int j =1; j <= n-i; j++){
                System.out.print(" ");
            }
            //print increasing numbers
            int temp = i;
            for (int j = 1; j<=i;j++){
                System.out.print(temp);
                temp++;
            }
            //print decreses
            temp = 2 * i -2;
            for (int k = 1; k<=(i-1); k++){
                System.out.print(temp);
                temp--;
            }
            System.out.println();
        }
    }
}
