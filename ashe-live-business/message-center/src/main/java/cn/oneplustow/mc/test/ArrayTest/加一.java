package cn.oneplustow.mc.test.ArrayTest;

import java.util.Arrays;

/**
 * Author lwj
 * Date 2022/4/18
 * Description TODO
 * BVersion 1.0
 **/
public class 加一 {
    public static void main(String[] args) {
        int [] nums1=new int[]{1,2,3};
        for (int i : plusOne(nums1)) {
            System.out.println(i);
        }
    }

    public static int[] plusOne(int[] digits) {
        boolean flag=false;
        for (int i = digits.length-1; i >= 0; i--) {
            int a=digits[i];
            if(flag){
                digits[i]=a+1;
                if(digits[i]==10){
                    flag=true;
                    digits[i]=0;
                }else{
                    flag=false;
                    digits[i]=a+1;
                }
            }else{
                digits[i]=a+1;
                if(digits[i]==10){
                    flag=true;
                    digits[i]=0;
                }else{
                    break;
                }

            }

        }
        if(flag){
            int[] result=new int[digits.length+1];
            for (int i = 0; i < result.length; i++) {
                if(i==0){
                    result[i]=1;
                }else{
                    result[i]=0;
                }
            }
            return result;
        }
        return digits;

    }
}
