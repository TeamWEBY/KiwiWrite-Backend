package weby.kiwi.domain.note;

import lombok.Getter;
import weby.kiwi.domain.word.entity.Word;
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

    //Note와 Word는 서로 다대일 관계이다 (하나의 단어쌍에 여러개 글 존재할 수 있. 단어가 2개인데 다대다 관계는 지양해야 하므로 이렇게 분리해봄)
    //Note에 추가해야 하는, 단어랑 노트 연결부분 (fk)
    @ManyToOne
    @JoinColumn(name = "first_word_id") // first word와의 관계를 나타내는 외래 키 컬럼(fk)
    private Word firstWord;

    @ManyToOne
    @JoinColumn(name = "second_word_id") // second word와의 관계를 나타내는 외래 키 컬럼(fk)
    private Word secondWord;

    @ManyToOne(fetch = FetchType.EAGER) // 게시글과 유저의 관계는 ManyToOne의 관계
    @JoinColumn(name = "user_id") // foreign 키의 컬럼명 설정
    private User user;

    // Getter, Setter

    public void setTitle(String title) { this.title = title;}

    public void setContent(String content) { this.content = content;}

    public void setUser(User user) { this.user = user;}

    public void setVisible(Boolean visible) { this.visible = visible;}

    // Note 클래스에 setFirstWord()와 setSecondWord() 메서드 추가
    public void setFirstWord(Word firstWord) {
        this.firstWord = firstWord;
    }

    public void setSecondWord(Word secondWord) {
        this.secondWord = secondWord;
    }

    public User getUser() { return user; }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public Long getId() { return id; }

    public boolean getVisible() { return visible;}

    public Long getFirstWord() { return firstWord.getWordId(); }

    public Long getSecondWord() { return secondWord.getWordId(); }

}