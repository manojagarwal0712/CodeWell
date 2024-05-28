package com.mankumm.leetcode.tree;
import com.stack.com.mankumm.leetcode.tree.NodeTree;
public class Tuple {
    public NodeTree node;
    public int vertical;
    public int level;
    public Tuple(NodeTree nodeTree, int vertical, int level){
        this.node = nodeTree;
        this.vertical = vertical;
        this.level = level;
    }
}
