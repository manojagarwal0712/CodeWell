package com.stack.com.mankumm.coding.ninja.binarytree;

import com.stack.com.mankumm.coding.ninja.ListNode;
import com.stack.com.mankumm.coding.ninja.ListQuestions;
import com.stack.com.mankumm.coding.ninja.Node;
import com.stack.com.mankumm.coding.ninja.TreeNode;

import java.util.*;

public class BinaryTreeUsages {

    public static void printLevelWise(BinaryTreeNode<Integer> root) {
        if (root==null){
            return;
        }
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int nodeCountLevel = queue.size();
            while (nodeCountLevel > 0) {
                BinaryTreeNode<Integer> node = queue.poll();
                System.out.print(node.data + ":");
                if (node.left != null) {
                    queue.add(node.left);
                    System.out.print("L:" + node.left.data + ",");
                } else {
                    System.out.print("L:" + -1 + ",");
                }
                if (node.right != null) {
                    queue.add(node.right);
                    System.out.print("R:" + node.right.data + "");
                } else {
                    System.out.print("R:" + -1 + "");
                }
                System.out.println();
                nodeCountLevel--;
            }
        }
    }

    public static int countNodes(BinaryTreeNode<Integer> root){
        int countL =0;
        int countR = 0;
        if (root==null){
            return 0;
        }
        countL = countNodes(root.left);
        countR = countNodes(root.right);
        return countR+countL+1;
    }
    public static boolean isNodePresent(BinaryTreeNode<Integer> root, int x) {
        //Your code goes here
        if (root==null){
            return false;
        }
        if (root.data==x){
            return true;
        }
        if (root.left!=null){
            if (root.left.data==x){
                return true;
            }
        }
        if (root.right!=null){
            if (root.right.data==x){
                return true;
            }
        }
        return isNodePresent(root.left, x) || isNodePresent(root.right, x);
    }

    public static int height(BinaryTreeNode<Integer> root) {
        //Your code goes here
        if (root==null){
            return 0;
        }
        int lhight = height(root.left);
        int rHeight = height(root.right);
        return 1+ Math.max(lhight, rHeight);
    }

    public static BinaryTreeNode<Integer> mirrorBinaryTree(BinaryTreeNode<Integer> root){
        if (root==null){
            return null;
        }
        BinaryTreeNode<Integer> left = mirrorBinaryTree(root.left);
        BinaryTreeNode<Integer> right = mirrorBinaryTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    public static int findDiameter(BinaryTreeNode<Integer> root){
        if (root==null){
            return 0;
        }
        int opt1 = height(root);
        int opt2 = findDiameter(root.left);
        int opt3 = findDiameter(root.right);
        return Math.max(Math.max(opt1, opt2), opt3);
    }

    public static void preOrder(BinaryTreeNode<Integer> root) {
        if (root==null){
            return;
        }
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(BinaryTreeNode<Integer> root) {
        if (root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }

    public static BinaryTreeNode<Integer> buildTree(int[] preOrder, int[] inOrder) {
        //Your code goes here
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i =0; i<inOrder.length; i++){
            inMap.put(inOrder[i], i);
        }
        int preOrderStartIndex = 0;
        return solve(preOrder, inOrder, 0,preOrder.length-1, 0,inOrder.length-1, inMap);
    }

    public static BinaryTreeNode<Integer> solve(int[] preOrder, int[] inOrder, int preS, int preE, int inS, int inE, Map<Integer, Integer> inMap) {
        if (inS>inE){
            return null;
        }
        int rootData = preOrder[preS];
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        /*int rootIndex = inMap.get(rootData);
        int lInS = inS;
        int lInE = rootIndex-1;
        int rPreS = lPreE+1;
        int rPreE = preE;

        int lPreS = preS+1;
        int lPreE =  ;
        int rInS = rootIndex+1;
        int rInE = inE;

        root.left = solve(preOrder, inOrder, preS+1, leftPreE, inS, rootIndex-1, inMap);
        root.right = solve(preOrder, inOrder, rPreS, preE, rootIndex+1, inE, inMap);*/
       return root;
    }

    class LinkedListNode<T>
	 {
	 		T data;
	  		LinkedListNode<T> next;
	  		public LinkedListNode(T data)
            {
                  		this.data = data;
	   	}

           public LinkedListNode<Integer> addNodes(LinkedListNode<Integer> head, int data){
               LinkedListNode<Integer> listNode = new LinkedListNode<>(data);
               LinkedListNode<Integer> temp = head;
               if (head==null){
                      listNode.next = head;
                      head = listNode;
                      return head;
                }
               if (head.next==null){
                   head.next = listNode;
                   return head;
               }
                  while (temp.next!=null){
                      temp=temp.next;
                  }
                  temp.next = listNode;
                  return head;
           }
	   }

    public ArrayList<LinkedListNode<Integer>> constructLinkedListForEachLevel(BinaryTreeNode<Integer> root){
        // Write your code here
        ArrayList<LinkedListNode<Integer>> ans = new ArrayList<>();
        if (root==null){
            return null;
        }
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int nodeCount = queue.size();
            LinkedListNode<Integer> list =null;
            while (nodeCount>0){
                BinaryTreeNode<Integer> node  = queue.poll();
                if (list==null){
                    list = new LinkedListNode<>(node.data);
                }else {
                    list.addNodes(list, node.data);
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
                nodeCount--;
            }
            ans.add(list);
        }
        return ans;
    }




    ArrayList<Integer> ans = new ArrayList<>();
    //Boundary Traversal
    public  void solve(BinaryTreeNode<Integer> root){
        if (root==null){
            return;
        }
        if (! isLeaf(root)){
            ans.add(root.data);
        }
        addLeftBoundry(root);
        addLeafs(root);
        addRightBoundry(root);
        for (Integer a:
             ans) {
            System.out.print(a+" ");
        }
    }

    public void addLeafs(BinaryTreeNode<Integer> root) {
        if (isLeaf(root)){
            ans.add(root.data);
            return;
        }
        if (root.left!=null){
            addLeafs(root.left);
        }
        if (root.right!=null){
            addLeafs(root.right);
        }
    }

    public void addRightBoundry(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        BinaryTreeNode<Integer> cur = root.right;
        while (cur!=null){
            if (! isLeaf(cur)){
                arrayList.add(cur.data);
            }
            if (cur.right!=null){
                arrayList.add(cur.data);
            }else {
                cur = cur.left;
            }
        }
        for (int i = arrayList.size()-1; i>=0; i--){
            ans.add(arrayList.get(i));
        }
    }

    public void addLeftBoundry(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer> cur = root.left;
        while (cur!=null){
            if (!isLeaf(cur)){
                ans.add(cur.data);
            }
            if (cur.left!=null){
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
    }

    public  boolean isLeaf(BinaryTreeNode<Integer> root) {
        if (root.left==null && root.right==null){
            return true;
        }
        return false;
    }

}
