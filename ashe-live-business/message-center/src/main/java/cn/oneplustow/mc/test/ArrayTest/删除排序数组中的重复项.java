package cn.oneplustow.mc.test.ArrayTest;

/**
 * Author lwj
 * Date 2022/4/13
 * Description TODO
 * BVersion 1.0
 **/
public class 删除排序数组中的重复项 {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        int[] expectedNums = new int[]{1, 2};
        int k = removeDuplicates(nums); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

    /**
     * 双指针解决,前提有排序
     *
     * @param A
     * @return
     */
    public static int removeDuplicates(int[] A) {
        //边界条件判断
        if (A == null || A.length == 0)
            return 0;
        int left = 0;
        for (int right = 1; right < A.length; right++)
            //如果左指针和右指针指向的值一样，说明有重复的，
            //这个时候，左指针不动，右指针继续往右移。如果他俩
            //指向的值不一样就把右指针指向的值往前挪
            if (A[left] != A[right])
                A[++left] = A[right];
        return ++left;
    }
}
