package test.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import static org.junit.Assert.*;
import java.util.*;

/** 
* Input Tester. 
* 
* @author <Iris>
* @since <pre>Jun 26, 2017</pre> 
* @version 1.0 
*/ 
public class InputTest { 
    leetcode.Input i;
@Before
public void before() throws Exception {
    i = new leetcode.Input();
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: processInput(String s) 
* 
*/ 
@Test
public void testProcessInput() throws Exception { 
//TODO: Test goes here...

} 

/** 
* 
* Method: InputValid(String s) 
* 
*/ 
@Test
public void testInputValid() throws Exception { 
//TODO: Test goes here...

    boolean f1 = i.InputValid("1+2+3");
    assertTrue(f1);
    boolean f3 = i.InputValid(")1+1(");
    assertFalse(f3);
    boolean f4 = i.InputValid("-100");
    assertTrue(f4);
    boolean f5 = i.InputValid("(100)");
    assertTrue(f5);
    boolean f6 = i.InputValid("(1 + (20*30) -16)/(9/(10 2*3))");
    assertFalse(f6);
    boolean f7 = i.InputValid("(1 + (20*30) -16)/(9/(10 2*3))+4");
    assertFalse(f7);
    boolean f8 = i.InputValid("(1 + (20*30) -16)/(9/(10 - 2*3))");
    assertTrue(f8);
    boolean f9 = i.InputValid("-(100)");
    assertTrue(f9);
    boolean f10 = i.InputValid("-1+2+3");
    assertTrue(f10);
    boolean f11 = i.InputValid("(-1+2+3)");
    assertTrue(f11);
    boolean f12 = i.InputValid("-(-1)");
    assertTrue(f12);
    boolean f13 = i.InputValid("(-)");
    assertFalse(f13);
    boolean f14 = i.InputValid("(-1)*(-1)");
    assertTrue(f14);
    boolean f15 = i.InputValid("1+)-1(");
    assertFalse(f15);
    boolean f16 = i.InputValid("1 + ) -1 ( -3");
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
    ArrayList<Object> a1= new ArrayList<>();
    a1.add(1);a1.add("+");a1.add(2);a1.add("+");a1.add(3);
    assertTrue(i.isNumberOrOpe(a1));

    ArrayList<Object> a2= new ArrayList<>();
    a2.add(1);a2.add("err");a2.add(2);a2.add("+");a2.add(3);
    assertFalse(i.isNumberOrOpe(a2));

    ArrayList<Object> a3= new ArrayList<>();
    a3.add("-");a3.add(")");a3.add(2);a3.add(")");
    assertTrue(i.isNumberOrOpe(a3));

/* 
try { 
   Method method = Input.getClass().getMethod("isNumberOrOpe", ArrayList.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: isOpePosRight(ArrayList ac) 
* 
*/ 
@Test
public void testIsOpePosRight() throws Exception { 
//TODO: Test goes here...
    ArrayList<Object> a0= new ArrayList<>();
    a0.add(1); a0.add("-");a0.add("*"); a0.add("5");
    assertFalse(i.isOpePosRight(a0));

    ArrayList<Object> a1= new ArrayList<>();
    a1.add("-");a1.add(")");a1.add(2);a1.add(")");
    assertFalse(i.isOpePosRight(a1));

    ArrayList<Object> a2 = new ArrayList<>();
    a2.add("("); a2.add("/");a2.add(0);
    assertFalse(i.isOpePosRight(a2));

    ArrayList<Object> a3 = new ArrayList<>();
    a3.add("*"); a3.add("5");
    assertFalse(i.isOpePosRight(a3));

    ArrayList<Object> a4 = new ArrayList<>();
    a4.add("5"); a4.add("(");a4.add("+");a4.add(4);
    assertFalse(i.isOpePosRight(a4));

    ArrayList<Object> a5 = new ArrayList<>();
    a5.add(6); a5.add("/");a5.add(3);a5.add("-");
    assertFalse(i.isOpePosRight(a5));

    ArrayList<Object> a6 = new ArrayList<>();
    a6.add("("); a6.add("5"); a6.add("+"); a6.add(6); a6.add(")"); a6.add(9);
    assertFalse(i.isOpePosRight(a6));

/* 
try { 
   Method method = Input.getClass().getMethod("isOpePosRight", ArrayList.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: isDivisorZero(ArrayList ac) 
* 
*/ 
@Test
public void testIsDivisorZero() throws Exception { 
//TODO: Test goes here...
    ArrayList<Object> a0 = new ArrayList<>();
    a0.add(5); a0.add("/"); a0.add(0);
    assertFalse(i.isDivisorZero(a0));

    ArrayList<Object> a1 = new ArrayList<>();
    a1.add(5); a1.add("/"); a1.add("("); a1.add(1); a1.add("-"); a1.add(1); a1.add(")");
    assertTrue(i.isDivisorZero(a1));

/* 
try { 
   Method method = Input.getClass().getMethod("isDivisorZero", ArrayList.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: isValidparentheses(ArrayList ac) 
* 
*/ 
@Test
public void testIsValidparentheses() throws Exception { 
//TODO: Test goes here...
    ArrayList<Object> a0 = new ArrayList<>();
    a0.add("("); a0.add("5"); a0.add("-");a0.add("(");a0.add("5");a0.add(")");
    assertFalse(i.isValidparentheses(a0));

    ArrayList<Object> a1 = new ArrayList<>();
    a1.add(5); a1.add(")"); a1.add("+");a1.add("("); a1.add(7);
    assertFalse(i.isValidparentheses(a1));
/* 
try { 
   Method method = Input.getClass().getMethod("isValidparentheses", ArrayList.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
}
}