package leetcode;

import java.util.ArrayList;
import java.util.Stack;

public class Input{

    public ArrayList<Object> processInput(String s) {
        if(s==null){
            return null;
        }
        ArrayList<Object> operation = new ArrayList<>();
        StringBuffer num = new StringBuffer();

        if(s.charAt(0)==' '){
            for(int z=1;z<s.length();z++){
                if(s.charAt(z)==' '){
                    break;
                } else if(s.charAt(z)=='-'){
                    operation.add(0);
                    z=s.length();
                } else {
                    z = s.length();
                }
            }
        } else if(s.charAt(0)=='-'){
            operation.add(0);
        }

        s = s.replace(" ","");

        for(int i=0;i<s.length();i++) {
            if (Character.isDigit(s.charAt(i))) {
                num.append(s.charAt(i));
                if(i==s.length()-1){
                    operation.add(Integer.valueOf(num.toString()));
                }
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    operation.add(Integer.valueOf(tmp));
                    num = new StringBuffer();
                }
                char sc = s.charAt(i);
                if (sc == ' ') {
                    break;
                } else if (sc == '(') {
                    operation.add(String.valueOf(sc));
                    for(int j=i+1;j<s.length();j++){
                        char ssc = s.charAt(j);
                        if(ssc==' '){
                            break;
                        } else if(ssc=='-'){
                            operation.add(0);
                            j=s.length();
                        } else {
                            j=s.length();
                        }
                    }
                } else if (sc == ')' || sc=='+' || sc == '-' || sc=='*' || sc=='/') {
                    operation.add(String.valueOf(sc));
                } else {
                    operation.add("err");
                }

            }
        }
        return operation;
    }

    //check whether the input is valid
    public boolean InputValid(String s) {
        if(s.length()==0) {
            return false;
        }

        //check whether there are spaces between digits
        for(int ii=0;ii<s.length()-1;ii++){
            if(Character.isDigit(s.charAt(ii)) && s.charAt(ii+1)==' ' ){
                for(int jj=ii+1;jj<s.length();jj++){
                    char cjj = s.charAt(jj);
                    if(cjj==' ') {
                        continue;
                    } else if (Character.isDigit(cjj)){
                        System.out.println("You can't input space between digits.");
                        return false;
                    } else {
                        jj=s.length();
                    }
                }
            }
        }

        ArrayList<Object> operation = processInput(s);

        return isNumberOrOpe(operation) && isOpePosRight(operation) && isDivisorZero(operation) && isValidparentheses(operation);
    }

    public boolean isNumberOrOpe (ArrayList ac) {
        return !ac.contains("err");
    }

    public boolean isOpePosRight (ArrayList ac) {
        if (!(Character.isDigit(ac.get(0).toString().charAt(0)) || ac.get(0).toString().equals("(") || ac.get(0).toString().equals("-")) || (!(Character.isDigit(ac.get(ac.size() - 1).toString().charAt(0)) || ac.get(ac.size() - 1).toString().equals(")")))) {
            System.out.println("The position of operators is wrong.");
            return false;
        }
        String tmps;
        String tmpss;
        for (int i = 0; i < ac.size()-1; i++) {
            tmps = ac.get(i).toString();
            tmpss = ac.get(i + 1).toString();
            if ((tmps.equals("+") || tmps.equals("-") || tmps.equals("*") || tmps.equals("/") ) &&
                    (tmpss.equals("+") || tmpss.equals("-") || tmpss.equals("*") || tmpss.equals("/") || tmpss.equals(")"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (tmps.equals("(") &&
                    (tmpss.equals("+") || tmpss.equals("*") || tmpss.equals("/") ||  tmpss.equals(")"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (Character.isDigit(tmps.charAt(0)) && (tmpss.equals("("))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if ((tmps.equals(")")) && (tmpss.equals("(") || Character.isDigit(tmpss.charAt(0)) )) {
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }

    public boolean isDivisorZero (ArrayList ac) {
        for(int i=0;i<ac.size();i++){
            if(ac.get(i).toString().equals("/")){
                if(ac.get(i+1).toString().equals("0")){
                    System.out.println("Divisor can't be zero.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidparentheses (ArrayList ac) {
        Stack<String> parStk = new Stack<>();

        for(Object so : ac){
            if (so.toString().equals("(")){
                parStk.push(")");
            } else if (so.toString().equals(")")) {
                if (parStk.empty() || !so.toString().equals(parStk.pop())) {
                    System.out.println("parentheses are wrong.");
                    return false;
                }
            }
        }
        if(!parStk.isEmpty()) {
            System.out.println("parentheses are wrong.");
        }
        return parStk.isEmpty();
    }
}