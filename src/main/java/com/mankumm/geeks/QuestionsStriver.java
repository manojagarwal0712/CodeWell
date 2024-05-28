package com.stack.com.mankumm.geeks;

import java.util.*;

public class QuestionsStriver {
    public static void main(String[] args) {
        String str = "(()())(())()";
        String []strs = {"flower","flow","flight"};
        //System.out.print(longestCommonPrefix(strs));
        System.out.print(countSubStrings("aacfssa", 3));

    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * @param s
     * @return
     */
    public String longestPalindrome(String str) {
        int len =0;
        String pal = "";
        for (int i =0; i<str.length(); i++){
            for (int j = i; j<str.length(); j++){
                String ans = str.substring(i, j+1);
                if (isPalindrome(ans) && ans.length()>pal.length()){
                    pal = ans;
                }
            }
        }
        return pal;
    }
    public static boolean isPalindrome(String str){
        int start = 0;
        int end = str.length()-1;
        while (start<end){
            if (str.charAt(start) != str.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    /*
    Count Number of Substrings
    https://www.codingninjas.com/studio/problems/count-with-k-different-characters_1214627?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
     */
    public static int countSubStringsBetter(String str, int k) {
        int count =0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i =0; i<str.length(); i++){
            for (int j = i; j<str.length(); j++){
                String ans = str.substring(i, j+1);
                if (areUniqueChar(ans, k)){
                    count++;
                }
            }
        }
        return count;
    }

    public static int countSubStrings(String str, int k) {
        int count =0;
        for (int i =0; i<str.length(); i++){
            for (int j = i; j<str.length(); j++){
                String ans = str.substring(i, j+1);
                if (areUniqueChar(ans, k)){
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean areUniqueChar(String str, int k){
        Set<Character> set = new HashSet<>();
        for (int i =0; i<str.length(); i++) {
            if (!set.contains(str.charAt(i))){
                k--;
            }
            set.add(str.charAt(i));
        }
        if (k==0){
            return true;
        }
        return false;
    }


    //1234
    public static int atoi(String str){
        int res = 0;
        int sign = 1;
        int i =0;
        while (i<=str.length()){
            if (str.charAt(i)==' '){
                i++;
            }else{break;}
        }
        if (str.charAt(i)=='-'){
            sign = -1;
            i++;
        }else if (str.charAt(0)=='+'){
            i++;
        }

        for (; i<str.length(); i++){
            // handling overflow test case
            if (res > Integer.MAX_VALUE / 10
                    || (res == Integer.MAX_VALUE / 10
                    && str.charAt(i) - '0' > 7)) {
                if (sign == 1)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }

            if (str.charAt(i)-'0'>=0 && str.charAt(i)-'0'<=9){
                res = res*10 + str.charAt(i)-'0';
            }else {
                if (res>0){
                    return sign*res;
                }
                return 0;
            }
        }
        return sign * res;
    }

    /**
     * https://leetcode.com/problems/sort-characters-by-frequency/
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        Map<Character, Integer> map = new TreeMap<>();
        char []arr = s.toCharArray();
        Arrays.sort(arr);
        s = "";
        for (int i =0; i<arr.length;i++){
            s+=arr[i];
        }
        for (int i =0;i<s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), map.get(s.charAt(i))+1);
            }else {
                map.put(s.charAt(i),1);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (Map.Entry<Character, Integer> entry:
             map.entrySet()) {
            int k = entry.getValue();
            char ch = entry.getKey();
            while (k>0){
                ans.append(ch);
                k--;
            }
        }
        return ans.toString();
    }


    public static String longestCommonPrefix(String[] arr){
       String prefix = "";
       for (int i =0;i<arr.length; i++){
           if (prefix.isEmpty() && i==0){
               prefix = arr[i];
           }else{
               int k =0;
               String temp = arr[i];
               String tempPref ="";
               while (k<prefix.length() && k<temp.length()){
                   if (prefix.charAt(k)==temp.charAt(k)){
                       tempPref+=prefix.charAt(k);
                       k++;
                   }else {
                       break;
                   }
               }
               prefix = prefix.length()>tempPref.length()? tempPref: prefix;
           }

       }
       return prefix;

    }
    /**
     * https://takeuforward.org/data-structure/reverse-words-in-a-string/
     */
    public static String reverseWordsInString(String str){
        char []arr = str.toCharArray();
        reverseString(arr, 0, arr.length-1);
        int start = -1;
        for (int i =0; i<arr.length; i++){
            if (start==-1 && arr[i]!=' '){
                start = i;
            }
            if (start!=-1 && arr[i]==' '){
                reverseString(arr, start, i-1);
                start = -1;
            }
        }
        reverseString(arr, start, arr.length-1); // for the last word
        return new String(arr);
    }
    public static void reverseString(char arr[], int start, int end){
        while (start<=end){
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    /**
     * https://www.geeksforgeeks.org/reduce-string-by-removing-outermost-parenthesis-from-each-primitive-substring/
     * @param str
     * @return
     */
    public static String removeOuterParentheses(String str){
        int open_count =0;
        int closed_count = 0;
        String res = "";
        int start =0;
        for (int i =0; i<str.length(); i++){
            if (str.charAt(i)=='('){
                open_count++;
            }else if (str.charAt(i)==')'){
                closed_count++;
            }
            if (open_count==closed_count){
                res+=str.substring(start+1, i);
            }
            start= i+1;
        }
        return res;
    }

    /**
     * https://takeuforward.org/arrays/count-subarray-sum-equals-k/
     */
    public static int countSubArray(int arr[], int k){
        int count =0;
        int sum =0;
        for (int i =0; i<arr.length; i++){
            sum = 0;
            for (int j =i; j<arr.length; j++){
                sum+=arr[j];
                if (sum==k){
                    count++;
                }
            }
        }
        return count;
    }

    public static int countSubArrayOptimal(int arr[], int k){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int preSum = 0;
        int count = 0;
        for (int i =0; i<arr.length; i++){
            preSum+= arr[i];
            if (map.containsKey(preSum-k)){
                count++;
            }
            map.put(preSum, i);
        }
        return count;
    }





    /**
     * https://takeuforward.org/data-structure/rotate-image-by-90-degree/
     */
    public static void rotateMatrixBy90(int arr[][]){
        int n = arr.length-1;
        int row = arr.length;
        int col = arr[0].length;
        int ans[][] = new int[row][col];
        for (int i =0;i<arr.length; i++){
            for (int j =0; j<arr.length;j++){
                arr[j][n-1-i] = arr[i][j];
            }
        }
    }
    /**
     * https://takeuforward.org/data-structure/set-matrix-zero/
     */
    public static void setMatrixZero(int arr[][]){
        int row = arr.length;
        int col = arr[0].length;
        for (int i =0; i<row; i++){
            for (int j =0; j<col; j++){
                if (arr[i][j]==0){
                    int r = i;
                    int c = 0;
                    //make the row zero , mean row is constent
                    while (c<col){
                        if (arr[r][c]!=0){
                            arr[r][c] =-1;
                            c++;
                        }
                    }
                    c = j;
                    r =0;
                    while (r<row){
                        if (arr[r][c]!=0){
                            arr[r][c] =-1;
                            r++;
                        }
                    }
                }
            }
        }
        for (int i =0; i<row; i++){
            for (int j =0; j<col; j++){
                if (arr[i][j]==-1){
                    arr[i][j] =0;
                }
            }
        }
    }

    /**
     * https://takeuforward.org/data-structure/longest-consecutive-sequence-in-an-array/
     * @param arr
     * @return
     *
     * BETTER - SORT THE ARRAY AND FIND THE CONSECUTIVE ELEMENTS COUNT
     */
    public static int findLongestConsecutiveSequenceBrute(int arr[]){
        int longest = 1;
        for (int i =0; i<arr.length; i++){
            int k =arr[i];
            int cnt = 1;
            while (linearSearch(arr, k)){
                cnt++;
                k = k+1;
            }
            longest = Math.max(longest, cnt);
        }
        return longest;
    }
    public static boolean linearSearch(int arr[], int x){
        for (int i =0; i<arr.length; i++){
            if (arr[i]==x){
                return true;
            }
        }
        return false;
    }
    /**
     * https://takeuforward.org/data-structure/leaders-in-an-array/
     *
     */
    public static void findLeadersInArray(int arr[]){
        int leader = Integer.MIN_VALUE;
        for (int i =arr.length-1; i>=0; i--){
            if (arr[i]>leader){
                System.out.println(arr[i]);
                leader = arr[i];
            }
        }
    }
    /**
     * https://takeuforward.org/arrays/rearrange-array-elements-by-sign/
     * positive, negative
     */
    public static void rearrange(int arr[]){
        int ans [] = new int[arr.length];
        int pos =0;
        int neg = 1;
        for (int i=0; i<arr.length; i++){
            if (arr[i]>0){
                ans[pos] = arr[i];
                pos+=2;
            }else {
                ans[neg] = arr[i];
                neg+=2;
            }
        }
    }

    /**
     * https://takeuforward.org/data-structure/stock-buy-and-sell/
     * @param arr
     * @return
     */
    public static int stockBuySell(int arr[]){
        int minBuyPrice = Integer.MAX_VALUE;
        int profit = 0;
        for (int i=0; i<arr.length; i++){
            if (arr[i]<minBuyPrice){
                minBuyPrice = arr[i];
            }
            profit = Math.max(profit, arr[i]-minBuyPrice);
        }
        return profit;
    }

    public static long findSubArrayWithMaxSum(int arr[]){
        long sum=0;
        long maxi = Integer.MIN_VALUE;
        for (int i =0; i<arr.length; i++){
            sum = 0;
            for (int j=i; j<arr.length; j++){
                sum+=arr[j];
                maxi = Math.max(maxi, sum);
            }
        }
        if(maxi<0){
            return 0;
        }
        return maxi;
    }

    /**
     * https://takeuforward.org/data-structure/kadanes-algorithm-maximum-subarray-sum-in-an-array/
     * @param arr
     * @return
     */
    public static long findSubArrayWithMaxSumOptimal(int arr[]){
        long maxi = Integer.MIN_VALUE;
        int sum = 0;
        for (int i =0; i<arr.length; i++){
            sum+=arr[i];
            if (sum<0){
                sum=0;
            }
            maxi = Math.max(maxi, sum);

        }
        return maxi;
    }

    public static int longestSubArrayWithSumK(int arr[], int k){
        long sum=0;
        int count = 0;
        for (int i =0; i<arr.length; i++){
            sum = 0;
            for (int j=i; j<arr.length; j++){
                sum+=arr[j];
                if (sum==k){
                    count = Math.max(count, j-i+1);
                }
            }
        }
        return count;
    }
    public static int longestSubArrayWithSumKBetter(int arr[], int k){
        Map<Integer, Integer> map = new HashMap<>();
        int len =0;
        int sum = 0;
        for (int i =0; i<arr.length; i++){
            sum+=arr[i];
            if (sum==k){
                len = Math.max(len, i+1); // i+1 due to zero based index, at 2 len will be 3
            }
            if (map.containsKey(k-sum)){
                len = Math.max(len, i-map.get(k-sum));
            }
            if (!map.containsKey(k-sum)){
                map.put(sum, i);
            }
        }
        return len;
    }
    public static int longestSubArrayWithSumKWithPosAndNeg(int arr[], int k){
        long sum=0;
        int count = 0;
        for (int i =0; i<arr.length; i++){
            sum = 0;
            for (int j=i; j<arr.length; j++){
                sum+=arr[j];
                if (sum==k){
                    count = Math.max(count, j-i+1);
                }
            }
        }
        return count;
    }

}
