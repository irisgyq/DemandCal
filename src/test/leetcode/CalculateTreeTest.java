package test.leetcode; 

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.*;
import leetcode.*;

/** 
* CalculateTree Tester. 
* 
* @author <Authors name> 
* @since <pre>Jun 30, 2017</pre> 
* @version 1.0 
*/ 
public class CalculateTreeTest {
    CalculateTree t0;
    InputTree i;
@Before
public void before() throws Exception {
    t0 =new CalculateTree();
    i = new InputTree();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: createTree(String s) 
* 
*/ 
@Test
public void testCreateTree() throws Exception { 
//TODO: Test goes here...

    String s3 = "(1 + (20*30) -16)/(9/(10 - 2*3))";
    Tree<Node> tt3 = t0.createTree(i.Tokenize(s3));
    Tree<Node> t3 = new Tree<>();
    t3.setRoot(new OperatorNode(47));
    t3.getRoot().setLeft(new OperatorNode(45));
    t3.getRoot().getLeft().setLeft(new OperatorNode(43));
    t3.getRoot().getLeft().setRight(new NumberNode(16));
    t3.getRoot().getLeft().getLeft().setLeft(new NumberNode(1));
    t3.getRoot().getLeft().getLeft().setRight(new OperatorNode(42));
    t3.getRoot().getLeft().getLeft().getRight().setLeft(new NumberNode(20));
    t3.getRoot().getLeft().getLeft().getRight().setRight(new NumberNode(30));
    t3.getRoot().setRight(new OperatorNode(47));
    t3.getRoot().getRight().setLeft(new NumberNode(9));
    t3.getRoot().getRight().setRight(new OperatorNode(45));
    t3.getRoot().getRight().getRight().setLeft(new NumberNode(10));
    t3.getRoot().getRight().getRight().setRight(new OperatorNode(42));
    t3.getRoot().getRight().getRight().getRight().setLeft(new NumberNode(2));
    t3.getRoot().getRight().getRight().getRight().setRight(new NumberNode(3));
    assertTrue(tt3.getRoot().getValue()==t3.getRoot().getValue());
    assertTrue(tt3.getRoot().getLeft().getValue()==t3.getRoot().getLeft().getValue());
    assertTrue(tt3.getRoot().getRight().getValue()==t3.getRoot().getRight().getValue());
    assertTrue(tt3.getRoot().getLeft().getLeft().getValue()==t3.getRoot().getLeft().getLeft().getValue());
    assertTrue(tt3.getRoot().getLeft().getRight().getValue()==t3.getRoot().getLeft().getRight().getValue());
    assertTrue(tt3.getRoot().getRight().getLeft().getValue()==t3.getRoot().getRight().getLeft().getValue());
    assertTrue(tt3.getRoot().getRight().getRight().getValue()==t3.getRoot().getRight().getRight().getValue());
    assertTrue(tt3.getRoot().getLeft().getLeft().getLeft().getValue()==t3.getRoot().getLeft().getLeft().getLeft().getValue());
    assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getValue()==t3.getRoot().getLeft().getLeft().getRight().getValue());
    assertTrue(tt3.getRoot().getRight().getRight().getLeft().getValue()==t3.getRoot().getRight().getRight().getLeft().getValue());
    assertTrue(tt3.getRoot().getRight().getRight().getRight().getValue()==t3.getRoot().getRight().getRight().getRight().getValue());
    assertTrue(tt3.getRoot().getRight().getRight().getRight().getLeft().getValue()==t3.getRoot().getRight().getRight().getRight().getLeft().getValue());
    assertTrue(tt3.getRoot().getRight().getRight().getRight().getRight().getValue()==t3.getRoot().getRight().getRight().getRight().getRight().getValue());
    assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getRight().getValue()==t3.getRoot().getLeft().getLeft().getRight().getRight().getValue());
    assertTrue(tt3.getRoot().getLeft().getLeft().getRight().getLeft().getValue()==t3.getRoot().getLeft().getLeft().getRight().getLeft().getValue());

    String s2 = "100";
    Tree<Node> tt2 = t0.createTree(i.Tokenize(s2));
    Tree<Node> t2 = new Tree<>();
    t2.setRoot(new NumberNode(100));
    assertTrue(tt2.getRoot().getValue()==t2.getRoot().getValue());

