package test.calculator;

import calculator.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import tree.Node;
import tree.Tree;

/**
 * Calculate Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Jun 30, 2017</pre>
 */
public class CalculateTest {
    Calculate t0;
    InputProcess i;

    @Before
    public void before() throws Exception {
        t0 = new Calculate();
        i = new InputProcess();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createTree(String s)
     */
    @Test
    public void testCreateTree() throws Exception {


        String s3 = "(1 + (20*30) -16)/(9/(10 - 2*3))";
        Tree<Node> tt3 = t0.createTree(i.tokenize(s3));
        Tree<Node> t3 = new Tree<>();
        t3.setRoot(new Node(Operator.DIV.getValue(),"Operator"));
        t3.getRoot().setLeft(new Node(Operator.SUB.getValue(),"Operator"));
        t3.getRoot().getLeft().setLeft(new Node(Operator.ADD.getValue(),"Operator"));
        t3.getRoot().getLeft().setRight(new Node(16,"Number"));
        t3.getRoot().getLeft().getLeft().setLeft(new Node(1,"Number"));
        t3.getRoot().getLeft().getLeft().setRight(new Node(Operator.MUL.getValue(),"Operator"));
        t3.getRoot().getLeft().getLeft().getRight().setLeft(new Node(20,"Number"));
        t3.getRoot().getLeft().getLeft().getRight().setRight(new Node(30,"Number"));
        t3.getRoot().setRight(new Node(Operator.DIV.getValue(),"Operator"));
        t3.getRoot().getRight().setLeft(new Node(9,"Number"));
        t3.getRoot().getRight().setRight(new Node(Operator.SUB.getValue(),"Operator"));
        t3.getRoot().getRight().getRight().setLeft(new Node(10,"Number"));
        t3.getRoot().getRight().getRight().setRight(new Node(Operator.MUL.getValue(),"Operator"));
        t3.getRoot().getRight().getRight().getRight().setLeft(new Node(2,"Number"));
        t3.getRoot().getRight().getRight().getRight().setRight(new Node(3,"Number"));
        assertTrue(tt3.getRoot().getValue() == t3.getRoot().getValue());
        assertTrue(tt3.getRoot().getLeft().getValue() == t3.getRoot().getLeft().getValue());
        assertTrue(tt3.getRoot().getRight().getValue() == t3.getRoot().getRight().getValue());
        assertTrue(tt3.getRoot().getLeft().getLeft().getValue() == t3.getRoot().getLeft().getLeft().getValue());
        assertTrue(tt3.getRoot().getLeft().getRight().getValue() == t3.getRoot().getLeft().getRight().getValue());
        assertTrue(tt3.getRoot().getRight().getLeft().getValue() == t3.getRoot().getRight().getLeft().getValue());
        assertTrue(tt3.getRoot().getRight().getRight().getValue() == t3.getRoot().getRight().getRight().getValue());
        assertTrue(tt3.getRoot().getLeft().getLeft().getLeft().getValue() == t3.getRoot().getLeft().getLeft().getLeft().getValue());
        assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getValue() == t3.getRoot().getLeft().getLeft().getRight().getValue());
        assertTrue(tt3.getRoot().getRight().getRight().getLeft().getValue() == t3.getRoot().getRight().getRight().getLeft().getValue());
        assertTrue(tt3.getRoot().getRight().getRight().getRight().getValue() == t3.getRoot().getRight().getRight().getRight().getValue());
        assertTrue(tt3.getRoot().getRight().getRight().getRight().getLeft().getValue() == t3.getRoot().getRight().getRight().getRight().getLeft().getValue());
        assertTrue(tt3.getRoot().getRight().getRight().getRight().getRight().getValue() == t3.getRoot().getRight().getRight().getRight().getRight().getValue());
        assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getRight().getValue() == t3.getRoot().getLeft().getLeft().getRight().getRight().getValue());
        assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getLeft().getValue() == t3.getRoot().getLeft().getLeft().getRight().getLeft().getValue());

        String s2 = "100";
        Tree<Node> tt2 = t0.createTree(i.tokenize(s2));
        Tree<Node> t2 = new Tree<>();
        t2.setRoot(new Node(100,"Number"));
        assertTrue(tt2.getRoot().getValue() == t2.getRoot().getValue());

        String s1 = "-100";
        Tree<Node> tt1 = t0.createTree(i.tokenize(s1));
        Tree<Node> t1 = new Tree<>();
        t1.setRoot(new Node(Operator.SUB.getValue(),"Operator"));
        t1.getRoot().setLeft(new Node(0,"Number"));
        t1.getRoot().setRight(new Node(100,"Number"));
        assertTrue(tt1.getRoot().getValue() == t1.getRoot().getValue());
        assertTrue(tt1.getRoot().getLeft().getValue() == t1.getRoot().getLeft().getValue());
        assertTrue(tt1.getRoot().getRight().getValue() == t1.getRoot().getRight().getValue());

        String s = "1+2*3*4*5";
        Tree<Node> tt = t0.createTree(i.tokenize(s));
        Tree<Node> t = new Tree<>();
        t.setRoot(new Node(Operator.ADD.getValue(),"Operator"));
        t.getRoot().setLeft(new Node(1,"Number"));
        t.getRoot().setRight(new Node(Operator.MUL.getValue(),"Operator"));
        t.getRoot().getRight().setLeft(new Node(Operator.MUL.getValue(),"Operator"));
        t.getRoot().getRight().setRight(new Node(5,"Number"));
        t.getRoot().getRight().getLeft().setLeft(new Node(Operator.MUL.getValue(),"Operator"));
        t.getRoot().getRight().getLeft().setRight(new Node(4,"Number"));
        t.getRoot().getRight().getLeft().getLeft().setLeft(new Node(2,"Number"));
        t.getRoot().getRight().getLeft().getLeft().setRight(new Node(3,"Number"));
        assertTrue(tt.getRoot().getValue() == t.getRoot().getValue());
        assertTrue(tt.getRoot().getLeft().getValue() == t.getRoot().getLeft().getValue());
        assertTrue(tt.getRoot().getRight().getValue() == t.getRoot().getRight().getValue());
        assertTrue(tt.getRoot().getRight().getLeft().getValue() == t.getRoot().getRight().getLeft().getValue());
        assertTrue(tt.getRoot().getRight().getRight().getValue() == t.getRoot().getRight().getRight().getValue());
        assertTrue(tt.getRoot().getRight().getLeft().getLeft().getValue() == t.getRoot().getRight().getLeft().getLeft().getValue());
        assertTrue(tt.getRoot().getRight().getLeft().getRight().getValue() == t.getRoot().getRight().getLeft().getRight().getValue());
        assertTrue(tt.getRoot().getRight().getLeft().getLeft().getLeft().getValue() == t.getRoot().getRight().getLeft().getLeft().getLeft().getValue());
        assertTrue(tt.getRoot().getRight().getLeft().getLeft().getRight().getValue() == t.getRoot().getRight().getLeft().getLeft().getRight().getValue());

        String s4 = "-(100)";
        Tree<Node> tt4 = t0.createTree(i.tokenize(s4));
        Tree<Node> t4 = new Tree<>();
        t4.setRoot(new Node(Operator.SUB.getValue(),"Operator"));
        t4.getRoot().setLeft(new Node(0,"Number"));
        t4.getRoot().setRight(new Node(100,"Number"));
        assertTrue(tt4.getRoot().getValue() == t4.getRoot().getValue());
        assertTrue(tt4.getRoot().getLeft().getValue() == t4.getRoot().getLeft().getValue());
        assertTrue(tt4.getRoot().getRight().getValue() == t4.getRoot().getRight().getValue());

        String s5 = "-1+2+3";
        Tree<Node> tt5 = t0.createTree(i.tokenize(s5));
        Tree<Node> t5 = new Tree<>();
        t5.setRoot(new Node(Operator.ADD.getValue(),"Operator"));
        t5.getRoot().setLeft(new Node(Operator.ADD.getValue(),"Operator"));
        t5.getRoot().setRight(new Node(3,"Number"));
        t5.getRoot().getLeft().setLeft(new Node(Operator.SUB.getValue(),"Operator"));
        t5.getRoot().getLeft().setRight(new Node(2,"Number"));
        t5.getRoot().getLeft().getLeft().setLeft(new Node(0,"Number"));
        t5.getRoot().getLeft().getLeft().setRight(new Node(1,"Number"));
        assertTrue(tt5.getRoot().getValue() == t5.getRoot().getValue());
        assertTrue(tt5.getRoot().getLeft().getValue() == t5.getRoot().getLeft().getValue());
        assertTrue(tt5.getRoot().getRight().getValue() == t5.getRoot().getRight().getValue());
        assertTrue(tt5.getRoot().getLeft().getLeft().getValue() == t5.getRoot().getLeft().getLeft().getValue());
        assertTrue(tt5.getRoot().getLeft().getRight().getValue() == t5.getRoot().getLeft().getRight().getValue());
        assertTrue(tt5.getRoot().getLeft().getLeft().getLeft().getValue() == t5.getRoot().getLeft().getLeft().getLeft().getValue());
        assertTrue(tt5.getRoot().getLeft().getLeft().getRight().getValue() == t5.getRoot().getLeft().getLeft().getRight().getValue());

        String s6 = "-(-1)";
        Tree<Node> tt6 = t0.createTree(i.tokenize(s6));
        Tree<Node> t6 = new Tree<>();
        t6.setRoot(new Node(Operator.SUB.getValue(),"Operator"));
        t6.getRoot().setLeft(new Node(0,"Number"));
        t6.getRoot().setRight(new Node(Operator.SUB.getValue(),"Operator"));
        t6.getRoot().getRight().setLeft(new Node(0,"Number"));
        t6.getRoot().getRight().setRight(new Node(1,"Number"));
        assertTrue(tt6.getRoot().getValue() == t6.getRoot().getValue());
        assertTrue(tt6.getRoot().getLeft().getValue() == t6.getRoot().getLeft().getValue());
        assertTrue(tt6.getRoot().getRight().getValue() == t6.getRoot().getRight().getValue());
        assertTrue(tt6.getRoot().getRight().getLeft().getValue() == t6.getRoot().getRight().getLeft().getValue());
        assertTrue(tt6.getRoot().getRight().getRight().getValue() == t6.getRoot().getRight().getRight().getValue());

        String s7 = "(-1)*(-1)";
        Tree<Node> tt7 = t0.createTree(i.tokenize(s7));
        Tree<Node> t7 = new Tree<>();
        t7.setRoot(new Node(Operator.MUL.getValue(),"Operator"));
        t7.getRoot().setLeft(new Node(Operator.SUB.getValue(),"Operator"));
        t7.getRoot().setRight(new Node(Operator.SUB.getValue(),"Operator"));
        t7.getRoot().getLeft().setLeft(new Node(0,"Number"));
        t7.getRoot().getLeft().setRight(new Node(1,"Number"));
        t7.getRoot().getRight().setLeft(new Node(0,"Number"));
        t7.getRoot().getRight().setRight(new Node(1,"Number"));
        assertTrue(tt7.getRoot().getValue() == t7.getRoot().getValue());
        assertTrue(tt7.getRoot().getLeft().getValue() == t7.getRoot().getLeft().getValue());
        assertTrue(tt7.getRoot().getRight().getValue() == t7.getRoot().getRight().getValue());
        assertTrue(tt7.getRoot().getRight().getLeft().getValue() == t7.getRoot().getRight().getLeft().getValue());
        assertTrue(tt7.getRoot().getRight().getRight().getValue() == t7.getRoot().getRight().getRight().getValue());
        assertTrue(tt7.getRoot().getLeft().getLeft().getValue() == t7.getRoot().getLeft().getLeft().getValue());
        assertTrue(tt7.getRoot().getLeft().getRight().getValue() == t7.getRoot().getLeft().getRight().getValue());

    }

    /**
     * Method: calculate(String s)
     */
    @Test
    public void testCalculate() throws Exception {
        String s3 = "(1 + (20*30) -16)/(9/(10 - 2*3))";
        double res3 = t0.calculate(t0.createTree(i.tokenize(s3)));
        double r3 = 260;
        assertTrue(res3 == r3);

        String s2 = "100";
        double res2 = t0.calculate(t0.createTree(i.tokenize(s2)));
        double r2 = 100;
        assertTrue(res2 == r2);

        String s1 = "-100";
        double res1 = t0.calculate(t0.createTree(i.tokenize(s1)));
        double r1 = -100;
        assertTrue(res1 == r1);

        String s = "1+2*3*4*5";
        double res = t0.calculate(t0.createTree(i.tokenize(s)));
        double r = 121;
        assertTrue(res == r);

        String s4 = "-(100)";
        double res4 = t0.calculate(t0.createTree(i.tokenize(s4)));
        double r4 = -100;
        assertTrue(res4 == r4);

        String s5 = "-1+2+3";
        double res5 = t0.calculate(t0.createTree(i.tokenize(s5)));
        double r5 = 4;
        assertTrue(res5 == r5);

        String s6 = "-(-1)";
        double res6 = t0.calculate(t0.createTree(i.tokenize(s6)));
        double r6 = 1;
        assertTrue(res6 == r6);

        String s7 = "(-1)*(-1)";
        double res7 = t0.calculate(t0.createTree(i.tokenize(s7)));
        double r7 = 1;
        assertTrue(res7 == r7);

        String s8 = "6*((1+2)/(2*1-3))+8*4/2-6";
        double res8 = t0.calculate(t0.createTree(i.tokenize(s8)));
        double r8 = -8;
        assertTrue(res8 == r8);


    }

    /**
     * Method: compareOpe(String op1, String op2)
     */
    @Test
    public void testCompareOpe() throws Exception {

    }

    /**
     * Method: postOrder
     */
    @Test
    public void testPostOrder() throws Exception {


    }


} 
