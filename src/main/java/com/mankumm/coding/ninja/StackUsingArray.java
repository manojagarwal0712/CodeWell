package com.mankumm.coding.ninja;

import java.util.Stack;

public class StackUsingArray {
    private int []data;
    private int top;
    public StackUsingArray(){
        data = new int[10];
        top = -1;
    }
    public StackUsingArray(int capacity){
        data =new int[capacity];
        top = -1;
    }
    public int top(){
        if (top==-1){
            throw new StackOverflowError("Stack overflow");
        }
        return data[top];
    }
    public int size(){
        return top+1;
    }
    public boolean isEmpty(){
        return (top==-1);
    }

    public void push(int ele){
        if (size()==data.length){
            throw new StackOverflowError("Stack is full");
        }
        top++;
        data[top] = ele;
    }
    public int pop(){
        if (top==-1){
            throw new StackOverflowError("Stack is full");
        }
        int ele = data[top];
        top--;
        return ele;

    }

    public static boolean isBalanced(String expression) {
        //Your code goes here
        Stack<Character> stack = new Stack<>();
        for (char ch:
             expression.toCharArray()) {
            if (ch=='('){
                stack.push(ch);
            }
            if (ch==')'){
                if (stack.isEmpty() || stack.peek()!='('){
                    return false;
                }else {
                    stack.pop();
                }
            }
        }
        return true;
    }

}
