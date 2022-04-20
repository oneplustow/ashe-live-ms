package cn.oneplustow.mc.test.ArrayTest;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Author lwj
 * Date 2022/4/20
 * Description TODO
 * BVersion 1.0
 **/
public class 有效的数独 {
    public static void main(String[] args) {
        System.out.println(4/3);
//        char[][] board = new char[][]
//                {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
//                        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
//                        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
//                        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
//                        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
//                        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
//                        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
//                        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
//                        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        char[][] board = new char[][] {
                {'.','.','.','.','5','.','.','1','.'},
                {'.','4','.','3','.','.','.','.','.'},
                {'.','.','.','.','.','3','.','.','1'},
                {'8','.','.','.','.','.','.','2','.'},
                {'.','.','2','.','7','.','.','.','.'},
                {'.','1','5','.','.','.','.','.','.'},
                {'.','.','.','.','.','2','.','.','.'},
                {'.','2','.','9','.','.','.','.','.'},
                {'.','.','4','.','.','.','.','.','.'}};
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        Set<String> set1 = null;
        Map<String, HashSet<String>> map = new HashMap<>();
        Map<String, HashSet<String>> map2 = new HashMap<>();
        for (int i = 0; i < board.length; i++) {

            set1 = new HashSet<>();
            char[] a = board[i];
            for (int j = 0; j < a.length; j++) {
                if((i==0&&j==7)||(i==2&&j==8)){
                    System.out.println(i+j);
                }
                char b = a[j];
               if('.' == a[j]) {
                   continue;
               }
                //每一列
                if (null == map.get(String.valueOf(j))) {
                    HashSet<String> set2 = new HashSet<>();
                    set2.add(String.valueOf(b));
                    map.put(String.valueOf(j), set2);
                }else{
                    HashSet<String> set2= map.get(String.valueOf(j));
                    if(!set2.add(String.valueOf(b))){
                        return false;
                    }
                }
                //每一行
                if (!set1.add(String.valueOf(b))) {
                    return false;
                }
                // 每三个
                int k=i/3;
                int l=j/3;
                if(k==0&&l==2){
                    System.out.println(k+j);
                }
                String kl=k+"-"+l;
                if (null == map2.get(kl)) {
                    HashSet<String> set3 = new HashSet<>();
                    set3.add(String.valueOf(b));
                    map2.put(kl, set3);
                }else{
                    HashSet<String> set3= map2.get(kl);
                    if(!set3.add(String.valueOf(b))){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 官方 把数字直接变成下标，通过下标判断如果重复 牛逼呀
     * @param board
     * @return
     */

    public static boolean isValidSudoku2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
