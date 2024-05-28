package com.stack.com.mankumm.coding.ninja;

public class Node {
    public int data;
    public Node next;

    Node(){
        this.data = 0;
        this.next = null;
    }
    public Node(int data){
        this.data = data;
        this.next = null;
    }
    Node(int data, Node next){
        this.data = data;
        this.next = next;
    }
}
