package weby.kiwi.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import weby.kiwi.domain.note.Note;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId; //유저번호

    @Column(name = "user_name", nullable = false)
    private String userName; // 유저이름

    @Column(name = "email", nullable = false)
    private String email; // 이메일

    @Column(name = "passwd", nullable = false)
    private String passwd; // 비밀번호

    @Column(name = "bio") //null일 수 있다.
    private String bio; // 한줄소개

    //(FK) Note와의 연관관계 매핑
    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();

    public User(String userName, String email, String passwd) {
        if (userName == null || email == null || passwd == null) {
            throw new IllegalArgumentException("User의 필수 파라미터 입력 오류!");
//        } else if (isValidEmail(email)) {
//            throw new IllegalArgumentException("User의 필수 파라미터 입력 오류!");
            //email형식이 올바른지 등도 체크해야 함
        }
        setUserName(userName);
        setEmail(email);
        setPasswd(passwd);
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}