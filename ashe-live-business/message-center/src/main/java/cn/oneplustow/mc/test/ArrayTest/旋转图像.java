package cn.oneplustow.mc.test.ArrayTest;

/**
 * Author lwj
 * Date 2022/4/22
 * Description TODO
 * BVersion 1.0
 **/
public class 旋转图像 {
    public static void main(String[] args) {
        int[][] matrix =new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
    }

    public static void rotate(int[][] matrix) {
        int l=matrix.length;
        // 上下对调
        for (int i = 0; i <(l/2)+l%2; i++) {
           int[] temp=matrix[i];
           matrix[i]= matrix[l-i-1];
           matrix[l-i-1]=temp;
        }
        // 对折
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i+1; j++) {
                if(i==j){
                    continue;
                }
                int temp= matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i]=temp;
            }
        }


    }
}
