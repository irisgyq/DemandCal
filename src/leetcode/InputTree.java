package leetcode;
import java.util.*;

public class InputTree {
    public ArrayList<Node> Tokenize(String s){
        if(s==null){
            return null;
        }

        ArrayList<Node> arr = new ArrayList<Node>();
        StringBuffer num = new StringBuffer();

        if(s.charAt(0)==' '){
            for(int z=1;z<s.length();z++){
                if(s.charAt(z)==' '){
                    break;
                } else if(s.charAt(z)=='-'){
                    arr.add(new NumberNode(0));
                    z=s.length();
                } else {
                    z = s.length();
                }
            }
        } else if(s.charAt(0)=='-'){
            arr.add(new NumberNode(0));
        }

        s = s.replace(" ","");

        for(int i=0;i<s.length();i++) {
            if (Character.isDigit(s.charAt(i))) {
                num.append(s.charAt(i));
                if (i == s.length() - 1) {
                    arr.add(new NumberNode(Integer.valueOf(num.toString())));
                }
            } else {
                String tmp = num.toString();
                if (!tmp.isEmpty()) {
                    arr.add(new NumberNode(Integer.valueOf(tmp)));
                    num = new StringBuffer();
                }
                int ic = (int) s.charAt(i);
                if (s.charAt(i) == ' ') {
                    break;
                } else if (ic == Operator.LEFT_BRACE.getValue()) {
                    arr.add(new OperatorNode(ic));
                    for (int j = i + 1; j < s.length(); j++) {
                        int iic = (int) s.charAt(j);
                        if (s.charAt(j) == ' ') {
                            break;
                        } else if (iic == Operator.SUB.getValue()) {
                            arr.add(new NumberNode(0));
                            j = s.length();
                        } else {
                            j = s.length();
                        }
                    }
                } else if (ic == Operator.ADD.getValue() || ic == Operator.SUB.getValue() || ic == Operator.MUL.getValue() || ic == Operator.DIV.getValue() ||  ic == Operator.RIGHT_BRACE.getValue()){
                    arr.add(new OperatorNode(ic));
                } else {
                    arr.add(null);
                }
            }
        }
        return arr;
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
        ArrayList<Node> operation = Tokenize(s);
        return isNumberOrOpe(operation) && isOpePosRight(operation) && isDivisorZero(operation) && isValidparentheses(operation);
    }

    public boolean isNumberOrOpe (ArrayList<Node> ac) {
        return !ac.contains(null);
    }

    public boolean isOpePosRight (ArrayList<Node> ac) {
        Node n0 = ac.get(0);
        int i0 = n0.getValue();
        Node nn = ac.get(ac.size()-1);
        int in = nn.getValue();
        if (!((n0.getName()==NumberNode.class || (n0.getName()==OperatorNode.class && (i0==Operator.LEFT_BRACE.getValue() || i0==Operator.SUB.getValue())))) || (!(nn.getName()==NumberNode.class || (nn.getName()==OperatorNode.class && in==Operator.RIGHT_BRACE.getValue())))) {
            System.out.println("The position of operators is wrong.");
            return false;
        }
        for (int i = 0; i < ac.size()-1; i++) {
            Node ni = ac.get(i);
            int ii = ni.getValue();
            Node nii = ac.get(i+1);
            int iii = nii.getValue();
            if ((ni.getName()==OperatorNode.class && (ii==Operator.ADD.getValue() || ii==Operator.SUB.getValue() || ii==Operator.MUL.getValue() || ii==Operator.DIV.getValue())) &&
                    (nii.getName()==OperatorNode.class && (iii==Operator.ADD.getValue() || iii==Operator.SUB.getValue() || iii==Operator.MUL.getValue() || iii==Operator.DIV.getValue() || iii==Operator.RIGHT_BRACE.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getName()==OperatorNode.class && ii==Operator.LEFT_BRACE.getValue() &&
                    (nii.getName()==OperatorNode.class && (iii==Operator.ADD.getValue() || iii==Operator.MUL.getValue() || iii==Operator.DIV.getValue() || iii==Operator.RIGHT_BRACE.getValue()))) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getName()==NumberNode.class && nii.getName()==OperatorNode.class && iii==Operator.LEFT_BRACE.getValue()) {
                System.out.println("The position of operators is wrong.");
                return false;
            } else if (ni.getName()==OperatorNode.class && ii==Operator.RIGHT_BRACE.getValue() && ((nii.getName()==OperatorNode.class && iii==Operator.LEFT_BRACE.getValue()) || nii.getName()==NumberNode.class )) {
                System.out.println("The position of operators is wrong.");
                return false;
            }
        }
        return true;
    }

    public boolean isDivisorZero (ArrayList<Node> ac) {
        for(int i=0;i<ac.size();i++){
            Node n = ac.get(i);
            if(n.getName()==OperatorNode.class && n.getValue()==Operator.DIV.getValue()){
                Node nn = ac.get(i+1);
                if(nn.getName()==NumberNode.class && nn.getValue()==0){
                    System.out.println("Divisor can't be zero.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidparentheses (ArrayList<Node> ac) {
        Stack<Node> parStk = new Stack<>();
        for(int i=0;i<ac.size();i++){
            Node n = ac.get(i);
            if (n.getName()==OperatorNode.class && n.getValue()==Operator.LEFT_BRACE.getValue()){
                parStk.push(new OperatorNode(Operator.RIGHT_BRACE.getValue()));
            } else if (n.getName()==OperatorNode.class && n.getValue()==Operator.RIGHT_BRACE.getValue()) {
                if (parStk.empty() || n.getValue()!=(parStk.pop().getValue())) {
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