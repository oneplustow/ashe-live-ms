package cn.oneplustow.mc.test.ArrayTest;

import javax.xml.transform.Result;
import java.util.Arrays;

/**
 * Author lwj
 * Date 2022/4/18
 * Description TODO
 * BVersion 1.0
 **/
public class 两个数组的交集 {
    public static void main(String[] args) {
        int [] nums1=new int[]{4,9,5};
        int [] nums2 =new int[]{9,4,9,8,4};
        int[] intersect = intersect(nums1, nums2);
        for (int i = 0; i < intersect.length; i++) {
            System.out.println(intersect[i]);
        }
    }

    /**
     * 给你两个整数数nums1 和 nums2 ，请你以数组形式返回两数组的交集。
     * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int k=0;
        int n=0;
        int[] result=new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            for (int j = k; j < nums2.length; j++) {
                if(nums1[i]==nums2[j]){
                    k=j+1;
                    result[n]=nums1[i];
                    n++;
                    break;
                }
            }
        }
        int[] newResult=Arrays.copyOfRange(result, 0, n);
        return newResult;

    }
}
