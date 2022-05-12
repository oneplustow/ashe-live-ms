package cn.oneplustow.mc.test.ArrayTest;

/**
 * Author lwj
 * Date 2022/4/15
 * Description TODO
 * BVersion 1.0
 **/
public class 买卖股票的最佳时机 {
    public static void main(String[] args) {
        int [] prices=new int[]{7,6,4,3,1};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {
        int result=0;
        for (int i = 0; i < prices.length-1; i++) {
            if(prices[i]<prices[i+1]){
                result =result+prices[i+1]-prices[i];
            }
        }
        return result;
    }
}
