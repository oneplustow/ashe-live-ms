import org.springframework.util.AntPathMatcher;

public class MainTest {
    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/**/auth-center/**", "http://192.168.1.104:8080/auth-center/login?username=admin&password=admin123"));
    }
}
