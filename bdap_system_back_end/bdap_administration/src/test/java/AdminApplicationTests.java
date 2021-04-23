import com.bdap.common.utils.Sql;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;


public class AdminApplicationTests {

  @Test
  public void Test01(){
    String password = "BDAP_SECRET_KET_FEIGN_@**230";
//  String password = "guokegd@bdap_admini_*_#3579";
    String encode = new BCryptPasswordEncoder().encode("123456");
    System.out.println(encode);
  }

  @Test
  public void Test02(){
    String s = Sql.generatorNumber(8);
    System.out.println(s);
  }
//
//  String hashCode = String.valueOf(Math.abs(UUID.randomUUID().toString().hashCode()));
//    return hashCode.substring(hashCode.length() - 6);
}
//bdap_administration
//$2a$10$pmZ0cnXLUxeDdwfiKlscSu6kBkYq1C/au/CPa5qhGsppB8h6uVorm
//bdap_administration,bdap_user_operate,bdap_user_traffic
//ROLE_ADMIN,ROLE_USER,ROLE_API
//http://bm.guokegd.com
//authorization_code,password,refresh_token
