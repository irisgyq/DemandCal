package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InputProcess {

    public ArrayList<Token> tokenize(String s) {
        if (s == null) {
            return null;
        }

        ArrayList<Token> arr = new ArrayList<Token>();
        StringBuffer num = new StringBuffer();

        if (s.charAt(0) == '-') {
            arr.add(new Token(0,"Number"));
        }

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) || s.charAt(i)=='.') {
                num.append(s.charAt(i));
                if (i == s.length() - 1) {
                    arr.add(new Token(Double.valueOf(num.toString()),"Number"));
                }
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    arr.add(new Token(Double.valueOf(tmp),"Number"));
                    num = new StringBuffer();
                }
                int ic = -1;
                if(s.charAt(i)=='+' || s.charAt(i)=='-' || s.charAt(i)=='*' || s.charAt(i)=='/' || s.charAt(i)=='%'
                        || s.charAt(i)=='^' || s.charAt(i)=='(' || s.charAt(i)==')'){
                    ic = (int) s.charAt(i);
                } else if(s.charAt(i)=='√') {
                    ic = Operator.SQRT.getValue();
                } else if(s.charAt(i)=='π') {
                    ic = Operator.PI.getValue();
                } else if(s.charAt(i)=='e'){
                    ic = Operator.E.getValue();
                } else if(s.charAt(i)=='l' && i!=s.length()-2 && s.charAt(i+1)=='o' && s.charAt(i+2)=='g'){
                    ic = Operator.LOG.getValue();
                    i = i+2;
                } else if(s.charAt(i)=='c' && s.charAt(i+1)=='o' && i!=s.length()-2 && s.charAt(i+2)=='s'){
                    ic = Operator.COS.getValue();
                    i = i+2;
                } else if(s.charAt(i)=='s' && s.charAt(i+1)=='i' && i!=s.length()-2 && s.charAt(i+2)=='n'){
                    ic = Operator.SIN.getValue();
                    i = i+2;
                } else if(s.charAt(i)=='t' && s.charAt(i+1)=='a' && i!=s.length()-2 && s.charAt(i+2)=='n'){
                    ic = Operator.TAN.getValue();
                    i = i+2;
                } else if(s.charAt(i) == ',') {
                    ic = Operator.COMMA.getValue();
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
                        ic == Operator.MOD.getValue() || ic == Operator.SQRT.getValue()  || ic == Operator.LOG.getValue() || ic==Operator.COMMA.getValue() || ic == Operator.COS.getValue() || ic == Operator.SIN.getValue() || ic == Operator.TAN.getValue() ) {
                    arr.add(new Token(ic,"Operator"));
                }  else if(ic == Operator.PI.getValue()) {
                    arr.add(new Token(Math.PI,"Number"));
                } else if(ic == Operator.E.getValue()) {
                    arr.add(new Token(Math.E,"Number"));
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

        //check whether there are wrong commas
        for (int ii = 1; ii < s.length() - 1; ii++) {
            if (s.charAt(ii) == ',') {
                if(!s.contains("log")) {
                    System.out.println("There are wrong commas");
                    return false;
                }
            }
        }

        //check whether there are multiple points
        if(s.charAt(0)=='.' || s.charAt(s.length()-1)=='.') return false;
        for (int ii = 1; ii < s.length() - 1; ii++) {
            if (s.charAt(ii) == '.') {
                if (!Character.isDigit(s.charAt(ii - 1))) {
                    System.out.println("These are wrong points.");
                    return false;
                } else if (!Character.isDigit(s.charAt(ii + 1))) {
                    System.out.println("These are wrong points.");
                    return false;
                } else {
                    for (int jj = ii + 2; jj < s.length(); jj++) {
                        if (s.charAt(jj) == '.') {
                            String ss = s.substring(ii + 2, jj);
                            if (!(ss.contains("+") || ss.contains("-") || ss.contains("*") || ss.contains("/") || ss.contains("%")
                                    || ss.contains("^") || ss.contains(")"))) {
                                System.out.println("These are wrong points.");
                                return false;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Token> operation = tokenize(s);
        return isNumberOrOpe(operation) && isOpePosRight(operation) && isValidparentheses(operation) && isValidTan(operation) && isLogWrong(operation) && isDivisorZero(operation);
    }

    public boolean isNumberOrOpe(ArrayList<Token> ac) {
        if(ac.contains(null)) System.out.println("Your input contains other characters.");
        return !ac.contains(null);
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

    public boolean isOpePosRight(ArrayList<Token> ac) {
        Token t0 = ac.get(0);
        double i0 = t0.getValue();
        Token tn = ac.get(ac.size() - 1);
        double in = tn.getValue();
        if (!((t0.getType().equals("Number") || (t0.getType().equals("Operator") && (i0 == Operator.LEFT_BRACE.getValue() || i0 == Operator.SUB.getValue() || i0 == Operator.COS.getValue() || i0 == Operator.SIN.getValue() || i0 == Operator.TAN.getValue() || i0 == Operator.LOG.getValue() || i0==Operator.SQRT.getValue())))) || (!(tn.getType().equals("Number") || (tn.getType().equals("Operator") && in == Operator.RIGHT_BRACE.getValue())))) {
            System.out.println("The position of operators is wrong.");
            return false;
        }
        for (int i = 0; i < ac.size() - 1; i++) {
            Token ni = ac.get(i);
            double ii = ni.getValue();
            Token nii = ac.get(i + 1);
            double iii = nii.getValue();
            if ((ni.getType().equals("Operator") && (ii == Operator.ADD.getValue() || ii == Operator.SUB.getValue() || ii == Operator.MUL.getValue() || ii == Operator.DIV.getValue() || ii == Operator.MOD.getValue() || ii == Operator.POW.getValue() || ii == Operator.COMMA.getValue())) &&
                    (nii.getType().equals("Operator") && (iii == Operator.ADD.getValue() || iii == Operator.SUB.getValue() || iii == Operator.MUL.getValue() || iii == Operator.DIV.getValue() || iii == Operator.RIGHT_BRACE.getValue() || iii == Operator.MOD.getValue() || iii == Operator.POW.getValue() || iii == Operator.COMMA.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Operator") && ii == Operator.LEFT_BRACE.getValue() &&
                    (nii.getType().equals("Operator") && (iii == Operator.ADD.getValue() || iii == Operator.MUL.getValue() || iii == Operator.DIV.getValue() || iii == Operator.MOD.getValue() || iii == Operator.POW.getValue() || iii == Operator.RIGHT_BRACE.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Number") && nii.getType().equals("Operator") && (iii == Operator.LEFT_BRACE.getValue() || iii == Operator.LOG.getValue()  || iii == Operator.SQRT.getValue() || iii == Operator.COS.getValue() || iii == Operator.SIN.getValue() || iii == Operator.TAN.getValue() )) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Operator") && ii == Operator.RIGHT_BRACE.getValue() && ((nii.getType().equals("Operator") && (iii == Operator.LEFT_BRACE.getValue() || iii == Operator.SQRT.getValue() || iii == Operator.COS.getValue() || iii == Operator.SIN.getValue() || iii == Operator.TAN.getValue() || iii == Operator.LOG.getValue()  || nii.getType().equals("Number"))))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getType().equals("Operator") && ii == Operator.LOG.getValue() && !(nii.getType().equals("Operator") && iii==Operator.LEFT_BRACE.getValue())){
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }

    public boolean isValidTan(ArrayList<Token> ac){
        for(int i=0;i<ac.size();i++){
            if(ac.get(i).getType().equals("Operator") && ac.get(i).getValue()==Operator.TAN.getValue()){
                if(i!=ac.size()-1){
                    double d = (ac.get(i+1).getValue()-(Math.PI/2))/Math.PI;
                    int dd = (int)((ac.get(i+1).getValue()-(Math.PI/2))/Math.PI);
                    if(d-dd==0.0) {
                        System.out.println("Your tan is wrong.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isLogWrong(ArrayList<Token> ac) {
        for(int i=0;i<ac.size();i++) {
            int flag1 = 1;
            List<Token> aaaa = new ArrayList<>();
            List<Token> bbbb = new ArrayList<>();
            List<Token> cccc = new ArrayList<>();
            if (ac.get(i).getType().equals("Operator") && ac.get(i).getValue() == Operator.LOG.getValue()) {
                for (int j = i + 2; j < ac.size(); j++) {
                    if (ac.get(j).getType().equals("Operator") && ac.get(j).getValue() == Operator.LEFT_BRACE.getValue()) flag1++;
                    else if (ac.get(j).getType().equals("Operator") && ac.get(j).getValue() == Operator.RIGHT_BRACE.getValue()) {
                        flag1--;
                        if (flag1 == 0) {
                            aaaa = ac.subList(i + 2, j);
                            bbbb = ac.subList(0,i);
                            cccc = ac.subList(j+1,ac.size());
                            j = ac.size();
                        }
                    }
                }
                int count=0, count1=0, count2=0;
                for (int k = 0; k < aaaa.size(); k++) {
                    if(aaaa.get(k).getType().equals("Operator") && aaaa.get(k).getValue()==135) count++;
                }
                for (int k = 0; k < bbbb.size(); k++) {
                    if(bbbb.get(k).getType().equals("Operator") && bbbb.get(k).getValue()==135) count1++;
                }
                for (int k = 0; k < cccc.size(); k++) {
                    if(cccc.get(k).getType().equals("Operator") && cccc.get(k).getValue()==135) count2++;
                }
                if(count==0 || count>1) {
                    System.out.println("Your log is wrong.");
                    return false;
                }
                if(count1!=0 || count2!=0){
                    System.out.println("There are wrong commas");
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
        if (!parStk.isEmpty()) System.out.println("parentheses are wrong.");
        return parStk.isEmpty();
    }
}