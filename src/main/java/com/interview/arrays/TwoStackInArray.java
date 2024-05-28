package com.interview.arrays;

public class TwoStackInArray {

    int max =100;
    int stack[] = new int[max];
    int top1, top2;

    public void push1(int x){
        if(top1<top2-1){
            top1++;
            stack[top1] =x;
        }
        else{
            System.out.println("stack overflow");
        }
    }

    public void push2(int x){
        if(top1<top2-1){
            top2--;
            stack[top2] =x;
        }
        else{
            System.out.println("stack overflow");
        }
    }

    public int pop1(){
        if(top1>=0){
            int pop = stack[top1];
            top1--;
            return pop;
        }
        else{
            System.out.println("Stack overflow");
        }
        return 0;
    }

    public int pop2(){
        if(top2<max){
            int pop = stack[top2];
            top2++;
            return pop;
        }
        else{
            System.out.println("stack overflow");
        }
        return 0;
    }

}
