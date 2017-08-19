package calculator;

import tree.Node;
import tree.Tree;

import java.util.*;

public class Calculate {
    private InputProcess t = new InputProcess();
    private Queue<Node> q = new LinkedList<>();

    public Tree<Node> createTree(List<Token> ar) {
        Tree<Node> t = new Tree<>();
        Node sub;
        int flag = 0;
        int pos1 = 0;
        int pos2 = 0;
        Node n3 = null;
        for (int i = 0; i < ar.size(); i = i + 2) {
            if (ar.get(i).getType().equals("Operator") && ar.get(i).getValue() == Operator.LEFT_BRACE.getValue()) {
                flag++;
                if (flag == 1) {
                    pos1 = i;
                }
                for (int j = i + 1; j < ar.size(); j++) {
                    if (ar.get(j).getType().equals("Operator") && ar.get(j).getValue() == Operator.LEFT_BRACE.getValue()) {
                        flag++;
                        if (flag == 1) {
                            pos1 = j;
                        }
                    }
                    if (ar.get(j).getType().equals("Operator") && ar.get(j).getValue() == Operator.RIGHT_BRACE.getValue()) {
                        flag--;
                        if (flag == 0) {
                            pos2 = j;
                            sub = createTree(ar.subList(pos1 + 1, pos2)).getRoot();
                            Node n1 = null;
                            Node n2 = null;
                            if (pos1 >= 1) {
                                if (t.getRoot().getRight() != null) {
                                    n1 = t.getRoot().getRight();
                                } else {
                                    n1 = t.getRoot();
                                }
                            }
                            if (pos2 <= ar.size() - 2) {
                                n2 = new Node(ar.get(pos2 + 1).getValue(), ar.get(pos2 + 1).getType());
                            }
                            if (pos1 >= 1 && ar.get(pos1 - 1).getType().equals("Operator") && ar.get(pos1 - 1).getValue() != Operator.LEFT_BRACE.getValue()) {
                                if (pos2 <= ar.size() - 2 && ar.get(pos2 + 1).getType().equals("Operator") && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                    if (!compareOpe(ar.get(pos2 + 1), ar.get(pos1 - 1))) {
                                        n1.setRight(sub);
                                        if (compareOpe(ar.get(pos2 + 1), new Token(t.getRoot().getValue(), t.getRoot().getType()))) {
                                            n2.setLeft(n1);
                                            t.getRoot().setRight(n2);
                                        } else {
                                            n2.setLeft(t.getRoot());
                                            t.setRoot(n2);
                                        }
                                        t.setRoot(n2);
                                    } else {
                                        n2.setLeft(sub);
                                        n1.setRight(n2);
                                    }
                                } else {
                                    n1.setRight(sub);
                                }
                            } else if (pos2 <= ar.size() - 2 && ar.get(pos2 + 1).getType().equals("Operator") && ar.get(pos2 + 1).getValue() != Operator.RIGHT_BRACE.getValue()) {
                                n2.setLeft(sub);
                                t.setRoot(n2);
                            } else {
                                t.setRoot(sub);
                            }
                            n3 = n2;
                        }
                    }
                }
                i = pos2;
            } else {
                Node n4 = new Node(ar.get(i).getValue(), ar.get(i).getType());
                String type = n4.getType();
                double val = n4.getValue();
                if (type.equals("Operator")) {
                    if (val == Operator.SQRT.getValue() || val == Operator.COS.getValue() || val == Operator.SIN.getValue() || val == Operator.TAN.getValue()) {
                        if (ar.get(i + 1).getType().equals("Operator")) {
                            int pos3 = 0, pos4 = 0, flag1 = 0;
                            for (int k = i + 1; k < ar.size(); k++) {
                                if (ar.get(k).getType().equals("Operator") && ar.get(k).getValue() == Operator.LEFT_BRACE.getValue()) {
                                    flag1++;
                                    if (flag1 == 1) {
                                        pos3 = k;
                                    }
                                } else if (ar.get(k).getType().equals("Operator") && ar.get(k).getValue() == Operator.RIGHT_BRACE.getValue()) {
                                    flag1--;
                                    if (flag == 0) {
                                        pos4 = k;
                                        Tree<Node> subb = createTree(ar.subList(pos3 + 1, pos4));
                                        double res0 = calculate(subb);
                                        if (val == Operator.SQRT.getValue()) {
                                            ar.set(pos4, new Token(Math.sqrt(res0), "Number"));
                                        } else if (val == Operator.COS.getValue()) {
                                            ar.set(pos4, new Token(Math.cos(res0), "Number"));
                                        } else if (val == Operator.SIN.getValue()) {
                                            ar.set(pos4, new Token(Math.sin(res0), "Number"));
                                        } else if (val == Operator.TAN.getValue()) {
                                            double x = (res0 - Math.PI/2)/Math.PI;
                                            int y = (int)x;
                                            if(x-y==0.0){
                                                System.out.println("Your tan is wrong.");
                                                System.exit(0);
                                            }
                                            ar.set(pos4, new Token(Math.tan(res0), "Number"));
                                        }
                                        ar.remove(i);
                                        n4 = new Node(ar.get(pos4 - 1).getValue(), ar.get(pos4 - 1).getType());
                                        i = pos4 - 1;
                                        k = ar.size();
                                    }
                                }
                            }
                        } else {
                            double m = ar.get(i + 1).getValue();
                            if (val == Operator.SQRT.getValue()) {
                                ar.set(i + 1, new Token(Math.sqrt(m), ar.get(i + 1).getType()));
                            } else if (val == Operator.COS.getValue()) {
                                ar.set(i + 1, new Token(Math.cos(m), "Number"));
                            } else if (val == Operator.SIN.getValue()) {
                                ar.set(i + 1, new Token(Math.sin(m), "Number"));
                            } else if (val == Operator.TAN.getValue()) {
                                ar.set(i + 1, new Token(Math.tan(m), "Number"));
                            }
                            ar.remove(i);
                            n4 = new Node(ar.get(i).getValue(), ar.get(i).getType());
                        }

                    } else if (val == Operator.LOG.getValue()) {
                        int pos5 = i + 1, pos6 = 0, flag2 = 1;
                        for (int k = i + 2; k < ar.size(); k++) {
                            if (ar.get(k).getType().equals("Operator") && ar.get(k).getValue() == Operator.LEFT_BRACE.getValue()) {
                                flag2++;
                                if (flag2 == 1) {
                                    pos5 = k;
                                }
                            } else if (ar.get(k).getType().equals("Operator") && ar.get(k).getValue() == Operator.RIGHT_BRACE.getValue()) {
                                flag2--;
                                if (flag2 == 0) {
                                    pos6 = k;
                                    for (int p = pos5 + 1; p < pos6; p++) {
                                        if (ar.get(p).getType().equals("Operator") && ar.get(p).getValue() == Operator.COMMA.getValue()) {
                                            Tree<Node> subbb1 = createTree(ar.subList(pos5 + 1, p));
                                            double res1 = calculate(subbb1);
                                            Tree<Node> subbb2 = createTree(ar.subList(p + 1, pos6));
                                            double res2 = calculate(subbb2);
                                            if (res2 <= 0) {
                                                System.out.println("Your log can't be negative and zero.");
                                                System.exit(0);
                                            }
                                            ar.set(pos6, new Token(Math.log(res2) / Math.log(res1), "Number"));
                                            ar.remove(i);
                                            n4 = new Node(ar.get(pos6 - 1).getValue(), ar.get(pos6 - 1).getType());
                                            i = pos6 - 1;
                                            p = pos6;
                                        }
                                    }
                                    k = ar.size();
                                }
                            }
                        }
                    }
                }

                    if (i == ar.size() - 1 && n4.getType().equals("Number")) {
                        if (t.getRoot()==null) {
                            t.setRoot(n4);
                            break;
                        }
                        Node n = t.getRoot();
                        while (n.getRight() != null) {
                            n = n.getRight();
                        }
                        n.setRight(n4);
                    } else {
                        Node n5 = new Node(ar.get(i + 1).getValue(), ar.get(i + 1).getType());
                        if (n5.getType().equals("Operator") && n4.getType().equals("Number")) {
                            n5.setLeft(n4);
                        }
                        if (n5.getType().equals("Operator") && i == 0) {
                            t.setRoot(n5);
                        } else if (i >= 2 && compareOpe(ar.get(i + 1), ar.get(i - 1))) {
                            n3.setRight(n5);
                        } else {
                            Node n6 = t.getRoot();
                            Stack<Node> stk = new Stack<>();
                            stk.push(n6);
                            while (n6.getRight() != null && n6.getRight() != n3) {
                                n6 = n6.getRight();
                                stk.push(n6);
                            }
                            n3.setRight(n4);
                            Node tmpp = n3;
                            while (!stk.isEmpty()) {
                                Node tmpn = stk.pop();
                                if (compareOpe(ar.get(i + 1), new Token(tmpn.getValue(), tmpn.getType()))) {
                                    n5.setLeft(tmpp);
                                    tmpn.setRight(n5);
                                } else {
                                    tmpp = tmpn;
                                    n5.setLeft(tmpn);
                                    if (!stk.isEmpty()) {
                                        stk.peek().setRight(n5);
                                    } else {
                                        t.setRoot(n5);
                                    }

                                }
                            }
                        }
                        n3 = n5;
                    }
                }
        }
        return t;
    }

