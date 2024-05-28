package com.interview.strings;

import java.util.*;
import java.util.LinkedList;

public class StringsQuestions {
    public static void main(String[] args) {
        //System.out.println(reverse(231));
        myAtoi("ds");
    }
    /**
     * Write a function that reverses a string. The input string is given as an array of characters s.
     */

    public void reverseString(char[] s) {
        int i =0;
        int j = s.length-1;
        while (i< j){
            char ch = s[i];
            s[i] = s[j];
            s[j] = ch;
            i++;
            j--;
        }
    }



    /**
     * https://leetcode.com/problems/longest-common-prefix/
     * @param str
     * @return
     */

    public String longestCommonPrefixBrute(String[] str) {
        int len = Integer.MAX_VALUE;
        int index = -1;
        for (int i =0; i<str.length; i++){
            if (str[i].length()<len){
                len = str[i].length();
                index = i;
            }
        }
        String pref = str[index];
        for (int i =0; i<str.length; i++){
            pref = findCommonPref(pref, str[i]);
        }
        return pref;
    }

    public static String longestCommonPrefixOptimal(String []str){
        Arrays.sort(str);
        return findCommonPref(str[0], str[str.length-1]);
    }
    private static String findCommonPref(String str1, String str2) {
        int i =0;
        int j = 0;
        String pref = "";
        while (i<str1.length() && j <str2.length()){
            if (str1.charAt(i)==str2.charAt(j)){
                pref+=str1.charAt(i);
                i++;
                j++;
            }else {
                return pref;
            }
        }
        return pref;
    }

    /**
     * Reverse a string without affecting special characters
     * https://www.geeksforgeeks.org/reverse-a-string-without-affecting-special-characters/
     * 1) Let input string be 'str[]' and length of string be 'n'
     * 2) l = 0, r = n-1
     * 3) While l is smaller than r, do following
     *     a) If str[l] is not an alphabetic character, do l++
     *     b) Else If str[r] is not an alphabetic character, do r--
     *     c) Else swap str[l] and str[r]
     *
     */

    public static void reverseWithoutAffectiveSpecial(char str[]){
        int i =0;
        int j = str.length-1;
        while (i<j){
            if (!Character.isAlphabetic(str[i])){
                i++;
            }
            else if (!Character.isAlphabetic(str[j])){
                j--;
            }
            else {
                char temp= str[i];
                str[i] = str[j];
                str[j] = temp;
                i++;
                j--;
            }
        }
        for (int k =0;k<str.length;k++){
            System.out.println(str[k]);
        }
    }

    /**
     * Maximum number of characters between any two same character in a string
     * https://www.geeksforgeeks.org/maximum-number-characters-two-character-string/
     * 1) Use two nested loops. The outer loop picks character from left to right,
     * the inner loop finds farthest occurrence and keeps track of maximum.
     *
     */
    public static int maximumChars(String str){
        int res=-1;
        for (int i =0;i<str.length();i++){
            for (int j=i+1;j<str.length();j++){
                if (str.charAt(i)==str.charAt(j)){
                    res = Math.max(res, Math.abs(j-i-1));
                }
            }
        }
        return res;
    }

    /**
     * https://www.geeksforgeeks.org/check-whether-second-string-can-formed-first-string-using-count-array/
     * Check whether second string can be formed from characters of first string
     * Input : str1 = geekforgeeks, str2 = geeks
     * Output : Yes
     * Input : str1 = geekforgeeks, str2 = and
     * Output :  No
     *1) The idea is to count frequencies of characters of str1 in a count array.
     * 2) Then traverse str2 and decrease frequency of characters of str2 in the count array.
     * If frequency of a characters becomes negative at any point, return false.
     */

    public static void canMakeStr2(String str1, String str2){
        //refer above.
    }

    /**
     * https://leetcode.com/problems/longest-palindromic-substring/
     * @param str
     */
    public String longestPalindrome(String str) {
        char chArray[] = str.toCharArray();
        String ans ="";
        for (int i =0; i<str.length(); i++){
            for (int j = i; j<str.length(); j++){
                if (isPal(chArray, i, j)){
                    int currentLen = j-i+1;
                    if (currentLen>ans.length()){
                        ans = str.substring(i, j+1);
                    }
                }
            }
        }
        return ans;
    }
    public static boolean isPal(char [] arr, int startIndex, int endIndex){
        while (startIndex<=endIndex){
            if (arr[startIndex]!=arr[endIndex]){
                return false;
            }else {
                startIndex++;
                endIndex--;
            }
        }
        return true;
    }

