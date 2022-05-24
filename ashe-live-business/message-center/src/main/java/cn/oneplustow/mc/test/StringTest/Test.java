package cn.oneplustow.mc.test.StringTest;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

//       int x=123;
//        System.out.println(reverse(x));

//        String s = "aabb";
//        System.out.println(firstUniqChar(s));


//       String s = "anagram";
//       String t = "nagaram";
//
//        System.out.println(isAnagram(s, t));
//        test();

        String s="A man, a plan, a canal: Panama";

        // 获取三个月前的时间
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH, -3);
//        Date time = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = sdf.format(time);
//        System.out.println(date);

        System.out.println(new Date().getTime());


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

    /**
     * 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        if(chars.length==1){return 0;}
        Set<String> a=new HashSet<String>();
        for (int i = 0; i < chars.length; i++) {
            boolean flag=false;
            if(a.contains(String.valueOf(chars[i]))){
                continue;
            }
            for (int j = i+1; j < chars.length; j++) {
                if(chars[i]==chars[j]){
                    a.add(String.valueOf(chars[i]));
                    flag=true;
                    break;
                }
            }
            if(!flag){
                return i;
            }
        }
        return -1;
    }
    public static int firstUniqCharsG(String s) {
        Map<Character, Integer> frequency = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < s.length(); ++i) {
            if (frequency.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 有效的字母异位词
     */
    public static boolean isAnagram(String s, String t) {
        Map<String, Integer> smap=new HashMap<>();
        Map<String, Integer> tmap=new HashMap<>();
        if(null!=s&&null!=t&&s.length()==t.length() ){
            char[] sc = s.toCharArray();
            char[] tc = t.toCharArray();
            for (int i = 0; i < sc.length; i++) {
                String stemp=String.valueOf(sc[i]);
                String ttemp=String.valueOf(tc[i]);
                smap.put(stemp,smap.getOrDefault(stemp,0)+1);
                tmap.put(ttemp,tmap.getOrDefault(ttemp,0)+1);
            }
            for (Map.Entry<String, Integer> e : smap.entrySet()) {
                  String key= e.getKey();
                  Integer value= e.getValue();
                  Integer tvalue = tmap.get(key);
                if(!value.equals(tvalue)){
                    return false;
                }

            }
            return  true;

        }
        return  false;
    }
    /**
     * 有效的字母异位词--官方
     */
    public static boolean isAnagramG(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public static void test(){
        List<A> list =new ArrayList<>();
        List<String> strings = Arrays.asList("a", "b", "c", "d", "e");
        for (int i = 0; i < 10; i++) {
            A a=new A();
            a.setName(i+"");
            a.setbName(strings);
            list.add(a);
        }

        List<String> ridStrList = list.stream().map(x->x.getbName()).flatMap(List::stream).collect(Collectors.toList());
        for (String s : ridStrList) {
            System.out.println(s);
        }
    }

    /**
     *验证回文串
     * @param s
     * @return
     */

    public static boolean isPalindrome(String s) {
        if (s.length() == 0)
            return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            //因为题中说了，只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            //然后把两个字符变为小写，在判断是否一样，如果不一样，直接返回false
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }
    
    


}
