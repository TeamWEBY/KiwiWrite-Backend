//package weby.kiwi.domain.user.dto;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import weby.kiwi.domain.user.entity.User;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Data
//@NoArgsConstructor
//@EnableWebSecurity
//public class SignupReqDto {
//
//    private String userName;
//    private String email;
//    private String passwd;
//
//    public SignupReqDto(String userName, String passwd, String email) {
//        this.userName = userName;
//        this.email = email;
//        this.passwd = passwd;
//    }
//
//    public User toEntity(){
//        User user = new User();
//        user.setUserName(userName);
//        user.setPasswd(new BCryptPasswordEncoder().encode(passwd));
//        user.setEmail(email);
//        return user;
//    }
//}