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

    @Column(unique = true, name = "user_name", nullable = false)
    private String userName; // 유저이름

    @Column(unique = true, name = "email", nullable = false)
    private String email; // 이메일

    @Column(name = "passwd", nullable = false)
    private String passwd; // 비밀번호

    @Column(name = "bio") //null일 수 있다.
    private String bio; // 한줄소개

    //(FK) Note와의 연관관계 매핑
    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();
}