package leetcode;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Main m = new Main();
        boolean flag = false;
        System.out.println("---------Welcome to my calculator-------------");
        do {
            System.out.println("start or end?");
            Scanner scan = new Scanner(System.in);
            String ins = scan.nextLine();
            if (ins.equals("start")) {
                flag = true;
                m.calculator();
            } else if (ins.equals("end")) {
                flag = false;
                m.off();
            }  else {
                System.out.println("Nonono! You need to tell me 'start' or 'end'...");
                flag = true;
            }
        } while (flag);
    }

    public void reset() {
        Main m = new Main();
        m.calculator();
    }

    public void off(){
        System.exit(0);
    }

    public void calculator() {
        boolean isValid = true;
        String FinalInput;
        do {
            System.out.println("Please input an operation:");

            Scanner scan = new Scanner(System.in);
            String inputS = scan.nextLine();

            Input input = new Input();
            isValid = input.InputValid(inputS);
            FinalInput =inputS.replaceAll(" ","");

        } while (!isValid);

        Calculate cal = new Calculate();
        System.out.println("The result is "+cal.calculate(FinalInput));
    }
}

