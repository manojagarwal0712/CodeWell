package com.stack.com.mankumm.coding.ninja;

public class StackUsingLinkedList {
    class Node<T> {
        T data;
        StackUsingLinkedList.Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
    private Node<Integer> head;
    private int size;


    public StackUsingLinkedList() {
        //Implement the Constructor
        size =0;
        head = null;

    }

    /*----------------- Public Functions of Stack -----------------*/


    public int getSize() {
        //Implement the getSize() function
        return size;
    }

    public boolean isEmpty() {
        //Implement the isEmpty() function
        return size==0;
    }

    public void push(int element) {
        //Implement the push(element) function
        Node<Integer> newNode = new Node<>(element);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int pop() {
        //Implement the pop() function
        if (isEmpty()){
            throw new StackOverflowError("Stack overflow");
        }
        int ele = head.data;
        head = head.next;
        size--;
        return ele;

    }

    public int top() {
        //Implement the top() function
        if (isEmpty()){
            throw new StackOverflowError("Stack overflow");
        }
        return head.data;
    }
}