    String s1 = "-100";
    Tree<Node> tt1 = t0.createTree(i.Tokenize(s1));
    Tree<Node> t1 = new Tree<>();
    t1.setRoot(new OperatorNode(45));
    t1.getRoot().setLeft(new NumberNode(0));
    t1.getRoot().setRight(new NumberNode(100));
    assertTrue(tt1.getRoot().getValue()==t1.getRoot().getValue());
    assertTrue(tt1.getRoot().getLeft().getValue()==t1.getRoot().getLeft().getValue());
    assertTrue(tt1.getRoot().getRight().getValue()==t1.getRoot().getRight().getValue());

    String s = "1+2*3*4*5";
    Tree<Node> tt = t0.createTree(i.Tokenize(s));
    Tree<Node> t = new Tree<>();
    t.setRoot(new OperatorNode(43));
    t.getRoot().setLeft(new NumberNode(1));
    t.getRoot().setRight(new OperatorNode(42));
    t.getRoot().getRight().setLeft(new OperatorNode(42));
    t.getRoot().getRight().setRight(new NumberNode(5));
    t.getRoot().getRight().getLeft().setLeft(new OperatorNode(42));
    t.getRoot().getRight().getLeft().setRight(new NumberNode(4));
    t.getRoot().getRight().getLeft().getLeft().setLeft(new NumberNode(2));
    t.getRoot().getRight().getLeft().getLeft().setRight(new NumberNode(3));
    assertTrue(tt.getRoot().getValue()==t.getRoot().getValue());
    assertTrue(tt.getRoot().getLeft().getValue()==t.getRoot().getLeft().getValue());
    assertTrue(tt.getRoot().getRight().getValue()==t.getRoot().getRight().getValue());
    assertTrue(tt.getRoot().getRight().getLeft().getValue()==t.getRoot().getRight().getLeft().getValue());
    assertTrue(tt.getRoot().getRight().getRight().getValue()==t.getRoot().getRight().getRight().getValue());
    assertTrue(tt.getRoot().getRight().getLeft().getLeft().getValue()==t.getRoot().getRight().getLeft().getLeft().getValue());
    assertTrue(tt.getRoot().getRight().getLeft().getRight().getValue()==t.getRoot().getRight().getLeft().getRight().getValue());
    assertTrue(tt.getRoot().getRight().getLeft().getLeft().getLeft().getValue()==t.getRoot().getRight().getLeft().getLeft().getLeft().getValue());
    assertTrue(tt.getRoot().getRight().getLeft().getLeft().getRight().getValue()==t.getRoot().getRight().getLeft().getLeft().getRight().getValue());

    String s4 = "-(100)";
    Tree<Node> tt4 = t0.createTree(i.Tokenize(s4));
    Tree<Node> t4 = new Tree<>();
    t4.setRoot(new OperatorNode(45));
    t4.getRoot().setLeft(new NumberNode(0));
    t4.getRoot().setRight(new NumberNode(100));
    assertTrue(tt4.getRoot().getValue()==t4.getRoot().getValue());
    assertTrue(tt4.getRoot().getLeft().getValue()==t4.getRoot().getLeft().getValue());
    assertTrue(tt4.getRoot().getRight().getValue()==t4.getRoot().getRight().getValue());

    String s5 = "-1+2+3";
    Tree<Node> tt5 = t0.createTree(i.Tokenize(s5));
    Tree<Node> t5 = new Tree<>();
    t5.setRoot(new OperatorNode(43));
    t5.getRoot().setLeft(new OperatorNode(43));
    t5.getRoot().setRight(new NumberNode(3));
    t5.getRoot().getLeft().setLeft(new OperatorNode(45));
    t5.getRoot().getLeft().setRight(new NumberNode(2));
    t5.getRoot().getLeft().getLeft().setLeft(new NumberNode(0));
    t5.getRoot().getLeft().getLeft().setRight(new NumberNode(1));
    assertTrue(tt5.getRoot().getValue()==t5.getRoot().getValue());
    assertTrue(tt5.getRoot().getLeft().getValue()==t5.getRoot().getLeft().getValue());
    assertTrue(tt5.getRoot().getRight().getValue()==t5.getRoot().getRight().getValue());
    assertTrue(tt5.getRoot().getLeft().getLeft().getValue()==t5.getRoot().getLeft().getLeft().getValue());
    assertTrue(tt5.getRoot().getLeft().getRight().getValue()==t5.getRoot().getLeft().getRight().getValue());
    assertTrue(tt5.getRoot().getLeft().getLeft().getLeft().getValue()==t5.getRoot().getLeft().getLeft().getLeft().getValue());
    assertTrue(tt5.getRoot().getLeft().getLeft().getRight().getValue()==t5.getRoot().getLeft().getLeft().getRight().getValue());

