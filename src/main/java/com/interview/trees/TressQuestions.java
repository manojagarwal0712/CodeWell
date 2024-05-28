package com.interview.trees;

import com.stack.com.mankumm.coding.ninja.TreeNode;
import com.stack.com.mankumm.coding.ninja.binarytree.BinaryTreeNode;
import javafx.util.Pair;

import java.util.*;

public class TressQuestions {
    public static void main(String[] args) {

    }

    class Tuple{
        BinaryTreeNode<Integer> node;
        int vertical;
        int level;


        public  Tuple(BinaryTreeNode<Integer> node, int vertical, int level){
            this.node = node;
            this.vertical = vertical;
            this.level = level;
        }
    }

    public static void rightViewOfBinaryTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        System.out.print(root.data);
        if (root.right!=null){
            leftViewOfBinaryTree(root.right);
        }else {
            leftViewOfBinaryTree(root.left);
        }
    }
    public static void rightViewOfBinaryTreeRec(BinaryTreeNode<Integer> root, int level, ArrayList<Integer> ds){
        if (root==null){
            return;
        }
        if (level==ds.size()){
            ds.add(root.data);
        }
        rightViewOfBinaryTreeRec(root.right, level+1, ds);
        rightViewOfBinaryTreeRec(root.left, level-1,ds);
    }

    public static void leftViewOfBinaryTreeRec(BinaryTreeNode<Integer> root, int level, ArrayList<Integer> ds){
        if (root==null){
            return;
        }
        if (level==ds.size()){
            ds.add(root.data);
        }
        leftViewOfBinaryTreeRec(root.left, level-1, ds);
        leftViewOfBinaryTreeRec(root.right, level+1,ds);
    }

    public static void leftViewOfBinaryTree(BinaryTreeNode<Integer> root){
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Pair<BinaryTreeNode<Integer>, Integer>> queue = new LinkedList<>(); // pair with node and its level
        Map<Integer, Integer> map = new HashMap<>(); // map with level and node data
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
            Pair<BinaryTreeNode<Integer>, Integer> pair = queue.poll();
            if (!map.containsKey(pair.getValue())){
                ans.add(pair.getValue());
            }else {
                map.put(pair.getValue(), pair.getKey().data);
            }
            if (pair.getKey().left!=null){
                queue.add(new Pair<>(pair.getKey().left, pair.getValue()-1));
            }
            if (pair.getKey().right!=null){
                queue.add(new Pair<>(pair.getKey().right, pair.getValue()+1));
            }
        }
    }
    /**
     * https://takeuforward.org/data-structure/bottom-view-of-a-binary-tree/
     * @param root
     */

    public static void bottomViewOfBinaryTree(BinaryTreeNode<Integer> root){
        Queue<Pair<BinaryTreeNode<Integer>, Integer>> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
             Pair<BinaryTreeNode<Integer>, Integer> pair = queue.poll();
             map.put(pair.getKey().data, pair.getValue());
             if (pair.getKey().left!=null){
                 queue.add(new Pair<>(pair.getKey().left, pair.getValue()-1));
             }
            if (pair.getKey().right!=null){
                queue.add(new Pair<>(pair.getKey().right, pair.getValue()+1));
            }
        }
    }

    /**
     * https://takeuforward.org/data-structure/top-view-of-a-binary-tree/
     * @param root
     */

    public static void topViewOfBinaryTree(BinaryTreeNode<Integer> root){
        Queue<Pair<BinaryTreeNode<Integer>, Integer>> queue = new LinkedList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()){
            Pair<BinaryTreeNode<Integer>, Integer> pair = queue.poll();
            if (!map.containsKey(pair.getKey())){
                map.put(pair.getKey().data, pair.getValue());
            }
            if (pair.getKey().left!=null){
                queue.add(new Pair<>(pair.getKey().left, pair.getValue()-1));
            }
            if (pair.getKey().right!=null){
                queue.add(new Pair<>(pair.getKey().right, pair.getValue()+1));
            }
        }
    }

    /**
     * vertical order traversal
     * https://takeuforward.org/data-structure/vertical-order-traversal-of-binary-tree/
     */
    public void verticalOrderTraversal(BinaryTreeNode<Integer> root){
        Queue<Tuple> queue  = new PriorityQueue<>();
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        queue.add(new Tuple(root, 0, 0));
        while (!queue.isEmpty()){
            Tuple tuple = queue.poll();
            if (map.containsKey(tuple.vertical)){
                    TreeMap<Integer, PriorityQueue<Integer>> treeMap = map.get(tuple.vertical);
                    if (treeMap.containsKey(tuple.level)){
                        PriorityQueue<Integer> pq = treeMap.get(tuple.level);
                        pq.add(tuple.node.data);
                        map.put(tuple.vertical, treeMap);
                    }
            }else {
                TreeMap<Integer, PriorityQueue<Integer>> treeMap = new TreeMap<>();
                treeMap.put(tuple.level, new PriorityQueue<>(tuple.node.data));
                map.put(tuple.vertical, treeMap);
            }
            if (tuple.node.left!=null){
                queue.add(new Tuple(tuple.node.left, tuple.vertical-1, tuple.level+1));
            }
            if (tuple.node.right!=null){
                queue.add(new Tuple(tuple.node.right, tuple.vertical+1, tuple.level+1));
            }
        }
    }

    /**
     * https://takeuforward.org/data-structure/boundary-traversal-of-a-binary-tree/
     */
    public static List<Integer> boundaryTraversal(BinaryTreeNode<Integer> root){
        List<Integer> ans = new ArrayList<>();
        if (!isLeaf(root)){
            ans.add(root.data);
        }
        printLeftTree(root.left, ans);
        System.out.println();
        printLeaves(root);
        System.out.println();
        printRightTreeInReverse(root, ans);
        return ans;
    }
    public static boolean isLeaf(BinaryTreeNode<Integer> root){
        if (root.right==null && root.left==null){
            return true;
        }
        return false;
    }

    public static void printRightTreeInReverse(BinaryTreeNode<Integer> root, List<Integer> ans) {
        BinaryTreeNode<Integer> curr = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while (curr!=null){
            if (!isLeaf(curr)){
                temp.add(root.data);
            }
            if (curr.right!=null){
                curr= curr.right;
            }else {
                curr=curr.left;
            }
        }
        for (int i = temp.size()-1; i>=0; i--){
            ans.add(temp.get(i));
        }
    }

    public static void printLeaves(BinaryTreeNode<Integer> root) {
        if (root==null){
            return;
        }
        if (isLeaf(root)){
            System.out.print(root.data);
        }
        printLeaves(root.left);
        printLeaves(root.right);
    }

    public static void printLeftTree(BinaryTreeNode<Integer> root, List<Integer> ans) {
        BinaryTreeNode<Integer> cur = root.left;
        while (cur!=null){
            if (!isLeaf(cur)){
                ans.add(cur.data);
            }
            if (cur.left!=null){
                cur=cur.left;
            }else {
                cur = cur.right;
            }
        }
    }

    /**
     * https://takeuforward.org/data-structure/zig-zag-traversal-of-binary-tree/
     */
    public static void zigZagTraversal(BinaryTreeNode<Integer> root){
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int numLevel = queue.size();
            List<Integer> subList = new ArrayList<>(numLevel);
            for (int i =0; i<numLevel; i++){
                BinaryTreeNode<Integer> tempNode = queue.poll();
                // Enqueue left child
                if (tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                // Enqueue right child
                if (tempNode.right != null) {
                    queue.add(tempNode.right);
                }
                if (flag){
                    subList.add(tempNode.data);
                }else {
                    subList.add(0, tempNode.data);
                }
            }
            flag = !flag;
        }
    }

    public static void levelOrderTraversal(BinaryTreeNode<Integer> root){
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            // poll() removes the present head.
            BinaryTreeNode<Integer> tempNode = queue.poll();
            System.out.println(tempNode.data);
            // Enqueue left child
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            // Enqueue right child
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    /**
     *https://takeuforward.org/data-structure/check-if-two-trees-are-identical/
     */
    public static boolean isIdentical(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2){
        if (root1==null && root2==null){
            return true;
        }else if (root1==null && root2!=null){
            return false;
        }else if (root1!=null && root2==null){
            return false;
        }

        if (!Objects.equals(root1.data, root2.data)){
            return false;
        }
        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }
    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/description/
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(BinaryTreeNode<Integer> root) {
        int[] diameter = new int[1];
        height(root, diameter);
        return diameter[0];
    }

    private int height(BinaryTreeNode<Integer> node, int[] diameter) {
        if (node == null) {
            return 0;
        }
        int lh = height(node.left, diameter);
        int rh = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }


    /**
     * https://leetcode.com/problems/maximum-depth-of-binary-tree/
     * https://takeuforward.org/strivers-a2z-dsa-course/strivers-a2z-dsa-course-sheet-2/
     * @param root
     * @return
     */
    public static int heightOfBinaryTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        return 1+Math.max(heightOfBinaryTree(root.left),heightOfBinaryTree(root.right));
    }

    /**
     * https://leetcode.com/problems/balanced-binary-tree/
     * https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/
     * @param root
     * @return
     */
    public static int isBalanced(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        int l  = heightOfBinaryTree(root.left);
        int r = heightOfBinaryTree(root.right);
        if (l==1 || r ==-1){
            return -1;
        }
        if (Math.abs(l-r)>1){
            return -1;
        }
        return 1+Math.max(l, r);
    }
    public static boolean isBalancedTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return true;
        }
        int l  = leftHeight(root.left);
        int r = rightHeight(root.right);
        if (Math.abs(l-r)>1){
            return false;
        }
        boolean left = isBalancedTree(root.left);
        boolean right = isBalancedTree(root.right);
        if (left || !right){
            return false;
        }
        return true;
    }

    public static int leftHeight(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        return 1+leftHeight(root.left);
    }
    public static int rightHeight(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        return 1+rightHeight(root.right);
    }
}
