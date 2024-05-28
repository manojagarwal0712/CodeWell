package com.stack.com.mankumm.coding.ninja;

import java.util.HashSet;
import java.util.Objects;
import java.util.Stack;

public class ListQuestions {
    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    /**
     * Insert a node in a linked list
     * https://www.geeksforgeeks.org/insertion-in-linked-list/
     *
     */
    public Node<Integer> insertAtBegin(Node<Integer> head, int key){
        Node<Integer> temp = new Node<Integer>(key);
        temp.next = head;
        head = temp;
        return head;
    }

    public void insertAfterGivenNode(Node<Integer> prev, int key){
        Node<Integer> temp = new Node<Integer>(key);
        temp.next = prev.next;
        prev.next = temp;
    }

    public void insertAtEnd(Node<Integer> head, int key){
        Node<Integer> temp = new Node<Integer>(key);
        if (head==null){
            temp.next = head;
            head = temp;
            return;
        }
        while (head.next!=null){
            head = head.next;
        }
        head.next = temp;
    }

    /**
     * Search an element in a linked list
     * https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/
     */
    public static boolean searchIterative(Node<Integer> head, int key){
        if (head==null){
            return false;
        }
        while (head!=null){
            if (head.data==key){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/
     * @param head
     * @param key
     * @return
     */
    public static boolean searchRec(Node<Integer> head, int key){
        if (head==null){
            return false;
        }
        if (head.data==key){
            return true;
        }
        return searchRec(head.next, key);
    }

    /**
     * https://www.geeksforgeeks.org/find-length-of-a-linked-list-iterative-and-recursive/
     * find length of list iteraive
     */
    public static int lengthIterative(Node<Integer> head){
        int count =0;
        if (head==null){
            return count;
        }
        while (head!=null){
            count++;
            head = head.next;
        }
        return count;
    }
    public static int lengthRec(Node<Integer> head){
        if (head==null){
            return 0;
        }
        return 1+ lengthRec(head.next);
    }

    /**
     * https://www.geeksforgeeks.org/reverse-a-linked-list/
     * Reverse a linked list
     */
    public static Node<Integer> reverseListIterative(Node<Integer> head){
        Node<Integer> prev = null;
        Node<Integer> curr = head;
        Node<Integer> next = null;
        while (curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head  = prev;
        return head;
    }

    /**
     * https://www.youtube.com/watch?v=v8mPBNK3JHc
     * @param head
     * @return
     */
    public static Node<Integer> reverseListRec(Node<Integer> head){
        if (head==null || head.next==null){
            return head;
        }
        Node<Integer> restHead = reverseListRec(head.next);
        Node<Integer> restTail = head.next; // rest tail is the head.next that we passed initially.
        restTail.next = head;
        head.next = null;
        return restHead;
    }

    /**
     * Delete a linked list given node.
     * https://www.geeksforgeeks.org/deletion-in-linked-list/
     */
    public static Node<Integer> delete(Node<Integer> head, int position){
        Node<Integer> temp = head;
        Node<Integer> prev = head;
        for (int i =0; i<position; i++){
            if (i ==0 && position ==1){
                head = head.next;
            }
            else {
                if (i ==position-1 && temp!=null){
                    prev.next = temp.next;
                }else {
                    prev = temp;
                    if (prev==null){
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
        return head;
    }
    public static Node<Integer> deleteRec(Node<Integer> head, int pos) {
        //Your code goes here
        if (head ==null){
            return head;
        }
        if (pos==0 ){
            return head.next;
        }
        head.next = deleteRec(head.next, pos-1);
        return head;
    }

    /**
     * get Nth node of a linked list
     * https://www.geeksforgeeks.org/write-a-function-to-get-nth-node-in-a-linked-list/
     */
    public static Integer findNthNode(Node<Integer> head, int pos){
        Node<Integer> temp = head;
        int count = 0;
        while (temp!=null){
            if (count==pos){
                return temp.data;
            }
            count++;
            temp = temp.next;
        }
        return -1;
    }
    public static Integer findNthNodeRec(Node<Integer> head, int pos){
        int count = 0;
        if (head ==null){
            return -1;
        }
        if (count ==pos){
            return head.data;
        }
        return findNthNodeRec(head.next, pos-1);
    }

    /**
     * https://www.geeksforgeeks.org/nth-node-from-the-end-of-a-linked-list/
     * @param head
     * @param pos
     * @return
     */
    public static Integer findNthFromEnd(Node<Integer> head, int pos){
        int n = lengthRec(head);
        //node from start
        pos = n - pos;
        int count =0;
        while (head !=null){
            if (count==pos){
                return head.data;
            }
            head = head.next;
            count++;
        }
        return  -1;
    }

    public static int findNthFromEndApproach2(Node<Integer> head, int pos){
        Node<Integer> first = head;
        Node<Integer> second = head;
        for (int i =1; i<pos; i++){
            second = second.next;
            if (second==null){
                return -1;
            }
        }
        while (second.next!=null){
            first = first.next;
            second = second.next;
        }
        return first.data;
    }

    /**
     * https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
     * @param head
     * @return
     */
    public static boolean detectLoop(Node<Integer> head){
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow){
                return true;
            }
        }
        return false;
    }
    /**
     * Find length of nodes in a loop in a circular list
     * https://www.geeksforgeeks.org/find-length-of-loop-in-linked-list/
     */
    public static int findLengthOfLoop(Node<Integer> head){
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast==slow){
                return findLoopLength(slow);
            }
        }
        return 0;
    }
    public static int findLoopLength(Node<Integer> common){
        Node<Integer> temp = common.next;
        int count =1;
        while (temp!=common){
            temp = temp.next;
            count++;
        }
        return count;
    }

    /**
     * remove duplicate from a sorted linked list
     */
    public static Node<Integer> removeDup(Node<Integer> head){
        HashSet hashSet = new HashSet<>();
        Node prev = null;
        Node temp = head;
        while (temp!=null){
            if (hashSet.contains(temp.data)){
                while (temp !=null && Objects.equals(prev.data, temp.data)){
                    prev.next = temp.next;
                    temp = temp.next;
                }
            }
            if(temp!=null){
                hashSet.add(temp.data);
                prev = temp;
                temp = temp.next;
            }
        }
        return head;
    }

    /*static Node addTwoLists(Node<Integer> list1, Node<Integer> list2){
        list1 = reverseList(list1);
        list2  = reverseList(list2);
        Node<Integer> listAns = null;
        int carry = 0;
        while (list1!=null && list2!=null){
            int sum = list1.data + list2.data + carry;
            carry = sum /10;
            int val = sum%10;
            listAns = addAtlast(listAns, val);
            list1 = list1.next;
            list2 = list2.next;
        }
        while (list1!=null){
            listAns = addAtlast(listAns, list1.data);
            list1 = list1.next;
        }
        while (list2!=null){
            listAns = addAtlast(listAns, list2.data);
            list2 = list2.next;
        }
        listAns  = reverseList(listAns);
        return listAns;
    }*/

    public Node addAtlast(Node head, int data){
        Node newNode = new Node<Integer>(data);
        if (head==null){
            head = newNode;
            return head;
        }
        while (head.next!=null){
            head = head.next;
        }
        head.next = newNode;
        return head;
    }
    /**
     * https://www.geeksforgeeks.org/count-nodes-circular-linked-list/
     *
     */
    public static int countNodesInCircularList(Node<Integer> head){

        int count =0;
        if (head!=null){
            do {
                count++;
                head = head.next;
            }while (head!=null);
        }
        return count;
    }
    /**
     * https://www.geeksforgeeks.org/check-if-a-linked-list-is-circular-linked-list/
     *
     */
    public static boolean checkIfCircularList(Node<Integer> head){
        Node<Integer> fast = head;
        Node<Integer> slow = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }
        }
        return false;
    }
    /**
     * https://www.geeksforgeeks.org/write-a-function-that-counts-the-number-of-times-a-given-int-occurs-in-a-linked-list/
     *
     */
    public static int countFreq(Node<Integer> head, int key){
        int count = 0;
        while (head!=null){
            if (head.data == key){
                count++;
            }
            head = head.next;
        }
        return count;
    }
    /**
     * Find mid of a linked list.
     * https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
     * @param head
     * @return
     */
    public static int findMidOfList(Node<Integer> head){
        Node<Integer> fast = head;
        Node<Integer> slow = head;
        while (fast!=null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    public static int length(Node head){
        //Your code goes here
        if (head==null){
            return 0;
        }
        return 1+length(head.next);
    }

    public static void printIthNode(Node<Integer> head, int i){
        while (head!=null && i>0){
            head=head.next;
            i--;
        }
        if (i==0 && head !=null){
            System.out.println(head.data);
        }
    }
//1 , 2, 3, 4, 5
    public static Node<Integer> deleteNode( Node<Integer> head, int pos) {
        // Write your code here.
        Node<Integer> temp = head;
        if (head==null || pos <=0){
            return head;
        }
        if (pos==1){
            return head.next;
        }

        for (int i =1; i<=pos-1 && temp!=null; i++){
            temp  = temp.next;
        }
        if (temp.next==null){
            temp.next=null;
        }else {
            temp.next = temp.next.next;
        }
        return head;
    }

    public static Node<Integer> insertNode(Node<Integer> head, int pos, Node<Integer> newNode) {
        Node<Integer> temp = head;
        if (head==null){
            head = newNode;
            return head;
        }
        if (pos<0){
            return head;
        }
        if (pos==0){
            newNode.next = head;
            head = newNode;
            return head;
        }
        for (int i=0; i<=pos-1 && temp!=null; i++){
            temp = temp.next;
            i++;
        }
        if (temp.next==null){
            temp.next = newNode;
        }else {
            newNode.next = temp.next;
            temp.next = newNode;
        }
        return head;

    }
    public static Node<Integer> insertNodeRec(Node<Integer> head, int pos, Node<Integer> newNode) {

        if (pos ==0){
            newNode.next = head;
            return newNode;
        }
        head.next = insertNodeRec(head.next, pos-1, newNode);
        return head;
    }

    public static Node<Integer> deleteNodeRec(Node<Integer> head, int pos) {
        //Your code goes here
        if (pos<0){
            return head;
        }
        if (pos==0){
            return head.next;
        }
        head.next = deleteNodeRec(head.next, pos-1);
        return head;
    }

    /**
     * Find if a linked list if pal.
     * @param head
     * @return
     */
    public static boolean isPalindrome(Node<Integer> head) {
        //Store all the element in the stack.
        Node<Integer> temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp!=null){
            stack.push(temp.data);
            temp = temp.next;
        }
    // reiterate all the elements and check if the elements from stack and list matched for PAL.
        temp = head;
        while (temp!=null){
            if (temp.data !=stack.pop()){
                return false;
            }
            temp = temp.next;
        }
    return true;
    }

    /**
     * reverse list iterative.
     * @param head
     * @return
     */
    public static Node<Integer> reverseList(Node<Integer> head){
        Node<Integer> prev = null;
        Node<Integer> curr = head;
        Node<Integer> next = null;
        while (curr!=null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    public static Node<Integer> swapNodes(Node<Integer> head, int i, int j) {
        if (i == j){
            return head;
        }
        Node<Integer> prevX = null, currX = head;
        int k =0;
        while (currX !=null && k<=i){
            prevX = currX;
            currX = currX.next;
            k++;
        }
        k =0;
        Node<Integer> prevY = null, currY = head;
        while (currY !=null && k<=j){
            prevY = currY;
            currY = currY.next;
            k++;
        }
        //either of is not present in the linked lisrt then nothing to do
        if (currY ==null || currX==null){
            return head;
        }

        // if x is not the head of list
        if (prevX !=null){
            prevX.next = currY;

        }else {
            head = currY;
        }

        // if y is not the head of list
        if (prevY !=null){
            prevX.next = currX;
        }else {
            head = currX;
        }

        //swap pointers
        Node<Integer> temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
        return head;
    }



}
