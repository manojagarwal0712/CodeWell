package com.interview.stack;


import java.util.Stack;

public class StackAsList {
    StackNode root;

    static class StackNode{
        int data;
        StackNode node;
        public StackNode(int data){
            this.data = data;
        }
    }

    public boolean isEmpty(){
        if(root==null){
            return true;
        }
        else{
            return false;
        }
    }

    //add on fronts
    public void push(int key){
        StackNode stackNode = new StackNode(key);
        if(root==null){
            root = stackNode;
        }else{
            StackNode temp =root;
            root = stackNode;
            stackNode.node = temp;
        }
    }

    //remove front element
    public int pop(){

        if(root==null){
            System.out.println("stack overflow");
        }
        else{
            StackNode temp = root;
            root = root.node;
            return temp.data;
        }
        throw new StackOverflowError("stack overflow");
    }

    public int peek(){
        if(root==null){
            throw new StackOverflowError("stack overflow");
        }else{
            return root.data;
        }
    }

    public boolean checkIfExpressionIsBalanced(String str){

        Stack<Character> stack = new Stack();

        for (int i =0; i<str.length();i++){

            if(str.charAt(i)=='('
                    || str.charAt(i)=='{'
                    || str.charAt(i)=='['){
                stack.push(str.charAt(i));
            }
            if(str.charAt(i)==')'
                    || str.charAt(i)=='}'
                    || str.charAt(i)==']'){
                if(stack.isEmpty()){
                    return false;
                }
                if((stack.peek()=='(' && str.charAt(i)!=')')
                        && (stack.peek()=='{' && str.charAt(i)!='}')
                        && (stack.peek()=='[' && str.charAt(i)!=']')){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    /**
     * Find Duplicate Parenthesis in an Expression
     *   ((x+y))+z output: have duplicate Parenthesis as expression could be (x+y)+z
     *     ((x+y)+((z))) =>   (x+y)+(z)
     *
     *     1) If the current character in the expression is a not a closing parenthesis , then we push the character into the stack.
     *     2) If the current character in the expression is a closing parenthesis
     *     3) , we check if the topmost element in the stack is an opening parenthesis or not.
     *     If it is opening parenthesis, then the sub-expression ending at current character is of
     *     the form
     *     4)  else we continue popping characters from the stack till matching ‘(’ is found for current ‘)’
     */

    public boolean dupParenthesis(String str){
        Stack<Character> stack = new Stack();

        for (int i=0;i<str.length();i++){

            if(str.charAt(i)!=')'){
                stack.push(str.charAt(i));
            }
            else {
                if (stack.peek()=='('){
                    return true;
                }

                while (stack.peek()!='('){
                    stack.pop();
                }
                stack.pop();
            }
        }
        return false;

    }

    /**
     * valuate a give post fix expression
     *
       Input: 82/ will evaluate to 4 (8/2)
        Input: 138*+ will evaluate to 25 (1+8*3)
     1) Push the element if its a oprand in the stack
     2) if a operator encountered then pop 2 items from stack and calculat with the current operand
     ans push the result back to stack.
     3) keep continue that.
     */

    public int evaluatePostFix(String str){
        Stack<Integer> stack = new Stack();

        int res =0;
        for (int i =0; i<str.length();i++){
            if (Character.isDigit(str.charAt(i))){
                stack.push(str.charAt(i)-'0');
            }
            else {
                int op1 = stack.pop();
                int op2 = stack.pop();
                if(str.charAt(i)=='+'){
                    stack.push(op1+op2);
                }else if (str.charAt(i)=='*'){
                    stack.push(op2*op1);
                }
                else if (str.charAt(i)=='-'){
                    stack.push(op2-op1);
                }
                else if (str.charAt(i)=='/'){
                    stack.push(op2*op1);
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        StackAsList stackAsList = new StackAsList();
        System.out.println(stackAsList.evaluatePostFix("138*+"));

    }
}
