package cn.oneplustow.mc.test.ArrayTest;

/**
 * Author lwj
 * Date 2022/4/15
 * Description TODO
 * BVersion 1.0
 **/
public class 旋转数组 {
    public static void main(String[] args) {
      int[]  nums =new int []{1,2,3,4,5,6,7};
      int k = 3;
      rotate(nums,k);
    }
    public static void rotate(int[] nums, int k) {
            int length = nums.length;
            int temp[] = new int[length];
            //把原数组值放到一个临时数组中，
            for (int i = 0; i < length; i++) {
                temp[i] = nums[i];
            }
            //然后在把临时数组的值重新放到原数组，并且往右移动k位
            for (int i = 0; i < length; i++) {
                nums[(i + k) % length] = temp[i];
            }


//            int[] result = new int[nums.length];
//            for (int i = 0; i < nums.length; i++) {
//                result[i] = nums[i];
//            }
//            for (int i = 0; i < nums.length; i++) {
//                if (i + k < nums.length) {
//                    nums[i + k] = result[i];
//                } else if(i + k - nums.length>=0) {
//                    nums[i + k - nums.length] = result[i];
//                }
//            }

    }
}
