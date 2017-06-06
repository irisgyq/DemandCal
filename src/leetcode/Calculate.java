package leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class Calculate {
    private Stack<Double> numStk = new Stack<>();
    private Stack<String> opeStk = new Stack<>();

    public double calculate(String s) {

        Input in = new Input();
        ArrayList<String> operation = in.processInput(s);
        operation.add("equals");

        double num1, num2;
        double res;
        String ope;

        for(int i=0;i<operation.size();i++) {
            if (Character.isDigit(operation.get(i).charAt(0))) {
                numStk.push(Double.parseDouble(operation.get(i)));
            } else {
                opeStk.push(operation.get(i));

                while (compareOpe(opeStk.pop(), operation ,i)) {
                    if(opeStk.empty()){
                        break;
                    }
                    ope = opeStk.pop();

                    switch (ope) {
                        case "add": {
                            num1 = numStk.pop();
                            num2 = numStk.pop();
                            numStk.push(num2 + num1);
                            if(!opeStk.empty() && opeStk.peek().equals("div") && numStk.peek()==0) {
                                System.out.println("Divisor can't be zero.");
                                Main m = new Main();
                                m.calculator();
                                System.exit(0);
                            }
                            break;

                        }
                        case "sub": {
                            num1 = numStk.pop();
                            num2 = numStk.pop();
                            numStk.push(num2 - num1);
                            if(!opeStk.empty() && opeStk.peek().equals("div") && numStk.peek()==0) {
                                System.out.println("Divisor can't be zero.");
                                Main m = new Main();
                                m.calculator();
                                System.exit(0);
                            }
                            break;

                        }
                        case "mul": {
                            num1 = numStk.pop();
                            num2 = numStk.pop();
                            numStk.push(num2 * num1);
                            if(!opeStk.empty() && opeStk.peek().equals("div") && numStk.peek()==0) {
                                System.out.println("Divisor can't be zero.");
                                Main m = new Main();
                                m.calculator();
                                System.exit(0);
                            }

                            break;

                        }
                        case "div": {
                            num1 = numStk.pop();
                            num2 = numStk.pop();
                            if(num1==0){
                                System.out.println("Divisor can't be zero.");
                                Main m = new Main();
                                m.calculator();
                                System.exit(0);
                            }
                            numStk.push(num2 / num1);
                            if(!opeStk.empty() && opeStk.peek().equals("div") && numStk.peek()==0) {
                                System.out.println("Divisor can't be zero.");
                                Main m = new Main();
                                m.calculator();
                                System.exit(0);
                            }

                            break;

                        }

                        default:
                            break;
                    }

                    if(!opeStk.empty() && operation.get(i).equals("rbrace") && opeStk.contains("lbrace") && !opeStk.peek().equals("lbrace")) {
                        opeStk.push(operation.get(i));
                    } else if(!opeStk.empty() &&operation.get(i).equals("rbrace") && opeStk.peek().equals("lbrace")){
                        opeStk.pop();
                        break;
                    } else if(i==operation.size()-1 && !opeStk.isEmpty()){
                        opeStk.push("equals");
                    } else {
                        break;
                    }

                }

                if (!operation.get(i).equals("equals") && !operation.get(i).equals("rbrace")) {
                    opeStk.push(operation.get(i));
                }
            }

        }


        res = numStk.pop();
        return res;

    }

    public boolean compareOpe (String ope, ArrayList operation ,int i) {

        if (opeStk.empty() && i==operation.size()-1) {
            return true;
        }

        if (opeStk.empty()){
            return false;
        }

        String preOpe = opeStk.peek();

        switch (ope) {
            case "lbrace": {
                return false;
            }
            case "rbrace": {
                return true;
            }
            case "mul": {
                if(preOpe.equals("add") || preOpe.equals("sub")|| preOpe.equals("lbrace")) {
                    return false;
                } else {
                    return true;
                }
            }
            case "div": {
                if(preOpe.equals("add") || preOpe.equals("sub") || preOpe.equals("lbrace")) {
                    return false;
                } else {
                    return true;
                }
            }
            case "add": {
                if (preOpe.equals("mul") || preOpe.equals("div")){
                    return true;
                } else if(preOpe.equals("lbrace") || operation.get(i+2).equals("mul") || operation.get(i+2).equals("div") || operation.get(i+2).equals("mod")|| operation.get(i+2).equals("pow") || operation.get(i+2).equals("rbrace") ) {
                    return false;
                } else {
                    return true;
                }
            }
            case "sub": {
                if (preOpe.equals("mul") || preOpe.equals("div")){
                    return true;
                } else if(preOpe.equals("lbrace") || operation.get(i+2).equals("mul") || operation.get(i+2).equals("div") || operation.get(i+2).equals("mod")|| operation.get(i+2).equals("pow") || operation.get(i+2).equals("rbrace")) {
                    return false;
                } else {
                    return true;
                }
            }

            default:
                return true;
        }

    }

}

