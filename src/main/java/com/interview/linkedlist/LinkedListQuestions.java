package com.interview.linkedlist;

import java.util.Stack;

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val=val;
        this.next=null;
    }
}
public class LinkedListQuestions {
    static ListNode head;
    public static void main(String[] args) {
        ListNode head = null;
        head = addEndOfList(1, head);
        head=addEndOfList(2, head);
        head= addEndOfList(3, head);
        head= addEndOfList(4, head);
        head= addEndOfList(5, head);
        removeNthFromEnd(head, 2);
    }

    /**
     * https://leetcode.com/problems/middle-of-the-linked-list/
     * Traverse linked list using two pointers. Move one pointer by one and other pointer by two.
     * When the fast pointer reaches end slow pointer will reach middle of the linked list.
     * @param head
     */
    public static ListNode printMiddle(ListNode head){
        ListNode slow, fast;
        slow = head;
        fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * https://leetcode.com/problems/linked-list-cycle/
     * @param head
     * @return
     */

    public static boolean hasCycle(ListNode head){
        if (head==null)
            return false;
        ListNode fast, slow;
        fast =slow = head;

        while (slow!=null && fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow=slow.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }

    /**
     * https://www.youtube.com/watch?v=JsVGDy0u1u8
     * @param head
     */
    public void detectAndFixLoop(ListNode head){
        if (head==null)
            return;
        ListNode fast, slow;
        fast =slow = head;

        while (slow!=null && fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow=slow.next;
            if (slow==fast){
                break;
            }
        }
        // if slow == fast that mean loop exist.
        // now we have to find out whats the point where the loop exist.
        if(slow==fast){
            slow = head;
            while (slow.next!=fast.next){
                slow=slow.next;
                fast=fast.next;
            }
            //sinc fast.next is the looping point break it.
            fast.next=null; // remove lopp.
        }
    }


    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=study-plan-v2&envId=top-interview-150
     *
     * @return
     * remove nth node from end
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int toalLen = findLength(head);
        int k = toalLen-n; // remove this node from start.
        ListNode temp = head;
        while (temp!=null){
            k--;
            if (k==0){
                break;
            }
            temp = temp.next;
        }
        ListNode prevNode = temp; // this is previous nod eof the node that we need to delete.
        ListNode delNode = temp.next; /// node that we need to delete
        prevNode.next = temp.next.next; // now the delNode is remove and prevnod is poinitng to the next node of del node.
        delNode = null;
        return head;
    }
    public static int findLength(ListNode head){
        int len = 0;
        while (head!=null){
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * https://leetcode.com/problems/add-two-numbers/?envType=study-plan-v2&envId=top-interview-150
     * @param l1
     * @param l2
     * @return
     * You are given two non-empty linked lists representing two non-negative integers.
     * The digits are stored in reverse order, and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     */
    //numbers are already stored in reverse.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum =0;
        int carryOver = 0;
        ListNode dummyHead = new ListNode(0);
        ListNode ans = dummyHead;
        while (l1!=null || l2!=null){
            sum = carryOver;
            if (l1!=null){
                sum=sum+l1.val;
                l1=l1.next;
            }
            if (l2!=null){
                sum=sum+l2.val;
                l2 = l2.next;
            }
            carryOver = sum/10;
            sum = sum%10;
            ListNode newNode = new ListNode(sum);
            ans.next = newNode;
            ans = ans.next;
        }
        if (carryOver>0){
            ListNode newNode = new ListNode(sum);
            ans.next = newNode;
            ans = ans.next;
        }
        return dummyHead.next;

    }

    /**
     * https://leetcode.com/problems/reverse-linked-list/
     * @param head
     * @return
     */
    public static ListNode reverseAList(ListNode head){
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp!=null){
            stack.add(temp.val);
            temp= temp.next;
        }
        temp = head;
        while (temp!=null){
            temp.val = stack.pop();
            temp = temp.next;
        }
        return head;
    }

    /**
     * https://takeuforward.org/data-structure/reverse-a-linked-list/
     * @param head
     * @return
     */
    public static ListNode reverseAListInplace(ListNode head){
        ListNode prev = null;
        ListNode current = head;
        ListNode front = null;
        while (current!=null){
            front = current.next;
            current.next = prev;
            prev = current;
            current = front;

        }
        return head;
    }

    /**
     * https://leetcode.com/problems/merge-two-sorted-lists/
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        return  null;
    }

    /**
     * https://leetcode.com/problems/intersection-of-two-linked-lists/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        return null;
    }

    /**
     * https://leetcode.com/problems/palindrome-linked-list/
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        return false;
    }


    public static  ListNode addEndOfList(int data, ListNode head){
        ListNode newNode =  new ListNode(data);
        if (head == null) {
            head = newNode;
            return head;
        }
        ListNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = new ListNode(data);
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public ListNode addInFront(int data, ListNode head) {
        ListNode temp = head;
        head = new ListNode(data);
        head.next = temp;
        return head;
    }

    public static void addAfterGivenNode(int data, ListNode prevNode) {
        if (prevNode == null) {
            throw new IllegalArgumentException("Prev node can not be null");
        }
        ListNode newNode = new ListNode(data);
        newNode.next = prevNode.next;
        prevNode.next = newNode;
    }

    public static void endOfList(int data, ListNode head){
        ListNode temp = head;
        while(head!=null && head.next!=null){
            head = head.next;
        }
        head.next = new ListNode(data);

    }

    public static void deleteNode(int data, ListNode head){
        ListNode prevNode = null;
        ListNode temp = head;

        //if head itself have the key
        if(head!=null && head.val ==data){
            head = head.next;
        }

        // find the prev node of which next node having data
        while(temp!=null && temp.val !=data){
            prevNode = temp;
            temp = temp.next;
        }
        if(prevNode == null){
            throw new IllegalArgumentException("Key not present in the list");
        }
        prevNode.next = prevNode.next.next;
    }

    public static void deleteAtGivePosition(int pos){
        int temp =0;
        if(pos == 0 && head!=null){
            head = head.next;
        }

        for (int i=0; i<pos-1 && head!=null; i++){
            head = head.next;
        }

        //if position is greater then number of nodes
        if(head==null && head.next ==null){
            return;
        }
        head.next = head.next.next;

    }

    public static int findLengthOfListRecursive(ListNode head){
        if(head==null)
            return 0;
        return 1+ findLengthOfListRecursive(head.next);
    }

    public static boolean searchElementInLinkedList(int key, ListNode head){
        if(head!=null && head.val ==key){
            return true;
        }
        if(head==null){
            return false;
        }
        return searchElementInLinkedList(key, head.next);
    }

    public static void getNthNodeInList(int index, ListNode head){

        for (int i =0; i<=index-1 && head!=null;i++){
            head = head.next;
        }

        if(head ==null)
            throw new IllegalArgumentException("Index is greater then number of nodes");
        System.out.println(head.val);
    }


    /**
     * 1) when slow will be at middle node, temp will have the previuos of slow.
     * 2) replace temp.next to middle's next (which is slow's next)
     * @param head
     */
    public static void deleteMiddle(ListNode head){
        ListNode slow, fast;
        slow = head;
        fast = head;
        ListNode temp = null;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            temp = slow;
            slow = slow.next;
        }
        temp.next = slow.next;
    }


    public static ListNode removeDuplicateFromSortedlist(ListNode head){
        if (head==null)
            return null;

        if(head.next!=null){
            if(head.val ==head.next.val){
                head.next=head.next.next;
                removeDuplicateFromSortedlist(head);
            }
            else {
                removeDuplicateFromSortedlist(head.next);
            }
        }
        return  head;

    }



}
