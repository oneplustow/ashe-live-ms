import cn.hutool.http.HttpUtil;

public class MainTest {
    public static void main(String[] args) {
        String s = HttpUtil.get("http://oneplustow.cn:1985/api/v1/");
        System.out.println(s);
    }
}
