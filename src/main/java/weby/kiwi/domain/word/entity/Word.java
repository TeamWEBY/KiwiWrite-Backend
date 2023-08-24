package weby.kiwi.domain.word.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import weby.kiwi.domain.note.Note;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "words")
@NoArgsConstructor
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "word_id", nullable = false)
    private Long wordId;    //단어번호

    @Column(name = "month", nullable = false)
    private int month;      //월 정보

    @Column(name = "day", nullable = false)
    private int day;      //일 정보

    @Column(name = "word_name", nullable = false)
    private String wordName;    //단어

    //(FK) Note와의 연관관계 매핑
    @OneToMany(mappedBy = "word_id")
    private List<Note> notes = new ArrayList<>();

    public Word(int month, int day, String wordName) {
        if(!(month >= 1 && month <= 12) || !(day >= 1 && day <= 31) || wordName == null) {
            throw new IllegalArgumentException("필수 파라미터 입력 오류!");
        }
        setMonth(month);
        setDay(day);
        setWordName(wordName);
    }
    public Long getWordId()
    {
        return this.wordId;
    }

    public int getMonth()
    {
        return this.month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getDay()
    {
        return this.day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public String getWordName()
    {
        return this.wordName;
    }

    public void setWordName(String wordName)
    {
        this.wordName = wordName;
    }
}
