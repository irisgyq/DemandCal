package leetcode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by irisgyq on 2017/6/2.
 */
public class Input {
    public ArrayList<String> processInput(String s) {
        if (s.equals("")) {
            return null;
        }

        s = s.replaceAll(" ","");
        s = s.replace("(-","(0-");
        if(s.charAt(0)=='-'){
            s = "0"+s;
        }

        StringBuffer num = new StringBuffer();
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                num.append(c);
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    s = s.replace(tmp,tmp+",");
                    s = s.replaceAll(",,",",");
                    num = new StringBuffer();
                }

            }
            for(int mm=0;mm<s.length()-2;mm++){
                if(Character.isDigit(s.charAt(mm)) && s.charAt(mm+1)==',' && Character.isDigit(s.charAt(mm+2))) {
                    s=s.substring(0,mm+1) + s.substring(mm+2);
                }
            }
        }



        String add = String.valueOf('+');
        s = s.replace(add,"add");
        String sub = String.valueOf('-');
        s = s.replace(sub,"sub");
        String mul = String.valueOf('*');
        s = s.replace(mul,"mul");
        String div = String.valueOf('/');
        s = s.replace(div,"div");
        String lbrace = String.valueOf('(');
        s = s.replace(lbrace,"lbrace");
        String rbrace = String.valueOf(')');
        s = s.replace(rbrace,"rbrace");

        for(Operation e : Operation.values()){
            String ope = e.toString();
            s = s.replace(ope,ope+",");
        }

        if(s.charAt(s.length()-1) == ',') {
            s = s.substring(0,s.length()-1);
        }

        s = s.replaceAll(",,",",");

        ArrayList<String> operation = new ArrayList<>();
        int pos=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==','){
                operation.add(s.substring(pos,i));
                pos=i+1;
            }
            if (i==s.length()-1){
                operation.add(s.substring(pos));
            }
        }

        return operation;
    }

    public boolean InputValid(String s) {

        if(s.length()==0) {
            return false;
        }

        for(int ii=0;ii<s.length()-1;ii++){
            if(Character.isDigit(s.charAt(ii)) && s.charAt(ii+1)==' ' ){
                for(int jj=ii+1;jj<s.length();jj++){
                    if(s.charAt(jj)==' ') {
                        continue;
                    } else if (Character.isDigit(s.charAt(jj))){
                        System.out.println("You can't input space between digits.");
                        return false;
                    } else {
                        break;
                    }
            }


            }
        }

        ArrayList<String> operation = processInput(s);

        ArrayList<String> ope = new ArrayList<>();
        for(Operation e : Operation.values()){
            ope.add(e.toString());
        }

        if (!isNumberOrOpe(operation, ope) || !isOpePosRight(operation) || !isDivisorZero(operation)  ) {
            return false;
        }

        if (!isValidparentheses(operation)) {
            return false;
        }

        return true;
    }

    public boolean isNumberOrOpe (ArrayList ac, ArrayList ope) {
        String s="";
        for(int i=0;i<ac.size();i++){
            if(!ope.contains(ac.get(i).toString())){
                for(int j=0;j<ac.get(i).toString().length();j++){
                    if(!Character.isDigit(ac.get(i).toString().charAt(j))){
                        System.out.println("There is unexpected characters.");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isOpePosRight (ArrayList ac) {
        String tmps = "";
        String tmpss = "";
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
            } else if (!(Character.isDigit(ac.get(0).toString().charAt(0)) || ac.get(0).toString().equals("lbrace") || ac.get(0).toString().equals("sub")) || (!(Character.isDigit(ac.get(ac.size() - 1).toString().charAt(0)) || ac.get(ac.size() - 1).toString().equals("rbrace")))) {
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }


    public boolean isDivisorZero (ArrayList ac) {
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

    public boolean isValidparentheses (ArrayList ac) {

        Stack<String> parStk = new Stack<>();

        for(int i=0;i<ac.size();i++) {
            if (ac.get(i).toString().equals("lbrace")) {
                parStk.push("rbrace");
            } else if (ac.get(i).toString().equals("rbrace")) {
                if (parStk.empty() || !ac.get(i).toString().equals(parStk.pop())) {
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
