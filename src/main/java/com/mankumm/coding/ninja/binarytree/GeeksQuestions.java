package com.stack.com.mankumm.coding.ninja.binarytree;

public class GeeksQuestions {

    public static int height(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return 1+ Math.max(lh, rh);
    }

    /**
     * https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
     * @param a
     * @param b
     * @return
     */
    boolean isIdentical(BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b)
    {
        /*1. both empty */
        if (a == null && b == null)
            return true;

        /* 2. both non-empty -> compare them */
        if (a != null && b != null)
            return (a.data == b.data
                    && isIdentical(a.left, b.left)
                    && isIdentical(a.right, b.right));

        /* 3. one empty, one not -> false */
        return false;
    }

    /**
     * https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
     */
    public static BinaryTreeNode<Integer> convertToMirror(BinaryTreeNode<Integer> root){
        if (root==null){
            return root;
        }
        BinaryTreeNode<Integer> left = convertToMirror(root.left);
        BinaryTreeNode<Integer> right = convertToMirror(root.right);
        BinaryTreeNode<Integer> temp = left;
        left = right;
        right = temp;
        return root;
    }

}
