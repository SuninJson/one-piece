package com.evan.onepiece;

import org.junit.Test;

/**
 * @author Evan Huang
 * @date 2018/4/18 15:54
 */
public class OperatorTest {

    @Test
    public void test(){
        String cashProp = "98";
        String status = cashProp.equals("98") ? "1" : (cashProp.equals("99") ? "2" : "3");
        System.out.println(status);
    }
}