    String s6 = "-(-1)";
    Tree<Node> tt6 = t0.createTree(i.Tokenize(s6));
    Tree<Node> t6 = new Tree<>();
    t6.setRoot(new OperatorNode(45));
    t6.getRoot().setLeft(new NumberNode(0));
    t6.getRoot().setRight(new OperatorNode(45));
    t6.getRoot().getRight().setLeft(new NumberNode(0));
    t6.getRoot().getRight().setRight(new NumberNode(1));
    assertTrue(tt6.getRoot().getValue()==t6.getRoot().getValue());
    assertTrue(tt6.getRoot().getLeft().getValue()==t6.getRoot().getLeft().getValue());
    assertTrue(tt6.getRoot().getRight().getValue()==t6.getRoot().getRight().getValue());
    assertTrue(tt6.getRoot().getRight().getLeft().getValue()==t6.getRoot().getRight().getLeft().getValue());
    assertTrue(tt6.getRoot().getRight().getRight().getValue()==t6.getRoot().getRight().getRight().getValue());

    String s7 = "(-1)*(-1)";
    Tree<Node> tt7 = t0.createTree(i.Tokenize(s7));
    Tree<Node> t7 = new Tree<>();
    t7.setRoot(new OperatorNode(42));
    t7.getRoot().setLeft(new OperatorNode(45));
    t7.getRoot().setRight(new OperatorNode(45));
    t7.getRoot().getLeft().setLeft(new NumberNode(0));
    t7.getRoot().getLeft().setRight(new NumberNode(1));
    t7.getRoot().getRight().setLeft(new NumberNode(0));
    t7.getRoot().getRight().setRight(new NumberNode(1));
    assertTrue(tt7.getRoot().getValue()==t7.getRoot().getValue());
    assertTrue(tt7.getRoot().getLeft().getValue()==t7.getRoot().getLeft().getValue());
    assertTrue(tt7.getRoot().getRight().getValue()==t7.getRoot().getRight().getValue());
    assertTrue(tt7.getRoot().getRight().getLeft().getValue()==t7.getRoot().getRight().getLeft().getValue());
    assertTrue(tt7.getRoot().getRight().getRight().getValue()==t7.getRoot().getRight().getRight().getValue());
    assertTrue(tt7.getRoot().getLeft().getLeft().getValue()==t7.getRoot().getLeft().getLeft().getValue());
    assertTrue(tt7.getRoot().getLeft().getRight().getValue()==t7.getRoot().getLeft().getRight().getValue());

}

/** 
* 
* Method: calculate(String s) 
* 
*/ 
@Test
public void testCalculate() throws Exception { 
//TODO: Test goes here...
    String s3 = "(1 + (20*30) -16)/(9/(10 - 2*3))";
    double res3 = t0.calculate(t0.createTree(i.Tokenize(s3)));
    double r3 = 260;
    assertTrue(res3==r3);

    String s2 = "100";
    double res2 = t0.calculate(t0.createTree(i.Tokenize(s2)));
    double r2 = 100;
    assertTrue(res2==r2);

    String s1 = "-100";
    double res1 = t0.calculate(t0.createTree(i.Tokenize(s1)));
    double r1 = -100;
    assertTrue(res1==r1);

    String s = "1+2*3*4*5";
    double res = t0.calculate(t0.createTree(i.Tokenize(s)));
    double r = 121;
    assertTrue(res==r);

    String s4 = "-(100)";
    double res4 = t0.calculate(t0.createTree(i.Tokenize(s4)));
    double r4 = -100;
    assertTrue(res4==r4);

    String s5 = "-1+2+3";
    double res5 = t0.calculate(t0.createTree(i.Tokenize(s5)));
    double r5 = 4;
    assertTrue(res5==r5);

    String s6 = "-(-1)";
    double res6 = t0.calculate(t0.createTree(i.Tokenize(s6)));
    double r6 = 1;
    assertTrue(res6==r6);

    String s7 = "(-1)*(-1)";
    double res7 = t0.calculate(t0.createTree(i.Tokenize(s7)));
    double r7 = 1;
    assertTrue(res7==r7);

    String s8 = "6*((1+2)/(2*1-3))+8*4/2-6";
    double res8 = t0.calculate(t0.createTree(i.Tokenize(s8)));
    double r8 = -8;
    assertTrue(res8==r8);



} 

/** 
* 
* Method: compareOpe(String op1, String op2) 
* 
*/ 
@Test
public void testCompareOpe() throws Exception { 
//TODO: Test goes here... 
}

/**
* 
* Method: postOrder
* 
*/ 
@Test
public void testPostOrder() throws Exception {
//TODO: Test goes here...

} 


} 
