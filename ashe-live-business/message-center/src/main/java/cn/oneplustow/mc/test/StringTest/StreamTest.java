package cn.oneplustow.mc.test.StringTest;

import com.alibaba.excel.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author lwj
 * Date 2022/6/8
 * Description TODO
 * BVersion 1.0
 **/
public class StreamTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");
        if (CollectionUtils.isEmpty(list)) {
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
