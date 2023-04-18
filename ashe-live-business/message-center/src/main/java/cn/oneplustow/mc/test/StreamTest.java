package cn.oneplustow.mc.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {
        filter();
    }
    
    private static void filter(){

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");

        List filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
    }
}
