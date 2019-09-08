package com.evan.onepiece.funny;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Huang Sen
 */
public class FunnyTest {

    public static void main(String[] args) {
        testSet();
    }

    /**
     * add添加的是short ,remove的 i-1,在赋值运算中，
     * 根据低位类型会自动向高位类型转换，此时 i-1 的值类型已经是int，set.remove（int），
     * set中不存在对应得int类型，所以通过100次add，最后的set的大小就是100
     */
    private static void testSet() {
        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i - 1);
        }
        System.out.println(set.size());
    }
}
