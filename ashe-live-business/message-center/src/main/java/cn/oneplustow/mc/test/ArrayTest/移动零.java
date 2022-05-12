package cn.oneplustow.mc.test.ArrayTest;

/**
 * Author lwj
 * Date 2022/4/20
 * Description TODO
 * BVersion 1.0
 **/
public class 移动零 {
    public static void main(String[] args) {
        int[]  nums =new int []{0,1,0,3,12};
        moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    public static void moveZeroes(int[] nums) {
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]==0){
                j++;
            }else{
                nums[i - j] = nums[i];
            }
        }
        for (int i = 0; i < j; i++) {
            nums[nums.length-1-i]=0;
        }
    }
}
