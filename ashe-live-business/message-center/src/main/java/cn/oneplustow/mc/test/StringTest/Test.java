package cn.oneplustow.mc.test.StringTest;

import org.apache.commons.lang3.StringUtils;

/**
 * Author lwj
 * Date 2022/4/22
 * Description TODO
 * BVersion 1.0
 **/
public class Test {
    public static void main(String[] args) {
//        char[] s = new char[]{'h','e','l','l','o'};
//        reverseString(s);
//        System.out.println(String.valueOf(s));

       int x=123;
        System.out.println(reverse(x));
    }

    /**
     * 反转字符串
     * @param s
     */
    public static void reverseString(char[] s) {
        int k= s.length;
        for (int i = 0; i <k/2+k%2; i++) {
            char temp=s[i];
            s[i]=s[k-i-1];
            s[k-i-1]=temp;
        }

    }

    /**
     * 整数反转
     * @param x
     * @return
     */

    public static int reverse(int x) {
        String temp="";
        boolean isUp=true;
        if(x>0){
            temp=String.valueOf(x);
        }else{
            temp=String.valueOf(x*-1);
            isUp=false;
        }
        char[] s=temp.toCharArray();
        int k= s.length;
        for (int i = 0; i <k/2+k%2; i++) {
            char temp2=s[i];
            s[i]=s[k-i-1];
            s[k-i-1]=temp2;
        }
        try {
            x=Integer.parseInt(String.valueOf(s));
            if(!isUp){
                x=-1*x;
            }
        }catch (Exception e){
            x=0;
        }

        return x;
    }


    /**
     * 整数反转--官方
     * @param x
     * @return
     */

    public static int reverseG(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }
}
