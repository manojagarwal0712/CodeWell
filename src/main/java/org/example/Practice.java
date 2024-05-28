package org.example;


import java.util.*;

public class Practice {

    public static void main(String[] args) {
        //System.out.println(reverseInt1(-123));
        int nums[] = {9,9,9};
        int k = 3;

        String strs[] = {"flower","flow","flight"};
        //longestSubseqWithK("geeksforgeeks", 2);
        longestCommonSubstrBetter("flower", "flow");

    }
    public static void longestCommonSubstrBetter(String str1, String str2){
        longestCommonSubstrBetterRec(str1, str2, str1.length(), str2.length());
    }
    private static int longestCommonSubstrBetterRec(String str1, String str2, int m, int n){
        if (m==0 || n==0){
            return 0;
        }
        if (str1.charAt(m-1) == str2.charAt(n-1)){
            return 1+ longestCommonSubstrBetterRec(str1, str2,m-1, n-1);
        }
        return Math.max(longestCommonSubstrBetterRec(str1, str2, m-1, n), longestCommonSubstrBetterRec(str1, str2, m, n-1));
    }

    private static void longestCommonSubstrBrute1(String str1, String str2) {
        int j =0;
        String currentComm = "";
        String ans = "";
        for (int i =0; i<str1.length() && j<str2.length(); i++){
            if (str1.charAt(i)==str2.charAt(j)){
                currentComm+=str1.charAt(i);
                j++;
            }else {
                if (ans.length()<currentComm.length()){
                    ans= currentComm;
                    currentComm="";
                    j=0;
                }
            }
        }
        if (currentComm.length()>ans.length()){
            ans=currentComm;
        }
        System.out.println(ans +"_"+ ans.length());
    }


    public static int findString(String str1, String str2){
        int i =0;
        int j=0;
        int startIndex = -1;
        while (i<str1.length() && j<str2.length()){
            if (str1.charAt(i)==str2.charAt(j) && startIndex==-1){
                i++;
                j++;
                startIndex=i;
            }else {
                i++;
                j=0;
                startIndex=-1;
            }
        }
        return startIndex;
    }


    public static void permutationOfString11(String str){
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



    public static void permutationOfString(String str){
        List<List<Character>> ans = new ArrayList<>();
        List<Character> ds = new ArrayList<>();
        boolean [] visited = new boolean[str.length()];
        permutationOfStringSol1(str, ans, ds,  visited);
        System.out.println(ans);
    }

    private static void permutationOfStringSol1(String str, List<List<Character>> ans, List<Character> ds,  boolean []visted) {
        if (ds.size()==str.length()){
            ans.add(new ArrayList<>(ds));
            return;
        }
        for (int i =0; i<str.length(); i++){
            if (!visted[i]){
                visted[i] = true;
                ds.add(str.charAt(i));
                permutationOfStringSol1(str, ans, ds, visted);
                visted[i] = false;
                ds.remove(ds.size()-1);
            }

        }
    }
}
