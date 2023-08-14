package weby.kiwi.domain.word.entity;

import com.sun.istack.NotNull;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int word_id;    //단어번호 (영속성에 등록되면 자동 할당...?

    @NotNull
    private int month;      //월 정보

    @NotNull
    private String word;    //단어

    public Word(int month, String word) {
        if(!(month >= 1 && month <= 12) || word == null) {
            throw new IllegalArgumentException("필수 파라미터 입력 오류!");
        }
        setMonth(month);
        setWord(word);
    }

    private int getMonth()
    {
        return this.month;
    }

    private void setMonth(int month)
    {
        this.month = month;
    }

    private String getWord()
    {
        return this.word;
    }

    private void setWord(String word)
    {
        this.word = word;
    }
}
