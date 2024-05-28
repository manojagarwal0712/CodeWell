package com.interview.stack;

import java.util.Stack;

public class StackWithMinConstant {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> aux = new Stack<>();

    StackNode root;

    public void push(int data){
        stack.push(data);

        if(aux.isEmpty()){
            aux.push(data);
        }else {

            if(data<=aux.peek()){
                aux.push(data);
            }
        }

    }

    public int pop(){

        if (stack.isEmpty()){
            throw new StackOverflowError("Stack overflow");
        }
        int data = stack.pop();
        if (data==aux.peek()){
            aux.pop();
        }
        return data;
    }

    public int peek(){
        return stack.peek();
    }

    public int min(){
        if (aux.isEmpty())
            throw new StackOverflowError("Overflow");

        return aux.peek();
    }

    class StackNode{
        int data;
        int min;
        StackNode next;
        public StackNode(int data){
            this.data =data;
            this.min =data;
            next =null;
        }

        public void push(int data){
            if (root==null)
                root = new StackNode(data);
            else {
                StackNode newNode = new StackNode(data);
                newNode.next =root.next;
                newNode.min = Math.min(root.data,data);
                root = newNode;
            }
        }

        public int pop(){
            if (root==null)
                throw new StackOverflowError("Stack overflow");
            int data = root.data;
            root = root.next;
            return data;
        }

        public int min(){
            if (root==null)
                throw new StackOverflowError("Stack overflow");
            return root.data;
        }
    }



}
