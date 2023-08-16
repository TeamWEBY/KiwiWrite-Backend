package weby.kiwi.domain.word.entity;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue
    @Column(name = "word_id", nullable = false)
    private int word_id;    //단어번호

    @Column(name = "month", nullable = false)
    private int month;      //월 정보

    @Column(name = "word", nullable = false)
    private String word;    //단어

    public Word(int month, String word) {
        if(!(month >= 1 && month <= 12) || word == null) {
            throw new IllegalArgumentException("필수 파라미터 입력 오류!");
        }
        setMonth(month);
        setWord(word);
    }
    public int getWordId()
    {
        return this.word_id;
    }

    public int getMonth()
    {
        return this.month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public String getWord()
    {
        return this.word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }
}
