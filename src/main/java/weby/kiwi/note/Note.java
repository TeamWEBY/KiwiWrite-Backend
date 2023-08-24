package weby.kiwi.note;

import weby.kiwi.user.User;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Lob // 대용량 데이터를 저장할 때 사용
    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "visible")
    private boolean visible;

    @ManyToOne(fetch = FetchType.EAGER) // 게시글과 유저의 관계는 ManyToOne의 관계
    @JoinColumn(name = "user_id") // foreign 키의 컬럼명 설정
    private User user;

    // Getter, Setter

    public void setTitle(String title) { this.title = title;}

    public void setContent(String content) { this.content = content;}

    public void setUser(User user) { this.user = user;}

    public void setVisible(Boolean visible) { this.visible = visible;}

    public User getUser() { return user; }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public Long getId() { return id; }

    public boolean getVisible() { return visible;}
}