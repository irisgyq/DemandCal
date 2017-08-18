package calculator;

import java.util.ArrayList;
import java.util.Stack;


public class InputProcess {
    public ArrayList<Token> tokenize(String s) {
        if (s == null) {
            return null;
        }

        ArrayList<Token> arr = new ArrayList<Token>();
        StringBuffer num = new StringBuffer();

        s = s.replace(" ", "");

        if (s.charAt(0) == '-') {
            arr.add(new Token(0,"Number"));
        }

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num.append(s.charAt(i));
                if (i == s.length() - 1) {
                    arr.add(new Token(Integer.valueOf(num.toString()),"Number"));
                }
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    arr.add(new Token(Integer.valueOf(tmp),"Number"));
                    num = new StringBuffer();
                }
                int ic = -1;
                if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/' || s.charAt(i)=='%'
                || s.charAt(i)=='^' || s.charAt(i)=='(' || s.charAt(i)==')'){
                    ic = (int) s.charAt(i);
                } else if(s.charAt(i)=='√') {
                    ic = 128;
                } else if(s.charAt(i)=='l' && i!=s.length()-1){
                    if(s.charAt(i+1)=='g'){
                        ic = 129;
                        i++;
                    } else if(s.charAt(i+1)=='n'){
                        ic = 131;
                        i++;
                    } else if(s.charAt(i+1)=='o' && i+1!=s.length()-1 && s.charAt(i+2)=='g'){
                        ic = 130;
                        i = i+2;
                    }
                }

                if (s.charAt(i) == ' ') {
                    break;
                } else if (ic == Operator.LEFT_BRACE.getValue()) {
                    arr.add(new Token(ic,"Operator"));
                    for (int j = i + 1; j < s.length(); j++) {
                        int iic = (int) s.charAt(j);
                        if (s.charAt(j) == ' ') {
                            break;
                        } else if (iic == Operator.SUB.getValue()) {
                            arr.add(new Token(0,"Number"));
                            j = s.length();
                        } else {
                            j = s.length();
                        }
                    }
                } else if (ic == Operator.ADD.getValue() || ic == Operator.SUB.getValue() || ic == Operator.MUL.getValue() || ic == Operator.DIV.getValue() || ic == Operator.RIGHT_BRACE.getValue() || ic == Operator.POW.getValue() ||
                        ic == Operator.MOD.getValue() || ic == Operator.SQRT.getValue() || ic == Operator.LG.getValue() || ic == Operator.LOG.getValue() || ic == Operator.LN.getValue()) {
                    arr.add(new Token(ic,"Operator"));
                } else {
                    arr.add(null);
                }
            }
        }
        return arr;
    }

    //check whether the input is valid
    public boolean inputValid(String s) {
        if (s.length() == 0) {
            return false;
        }
        //check whether there are spaces between digits
        for (int ii = 0; ii < s.length() - 1; ii++) {
            if (Character.isDigit(s.charAt(ii)) && s.charAt(ii + 1) == ' ') {
                for (int jj = ii + 1; jj < s.length(); jj++) {
                    char cjj = s.charAt(jj);
                    if (cjj == ' ') {
                        continue;
                    } else if (Character.isDigit(cjj)) {
                        System.out.println("You can't input space between digits.");
                        return false;
                    } else {
                        jj = s.length();
                    }
                }
            }
        }
        ArrayList<Token> operation = tokenize(s);
        return isNumberOrOpe(operation) && isOpePosRight(operation) && isDivisorZero(operation) && isLogNeg(operation) && isValidparentheses(operation);
    }

    public boolean isNumberOrOpe(ArrayList<Token> ac) {
        if(ac.contains(null)){
            System.out.println("Your input contains other characters.");
        }
        return !ac.contains(null);
    }

    public boolean isOpePosRight(ArrayList<Token> ac) {
        Token t0 = ac.get(0);
        int i0 = t0.getValue();
        Token tn = ac.get(ac.size() - 1);
        int in = tn.getValue();
        if (!((t0.getType().equals("Number") || (t0.getType().equals("Operator") && (i0 == Operator.LEFT_BRACE.getValue() || i0 == Operator.SUB.getValue() || i0 == Operator.LG.getValue() || i0 == Operator.LN.getValue() || i0 == Operator.LOG.getValue() || i0==Operator.SQRT.getValue())))) || (!(tn.getType().equals("Number") || (tn.getType().equals("Operator") && in == Operator.RIGHT_BRACE.getValue())))) {
            System.out.println("The position of operators is wrong.");
            return false;
        }
        for (int i = 0; i < ac.size() - 1; i++) {
            Token ni = ac.get(i);
            int ii = ni.getValue();
            Token nii = ac.get(i + 1);
            int iii = nii.getValue();
            if ((ni.getType().equals("Operator") && (ii == Operator.ADD.getValue() || ii == Operator.SUB.getValue() || ii == Operator.MUL.getValue() || ii == Operator.DIV.getValue() || ii == Operator.MOD.getValue() || ii == Operator.POW.getValue())) &&
                    (nii.getType().equals("Operator") && (iii == Operator.ADD.getValue() || iii == Operator.SUB.getValue() || iii == Operator.MUL.getValue() || iii == Operator.DIV.getValue() || iii == Operator.RIGHT_BRACE.getValue() || iii == Operator.MOD.getValue() || iii == Operator.POW.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Operator") && ii == Operator.LEFT_BRACE.getValue() &&
                    (nii.getType().equals("Operator") && (iii == Operator.ADD.getValue() || iii == Operator.MUL.getValue() || iii == Operator.DIV.getValue() || iii == Operator.MOD.getValue() || iii == Operator.POW.getValue() || iii == Operator.RIGHT_BRACE.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Number") && nii.getType().equals("Operator") && iii == Operator.LEFT_BRACE.getValue()) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Operator") && ii == Operator.RIGHT_BRACE.getValue() && ((nii.getType().equals("Operator") && (iii == Operator.LEFT_BRACE.getValue() || iii == Operator.SQRT.getValue() || iii == Operator.LG.getValue() || iii == Operator.LOG.getValue() || iii == Operator.LN.getValue())) || nii.getType().equals("Number"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }

    public boolean isDivisorZero(ArrayList<Token> ac) {
        for (int i = 0; i < ac.size(); i++) {
            Token t = ac.get(i);
            if (t.getType().equals("Operator") && t.getValue() == Operator.DIV.getValue()) {
                Token tn = ac.get(i + 1);
                if (tn.getType().equals("Number") && tn.getValue() == 0) {
                    System.out.println("Divisor can't be zero.");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isLogNeg(ArrayList<Token> ac) {
        for(int i=0;i<ac.size();i++){
            Token t = ac.get(i);
            if(t.getType().equals("Operator") && (t.getValue() == Operator.LG.getValue() || t.getValue() == Operator.LOG.getValue() || t.getValue() == Operator.LN.getValue()) ){
                Token tn = ac.get(i+1);
                if(tn.getType().equals("Number") && tn.getValue()<=0) {
                    System.out.println("Log can't be used on negative number.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidparentheses(ArrayList<Token> ac) {
        Stack<Token> parStk = new Stack<Token>();
        for (int i = 0; i < ac.size(); i++) {
            Token t = ac.get(i);
            if (t.getType().equals("Operator") && t.getValue() == Operator.LEFT_BRACE.getValue()) {
                parStk.push(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
            } else if (t.getType().equals("Operator") && t.getValue() == Operator.RIGHT_BRACE.getValue()) {
                if (parStk.empty() || t.getValue() != (parStk.pop().getValue())) {
                    System.out.println("parentheses are wrong.");
                    return false;
                }
            }
        }
        if (!parStk.isEmpty()) {
            System.out.println("parentheses are wrong.");
        }
        return parStk.isEmpty();
    }
}