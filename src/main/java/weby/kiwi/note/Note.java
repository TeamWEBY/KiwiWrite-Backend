package weby.kiwi.note;

import weby.kiwi.user.User;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob // 대용량 데이터를 저장할 때 사용
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER) // 게시글과 유저의 관계는 ManyToOne의 관계
    @JoinColumn(name = "user_id") // foreign 키의 컬럼명 설정
    private User user;

    public void setTitle(String title) { this.title = title;}

    public void setContent(String content) { this.content = content;}

    public User getUser() { return user; }

    // Setter 메서드 추가
    public void setUser(User user) { this.user = user;}

}