    private boolean compareOpe(Token op1, Token op2) {
        switch ((int)op1.getValue()) {
            case 94: {
                return (op2.getValue() == 43 || op2.getValue() == 45 || op2.getValue() == 42 || op2.getValue() == 47 || op2.getValue() == 37);
            }
            case 42: {
                return (op2.getValue() == 43 || op2.getValue() == 45);
            }
            case 47: {
                return (op2.getValue() == 43 || op2.getValue() == 45);
            }
            case 37: {
                return (op2.getValue() == 43 || op2.getValue() == 45);
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
        while (!q.isEmpty()) {
            Node n = q.peek();
            if (n.getType().equals("Number")) {
                val.push((double) q.poll().getValue());
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.ADD.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 + num2);
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.SUB.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num2 - num1);
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.MUL.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num1 * num2);
                q.poll();
            }  else if (n.getType().equals("Operator") && n.getValue() == Operator.MOD.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(num2 % num1);
                q.poll();
            }  else if (n.getType().equals("Operator") && n.getValue() == Operator.POW.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                val.push(Math.pow(num2, num1));
                q.poll();
            } else if (n.getType().equals("Operator") && n.getValue() == Operator.DIV.getValue()) {
                num1 = val.pop();
                num2 = val.pop();
                if (num1 == 0) {
                    System.out.println("Divisor can't be zero.");
                    System.exit(0);
                }
                val.push(num2 / num1);
                q.poll();
            }
        }
        return val.pop();
    }

    private void postOrder(Node root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            q.add(root);
        }
    }
}

