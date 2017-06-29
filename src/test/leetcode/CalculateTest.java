package test.leetcode; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import java.util.*;
import static org.junit.Assert.*;

/** 
* Calculate Tester. 
* 
* @author <Iris>
* @since <pre>Jun 26, 2017</pre> 
* @version 1.0 
*/ 
public class CalculateTest {
    leetcode.Calculate c;

    @Before
    public void before() throws Exception {
        c = new leetcode.Calculate();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: calculate(String s)
     */
    @Test
    public void testCalculate() throws Exception {
//TODO: Test goes here...
        String s0 = "1+2+3";
        assertTrue(c.calculate(s0) == 6.0);
        String s1 = "-100";
        assertTrue(c.calculate(s1) == -100.0);
        String s2 = "(100)";
        assertTrue(c.calculate(s2) == 100.0);
        String s3 = "(1 + (20*30) -16)/(9/(10 - 2*3))";
        assertTrue(c.calculate(s3) == 260);
        String s4 = "-(100)";
        assertTrue(c.calculate(s4) == -100.0);
        String s5 = "-1+2+3";
        assertTrue(c.calculate(s5) == 4.0);
        String s6 = "(-1+2+3)";
        assertTrue(c.calculate(s6) == 4.0);
        String s7 = "-(-1)";
        assertTrue(c.calculate(s7) == 1.0);
        String s8 = "(-1)*(-1)";
        assertTrue(c.calculate(s8) == 1.0);

    }


    /**
     * Method: exchange(String s)
     */
    @Test
    public void testExchange() throws Exception {
//TODO: Test goes here...
        String s1 = "1+2*3";
        Queue<Object> q1 = c.exchange(s1);
        Queue<Object> u1 = new LinkedList<>();
        u1.add(1);
        u1.add(2);
        u1.add(3);
        u1.add("*");
        u1.add("+");
        assertEquals(q1, u1);
        c.reset();

        String s2 = "5/(12+2)*3";
        Queue<Object> q2 = c.exchange(s2);
        Queue<Object> u2 = new LinkedList<>();
        u2.add(5);
        u2.add(12);
        u2.add(2);
        u2.add("+");
        u2.add("/");
        u2.add(3);
        u2.add("*");
        assertEquals(q2, u2);
        c.reset();

        String s3 = "-2*(-4)-(12+2)*3";
        Queue<Object> q3 = c.exchange(s3);
        Queue<Object> u3 = new LinkedList<>();
        u3.add(0);
        u3.add(2);
        u3.add(0);
        u3.add(4);
        u3.add("-");
        u3.add("*");
        u3.add("-");
        u3.add(12);
        u3.add(2);
        u3.add("+");
        u3.add(3);
        u3.add("*");
        u3.add("-");
        assertEquals(q3, u3);
        c.reset();

        String s4 = "(5*((-1-6)-(-8))-3)/2";
        Queue<Object> q4 = c.exchange(s4);
        Queue<Object> u4 = new LinkedList<>();
        u4.add(5);
        u4.add(0);
        u4.add(1);
        u4.add("-");
        u4.add(6);
        u4.add("-");
        u4.add(0);
        u4.add(8);
        u4.add("-");
        u4.add("-");
        u4.add("*");
        u4.add(3);
        u4.add("-");
        u4.add(2);
        u4.add("/");
        assertEquals(q4, u4);
        c.reset();

/* 
try { 
   Method method = Calculate.getClass().getMethod("exchange", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: compareOpe(String op1, String op2)
     */
    @Test
    public void testCompareOpe() throws Exception {
//TODO: Test goes here...
        assertFalse(c.compareOpe("*", "+"));
        assertTrue(c.compareOpe("/", "*"));
        assertTrue(c.compareOpe("-", "/"));

/* 
try { 
   Method method = Calculate.getClass().getMethod("compareOpe", String.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }
}