package com.stack.com.mankumm.coding.ninja;

public class QueueUsingArray {
    public static void main(String[] args) {

    }
    private int[] data;
    private int front;
    private int rear;
    private int size;

    public QueueUsingArray(){
        data = new int[10];
        int size =0;
        int front = -1;
        int rear = -1;
    }

    public QueueUsingArray(int capacity){
        data = new int[capacity];
        int size =0;
        int front = -1;
        int rear = -1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void enqueue(int ele){
        if (isEmpty()){
            front++;
            rear++;
        }
        rear++;
        data[rear] = ele;
        size++;
    }

    public int dequeue(){
        if (isEmpty()){
            throw new IllegalArgumentException("Queue overflow");
        }
        int ele = data[front];
        front++;
        size--;
        return ele;

    }
}
