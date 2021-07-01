import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        //AntPathMatcher antPathMatcher = new AntPathMatcher();
        //System.out.println(antPathMatcher.match("/**/auth-center/**", "http://192.168.1.104:8080/auth-center/login?username=admin&password=admin123"));
        String context = FileUtil.readString("D:\\test.txt", "utf-8");
        String[] split = context.split("\r\n");
        Map<String,String> map = new HashMap<>();
        String back = "";
        for (String s : split) {
            if (StrUtil.isBlank(s)) {continue;}
            if (StrUtil.startWith(s, '#')) {back += s;continue;}
            map.put(s,back);
            back = "";
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("值:"+entry.getKey()+"  备注:"+entry.getValue());
        }
    }
}
