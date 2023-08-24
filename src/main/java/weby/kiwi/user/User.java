package weby.kiwi.user;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id") // 매핑 추가
    private Long id;

    @Column(name = "name") // 필드명 수정
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "passwd")
    private String passwd;

    public void setId(Long id) { // 타입 변경
        this.id = id;
    }

    public void setUsername(String username) { // 메소드명 수정
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Long getId() {
        return id;
    }

}
