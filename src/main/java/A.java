import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//PASSWORD ENCRYPTION CONCEPT
public class A {
    public static void main(String[] args) {
//        BCryptPasswordEncoder en = new BCryptPasswordEncoder();
//        System.out.println(en.encode("testing"));

        String enPwd = BCrypt.hashpw("testing", BCrypt.gensalt(4));
        System.out.println(enPwd);

    }
}
