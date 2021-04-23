import com.bdap.user.system.entity.RegistrationInformation;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.*;

public class UserApplicationTests {


  @Test
  void test01(){
//    String password = "bdap_user_operate@secret_key*";
    String password = "BDAP_SECRET_KET_FEIGN_@**230";
    String encode = new BCryptPasswordEncoder().encode(password);
    System.out.println(encode);
  }

  @Test
  void test02(){
    SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    Date date = new Date();
    System.out.println(bartDateFormat.format(date));
  }

  @Test
  void test03(){
    String Number = "13500521450";

    String substring = Number.substring(0,3)+("****")+Number.substring(Number.length()-4);
    System.out.println(substring);
  }

  @Test
  void test04(){
//    String Number = "guokegd@bdap_user_*_@7789";
//    String encode = ;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    System.out.println(encoder.encode("guokegd@bdap_user_*_@7789"));
  }

  @Test
  void test05(){
//    String Number = "guokegd@bdap_user_*_@7789";
//    String encode = ;
//    RegistrationInformation registrationInformation = new RegistrationInformation();
//    HashMap<String, Object> map = new HashMap<>();
//    map.put("registrationInformation",registrationInformation);
//    System.out.println(map);
//    System.out.println(registrationInformation);
//    System.out.println(map.get("registrationInformation"));

  }
}
