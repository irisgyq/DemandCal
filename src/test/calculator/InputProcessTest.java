package test.calculator;

import calculator.Token;
import calculator.InputProcess;
import calculator.Operator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * InputProcess Tester.
 *
 * @author <Iris>
 * @version 1.0
 * @since <pre>Jun 30, 2017</pre>
 */
public class InputProcessTest {

    InputProcess tt;

    @Before
    public void before() throws Exception {
        tt = new InputProcess();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: Tokenize(String s)
     */
    @Test
    public void testTokenize() throws Exception {
        String s = "-1+(2+3)*2";
        ArrayList<Token> arr = new ArrayList<Token>();
        arr.add(new Token(0,"Number"));
        arr.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr.add(new Token(1,"Number"));
        arr.add(new Token(Operator.ADD.getValue(),"Operator"));
        arr.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr.add(new Token(2,"Number"));
        arr.add(new Token(Operator.ADD.getValue(),"Operator"));
        arr.add(new Token(3,"Number"));
        arr.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        arr.add(new Token(Operator.MUL.getValue(),"Operator"));
        arr.add(new Token(2,"Number"));
        ArrayList<Token> n = tt.tokenize(s);
        assertTrue(arr.size() == n.size());
        for (int i = 0; i < arr.size(); i++) {
            assertTrue(arr.get(i).getValue() == n.get(i).getValue());
            assertTrue(arr.get(i).getType().equals(n.get(i).getType()));
        }

        String s1 = "       -5";
        ArrayList<Token> arr1 = new ArrayList<>();
        arr1.add(new Token(0,"Number"));
        arr1.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr1.add(new Token(5,"Number"));
        ArrayList<Token> n1 = tt.tokenize(s1);
        assertTrue(arr1.size() == n1.size());
        for (int i = 0; i < arr1.size(); i++) {
            assertTrue(arr1.get(i).getValue() == n1.get(i).getValue());
            assertTrue(arr1.get(i).getType().equals(n1.get(i).getType()));
        }
    }

    /**
     * Method: InputValid(String s)
     */
    @Test
    public void testInputValid() throws Exception {

        boolean f1 = tt.inputValid("1+2+3");
        assertTrue(f1);
        boolean f3 = tt.inputValid(")1+1(");
        assertFalse(f3);
        boolean f4 = tt.inputValid("-100");
        assertTrue(f4);
        boolean f5 = tt.inputValid("(100)");
        assertTrue(f5);
        boolean f6 = tt.inputValid("(1 + (20*30) -16)/(9/(10 2*3))");
        assertFalse(f6);
        boolean f7 = tt.inputValid("(1 + (20*30) -16)/(9/(10 2*3))+4");
        assertFalse(f7);
        boolean f8 = tt.inputValid("(1 + (20*30) -16)/(9/(10 - 2*3))");
        assertTrue(f8);
        boolean f9 = tt.inputValid("-(100)");
        assertTrue(f9);
        boolean f10 = tt.inputValid("-1+2+3");
        assertTrue(f10);
        boolean f11 = tt.inputValid("(-1+2+3)");
        assertTrue(f11);
        boolean f12 = tt.inputValid("-(-1)");
        assertTrue(f12);
        boolean f13 = tt.inputValid("(-)");
        assertFalse(f13);
        boolean f14 = tt.inputValid("(-1)*(-1)");
        assertTrue(f14);
        boolean f15 = tt.inputValid("1+)-1(");
        assertFalse(f15);
        boolean f16 = tt.inputValid("1 + ) -1 ( -3");
        assertFalse(f16);

    }

    /**
     * Method: isNumberOrOpe(ArrayList ac)
     */
    @Test
    public void testIsNumberOrOpe() throws Exception {

        ArrayList<Token> arr = new ArrayList<Token>();
        arr.add(new Token(0,"Number"));
        arr.add(null);
        arr.add(new Token(2,"Number"));
        assertFalse(tt.isNumberOrOpe(arr));
    }

    /**
     * Method: isOpePosRight(ArrayList ac)
     */
    @Test
    public void testIsOpePosRight() throws Exception {

        ArrayList<Token> arr0 = new ArrayList<Token>();
        arr0.add(new Token(Operator.ADD.getValue(),"Operator"));
        arr0.add(new Token(4,"Number"));
        assertFalse(tt.isOpePosRight(arr0));

        ArrayList<Token> arr1 = new ArrayList<Token>();
        arr1.add(new Token(4,"Number"));
        arr1.add(new Token(Operator.SUB.getValue(),"Operator"));
        assertFalse(tt.isOpePosRight(arr1));

        ArrayList<Token> arr2 = new ArrayList<Token>();
        arr2.add(new Token(4,"Number"));
        arr2.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr2.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr2.add(new Token(4,"Number"));
        assertFalse(tt.isOpePosRight(arr2));

        ArrayList<Token> arr3 = new ArrayList<Token>();
        arr3.add(new Token(4,"Number"));
        arr3.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr3.add(new Token(4,"Number"));
        arr3.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr3.add(new Token(4,"Number"));
        arr3.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        assertFalse(tt.isOpePosRight(arr3));

        ArrayList<Token> arr4 = new ArrayList<Token>();
        arr4.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr4.add(new Token(Operator.ADD.getValue(),"Operator"));
        arr4.add(new Token(4,"Number"));
        arr4.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        assertFalse(tt.isOpePosRight(arr4));

        ArrayList<Token> arr5 = new ArrayList<Token>();
        arr5.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr5.add(new Token(4,"Number"));
        arr5.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr5.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        assertFalse(tt.isOpePosRight(arr5));

        ArrayList<Token> arr6 = new ArrayList<Token>();
        arr6.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr6.add(new Token(4,"Number"));
        arr6.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr6.add(new Token(4,"Number"));
        arr6.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        arr6.add(new Token(4,"Number"));
        assertFalse(tt.isOpePosRight(arr6));


    }

    /**
     * Method: isDivisorZero(ArrayList ac)
     */
    @Test
    public void testIsDivisorZero() throws Exception {

        ArrayList<Token> arr = new ArrayList<Token>();
        arr.add(new Token(3,"Number"));
        arr.add(new Token(Operator.DIV.getValue(),"Operator"));
        arr.add(new Token(0,"Number"));
        assertFalse(tt.isDivisorZero(arr));

    }

    /**
     * Method: isValidparentheses(ArrayList ac)
     */
    @Test
    public void testIsValidparentheses() throws Exception {

        ArrayList<Token> arr0 = new ArrayList<Token>();
        arr0.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr0.add(new Token(3,"Number"));
        arr0.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        assertTrue(tt.isValidparentheses(arr0));

        ArrayList<Token> arr1 = new ArrayList<Token>();
        arr1.add(new Token(Operator.LEFT_BRACE.getValue(),"Operator"));
        arr1.add(new Token(3,"Number"));
        arr1.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        arr1.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        assertFalse(tt.isValidparentheses(arr1));

        ArrayList<Token> arr2 = new ArrayList<Token>();
        arr2.add(new Token(3,"Number"));
        arr2.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        arr2.add(new Token(Operator.SUB.getValue(),"Operator"));
        arr2.add(new Token(Operator.RIGHT_BRACE.getValue(),"Operator"));
        arr2.add(new Token(3,"Number"));
        assertFalse(tt.isValidparentheses(arr2));
    }


} 
