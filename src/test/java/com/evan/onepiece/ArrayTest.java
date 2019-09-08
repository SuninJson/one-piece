package com.evan.onepiece;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Evan Huang
 * @date 2018/4/18 10:59
 */
@Slf4j
public class ArrayTest {

    @Test
    public void testTwoDimension(){
        int[][] fields = new int[2][2];
        fields[0] = new int[]{10, 2};
        System.out.println(fields[0]);
    }
}
