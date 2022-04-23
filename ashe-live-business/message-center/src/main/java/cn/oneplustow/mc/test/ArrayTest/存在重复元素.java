package cn.oneplustow.mc.test.ArrayTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Author lwj
 * Date 2022/4/15
 * Description TODO
 * BVersion 1.0
 **/
public class 存在重复元素 {
    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,1};
        containsDuplicate(nums);
    }
    public static boolean containsDuplicate(int[] nums) {
//        Arrays.sort(nums);
//        int n = nums.length;
//        for (int i = 0; i < n - 1; i++) {
//            if (nums[i] == nums[i + 1]) {
//                return true;
//            }
//        }
//        return false;


        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;

//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if(nums[i]==nums[j]) return true;
//            }
//        }
//        return  false;
    }
}