    public static void maxOccChar(String str){
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (int i =0;i<str.length();i++){
            if(map.containsKey(str.charAt(i))){
                map.put(str.charAt(i),map.get(str.charAt(i))+1);
            }else{
                map.put(str.charAt(i),1);
            }
        }

        System.out.println(map);

        int max = Integer.MIN_VALUE;
        char ch ='#';
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            if(entry.getValue()>max){
                max = entry.getValue();
                ch = entry.getKey();
            }
        }
        System.out.println(ch+"-"+max);
    }

    /**
     * How to remove all duplicates from a given string?
     * ### Write in place removal of dup
     */
    public static void removDup(String str){
        Set<Character> set = new LinkedHashSet<>();
        for (Character ch:
                str.toCharArray()) {
            set.add(ch);
        }
        String temp ="";
        for (Character ch:
                set) {
            temp = temp+ch;
        }
        str = temp;
        System.out.println(str);
    }

    /**
     * How to print the duplicate characters from the given String?
     *
     */
    public  static void printDup(String str){
        //Take Linked HasMap and in last print the elemets having more then 1 char as those wouod be
        //duplicates
    }

    /**
     * How to remove characters from the first String which are present in the second String?
     *
     *https://takeuforward.org/data-structure/remove-characters-from-first-string-present-in-the-second-string/
     */
    public static void removeCharFromFristString(String str1, String str2){
        Queue<Character> queue = new LinkedList<>();
        String temp ="";
        for (Character ch:
                str1.toCharArray()) {
            queue.add(ch);
        }
        while (!queue.isEmpty()){
            Character ch = queue.remove();
            if(!str2.contains(""+Character.toLowerCase(ch))){
                temp = temp+ch;
            }
        }
        System.out.println(temp);
    }

    /**
     * How to check if two strings are rotations of each other?
     *
     * str1= "XYZ", str2="YXZ"
     *
     * **** Warning: while this is complex solution. The easy solition is to
     * add the string1 to it self (XYZXYZ) now check if str2 exist in concatenated string. then
     * rotation exist.
     *
     */
    public static boolean checkTwoStringAreRotation(String str1, String str2) {
        //Rotate the original string by one and compare with the rotated string if equals then
        //return true or else false. do this till the length of str1
        char ch[] = str1.toCharArray();
        if(str1.equalsIgnoreCase(str2)){
            return false;
        }
        for (int i =0;i<ch.length;i++){
            char temp[] = rotatebyOne(ch);
            //verify if the 2 array are same then return true or after the loop it will return false
        }
        return false;
    }
    public static char[] rotatebyOne(char ch[]){
        char temp = ch[0];
        for (int i =0;i<ch.length-1;i++){
            ch[i] = ch[i+1];
        }
        ch[ch.length-1]=temp;
        return ch;
    }

    /**
     * How to find the first non-repeating character in a given String?
     *
     *
     */
    public static void firstNonRepChar(String str){
        // take LinkedHaspMap and keep all the element in it.
        // now find the key with value 1 and that would be first onn -rep char
    }

    /**
     * How to find the smallest substring in a given string containing all characters of another string?
     * Str1:"this is a test string" and str2="tist"
     * output: "t stri"
     */
    public static String findSmallestSubstring(String searchString, String t){
        int k =0;
        int len = Integer.MAX_VALUE;
        String ans = "";
        for (int i =0; i<searchString.length(); i++){
            for (int j = 0; j<searchString.length() && k<t.length(); j++){
                String subStr = searchString.substring(i, j+1);
                if (containsAllChars(subStr, t)){
                    int currentLen = subStr.length();
                    if (currentLen<len){
                        len = currentLen;
                        ans = subStr;
                    }
                }
            }
        }
        return ans;
    }
    private static boolean containsAllChars(String subStr, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i =0; i<subStr.length();i++){
            map.put(subStr.charAt(i), map.getOrDefault(subStr.charAt(i), 0)+1);
        }
        for (int i =0; i<t.length(); i++){
            char ch = t.charAt(i);
            if (map.containsKey(ch) && map.get(ch)>0){
                map.put(ch, (map.get(ch))-1);
            }else if (map.containsKey(ch) && map.get(ch)==0){
                map.remove(ch);
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * How to check if two given String is the anagram of each other?
     * Note: An anagram contains are of the same length and contains the same character,
     * but in a different order, for example, "Army" and "Mary" is the anagram.
     *
     *
     */
    public static boolean findTwoStringAreAnagram(String str1, String str2){
        Map<Character,Integer> map = new HashMap<>();
        for (Character ch:
                str1.toCharArray()) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        System.out.println(map);
        for (Character ch:
                str2.toCharArray()) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }

        for (Map.Entry<Character,Integer> entry:
                map.entrySet()) {
            if(entry.getValue()%2!=0){
                return false;
            }
        }
        return true;

    }

    /**
     * How do you remove all occurance of a given character from String?
     *
     */
    public static String removeACharFromString(String str, char ch){

        if(str==null)
            return null;

        String temp ="";
        for (int i =0;i<str.length();i++){
            if(str.charAt(i)!=ch){
                temp += str.charAt(i);
            }
        }
        return temp;
    }

    /**
     * Longest subsequence in a string with atleast k repeated char
     * https://www.geeksforgeeks.org/longest-subsequence-where-every-character-appears-at-least-k-times/
     *
     * Input : str = "geeksforgeeks"
     *          k = 2
     * Output : geeksgeeks
     * Every character in the output
     * subsequence appears at-least 2
     * times.
     *
     */

    public static void longestSubseqWithK(String str, int k){
        List<List<Character>> ans = new ArrayList<>();
        List<Character> ds = new ArrayList<>();
        int startIndex = 0;
        longestSubseqWithKSol(str, ans, ds, startIndex, k);
        //System.out.println(ans);
        int len = 0;
        List<List<Character>> seq = new ArrayList<>();
        for (List<Character> ls :
                ans) {
            if (isHavingkRepeatedchar(ls, k)){
                int currentLen = ls.size();
                if (len<currentLen){
                    len = ls.size();
                    if (seq.size()!=0){
                        seq.remove(seq.size()-1);
                    }
                    seq.add(new ArrayList<>(ls));
                }
            }
        }
        System.out.println(seq);
    }
    private static void longestSubseqWithKSol(String str, List<List<Character>> ans, List<Character> ds, int startIndex, int k) {
        if (startIndex==str.length()){
            ans.add(new ArrayList<>(ds));
            return;
        }
        ds.add(str.charAt(startIndex));
        longestSubseqWithKSol(str, ans, ds, startIndex+1, k);
        ds.remove(ds.size()-1);
        longestSubseqWithKSol(str, ans, ds, startIndex+1, k);
    }

    private static boolean isHavingkRepeatedchar(List<Character> ls, int k) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch:
                ls) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for (Map.Entry<Character, Integer> entry:
                map.entrySet()) {
            if (entry.getValue()!=k){
                return false;
            }
        }
        return true;
    }

    /**
     * Given two strings, find if first string is a subsequence of second
     * https://leetcode.com/problems/is-subsequence/
     *
     * https://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
     * Input: str1 = "AXY", str2 = "ADXCPY"
     * Output: True (str1 is a subsequence of str2)
     *
     * Input: str1 = "AXY", str2 = "YADXCP"
     * Output: False (str1 is not a subsequence of str2)
     *
     */
    public static boolean isSubSequence(String str1, String str2){
        int index =0;
        for (int i =0; i<str1.length() && index<str2.length();i++){
            if(str1.charAt(i)==str2.charAt(index)){
                index++;
            }
        }
        if(index==str2.length()){
            return true;
        }
        return false;
    }
    /**
     * https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
     * find number of times string b appears as subsequence in string a
     */
    public static void countSubSequneces(String a, String b){
        int m = a.length();
        int n = b.length();
        countSubSequnecesSol(a,b,m, n);
    }

    private static int countSubSequnecesSol(String a, String b, int m, int n) {
        if ((m == 0 && n == 0) || n == 0)
            return 1;

        if (m==0)
            return 0;
        if (a.charAt(m-1) ==b.charAt(n-1))
            return countSubSequnecesSol(a, b, m-1, n-1) +
                    countSubSequnecesSol(a, b, m-1, n);
        else
            return countSubSequnecesSol(a, b, m-1, n);
    }

    /**
     * Sort an array of strings according to string lengths
     * https://www.geeksforgeeks.org/sort-array-strings-according-string-lengths/
     * Input : {"GeeksforGeeeks", "I", "from", "am"}
     * Output : I am from GeeksforGeeks
     *
     * Input :  {"You", "are", "beautiful", "looking"}
     * Output : You are looking beautiful
     *
     * @param str
     */
    public static void sortStringByLength(String str){
        // iterate and find length and keep in a map.


    }

    public static void permutationOfString(String str){
        boolean visited[] = new boolean[str.length()];
        List<List<Character>> ans = new ArrayList<>();
        List<Character> ds = new ArrayList<>();
        permutationOfStringSol(str, visited , ans, ds);
        System.out.println(ans);
    }

    private static void permutationOfStringSol(String str, boolean[] visited, List<List<Character>> ans, List<Character> ds) {
        if (ds.size()==str.length()){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i =0; i<str.length(); i++){
            if (!visited[i]){
                visited[i] = true;
                ds.add(str.charAt(i));
                permutationOfStringSol(str, visited, ans, ds);
                visited[i] = false;
                ds.remove(ds.size()-1);
            }
        }
    }

    /**
     * K’th Non-repeating Character
     * https://www.geeksforgeeks.org/kth-non-repeating-character-python-using-list-comprehension-ordereddict/
     */

    public static void kthNonRepChar(String str, int k){
        Map<Character, Integer> map = new LinkedHashMap<>();

        for (Character ch:
                str.toCharArray()) {
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch)+1);
            }else{
                map.put(ch,1);
            }
        }
        for (Map.Entry<Character,Integer> entry:
                map.entrySet()) {

            if(entry.getValue()>1){
                continue;
            }else{
                if(k==1){
                    System.out.println(entry.getKey());
                    break;
                }else{
                    k--;
                }
            }

        }
    }

    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * Input: "()"
     * Output: true
     *
     * Input: "()[]{}"
     * Output: true
     */
    /**
     * Parenthesis Checker
     * https://www.geeksforgeeks.org/problems/parenthesis-checker2744/1
     *
     */
    public static boolean parenthesisChecker(String str){
        Stack<Character> stack = new Stack<>();
        for (int i =0; i<str.length(); i++){
            char ch = str.charAt(i);
            if (ch=='{' || ch=='(' || ch=='['){
                stack.push(ch);
            }
            if (ch=='}' || ch==')' || ch==']'){
                if (stack.isEmpty()) {
                    return false;
                }
                char chPopped = stack.pop();
                if (ch=='}'){
                    if (chPopped != '{'){
                        return false;
                    }
                }

                if (ch==')'){
                    if (chPopped != '('){
                        return false;
                    }
                }
                if (ch==']'){
                    if (chPopped != '['){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }



    /**
     * Maximum occurring character in an input string
     *Input: ”test sample”
     * Output: 't'
     *
     *
     * Input:”test”
     * Output:'t'
     */

    public static char findMaxOccurChar(String str){
        //create map to keep the iterated char their count
        Map<Character,Integer> mapCharCount = new LinkedHashMap<>();

        //iterate thorugh all the char of string one by one
        for (int i=0; i< str.length(); i++){
            Character ch = str.charAt(i);

            //verify if the char is already exists in Map , if yes - then increment the count of char by one else
            // add the char to map with count 1.
            if(mapCharCount.containsKey(ch)){
                mapCharCount.put(ch,mapCharCount.get(ch)+1);
            }else {
                mapCharCount.put(ch,1);
            }
        }

        //iterate through the created Map and return the char with max count.
        int count =0;
        char ch = ' ';
        for (Map.Entry<Character,Integer> entries : mapCharCount.entrySet()) {
            if(entries.getValue()>count){
                ch=entries.getKey();
                count = entries.getValue();
            }
        }
        return ch;
    }



    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
     *
     * The algorithm for myAtoi(string s) is as follows:
     *
     * Read in and ignore any leading whitespace.
     * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
     * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
     * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
     * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
     * Return the integer as the final result.
     * Note:
     *
     * Only the space character ' ' is considered a whitespace character.
     * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
     * @param s
     * @return
     */

    public static int myAtoi(String s) {
        int res  = 0;
        s = s.trim();
        for (int i =0; i<s.length(); i++){
            if(s.charAt(i)>=48 && s.charAt(i)<=57){
                res = res*10 + s.charAt(i)-'0';
            }
        }
        if(s.charAt(0)=='-'){
            res = -res;
        }
        return res;
    }

    /**
     * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Example 1
     * Input: haystack = "sadbutsad", needle = "sad"
     * Output: 0
     * Explanation: "sad" occurs at index 0 and 6.
     * The first occurrence is at index 0, so we return 0.
     * Example 2:
     * Input: haystack = "leetcode", needle = "leeto"
     * Output: -1
     * Explanation: "leeto" did not occur in "leetcode", so we return -1.
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        haystack = "mississippi";
        needle = "issip";
        int i =0;
        int startIndex=-1;
        for (int k =0; k<haystack.length() && i <needle.length();k++){
            if (haystack.charAt(k)==needle.charAt(i)){
                if (i==0){
                    startIndex=k;
                }
                i++;
            }else{
                i = 0;
                startIndex = -1;
            }
        }
        if(i==needle.length()){
            return startIndex;
        }else
            return -1;

    }

    /**
     * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
     * https://takeuforward.org/data-structure/longest-common-substring-dp-27/
     * @param str1
     * @param str2
     * Input : X = “GeeksforGeeks”, y = “GeeksQuiz”
     * Output : 5
     * Explanation:
     * The longest common substring is “Geeks” and is of length 5.
     *
     */
    public static void largestCommonSubString(String str1, String str2){
        String ans = "";
        int len =0;
        int start = 0;
        int end = 0;
        for (int i =0; i<str1.length(); i++){
            for (int j =0; j<str2.length(); j++){
                start = i ;
                end = j;
                boolean isCommon = true;
                while (start<=end){
                    if (str1.charAt(start)!=str2.charAt(start)){
                        isCommon = false;
                        break;
                    }else {
                        start++;
                    }
                }
                if (isCommon){
                    int length = str1.substring(i, j + 1).length();
                    if (length >len){
                        len = length;
                        ans = str1.substring(i, j+1);
                    }
                }
            }
        }
        System.out.println(ans);
    }

    public static void countWords(String str){
        String [] words = str.split("//s");
        Map<String , Integer> map = new HashMap<>();
        for (int i =0; i<words.length; i++){
            map.put(words[i], map.getOrDefault(words[i], 0)+1);
        }
        for (Map.Entry<String, Integer> set:
                map.entrySet()) {
            System.out.println("Word : " + set.getKey() + " Count : " + set.getValue());
        }
    }

    public static void largestCommonSubStringRecursive(String str1, String str2){
        int count =0;
        int m = str1.length();
        int n = str2.length();
        largestCommonSubStringRec(str1, str2, m, n, count);
    }

    private static int largestCommonSubStringRec(String str1, String str2, int m, int n, int count) {
        if (m==0 || n ==0){
            return count;
        }
        if (str1.charAt(m-1) == str2.charAt(n-1)){
            count = 1+largestCommonSubStringRec(str1, str2 , m-1, n-1, count);
        }
        count = Math.max(largestCommonSubStringRec(str1, str2, m-1, n, count) , largestCommonSubStringRec(str1, str2, m, n-1, count));
        return count;
    }

    /**
     * https://leetcode.com/problems/reverse-words-in-a-string/
     * @param str
     */
    public static void reverseWordsInAStringInItsplace(String str){
        char[] strCharArray = str.toCharArray();
        int start = -1;
        int end = -1;

        for (int i =0; i<strCharArray.length; i++){
            while (i<strCharArray.length && str.charAt(i)==' '){
                i++;
            }
            start =i;

            while (i<strCharArray.length && str.charAt(i)!=' '){
                i++;
            }
            end = i-1;

            while (start<=end){
                char temp = strCharArray[start];
                strCharArray[start] = strCharArray[end];
                strCharArray[end] = temp;
                start++;
                end--;
            }
        }
    }

    /** NEED TO CHECK THE OPTIMIZE SOLUTION.
     * https://takeuforward.org/data-structure/length-of-longest-substring-without-any-repeating-character/
     * @param str
     */
    public static void longestSubStringWithAllDistinctChar(String str){
        int len =0;
        String ans = "";
        for (int i =0; i<str.length(); i++){
            for (int j =i; j<str.length(); j++){
                if (areDistinct(str, i, j)){
                    len = Math.max(len, j-i+1);
                    ans = str.substring(i, j+1);
                }
            }
        }
        System.out.println(len);
    }

    public static boolean areDistinct(String str, int i, int j ){
        Set<Character> set = new HashSet<>();
        while (i<=j){
            if(set.contains(str.charAt(i))){
                return false;
            }
            set.add(str.charAt(i));
            i++;
        }
        return true;
    }

    /**
     * https://leetcode.com/problems/rotate-string/description/
     * @param str
     * @param str2
     * @return
     */
    public static boolean findIfRotatedBrute(String str, String str2){
        String temp = rotateByOne(str);
        temp = rotateByOne(temp);
        return temp.equals(str2);
    }
    public static String rotateByOne(String str){
        StringBuilder sb = new StringBuilder(str);
        char ch = str.charAt(0);
        for (int i =0; i<sb.length()-1; i++){
            sb.setCharAt(i, str.charAt(i+1));
        }
        sb.setCharAt(str.length()-1, ch);
        return sb.toString();
    }
    public static boolean findIfRotatedBetter(String str1, String str2){
        String temp = str1+str1;
        return (str1.length() == str2.length())
                && (temp.contains(str2));
    }

    public static String removeDup(String str){
        Set<Character> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<str.length(); i++){
            if (!set.contains(str.charAt(i))){
                sb.append(str.charAt(i));
            }
            set.add(str.charAt(i));
        }
        return sb.toString();
    }


    public static void replaceSubString_1(String str, String s1, String s2){
        String ans = "";
        for (int i =0; i<str.length(); i++){
            int k =0;
            if (str.charAt(i)==s1.charAt(k) && i+s1.length()<str.length()){
                int j = 0;
                for (j = i; j<s1.length();j++){
                    if (str.charAt(j)!=s1.charAt(k)){
                        break;
                    }else {
                        k=k+1;
                    }
                }
                if (j==i+s1.length()){
                    ans+=s2;
                    i=j-1;
                }
            }else {
                ans+=str.charAt(i);
            }
        }
    }

    public static void findNonRepeatedChar(String str){
        //print the char with freq 1
        int [] freq = new int[256];
        for (int i = 0; i<str.length(); i++){
            freq[str.charAt(i)]++;
        }
        for (int i =0; i<freq.length;i++){
            if (freq[i]==1){
                System.out.println((char)i);
            }
        }
    }

    /**
     * https://www.geeksforgeeks.org/count-of-pairs-of-strings-whose-concatenation-forms-a-palindromic-string/
     * Given an array A[ ] consisting of N strings, the task is to count the number of pairs of possible strings that on
     * merging forms a Palindromic String or can be rearranged to form a Palindromic String.
     */
    public List<List<Integer>> palindromePairs(String[] str) {
        int count =0;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i =0; i<str.length; i++){
            for (int j = i+1; j<str.length; j++){
                String concate_1 = str[i]+str[j];
                String concate_2 = str[j]+str[i];
                if (isPalindrome(concate_1)){
                    List<Integer> ds = new ArrayList<>();
                    count++;
                    ds.add(i);
                    ds.add(j);
                    ans.add(new ArrayList<>(ds));
                }
                if (isPalindrome(concate_2)){
                    List<Integer> ds = new ArrayList<>();
                    count++;
                    ds.add(j);
                    ds.add(i);
                    ans.add(new ArrayList<>(ds));
                }
            }
        }
        return ans;
    }

    static boolean isPalindrome(String str)
    {
        int len = str.length();

        // compare each character from starting
        // with its corresponding character from last
        for (int i = 0; i < len/2; i++ )
            if (str.charAt(i) != str.charAt(len-i-1))
                return false;

        return true;
    }

    /**
     * https://www.youtube.com/watch?v=Lav6St0W_pQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=10
     * @param str
     * @param k
     * @return
     */
    public static int lengthOfLongestSubStringKUniqueChar(String str, int k) {
        return 0;
    }

}
