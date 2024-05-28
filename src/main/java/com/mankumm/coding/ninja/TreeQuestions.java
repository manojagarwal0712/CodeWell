package com.mankumm.coding.ninja;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import com.stack.com.mankumm.coding.ninja.TreeNode;
public class TreeQuestions {

    public static void main(String[] args) {
        /*TreeNode<Integer> root = new TreeNode<Integer>(4);
        TreeNode<Integer> node1 = new TreeNode<Integer>(2);
        TreeNode<Integer> node2 = new TreeNode<Integer>(3);
        TreeNode<Integer> node3 = new TreeNode<Integer>(5);
        TreeNode<Integer> node4 = new TreeNode<Integer>(6);
        TreeNode<Integer> node5 = new TreeNode<Integer>(8);
        root.children.add(node1);
        root.children.add(node2);
        root.children.add(node3);
        root.children.add(node4);
        System.out.println(root);*/
    }
    /*public static void printLevelWise(com.stack.com.mankumm.coding.ninja.TreeNode<Integer> root){
        *//* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Print output and don't return it.
         * Taking input is handled automatically.
         *//*
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                System.out.print(node.data+ " ");
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
            System.out.println();

        }
    }
    public static int countNodes(TreeNode<Integer> root){
        int count =1;
        if (root==null){
            return 0;
        }
        if (root.children==null || root.children.size()==0){
            return count;
        }
        for (int i =0; i<root.children.size(); i++){
            return 1+countNodes(root.children.get(i));
        }
        return count;
    }

    public static int sumOfAllNode(TreeNode<Integer> root){

        int sum =0;
        if (root==null){
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                sum= sum+node.data;
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
            //System.out.println();

        }
        return sum;
    }

    public static int numNodeGreater(TreeNode<Integer> root,int x){

        int count =0;
        if (root==null){
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                if (node.data>x){
                    count++;
                }
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
        }
        return count;
    }

    public static int getHeight(TreeNode<Integer> root){
        *//* Your class should be named Solution
         * Don't write main().
         * Don't read input, it is passed as function argument.
         * Return output and don't print it.
         * Taking input and printing output is handled automatically.
         *//*

        int count =0;
        if (root==null){
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
            count++;
        }
        return count;

    }

    public static int countLeafNodes(TreeNode<Integer> root){
        int count =0;
        if (root==null){
            return 0;
        }
        if (root.children.size()==0){
            return 1;
        }
        for (int i =0; i<root.children.size(); i++){
            count = count+countLeafNodes(root.children.get(i));
        }
        return count;
    }

    public static void printPostOrder(TreeNode<Integer> root){
        *//* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         *//*
        if (root==null){
            return;
        }
        for (TreeNode<Integer> ch:
                root.children) {
            printPostOrder(ch);
        }
        System.out.print(root.data+" ");
    }

    public static void printPreOrder(TreeNode<Integer> root){
        *//* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         *//*
        if (root==null){
            return;
        }
        System.out.print(root.data+" ");
        for (TreeNode<Integer> ch:
                root.children) {
            printPreOrder(ch);
        }

    }

    public static TreeNode<Integer> findNextLargerNode(TreeNode<Integer> root, int n){

        *//* Your class should be named Solution.
         * Don't write main() function.
         * Don't read input, it is passed as function argument.
         * Print output as specified in the question
         *//*
        TreeNode<Integer> ans = null;
        if (root==null){
            return null;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                if (node.data>n){
                    if (ans==null){
                        ans = node;
                    }
                    if (node.data<ans.data){
                        ans = node;
                    }
                }
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
        }
        return ans;
    }

    public static void replaceWithDepthValue(TreeNode<Integer> root){

        int level =0;
        if (root==null){
            return;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        root.data = level;
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCountLevel = queue.size();
            level++;
            while (nodeCountLevel>0){
                TreeNode<Integer> node = queue.poll();
                node.data = level;
                nodeCountLevel--;
                ArrayList<TreeNode<Integer>> arrayList = node.children;
                queue.addAll(arrayList);
            }
        }
    }

    public static TreeNode<Integer> findSecondLargest(TreeNode<Integer> root){
        TreeNode<Integer> largest = new TreeNode<>(Integer.MIN_VALUE);
        TreeNode<Integer> secondLargest = new TreeNode<>(Integer.MIN_VALUE);;
        if (root==null){
            return null;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode<Integer> node = queue.poll();
            queue.addAll(node.children);
            if (node.data>largest.data){
                secondLargest = largest;
                largest = node;
            }else {
                if (node.data>= secondLargest.data && node.data!=largest.data){
                    secondLargest = node;
                }
            }
        }
        return secondLargest;
    }



*/


}
