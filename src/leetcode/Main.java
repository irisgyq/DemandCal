package leetcode;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Main m = new Main();
        boolean flag=false;
        System.out.println("---------Welcome to my calculator-------------");
        do {
            System.out.println("start or end?");
            Scanner scan = new Scanner(System.in);
            String ins = scan.nextLine();
            switch(ins){
                case "start":
                    flag = true;
                    m.calculator();
                    break;
                case "end":
                    flag = false;
                    m.off();
                    break;
                default:
                    System.out.println("Nonono! You need to tell me 'start' or 'end'...");
            }
        } while (flag);
    }

    private void off(){
        System.exit(0);
    }

    void calculator() {
        boolean isValid = false;
        String FinalInput;
        do {
            System.out.println("Please input an operation:");

            Scanner scan = new Scanner(System.in);
            String inputS = scan.nextLine();

            InputTree input = new InputTree();
            isValid = input.InputValid(inputS);
            FinalInput =inputS.replaceAll(" ","");

        } while (!isValid);
        CalculateTree cal = new CalculateTree();
        InputTree in = new InputTree();
        System.out.println("The result is "+cal.calculate(cal.createTree(in.Tokenize(FinalInput))));
    }
}