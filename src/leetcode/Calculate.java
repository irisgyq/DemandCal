package leetcode;

import java.util.ArrayList;
import java.util.Stack;

class Calculate {
    private Stack<Double> numStk = new Stack<>();
    private Stack<String> opeStk = new Stack<>();

    double calculate(String s) {

        Input in = new Input();
        ArrayList<String> operation = in.processInput(s);
        operation.add("equals");

        double num1, num2, res;
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

    private boolean compareOpe (String ope, ArrayList operation ,int i) {

        if (opeStk.empty()) {
            return i==operation.size()-1;
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
                return !(preOpe.equals("add") || preOpe.equals("sub")|| preOpe.equals("lbrace"));
            }
            case "div": {
                return !(preOpe.equals("add") || preOpe.equals("sub") || preOpe.equals("lbrace"));

            }
            case "add": {
                return preOpe.equals("mul") || preOpe.equals("div") || !(preOpe.equals("lbrace") || operation.get(i+2).equals("mul") || operation.get(i+2).equals("div") || operation.get(i+2).equals("mod")|| operation.get(i+2).equals("pow") || operation.get(i+2).equals("rbrace") );
            }
            case "sub": {
                return preOpe.equals("mul") || preOpe.equals("div") || !(preOpe.equals("lbrace") || operation.get(i+2).equals("mul") || operation.get(i+2).equals("div") || operation.get(i+2).equals("mod")|| operation.get(i+2).equals("pow") || operation.get(i+2).equals("rbrace"));
            }

            default:
                return true;
        }
    }
}