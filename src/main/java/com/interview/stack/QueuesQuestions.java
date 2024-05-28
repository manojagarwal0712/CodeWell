package com.interview.stack;

public class QueuesQuestions {
    int front= Integer.MIN_VALUE;
    int rear = Integer.MIN_VALUE;
    int max =100;

    int queue[] = new int[max];

    public boolean isEmpty(){
        if(queue==null || front<0){
            return true;
        }else{
            return false;
        }
    }

    public void enqueue(int key){
        if(isEmpty()){
            System.out.println("Queue overflow");
        }
        if(front<(max-1) && rear < (max-1)){
            queue[rear++] = key;
        }

    }

    public int dequeue(){
        if(isEmpty()){
           System.out.println("Queue overflow");
        }
        int pop = queue[0];
        for (int i=0;i<queue.length-1;i++){
            queue[i] = queue[i+1];
        }
        rear = rear-1;
        return pop;
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Queue overflow");
            return -1;
        }
        return queue[0];
    }

    public static void main(String[] args) {
        QueuesQuestions queue = new QueuesQuestions();
        /*queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);*/
        System.out.println(queue.dequeue());
        /*System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());*/
    }
}
