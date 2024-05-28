package com.mankumm.leetcode.tree;

import java.util.*;
import com.stack.com.mankumm.leetcode.tree.NodeTree;
public class TreeSeries {
    public static void main(String[] args) {

    }

    /**
     * Inorder traversal - left , root, right
     * @param node
     */
    public void inOrderTraversal(NodeTree node){
        if (node.left.left == null && node.right==null){
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.data);
        inOrderTraversal(node.right);
    }

    /**
     *
     * @param node | root, left , right
     */
    public void preOrderTraversal(NodeTree node){
        if (node==null){
            return;
        }
        System.out.println(node.data);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    /**
     *
     * @param node | root, left , right
     */
    public void postOrderTraversal(NodeTree node){
        if (node==null){
            return;
        }
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
        System.out.println(node.data);
    }

    /**
     *https://www.youtube.com/watch?v=EoAsWbO7sqg&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=9
     * @param node
     */
    public List<List<Integer>> levelOrderTraversal(NodeTree node){
        Queue<NodeTree> queue = new LinkedList<NodeTree>();
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (node==null){
            return ans;
        }
        queue.offer(node); // add the first node(root) to the queue.
        while (!queue.isEmpty()){
            int levelNum = queue.size(); // find the number of nodes in the queue.
            List<Integer> subList = new LinkedList<>(); // take a sublist where you will add all the nodes data value in it.
            for (int i =0; i<levelNum; i++){ // This will run only 1 time for the root. next time it will run as many number time as the number of nodes inserted.
                if (queue.peek().left!=null){ // just peek on the queue and check if there is left of the node exisit in the three.
                    queue.offer(queue.peek().left); // if so, then add that left to the queue.
                }
                if (queue.peek().right!=null){
                    queue.offer(queue.peek().right); //check if the right of the node exist and then add it to the queue.
                }
                subList.add(queue.poll().data); // after traverrsing the nodes at a specific level
            }
            ans.add(subList); // add the sub list to ans list that we will return
        }
        return ans;
    }

    /**
     *
     * @param node
     * @return
     */
    public static List<Integer> iterativePreOrderTraversal(NodeTree node){
        List<Integer> preOrder = new LinkedList<>(); // list to keeo the elements traversed
        Stack<NodeTree> stack = new Stack<>(); //stack to push the tree nodes.
        if (node==null){ // if node ==null then return preOrder List.
            return preOrder;
        }
        stack.push(node); // push the first nodes to stack/
        while (!stack.isEmpty()){ // iterate till stack is empty.
            NodeTree temp = stack.pop(); // popup the first element
            preOrder.add(temp.data); // add it to the preorder list.
            if (temp.right!=null){ // verify the popped element have left and right , if so push them to stack. next time you wil again popup last element mean left that you just pushed and repeate.
                stack.push(temp.right);
            }
            if (temp.left!=null){
                stack.push(temp.left);
            }
        }
        return preOrder; // return the list finally.
    }

    public static List<Integer> iterativeInOrderTraversal(NodeTree node){
        List<Integer> inOrder = new LinkedList<>(); // list to keep the elements traversed
        Stack<NodeTree> stack = new Stack<>(); //stack to push the tree nodes.
        if (node==null){ // if node ==null then return preOrder List.
            return inOrder;
        }
        NodeTree temp = node;
        while (true){
            if (node!=null){
                stack.push(node.left);
            }else {
                if (stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                inOrder.add(node.data);
                node = node.right;
            }
        }
        return inOrder; // return the list finally.
    }

    /**
     * https://www.youtube.com/watch?v=2YBhNLodD8Q&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=12
     * @param node
     * @return
     */
    public static List<Integer> iterativePostOrderTraversal(NodeTree node){
        List<Integer> postOrder = new LinkedList<>(); // list to keep the elements traversed
        Stack<NodeTree> stack1 = new Stack<>(); //stack to push the tree node
        Stack<NodeTree> stack2 = new Stack<>(); //stack to push the tree nodes when we pop from the stack1
        if (node==null){
            return postOrder;
        }
        stack1.push(node); // push the first node
        while (!stack1.isEmpty()){ // iterate while the st1 is not empty.
            NodeTree temp = stack1.pop(); // pop up the element.
            if (temp.left!=null){  // while there exist an left push to st1.
                stack1.push(temp.left);
            }
            if (temp.right!=null){ // while there exisit a right push to st1.
                stack1.push(temp.right);
            }
            stack2.push(temp); // push the node to st2.
        }
        while (!stack2.isEmpty()){ // push all the st2 element to list and return.
            postOrder.add(stack2.pop().data);
        }
        return  postOrder;
    }

    /**
     * Mac depth of a binary tree
     * @param node
     * @return
     */
    public static int maxDepthOrHeightOfTree(NodeTree node){
        if (node==null){
            return 0;
        }
        int left = maxDepthOrHeightOfTree(node.left);
        int right = maxDepthOrHeightOfTree(node.right);
        return 1+ Math.max(left, right);
    }

    /**
     * If left tree height minus right tree height is <=1 then its balanced tree.
     * @param node
     * @return
     */
    public static boolean isBalanceTree(NodeTree node){
        if (node==null){
            return true;
        }
        int left = maxDepthOrHeightOfTree(node.left);
        int right = maxDepthOrHeightOfTree(node.right);
        return (left - right)<=0;
    }

    /**
     * Zig Zag traversal, its same as level order traversal , its just that we maintaine a flag to understand if the level we are in ,
     * we have to add the poppped element in reverse order or not.
     * @param node
     * @return
     */
    public static List<List<Integer>> zigZagTraversal(NodeTree node){
        Queue<NodeTree> queue = new LinkedList<NodeTree>();
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        int flag = 0;
        queue.offer(node);
        while (!queue.isEmpty()){
            int levelNum = queue.size();
            NodeTree temp = queue.poll();
            List<Integer> list1 = new LinkedList<>();
            for (int i =0; i<levelNum; i++){
                list1.add(temp.data);
            }
            if (flag!=0){
                //list1.reverse();
                flag =0;
            }else {
                flag = 1;
            }
            list.add(list1);
            if (queue.peek().left!=null){
                queue.add(node.left);
            }
            if (queue.peek().right!=null){
                queue.add(node.right);
            }
        }
        return list;
    }

    /**
     * 1. Left boundry exclding leaf
     * 2. lead nodes
     * 3. Right boundry in the reverse direction excluding leaf.
     * https://www.youtube.com/watch?v=0ca1nvR0be4&list=PLgUwDviBIf0q8Hkd7bK2Bpryj2xVJk8Vk&index=21
     * @param nodeTree
     */
    public static ArrayList<Integer> boundryTraversal(NodeTree nodeTree){
        ArrayList<Integer> ans = new ArrayList<>();
        if(!isLeafNode(nodeTree)){
            ans.add(nodeTree.data);
        }
        addLeftBoundary(nodeTree, ans);
        addleaves(nodeTree, ans);
        addRightBoundry(nodeTree, ans);
        return ans;
    }
    public static void addRightBoundry(NodeTree nodeTree, ArrayList<Integer> ans){
        NodeTree cur = nodeTree;
        while (cur!=null){
            if (!isLeafNode(cur)){
                ans.add(cur.data);
            }
            if (cur.right!=null){
                cur = cur.right;
            }else {
                cur = cur.left;
            }
        }
    }

    public static void addleaves(NodeTree nodeTree, ArrayList<Integer> ans){
        if (isLeafNode(nodeTree)){
            ans.add(nodeTree.data);
        }
        if (nodeTree.left!=null){
            addleaves(nodeTree.left, ans);
        }else if (nodeTree.right!=null){
            addleaves(nodeTree.left, ans);
        }
    }
    public static void addLeftBoundary(NodeTree nodeTree, ArrayList<Integer> ans){
        ArrayList<Integer> temp = new ArrayList<>();
        NodeTree cur = nodeTree.left;
        while (cur!=null){
            if (!isLeafNode(cur)){
                temp.add(cur.data);
            }
            if (cur.left!=null){
                cur = cur.left;
            }else {
                cur = cur.right;
            }
        }
        for (int i = temp.size()-1; i >=0 ; i++) {
            ans.add(temp.get(i));
        }
    }
    public static boolean isLeafNode(NodeTree nodeTree){
        return (nodeTree.right==null || nodeTree.left==null);

    }

    public static List<List<Integer>> verticalTraversal1(NodeTree nodeTree){
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        // TreeMap<vertical, TreeMap<level, [list of nodes in sorted form]>>
        Queue<Tuple> queue = new LinkedList<>(); // queue of tupple contiaing {node, vertical , level}
        queue.offer(new Tuple(nodeTree, 0, 0)); // add the root node which will initially have vertical 0 and level 0
        while (!queue.isEmpty()){ // check untill queue is empty , its just level order traversal.
            Tuple tuple = queue.poll(); // take out the tupple on the top of queue.
            int vertical = tuple.vertical; // vertical
            int level = tuple.level; //level
            NodeTree temp = tuple.node; // node in tupple.
            if (!map.containsKey(vertical)){ // check if the vertical already exisit in map
                map.put(vertical, new TreeMap<>()); // if not ,then add that vertical in the map with empty treemap
            }
            if (!map.get(vertical).containsKey(level)){ // check if the level exist already in the tree map which contains level and nodes.
                map.get(vertical).put(level, new PriorityQueue<>()); // add that level and empty PriortyQueue.
            }
            map.get(vertical).get(level).offer(temp.data); // finally add that level to the priorty queue.
            if (nodeTree.left!=null){ // check if the left exist.
                queue.offer(new Tuple(nodeTree.left, vertical-1, level+1));
            }
            if (nodeTree.right!=null){
                queue.offer(new Tuple(nodeTree.right, vertical+1, level+1));
            }
        }
        //Now the tree map will have all the vertical -> {level -> nodes at different level} , We wanted to print all these nodes in sorted with level
        List<List<Integer>> ans = new ArrayList<>(); // store all the verticals list.
        for (TreeMap<Integer, PriorityQueue<Integer>> nodeListByLevel : map.values()) { // get all list of nodes at vertical X -> {level -> nodes}
            ans.add(new ArrayList<>()); // add an empty list
            for (PriorityQueue<Integer> node: nodeListByLevel.values()) { // read all the nodes at the levels
                while (!node.isEmpty()){ // is nodes queue is not empty
                    ans.get(ans.size()-1).add(node.poll()); // then add all the nodes in the list.
                }
            }
        }
        return ans;
    }
    class Pair{
        NodeTree node;
        int vertical;
        public Pair(NodeTree node, int vertical){
            this.node = node;
            this.vertical = vertical;
        }
    }

    public List<Integer> topViewOfBinaryTree(NodeTree nodeTree){
        Queue<Pair> queue = new LinkedList<>(); // Queue which will have the Pair {vertical, node}
        Map<Integer, Integer> map = new TreeMap<>(); // Map which will have {vertical , node data} in sorted form thats why Tree Map.
        queue.offer(new Pair(nodeTree, 0)); // add the first node at vertical 0
        while (!queue.isEmpty()){ // check till the queue is empty.
            Pair pair = queue.poll(); // take out the first pair from the queue.
            NodeTree temp = pair.node; // node
            int vertical = pair.vertical; // vertical
            if (!map.containsKey(vertical)){ // check if the map already contains this vertical
                map.put(vertical, temp.data); // if not then keep the node value else skip as we need to keep only the first value of the vertical.
            }
            if (temp.left!=null){ // check if the tree left exisit.
                queue.offer(new Pair(temp.left,vertical-1)); // if yes, then move to the left and thus the vertical will be -1
            }
            if (temp.right!=null){ // check if the right exists
                queue.offer(new Pair(temp.right,vertical+1)); // if yes, then move to right and thus the vertical will +1.
            }
        }
        // finally after this loop the mpa will have first value vertical
        List<Integer> ans = new ArrayList<>();  // array list to add the value of nodes.
        for (Map.Entry<Integer, Integer> ys: map.entrySet()) { // loop through map.
            ans.add(ys.getValue()); // add the node value in this case it will take
        }
        return ans; // return ans.
    }

    public List<Integer> bottomViewOfBinaryTree(NodeTree nodeTree){
        Queue<Pair> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        queue.offer(new Pair(nodeTree, 0));
        while (!queue.isEmpty()){
            Pair pair = queue.poll();
            NodeTree temp = pair.node;
            int vertical = pair.vertical;
            map.put(vertical, temp.data);
            if (temp.left !=null){
                queue.offer(new Pair(temp.left, vertical-1));
            }
            if (temp.right !=null){
                queue.offer(new Pair(temp.right, vertical+1));
            }
        }
        // finally after this loop the mpa will have first value vertical
        List<Integer> ans = new ArrayList<>();  // array list to add the value of nodes.
        for (Map.Entry<Integer, Integer> ys: map.entrySet()) { // loop through map.
            ans.add(ys.getValue()); // add the node value in this case it will take
        }
        return ans; // return ans.
    }
}
