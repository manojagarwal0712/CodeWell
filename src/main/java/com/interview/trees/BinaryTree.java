package com.interview.trees;


import java.util.*;
import java.util.LinkedList;

public class BinaryTree {
    TreeNode root;
    TreeNode root1;

    BinaryTree(int key){
        root = new TreeNode(key);
    }

    BinaryTree(){
        root = null;
    }


    public static void insertInTree(int data, TreeNode root){
        Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            root = queue.peek();
            queue.remove();
            if(root.left ==null){
                root.left = new TreeNode(data);
                break;
            }else{
                queue.add(root.left);
            }

            if(root.right == null){
                root.right = new TreeNode(data);
                break;
            }else{
                queue.add(root.right);
            }
        }

    }

    public static void inOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.data);
        inOrderTraversal(root.right);
    }

    public static void preOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void postOrderTraversal(TreeNode root){
        if(root==null){
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.data);
    }

    /**
     * Given a binary tree, delete a node from it by making sure that tree shrinks from the bottom
     * (i.e. the deleted node is replaced by bottom most and rightmost node)
     * @param data
     * https://www.geeksforgeeks.org/deletion-binary-tree/
     */
    public static TreeNode deleteInBinaryTree(int data, TreeNode root) {
        return null;
    }

    class TreeNodeWithDistance{
        TreeNode node;
        int dis;

        TreeNodeWithDistance(TreeNode node, int dis){
            this.dis = dis;
            this.node = node;
        }
    }
    /**
     * rules:
     * 1) for root
     *      hd = 0
     * 2) for left child
     * hd = hd (parent) -1
     * 3) for right child
     * hd = hd(parent)+1
     *
     * @param root
     */
    public void verticalOrderTraversal(TreeNode root){
        Queue<TreeNodeWithDistance> queue = new java.util.LinkedList<>();
        TreeNodeWithDistance obj = new TreeNodeWithDistance(root,0);
        queue.add(obj);
        TreeNodeWithDistance temp =null;
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer,ArrayList<Integer>>();
        while (!queue.isEmpty()){
            temp = queue.remove();
            if(temp!=null && temp.node.left!=null){

                queue.add(new TreeNodeWithDistance(temp.node.left,temp.dis-1));
            }
            if(temp!=null && temp.node.right!=null){

                queue.add(new TreeNodeWithDistance(temp.node.right, temp.dis+1));
            }
            if(hashMap.containsKey(temp.dis)){
                ArrayList arrayList = new ArrayList();
                arrayList.add(root.data);
                hashMap.put(temp.dis,hashMap.get(temp.dis)).add(temp.node.data);
            }else{
                ArrayList arrayList = new ArrayList();
                arrayList.add(temp.node.data);
                hashMap.put(temp.dis,arrayList);
            }
        }
        System.out.println(hashMap);

    }
    // Utility function to store vertical order in map 'm'
    // 'hd' is horizontal distance of current node from root.
    // 'hd' is initially passed as 0
    public static void getVerticalOrder(TreeNode root, int hd, TreeMap<Integer, Vector<Integer>> m){

        if(root == null){
            return;
        }

        Vector<Integer> get = m.get(hd);

        if(get == null){
            get = new Vector<>();
            get.add(root.data);
        }
        else{
            get.add(root.data);
        }
        m.put(hd, get);

        getVerticalOrder(root.left, hd-1, m);
        getVerticalOrder(root.right, hd+1,m);

    }
    public static void printVerticalOrder(TreeNode root){
        TreeMap<Integer, Vector<Integer>> m = new TreeMap<>();
        int hd =0;
        getVerticalOrder(root, hd, m);
        for (Map.Entry<Integer, Vector<Integer>> entry : m.entrySet())
        {
            System.out.println(entry.getValue());
        }
    }

    /**
     * print left viwe of the binary tree
     *
     * 1) We have to print the left child but only if no other child is printed on this level. Because if on
     * a level one of the child is printed then others would be hidden by that child on that level
     *
     * 2) Take a variable maxlevel and assign it to zero becuase we wanted to make sure that if once we printed any
     * child on that level then we are not supposed to print any other.
     * 3) compare the maxLevel<level (bacause then we are sure that we havent printed any child on this 'level' and once
     * we print any child on this level we assign level to max level and increase the level)
     */
     // because we will compare intially maxLevel < Level then only print left child else
                          //incresse the lelve and move to left and then right
    public static void printLeftView(TreeNode root, int level, int maxLevel){

        if(root == null)
            return;

        if(maxLevel<level){
            System.out.println(root.data);
            maxLevel = level;
        }

        printLeftView(root.left, level+1, maxLevel);
        printLeftView(root.right, level+1,maxLevel);
    }

    /**
     * print right viwe of the binary tree
     *
     * 1) Its same as abov just that we have move to right and then left
     */
    public static void printRightView(TreeNode root, int level, int maxLevel){

        if(root == null)
            return;

        if(maxLevel<level){
            System.out.println(root.data);
            maxLevel = level;
        }

        printRightView(root.right, level+1, maxLevel);
        printRightView(root.left, level+1,maxLevel);
    }

    /**
     * print top viwe of the binary tree
     */
    public static void printTopView(TreeNode root, int level){
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
    }


    public static boolean printAncestorOfBinaryTree(TreeNode root, int key){

        if(root==null){
            return false;
        }
        if(root.data==key){
            return true;
        }

        if(printAncestorOfBinaryTree(root.left,key) || printAncestorOfBinaryTree(root.right,key)){
            System.out.println(root.data);
            return true;
        }

        return false;
    }

    public static void printNodeAtKDisctance(TreeNode root, int key){

        if(root==null){
            return;
        }
        if(key==0){
            System.out.println(root.data);
            return;
        }
        else {
            printNodeAtKDisctance(root.left, key - 1);
            printNodeAtKDisctance(root.right, key - 1);
        }
    }

    public static boolean areIdentical(TreeNode root1, TreeNode root2){
        if(root1 ==null && root2==null)
            return true;

        if(root1==null || root2==null)
            return false;

        return root1.data == root2.data &&
                areIdentical(root1.left, root2.left) &&
                areIdentical(root1.right, root2.right);

    }

    public static boolean isSubTree(TreeNode root1, TreeNode root2){
        if(root1==null)
            return false;
        if(root2==null)
            return true;

        if(areIdentical(root1,root2))
            return true;

        return isSubTree(root1.left, root2) &&
                isSubTree(root1.right, root2);

    }

    /**
     * 1) Take a queue
     * 2) push the root to queue.
     * 2) check if queue is not empty then pop the element and push left and right child to queue.
     * 3) repeat the process untill queue is empty.
     *
     * Note: Just imaging that after printing root data, you would have pushed left and right child and in next time ,
     * you would pop left child and print that data and push the left and right child, and now the next element in the FIFO queue would the right cgild
     * of the root , it means that you would print children at that level first and then move to others because FIFO makes sure that
     * added childlren after the root would be popped out on the next level.
     *
     *
     * @param root
     */
    public static void levelOrderTraversal(TreeNode root){

        if(root==null)
            return;

        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode temp = queue.remove();
            if(temp.left!=null){
                queue.add(temp.left);
            }
            if(temp.right!=null){
                queue.add(temp.right);
            }
            System.out.println(temp.data);
        }

    }

    /**
     * https://www.youtube.com/watch?v=qT65HltK2uE&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=10
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L120
     * Idea is to take 2 stack and push root to s1 and then pop from s1 and push that to s2
     * and before pushing check if pop root from s1 have lesft and right child so push them to s1.
     *
     * Note: This way we are ensuiring that Root is push in stack first and then left and right child
     * making sure we print in order left, right, root
     * @param root
     */

    public static void iterativePostOrderTraversal(TreeNode root){
        if(root==null)
            return;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.add(root);
        while (!s1.isEmpty()){
            TreeNode temp = s1.pop();
            s2.push(root);
            if(root.left!=null)
                s1.push(root.left);
            if (root.right!=null)
                s1.push(root.right);
        }

        while (!s2.isEmpty()){
            System.out.println(s2.pop().data);
        }
    }

    /**
     * https://www.youtube.com/watch?v=nzmtCFNae9k&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=12s
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L120
     *
     * Note: basically in inorder we have to print the left tree first and then right tree.
     * In Order traversal recursion ussage call stack while in iterative we have to maintaine our own.
     *
     * 1) Idea is to take a stack and push the left node untill we get left as null
     * 2) once we get null , we check if stack is not empty then pop and element and print the data
     * 3) after data is printed make the root as right.
     *
     * @param root
     */
    public void inOrderTraversalIterative(TreeNode root){

        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();

        while (true){
            if(root!=null){
                stack.push(root);
                root=root.left;
            }else {
                if (stack.isEmpty()){
                    break;
                }
                root=stack.pop();
                System.out.println(root.data);;
                root = root.right;
            }
        }

    }

    /**
     * https://www.youtube.com/watch?v=elQcrJrfObg&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=11
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversals.java#L105
     *
     * @param root
     */
    public static void iterativePreOrderTraversal(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            TreeNode temp = stack.pop();
            System.out.println(temp.data);
            if(temp.right!=null)
                stack.push(temp.right);
            if(root.left!=null)
                stack.push(temp.right);
        }

    }

    /*
        Given a binary tree, print each level on new line.
        https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/TreeTraversalLevelByLevel.java

     */
    public static void levelOrderTraversalPrintLevelByLevel(TreeNode root){
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        queue.add(null);
        TreeNode current;
        while(!queue.isEmpty()){
            current = queue.remove();

            if(current!=null) {
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
                System.out.print(current.data);
            }
            else{
                if(!queue.isEmpty()) {
                    queue.add(null);
                    System.out.println();
                }
            }
        }

    }

    /**
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/BSTSearch.java
     * @param root
     * @param key
     * @return
     */
    public static TreeNode binarySearch(TreeNode root, int key){
        if(root==null)
            return null;
        if(root.data==key)
            return root;

        if(root.data<key)
            return binarySearch(root.right,key);
        else
            return binarySearch(root.left,key);
    }

    /**
     * insert in a binary tree
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/BinaryTree.java#L30
     * https://www.youtube.com/watch?v=bmaeYtlO2OE&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=4&t=0s
     * @param root
     * @param key
     * @return
     */
    public static TreeNode insertInBinaryTree(TreeNode root, int key){
        TreeNode node = new TreeNode(key);

        if(root==null){
            return node;
        }

        TreeNode parent=null, current=root;
        while (current!=null){
            parent = current;
            if(current.data>=key){
                current = current.left;
            }else{
                current =current.right;
            }
        }
        if(parent.data>=key){
            parent.left = node;
        }else{
            parent.right = node;
        }
        return  root;
    }

    /**
     *https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SameTree.java
     * https://www.youtube.com/watch?v=ySDDslG8wws&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=4
     *
     * @param root1
     * @param root2
     */
    public static boolean sameBinaryTree(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null)
            return true;
        else if(root1==null || root2==null)
            return false;

        return root1.data==root2.data
                && sameBinaryTree(root1.left,root2.left) && sameBinaryTree(root1.right, root2.right);
    }

    /**
     * https://www.youtube.com/watch?v=NA8B84DZYSA&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=5
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/SizeOfBinaryTree.java
     * @param root
     * @return
     */
    public static int binaryTreeSize(TreeNode root){
        if(root==null)
            return 0;

        int left = binaryTreeSize(root.left);
        int right = binaryTreeSize(root.right);
        return left+right+1;
    }

    /**
     * https://www.youtube.com/watch?v=_SiwrPXG9-g&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=6
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/BinaryTree.java#L58
     * @param root
     * @return
     */
    public static int hightOfBianryTree(TreeNode root){

        if(root==null)
            return 0;
        int leftHeight = hightOfBianryTree(root.left);
        int rightHeight = hightOfBianryTree(root.right);
        return 1+ Math.max(leftHeight,rightHeight)+1;
    }

    /**
     * Given a binary tree and a sum, find if there is a path from root to leaf which sums to this sum.
     *
     * Root to leaf sum
     * https://www.youtube.com/watch?v=Jg4E4KZstFE&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=8&t=424s
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/RootToLeafToSum.java
     *
     */

    public static boolean roottoLeafSumm(TreeNode root, int sum, List<Integer> results){
        if(root==null)
            return false;

        //if its a leaf
        if(root.left==null && root.right==null){
            if(root.data==sum){  // if its leaf and sum matches then add the data to result
                results.add(root.data);
                return true;
            }
            else{
                return false;
            }
        }
        if(roottoLeafSumm(root.left,sum-root.data, results)){
            results.add(root.data);
            return true;
        }
        if(roottoLeafSumm(root.right, sum-root.data,results)){
            results.add(root.data);
            return true;
        }
        return false;

    }

    /**
     * https://www.youtube.com/watch?v=MILxfAbIhrE&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=8
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/IsBST.java
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    public static boolean isBST(TreeNode root, int min, int max){
        if(root==null)
            return true;
        if(root.data<=min || root.data>max)
            return  false;

        return isBST(root.left, min, root.data)
                && isBST(root.right,root.data,max);

    }
    static int maxLevel1 =0;
    public static void leftViewTree(TreeNode root, int level){

        if(root==null)
            return;
        if(level>maxLevel1){
            System.out.println(root.data);
            maxLevel1=level;
        }
        leftViewTree(root.left,level+1);
        leftViewTree(root.right,level+1);
    }

    public static void rightview(TreeNode root, int level){

        if(root==null)
            return;
        if(level>maxLevel1){
            System.out.println(root.data);
            maxLevel1 = level;
        }
        rightview(root.right,level+1);
        rightview(root.left,level+1);
    }

   public static void levelOrderTrav(TreeNode root){
        if(root==null)
            return;

        Queue<TreeNode> queue = new java.util.LinkedList<>();
       queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.remove();
            System.out.println(temp.data);
            if(temp.left!=null)
                queue.add(temp.left);
            if(temp.right!=null)
                queue.add(temp.right);
        }

   }

    /**
     * In top view we will print the first element in the vertical order traverlsal
     * @param root
     */
   public void topviewofBinaryTree(TreeNode root){
        if (root==null)
            return;

        Map<Integer, Integer> map = new HashMap<>();
        Queue<TreeNodeWithDistance> queue = new java.util.LinkedList<>();
        queue.add(new TreeNodeWithDistance(root,0));

        while (!queue.isEmpty()){
            TreeNodeWithDistance temp = queue.remove();
            if(temp.node.left!=null)
                queue.add(new TreeNodeWithDistance(temp.node.left,temp.dis-1));
            if(temp.node.right!=null)
                queue.add(new TreeNodeWithDistance(temp.node.right,temp.dis+1));

            if(!map.containsKey(temp.dis)){
                map.put(temp.dis,temp.node.data);
            }
        }
        System.out.println(map);

   }

    /**
     * in Bottom view we will print the last element in the vertical order traversal
     * @param root
     */
    public void bottomViewofBinaryTree(TreeNode root){
        if (root==null)
            return;

        Map<Integer, Integer> map = new HashMap<>();
        Queue<TreeNodeWithDistance> queue = new java.util.LinkedList<>();
        queue.add(new TreeNodeWithDistance(root,0));

        while (!queue.isEmpty()){
            TreeNodeWithDistance temp = queue.remove();
            if(temp.node.left!=null)
                queue.add(new TreeNodeWithDistance(temp.node.left,temp.dis-1));
            if(temp.node.right!=null)
                queue.add(new TreeNodeWithDistance(temp.node.right,temp.dis+1));

            map.put(temp.dis,temp.node.data);
        }
        System.out.println(map);

    }

    /**
     * in Spiral traversal we have to print root and then from right side and after that from left side
     * so queue would not work as it pop FIFO, hence we would need a DS that can help us get LIFO
     * that's Stack.
     * 1) take 2 stack.
     * 2) in first stack push child like right first and then left that make sure that stack1 will print
     * element from right to left
     * 3) in stack2 push element like left and then right that make sure to print from left to right
     * 4) we also have to pop all the elements till stack gets empty.
     * @param root
     */
    public void spiralOrderTravel(TreeNode root){

        if(root==null)
            return;

        Stack<TreeNode> stack1 = new Stack<>(); // push right adn then left
        Stack<TreeNode> stack2 = new Stack<>(); // push left and then right
        stack1.add(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) { // while any of th stack have any left elemetn
            while (!stack1.isEmpty()) { // while stack2 is not empty
                TreeNode temp = stack1.pop();
                if (temp.left != null) {
                    stack2.push(temp.left);
                }
                if (temp.right != null) {
                    stack2.push(temp.right);
                }
                System.out.println(temp.data);
            }

            while (!stack2.isEmpty()) { // while stack2 is not empty
                TreeNode temp = stack2.pop();
                if (temp.right != null) {
                    stack1.push(temp.right);
                }
                if (temp.left != null) {
                    stack1.push(temp.left);
                }
                System.out.println(temp.data);
            }
        }

    }

    /**
     * Mirror of a binary tree
     * https://www.youtube.com/watch?v=apkprrLFZ6A&list=UUF-ki5E-1NPi52nWFEBmrSA&index=5
     * https://github.com/HimanshuVerma18111989/Code-Campaign/blob/master/BinaryTree/CoreUtil.cs
     *
     */

    public void mirroOfBinaryTree(TreeNode root){
        if(root==null)
            return;

        mirroOfBinaryTree(root.left);
        mirroOfBinaryTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    public void printLeftNodes(TreeNode root){

        if (root==null)
            return;
        else if(root.left==null && root.right==null){
            return;
        }
        else{
            System.out.println(root.data);
            printLeftNodes(root.left);
        }

    }

    public void printLeafOfNodes(TreeNode root){
        if (root==null)
            return;
        if(root.left==null && root.right==null)
            System.out.println(root.data);
        else {
            printLeafOfNodes(root.left);
            printLeafOfNodes(root.right);
        }
    }

    public void printRightNodeOfTree(TreeNode root){
        if (root==null){
            return;
        }
        else if(root.left==null && root.right==null){
            return;
        }
        else {
            printRightNodeOfTree(root.right);
            System.out.println(root.data);

        }
    }

    /**
     * https://www.youtube.com/watch?v=P3kaw87MFk4&list=UUF-ki5E-1NPi52nWFEBmrSA&index=7
     * https://github.com/HimanshuVerma18111989/Code-Campaign/blob/master/BinaryTree/TraversalUtil.cs
     * @param root
     */
    public void boundryTraversalOfBinaryTree(TreeNode root){
        printLeftNodes(root);
        printLeafOfNodes(root);
        printRightNodeOfTree(root.right); // since we have have already printed root in printLeftNodes
    }


    static  int leafs=0;
    public void findNumberOfLeafNodes(TreeNode root){
        if (root==null)
            return;
        if (root.right==null && root.left==null){
            leafs = leafs+1;
        }
        else {
            findNumberOfLeafNodes(root.left);
            findNumberOfLeafNodes(root.right);
        }
    }

    /**
     * recursive way to find the number of nodes in a binary tree
     * @param root
     * @return
     */
    public int numberOfNodesInTree(TreeNode root){
        if (root==null)
            return 0;
        return 1+numberOfNodesInTree(root.left) + numberOfNodesInTree(root.right);
    }

    public int numberOfNodesInTreeIterative(TreeNode root){
        int count =0;
        if (root==null)
            return 0;
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode temp = queue.remove();
            count++;
            if (temp.left!=null)
                queue.add(temp.left);
            if (temp.right!=null)
                queue.add(temp.right);
        }
        return count;
    }

    public int heightOfTree(TreeNode root){
        if (root==null)
            return 0;
        return 1+ Math.max(heightOfTree(root.left),heightOfTree(root.right));
    }

    /**
     * Diagonal traversal is same as vertival order traversal its just that instead of distance we maintaine
     * height in such a way that if we move to right child then we keep the height same as root
     * and if we move to left we change the height to parent +1. considering root is at 0 height.
     * @param root
     * https://www.youtube.com/watch?v=myPeo_AIZp0&list=UUF-ki5E-1NPi52nWFEBmrSA&index=11
     * https://github.com/HimanshuVerma18111989/Code-Campaign/blob/master/BinaryTree/TraversalUtil.cs
     */

    public void diagonalTraversalOfTree(TreeNode root){
        if (root==null)
            return;
        Queue<TreeNodeWithDistance> queue = new java.util.LinkedList<>();
        Map<Integer, java.util.LinkedList<Integer>> map = new HashMap<>();
        queue.add(new TreeNodeWithDistance(root,0));
        while (!queue.isEmpty()){
            TreeNodeWithDistance temp = queue.remove();
            if (temp.node.left!=null)
                queue.add(new TreeNodeWithDistance(temp.node.left,temp.dis+1));
            if (temp.node.right!=null)
                queue.add(new TreeNodeWithDistance(temp.node.right,temp.dis));

            if(map.containsKey(temp.dis)){
                java.util.LinkedList<Integer> list = map.get(temp.dis);
                list.add(temp.node.data);
                map.put(temp.dis,list);
            }else {
                java.util.LinkedList<Integer> list = new LinkedList<>();
                list.add(temp.node.data);
                map.put(temp.dis,list);
            }
        }
        System.out.println(map);

    }

    /**
     * Lowest Common Ancestor Binary Search Tree
     * https://www.youtube.com/watch?v=TIoCCStdiFo&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=17&t=0s
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LowestCommonAncestoryBinarySearchTree.java
     *
     */
    public TreeNode LCSINBinarySearch(TreeNode root, TreeNode n1, TreeNode n2){
        if (root.data> Math.max(n1.data,n2.data)){
            return LCSINBinarySearch(root.left,n1,n2);
        }
        else if(root.data<Math.min(n1.data,n2.data)){
            return LCSINBinarySearch(root,n1,n2);
        }
        else {
            return root;
        }

    }

    /**
     * Lowest Common Ancestor Binary Tree
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/LowestCommonAncestorInBinaryTree.java
     *  https://www.youtube.com/watch?v=13m9ZCB8gjw&list=PLrmLmBdmIlpv_jNDXtJGYTPNQ2L1gdHxu&index=17
     *
     */
    public TreeNode LCSInBinaryTree(TreeNode root, TreeNode n1, TreeNode n2){
        if (root==null)
            return null;
        if (root==n1 || root==n2){
            return root;
        }

        TreeNode left = LCSInBinaryTree(root.left,n1,n2);
        TreeNode right = LCSInBinaryTree(root.right,n1,n2);
        if (left!=null&& right!=null){
            return root;
        }
        if (left==null && right==null){
            return null;
        }
        return left!=null?left:right;
    }

    /**
     * https://www.youtube.com/watch?v=IJCg_jxOM_Q
     * Get Level of a node in a Binary Tree
     * https://www.geeksforgeeks.org/get-level-of-a-node-in-a-binary-tree/
     *
     *
     */

    public int findLevelOfANodeInBinaryTree(TreeNode root, int data){
        int level = findLevelUtil(root,data,1);
        return level;
    }

    public int findLevelUtil(TreeNode root,int data, int level){
        if (root==null)
            return 0;
        if (root.data==data){
            return level;
        }
        int downLevel = findLevelUtil(root.left,data,level+1);
        if (downLevel!=0){
            return downLevel;
        }
        downLevel = findLevelUtil(root.right,data,level+1);
        return downLevel;

    }

    /**
     * Check if a binary tree is subtree of another binary tree
     *
     */
    public boolean findSubTree(TreeNode root, TreeNode subRoot){
        if (root==null && subRoot==null)
            return true;
        if (root==null || subRoot==null)
            return false;

        return root.data==subRoot.data && findSubTree(root.left,subRoot.left)
                && findSubTree(root.right,subRoot.right);
    }

    /**
     * https://www.youtube.com/watch?v=Ev83cGBsQSA
     * https://github.com/tranquilshore/CS_learn/blob/master/Binary%20Tree/distance_between_two_nodes.py
     * Given 2 nodes , the task is to find distance between 2 nodes.
     * dist(n1,n2) = findLevel(lca, n1,0)+findlevel(lca,n2,0)
     *
     */
    public void  findDistanceBetweenTwoNodesBinaryTree(TreeNode root, TreeNode n1, TreeNode n2){

    }


    public static void main(String []arg){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = new TreeNode(1);
        binaryTree.root.left = new TreeNode(2);
        binaryTree.root.right = new TreeNode(3);

        binaryTree.root.left.left = new TreeNode(4);
        binaryTree.root.left.right = new TreeNode(5);
        /*binaryTree.root.left.right.left = new TreeNode(8);


        binaryTree.root.right.left = new TreeNode(6);
        binaryTree.root.right.right = new TreeNode(7);
        binaryTree.root.right.right.right = new TreeNode(9);*/

        binaryTree.root1 = new TreeNode(1);
        binaryTree.root1.left = new TreeNode(3);
        binaryTree.root1.right = new TreeNode(2);

        binaryTree.root1.left.left = new TreeNode(5);
        binaryTree.root1.left.right = new TreeNode(4);


        //inOrderTraversal(binaryTree.root);
        //insertInTree(7,binaryTree.root);
        //System.out.println("***************");
        //inOrderTraversal(binaryTree.root);
        //levelOrderTraversal(binaryTree.root);
        //printLeftView(binaryTree.root,0);
        //printRightView(binaryTree.root,0);
        //printVerticalOrder(binaryTree.root);
        //levelOrderTraversalPrintLevelByLevel(binaryTree.root);
        //leftViewTree(binaryTree.root,0);

        //binaryTree.verticalOrderTraversal(binaryTree.root);
        //System.out.println(binaryTree.heightOfTree(binaryTree.root));
        //binaryTree.diagonalTraversalOfTree(binaryTree.root);

    }


}

