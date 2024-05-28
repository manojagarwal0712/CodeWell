package com.stack.com.mankumm.coding.ninja;

public class Student {
    String name;
    int rollNo;
    Student(int num){
        rollNo = num;
    }
    public void print(){
        System.out.print(name +" " + rollNo + " ");
    }
}
