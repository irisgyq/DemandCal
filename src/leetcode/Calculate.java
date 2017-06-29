package leetcode;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class Calculate {
    private Queue<Object> q = new LinkedList<>();
    private Stack<Object> op = new Stack<>();

    public Queue<Object> exchange(String s) {

        Input in = new Input();
        ArrayList<Object> operation = in.processInput(s);

        for (int i = 0; i < operation.size(); i++) {
            if (Character.isDigit(operation.get(i).toString().charAt(0))) {
                q.add(operation.get(i));
            } else if (operation.get(i).equals("(")) {
                op.push(operation.get(i));
            } else if (operation.get(i).equals(")")) {
                while (!op.empty() && !op.peek().equals("(")) {
                    q.add(op.pop());
                }
                op.pop();
            } else {
                while (!op.empty() && compareOpe(operation.get(i).toString(), op.peek().toString())) {
                    q.add(op.pop());
                }
                op.push(operation.get(i));
            }
        }
        while (!op.empty()) {
            q.add(op.pop());
        }
        return q;
    }

    public double calculate(String s) {
       double num1, num2, num;
       Queue<Object> q =  exchange(s);
       Stack<Double> val = new Stack<>();
       while(!q.isEmpty()) {
           if (Character.isDigit(q.peek().toString().charAt(0))) {
               val.push(Double.valueOf(q.poll().toString()));
           } else if (q.peek().equals("+")) {
               num1 = val.pop();
               num2 = val.pop();
               val.push(num1 + num2);
               q.poll();
           } else if (q.peek().equals("-")) {
               num1 = val.pop();
               num2 = val.pop();
               val.push(num2 - num1);
               q.poll();
           } else if (q.peek().equals("*")) {
               num1 = val.pop();
               num2 = val.pop();
               val.push(num1 * num2);
               q.poll();
           } else if (q.peek().equals("/")) {
               num1 = val.pop();
               num2 = val.pop();
               if (num2 == 0) {
                   System.out.println("Divisor can't be zero.");
                   Main m = new Main();
                   m.calculator();
                   System.exit(0);

               }
               val.push(num2 / num1);
               q.poll();
           }
       }
       return val.pop();
    }

    public boolean compareOpe (String op1, String op2 ) {
        switch (op1) {
            case "*": {
                return (op2.equals("*") || op2.equals("/") );
            }
            case "/": {
                return (op2.equals("*") || op2.equals("/"));
            }
            case "+": {
                return (op2.equals("*") || op2.equals("/") || op2.equals("+") || op2.equals("-"));
            }
            case "-": {
                return (op2.equals("*") || op2.equals("/") || op2.equals("+") || op2.equals("-"));
            }
            default:
                return true;
        }
    }

    public void reset(){
        q.clear();
        op.removeAllElements();
    }
}