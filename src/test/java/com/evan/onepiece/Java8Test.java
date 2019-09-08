package com.evan.onepiece;

import com.evan.onepiece.model.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;

/**
 * @author Evan Huang
 * @date 2018/4/11 11:16
 */
public class Java8Test {

    public static List<Integer> numLs = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    public void testConsumer() {
        User user = new User();
        user.setUserName("Evan");

        Consumer<User> userConsumer = user1 -> user1.setUserName("Huang");
        userConsumer.accept(user);
        System.out.println(user.getUserName());
    }

    @Test
    public void testNumbers() {
        numLs.stream()
                .filter(i -> i % 2 == 0) //过滤非偶数
                .distinct() //过滤重复元素
                .forEach(System.out::println); //循环打印
    }

    @Test
    public void testLimit() {
        System.out.println(
                numLs.stream()
                        .filter(num -> num > 3)
                        .limit(4) //从头开始，留下几个元素
                        .collect(toList())
                        .toString()
        );
    }

    @Test
    public void testSkip() {
        List<Integer> collect = numLs.stream()
                .filter(num -> num > 3)
                .skip(1) //从头开始跳过几个元素
                .collect(toList());
        System.out.println(collect.toString());
    }

    @Test
    public void testMap() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> collect = words.stream()
                .map(word -> word.split("")) //传递给map方法的Lambda为每个单词返回了一个String[]（String列表）。因此，map返回的流实际上是Stream<String[]>类型的。
                .flatMap(Arrays::stream) //flatMap将Stream<String[]>类型转换为Stream<String>类型
                .distinct()
                .collect(toList());
        System.out.println(collect.toString());
    }

    @Test
    public void testReduce() {
        int sum = numLs.stream().reduce(0, (a, b) -> a + b); //相当于循环求和
        int maxNum = numLs.stream().reduce(Integer::max).get();
        System.out.println(sum);
    }


}
