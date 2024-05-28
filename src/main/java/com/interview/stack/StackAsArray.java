package com.interview.stack;

public class StackAsArray {
    int max = 100;
    int top;
    int stack[] = new int[max];

    public boolean push(int key){
        if(top>(max-1)){
            System.out.println("stack overflow");
            return false;
        }
        else {
            stack[++top] = key;
            return true;
        }
    }

    public boolean isEmpty(){
        if(top<=0){
            return true;
        }
        return false;
    }

    public int pop(){
        if(!isEmpty()){
            int key = stack[top--];
            return key;
        }
        else{
            System.out.println("stack overflow");
            return 0;
        }
    }

    public int peek(){
        if(!isEmpty()){
            int key = stack[top];
            return key;
        }
        else{
            System.out.println("stack overflow");
            return 0;
        }
    }

    public static void main(String[] args) {
        StackAsArray stack = new StackAsArray();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println(stack.pop() + "-"+ stack.pop() + "-"+ stack.pop());
        stack.peek();
    }
}
