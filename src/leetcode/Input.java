package leetcode;

import java.util.ArrayList;
import java.util.Stack;


class Input {
        ArrayList<String> processInput(String s) {
        if (s.equals("")) {
            return null;
        }
        ArrayList<String> operation = new ArrayList<>();

        StringBuffer num = new StringBuffer();

        if(s.charAt(0)==' '){
            for(int z=1;z<s.length();z++){
                if(s.charAt(z)==' '){
                    break;
                } else if(s.charAt(z)=='-'){
                    operation.add("0");
                    z=s.length();
                } else {
                    z = s.length();
                }
            }
        } else if(s.charAt(0)=='-'){
            operation.add("0");
        }

        for(int i=0;i<s.length();i++) {
            if (Character.isDigit(s.charAt(i))) {
                num.append(s.charAt(i));
                if(i==s.length()-1){
                    operation.add(num.toString());
                }
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    operation.add(tmp);
                    num = new StringBuffer();
                }
                if (s.charAt(i) == ' ') {
                    break;
                } else if (s.charAt(i) == '(') {
                    operation.add("lbrace");
                    for(int j=i+1;j<s.length();j++){
                        if(s.charAt(j)==' '){
                            break;
                        } else if(s.charAt(j)=='-'){
                            operation.add("0");
                            j=s.length();
                        } else {
                            j=s.length();
                        }
                    }
                } else if (s.charAt(i) == ')') {
                    operation.add("rbrace");
                } else if (s.charAt(i) == '+') {
                    operation.add("add");
                } else if (s.charAt(i) == '-') {
                    operation.add("sub");
                } else if (s.charAt(i) == '*') {
                    operation.add("mul");
                } else if (s.charAt(i) == '/') {
                    operation.add("div");
                } else {
                    operation.add("err");
                }

            }
        }
        return operation;
    }

    //check whether the input is valid
    boolean InputValid(String s) {

        if(s.length()==0) {
            return false;
        }

        //check whether there are spaces between digits
        for(int ii=0;ii<s.length()-1;ii++){
            if(Character.isDigit(s.charAt(ii)) && s.charAt(ii+1)==' ' ){
                for(int jj=ii+1;jj<s.length();jj++){
                    if(s.charAt(jj)==' ') {
                        break;
                    } else if (Character.isDigit(s.charAt(jj))){
                        System.out.println("You can't input space between digits.");
                        return false;
                    } else {
                        jj=s.length();
                    }
                }
            }
        }

        ArrayList<String> operation = processInput(s);

        return isNumberOrOpe(operation) && isOpePosRight(operation) && isDivisorZero(operation) && isValidparentheses(operation);
    }

    private boolean isNumberOrOpe (ArrayList ac) {
        return !ac.contains("err");
    }

    private boolean isOpePosRight (ArrayList ac) {
        if (!(Character.isDigit(ac.get(0).toString().charAt(0)) || ac.get(0).toString().equals("lbrace") || ac.get(0).toString().equals("sub")) || (!(Character.isDigit(ac.get(ac.size() - 1).toString().charAt(0)) || ac.get(ac.size() - 1).toString().equals("rbrace")))) {
            System.out.println("The position of operators is wrong.");
            return false;
        }
        String tmps;
        String tmpss;
        for (int i = 0; i < ac.size()-1; i++) {
            tmps = ac.get(i).toString();
            tmpss = ac.get(i + 1).toString();
            if ((tmps.equals("add") || tmps.equals("sub") || tmps.equals("mul") || tmps.equals("div") ) &&
                    (tmpss.equals("add") || tmpss.equals("sub") || tmpss.equals("mul") || tmpss.equals("div") || tmpss.equals("rbrace"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (tmps.equals("lbrace") &&
                    (tmpss.equals("add") || tmpss.equals("mul") || tmpss.equals("div") ||  tmpss.equals("rbrace"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (Character.isDigit(tmps.charAt(0)) && (tmpss.equals("lbrace"))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if ((tmps.equals("rbrace")) && (tmpss.equals("lbrace") || Character.isDigit(tmpss.charAt(0)) )) {
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }

    private boolean isDivisorZero (ArrayList ac) {
        for(int i=0;i<ac.size();i++){
            if(ac.get(i).toString().equals("div")){
                if(ac.get(i+1).toString().equals("0")){
                    System.out.println("Divisor can't be zero.");
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidparentheses (ArrayList ac) {

        Stack<String> parStk = new Stack<>();

        for(Object so : ac){
            if (so.toString().equals("lbrace")) {
                parStk.push("rbrace");
            } else if (so.toString().equals("rbrace")) {
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