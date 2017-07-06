package leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CalculateTree {
    private InputTree t = new InputTree();
    private Queue<Node> q = new LinkedList<>();

    public Tree<Node> createTree(List<Node> ar){
        Tree<Node> t = new Tree<>();
        Node sub;
        int flag=0;
        int pos1=0;
        int pos2=0;

        for(int i=0;i<ar.size();i=i+2){
            if(ar.get(i).getName()==OperatorNode.class && ar.get(i).getValue()==Operator.LEFT_BRACE.getValue()){
                flag++;
                if(flag==1){
                    pos1=i;
                }
                for(int j=i+1;j<ar.size();j++) {
                    if(ar.get(j).getName()==OperatorNode.class && ar.get(j).getValue()==Operator.LEFT_BRACE.getValue()){
                        flag++;
                        if(flag==1){
                            pos1=j;
                        }
                    }
                    if (ar.get(j).getName() == OperatorNode.class && ar.get(j).getValue() == Operator.RIGHT_BRACE.getValue()) {
                        flag--;
                        if (flag == 0) {
                            pos2 = j;
                            sub = createTree(ar.subList(pos1 + 1, pos2)).getRoot();
                                if (pos1>=1 && ar.get(pos1 - 1).getName() == OperatorNode.class && ar.get(pos1 - 1).getValue() != Operator.LEFT_BRACE.getValue()) {
                                    if (pos2<=ar.size()-2 && ar.get(pos2 + 1).getName() == OperatorNode.class && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                        if (!compareOpe(ar.get(pos2 + 1), ar.get(pos1 - 1))) {
                                            ar.get(pos1 - 1).setRight(sub);
                                            if(compareOpe(ar.get(pos2+1), t.getRoot())){
                                                ar.get(pos2 + 1).setLeft(ar.get(pos1 - 1));
                                                t.getRoot().setRight(ar.get(pos2+1));
                                            } else {
                                                ar.get(pos2+1).setLeft(t.getRoot());
                                                t.setRoot(ar.get(pos2+1));
                                            }
                                            t.setRoot(ar.get(pos2+1));
                                        } else {
                                            ar.get(pos2 + 1).setLeft(sub);
                                            ar.get(pos1 - 1).setRight(ar.get(pos2 + 1));
                                        }
                                    } else {
                                        ar.get(pos1 - 1).setRight(sub);
                                    }
                                } else if (pos2<=ar.size()-2 && ar.get(pos2 + 1).getName() == OperatorNode.class && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                    ar.get(pos2 + 1).setLeft(sub);
                                    t.setRoot(ar.get(pos2+1));
                                } else {
                                    t.setRoot(sub);
                                }
                            }
                    }
                }
                i=pos2;
            } else {
                Node n1 = ar.get(i);
                if(i==ar.size()-1 && n1.getName()==NumberNode.class){
                    if(i==0){
                        t.setRoot(n1);
                        break;
                    }
                    Node n = t.getRoot();
                    while(n.getRight()!=null){
                        n = n.getRight();
                    }
                    n.setRight(n1);
                } else {
                    Node n2 = ar.get(i + 1);
                    if (n2.getName() == OperatorNode.class && n1.getName() == NumberNode.class) {
                        n2.setLeft(n1);
                    }
                    if (n2.getName() == OperatorNode.class && i == 0) {
                     t.setRoot(n2);
                    } else if(i >= 2 && compareOpe(n2, ar.get(i - 1))) {
                            ar.get(i - 1).setRight(n2);
                    } else {
                        ar.get(i - 1).setRight(n1);
                        if (compareOpe(n2, t.getRoot())) {
                            n2.setLeft(ar.get(i - 1));
                            t.getRoot().setRight(n2);
                        } else {
                            n2.setLeft(t.getRoot());
                            t.setRoot(n2);
                        }
                    }
                }
            }
        }
        return t;
    }

    private boolean compareOpe (Node op1, Node op2 ) {
        switch (op1.getValue()) {
            case 42: {
                return (op2.getValue()==43 || op2.getValue()==45);
            }
            case 47: {
                return (op2.getValue()==43 || op2.getValue()==45);
            }
            case 43: {
                return false;
            }
            case 45: {
                return false;
            }
            default:
                return false;
        }
    }

    public double calculate(Tree<Node> tr) {
        postOrder(tr.getRoot());
        double num1, num2;
        Stack<Double> val = new Stack<>();
        while(!q.isEmpty()) {
            Node n = q.peek();
            if (n.getName()==NumberNode.class) {
                val.push((double) q.poll().getValue());
            } else if (n.getName()==OperatorNode.class && n.getValue()==Operator.ADD.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 + num2);
                q.poll();
            } else if (n.getName()==OperatorNode.class && n.getValue()==Operator.SUB.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num2 - num1);
                q.poll();
            } else if (n.getName()==OperatorNode.class && n.getValue()==Operator.MUL.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 * num2);
                q.poll();
            } else if (n.getName()==OperatorNode.class && n.getValue()==Operator.DIV.getValue()) {
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

    private void postOrder(Node root){
        if(root!=null){
            postOrder(root.getLeft());
            postOrder(root.getRight());
            q.add(root);
        }
    }
}