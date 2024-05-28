package com.stack.com.mankumm.coding.ninja;

import java.util.ArrayList;

public class TreeNode<T> {
    T data;
    ArrayList<TreeNode<Integer>> children;
    public TreeNode(T data){
        this.data = data;
        children = new ArrayList<TreeNode<Integer>>();
    }
}
