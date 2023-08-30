package weby.kiwi.domain.collection.dto;
import weby.kiwi.domain.word.entity.Word;

import java.util.List;
public class CollectionResDto {
    private int month;
    private Word word;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
