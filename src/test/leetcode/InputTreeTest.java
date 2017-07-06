package test.leetcode;

import leetcode.InputTree;
import leetcode.Node;
import leetcode.NumberNode;
import leetcode.OperatorNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** 
* InputTree Tester. 
* 
* @author <Iris>
* @since <pre>Jun 30, 2017</pre> 
* @version 1.0 
*/ 
public class InputTreeTest {

        InputTree tt;

        @Before
        public void before() throws Exception {
            tt = new InputTree();
        }

        @After
        public void after() throws Exception {
        }

        /**
         *
         * Method: Tokenize(String s)
         *
         */
        @Test
        public void testTokenize() throws Exception {
//TODO: Test goes here...
            String s = "-1+(2+3)*2";
            ArrayList<Node> arr = new ArrayList<Node>();
            arr.add(new NumberNode(0)); arr.add(new OperatorNode(45)); arr.add(new NumberNode(1)); arr.add(new OperatorNode(43));
            arr.add(new OperatorNode(40)); arr.add(new NumberNode(2)); arr.add(new OperatorNode(43)); arr.add(new NumberNode(3));
            arr.add(new OperatorNode(41)); arr.add(new OperatorNode(42)); arr.add(new NumberNode(2));
            ArrayList<Node> n = tt.Tokenize(s);
            assertTrue(arr.size()==n.size());
            for(int i=0; i<arr.size(); i++) {
                assertTrue(arr.get(i).getValue()==n.get(i).getValue());
                assertTrue(arr.get(i).getName()==n.get(i).getName());
            }

        }

/** 
* 
* Method: InputValid(String s) 
* 
*/ 
@Test
public void testInputValid() throws Exception { 
//TODO: Test goes here...
    boolean f1 = tt.InputValid("1+2+3");
    assertTrue(f1);
    boolean f3 = tt.InputValid(")1+1(");
    assertFalse(f3);
    boolean f4 = tt.InputValid("-100");
    assertTrue(f4);
    boolean f5 = tt.InputValid("(100)");
    assertTrue(f5);
    boolean f6 = tt.InputValid("(1 + (20*30) -16)/(9/(10 2*3))");
    assertFalse(f6);
    boolean f7 = tt.InputValid("(1 + (20*30) -16)/(9/(10 2*3))+4");
    assertFalse(f7);
    boolean f8 = tt.InputValid("(1 + (20*30) -16)/(9/(10 - 2*3))");
    assertTrue(f8);
    boolean f9 = tt.InputValid("-(100)");
    assertTrue(f9);
    boolean f10 = tt.InputValid("-1+2+3");
    assertTrue(f10);
    boolean f11 = tt.InputValid("(-1+2+3)");
    assertTrue(f11);
    boolean f12 = tt.InputValid("-(-1)");
    assertTrue(f12);
    boolean f13 = tt.InputValid("(-)");
    assertFalse(f13);
    boolean f14 = tt.InputValid("(-1)*(-1)");
    assertTrue(f14);
    boolean f15 = tt.InputValid("1+)-1(");
    assertFalse(f15);
    boolean f16 = tt.InputValid("1 + ) -1 ( -3");
    assertFalse(f16);

} 

/** 
* 
* Method: isNumberOrOpe(ArrayList ac) 
* 
*/ 
@Test
public void testIsNumberOrOpe() throws Exception { 
//TODO: Test goes here...
    ArrayList<Node> arr = new ArrayList<Node>();
    arr.add(new NumberNode(0)); arr.add(null);  arr.add(new NumberNode(2));
    assertFalse(tt.isNumberOrOpe(arr));
} 

/** 
* 
* Method: isOpePosRight(ArrayList ac) 
* 
*/ 
@Test
public void testIsOpePosRight() throws Exception { 
//TODO: Test goes here...
    ArrayList<Node> arr0 = new ArrayList<Node>();
    arr0.add(new OperatorNode(43));arr0.add(new NumberNode(4));
    assertFalse(tt.isOpePosRight(arr0));

    ArrayList<Node> arr1 = new ArrayList<Node>();
    arr1.add(new NumberNode(4));arr1.add(new OperatorNode(43));
    assertFalse(tt.isOpePosRight(arr1));

    ArrayList<Node> arr2 = new ArrayList<Node>();
    arr2.add(new NumberNode(4));arr2.add(new OperatorNode(43)); arr2.add(new OperatorNode(43)); arr2.add(new NumberNode(4));
    assertFalse(tt.isOpePosRight(arr2));

    ArrayList<Node> arr3 = new ArrayList<Node>();
    arr3.add(new NumberNode(4));arr3.add(new OperatorNode(40)); arr3.add(new NumberNode(4)); arr3.add(new OperatorNode(43)); arr3.add(new NumberNode(4));arr3.add(new OperatorNode(41));
    assertFalse(tt.isOpePosRight(arr3));

    ArrayList<Node> arr4 = new ArrayList<Node>();
    arr4.add(new OperatorNode(40));arr4.add(new OperatorNode(43));  arr4.add(new NumberNode(4));arr4.add(new OperatorNode(41));
    assertFalse(tt.isOpePosRight(arr4));

    ArrayList<Node> arr5 = new ArrayList<Node>();
    arr5.add(new OperatorNode(40)); arr5.add(new NumberNode(4));arr5.add(new OperatorNode(43));  arr5.add(new OperatorNode(41));
    assertFalse(tt.isOpePosRight(arr5));

    ArrayList<Node> arr6 = new ArrayList<Node>();
    arr6.add(new OperatorNode(40)); arr6.add(new NumberNode(4));arr6.add(new OperatorNode(43));  arr6.add(new NumberNode(4));arr6.add(new OperatorNode(41));arr6.add(new NumberNode(4));
    assertFalse(tt.isOpePosRight(arr6));


} 

/** 
* 
* Method: isDivisorZero(ArrayList ac) 
* 
*/ 
@Test
public void testIsDivisorZero() throws Exception { 
//TODO: Test goes here...
    ArrayList<Node> arr = new ArrayList<Node>();
    arr.add(new NumberNode(3));arr.add(new OperatorNode(47)); arr.add(new NumberNode(0));
    assertFalse(tt.isDivisorZero(arr));

} 

/** 
* 
* Method: isValidparentheses(ArrayList ac) 
* 
*/ 
@Test
public void testIsValidparentheses() throws Exception { 
//TODO: Test goes here...
    ArrayList<Node> arr0 = new ArrayList<Node>();
    arr0.add(new OperatorNode(40)); arr0.add(new NumberNode(3));  arr0.add(new OperatorNode(41));
    assertTrue(tt.isValidparentheses(arr0));

    ArrayList<Node> arr1 = new ArrayList<Node>();
    arr1.add(new OperatorNode(40)); arr1.add(new NumberNode(3));  arr1.add(new OperatorNode(41)); arr1.add(new OperatorNode(41));
    assertFalse(tt.isValidparentheses(arr1));

    ArrayList<Node> arr2 = new ArrayList<Node>();
    arr2.add(new NumberNode(3)); arr2.add(new OperatorNode(41)); arr2.add(new OperatorNode(43));  arr2.add(new OperatorNode(41));arr2.add(new NumberNode(3));
    assertFalse(tt.isValidparentheses(arr2));
} 


} 
