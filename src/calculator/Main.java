package calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean flag = false;
        System.out.println("---------Welcome to my calculator-------------");
        do {
            System.out.println("start or end?");
            Scanner scan = new Scanner(System.in);
            String ins = scan.nextLine();
            switch (ins) {
                case "start":
                    flag = true;
                    boolean isValid = false;
                    String FinalInput;
                    do {
                        System.out.println("Please input an operation:");

                        Scanner sc = new Scanner(System.in);
                        String inputS = sc.nextLine();

                        InputProcess input = new InputProcess();
                        isValid = input.inputValid(inputS);
                        FinalInput = inputS.replaceAll(" ", "");

                    } while (!isValid);
                    Calculate cal = new Calculate();
                    InputProcess in = new InputProcess();
                    System.out.println("The result is " + cal.calculate(cal.createTree(in.tokenize(FinalInput))));
                    break;
                case "end":
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nonono! You need to tell me 'start' or 'end'...");
                    break;
            }
        } while (flag);
    }
}