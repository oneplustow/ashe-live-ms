package cn.oneplustow.sc.service;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;

/**
 * 随机码生成器，用于生成不重复的随机数，依赖基准值,以及用户传递的num值<br></br>
 * *使用场景：兑换码，核销码等<br></br>
 * 1.无序生成、防刷号<br></br>
 * 2.增加校验位，可以快速判断是否为系统生成<br></br>
 * @author CC
 * @title: RandomCodeGenerator
 * @projectName ashe-live-ms
 * @description:
 * @date 2022/4/2717:02
 */
public class RandomCodeGenerator {

    /** Base57基数，6位长度可组合 34296447249(340亿)个int数<br></br>
     * 数字 + 大写字母 + 小写字母
     * 不使用数字"0"，字母大写"O"，字母大写"I"，和字母小写"l"、“o”，
     * */
    public static final String BASE_57 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";

    /** Base33基数，6位长度可组合 1291467969(12亿)个int数<br></br>
     * 数字 + 大写字母 + 小写字母
     * 不使用数字"0"，字母大写"O"，字母大写"I"
     * */
    public static final String BASE_33 = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * * 基位，用于将数字转为字符的基准字符串<br/>
     * * 可以理解为10进制转N进制的对照表<br/>
     */
    private String baseStr = BASE_57;

    /**
     * 用于打乱基位的扰乱字符串，可以是随便一个字符串，
     */
    private String disturb;

    public RandomCodeGenerator() {
    }

    public RandomCodeGenerator(String baseStr) {
        this.baseStr = baseStr;
    }

    public RandomCodeGenerator(String baseStr, String disturb) {
        this.baseStr = disturb(baseStr,disturb);
        this.disturb = disturb;
    }


    /**
     * 对基位进行打散
     * @param baseStr 基位字符
     * @param disturb 打乱key
     * @return
     */
    private String disturb(String baseStr,String disturb){
        char[] baseStrs = baseStr.toCharArray();
        String disturbMd5 = MD5.create().digestHex16(disturb);
        char[] disturbMd5s = disturbMd5.toCharArray();
        for (int i = 0; i < baseStrs.length; i++) {
            int hashCode = (int)Character.valueOf(disturbMd5s[i % disturbMd5s.length]);
            ArrayUtil.swap(baseStrs,i,hashCode % (baseStrs.length - 1) );
        }
        return new String(baseStrs);
    }

    public String gengerCode(long num) {
        return gengerCode(num,-1);
    }

        /**
         * * 根据num数字，生成一个code。num不相同则生成的code一定不相同<br/>
         * * 保证num唯一，则生成的code就唯一<br/>
         * 生成逻辑如下：<br/>
         * 1.num 取模基位字符的位数，拿到索引<br/>
         * 2.通过索引拿到基位对应字符，即为固定码<br/>
         * 3.固定码位数不够则生成随机码<br/>
         * 4.根据 随机码+固定码 生成校验位<br/>
         * 5. 校验位 + 随机码 + 固定码 == 最后返回的Code<br/>
         * @param num 需要转换为code的一个数字
         * @param minLen code最小长度，自动补齐
         * @return
         */
    public String gengerCode(long num,int minLen){

        int baseStrLeng = baseStr.length();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (num != 0){
            index = (int) ((num + index) % baseStrLeng);
            sb.insert(0,baseStr.charAt(index));
            num /= baseStrLeng;
        }
        if(minLen > 2) {
            int randomLen = minLen - 1 - sb.length();
            if (randomLen > 0) {
                sb.insert(0, RandomUtil.randomString(baseStr, randomLen));
            }
        }
        String code = sb.toString();
        return calculateParityBit(code) + code;
    }


    /**
     * 计算校验位，步骤如下<br><br/>
     * 1.将code转成char数组，<br/>
     * 2.然后将每个char字符转为int数字全部相加，<br/>
     * 3.取模基数长度得到索引，<br/>
     * 4.取基数对应位字符<br/>
     * @param code
     * @return
     */
    private char calculateParityBit(String code){
        //校验位
        int parityBit = 0;
        for (char c : code.toCharArray()) {
            parityBit += (int)c;
        }
        return baseStr.charAt((int) (parityBit % baseStr.length()));
    }

    public boolean verifyCode(String code){
        return code.charAt(0) == calculateParityBit(code.substring(1));
    }
}
