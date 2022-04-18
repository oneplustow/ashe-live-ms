package cn.oneplustow.mc.test.ArrayTest;

import java.util.Arrays;

/**
 * Author lwj
 * Date 2022/4/15
 * Description TODO
 * BVersion 1.0
 **/
public class 只出现一次的数字 {
    public static void main(String[] args) {
        int[] nums=new int[]{4,1,2,1,2};
        singleNumber(nums);
    }
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i]!=nums[i+1]&&nums[i]!=nums[i+1]) return nums[i];
        }
        return 0;
    }
}
