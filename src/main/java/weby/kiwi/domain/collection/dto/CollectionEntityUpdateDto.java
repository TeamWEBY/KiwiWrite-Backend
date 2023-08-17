package weby.kiwi.domain.collection.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import weby.kiwi.domain.word.entity.Word;

import java.util.List;
@Getter
@NoArgsConstructor
public class CollectionEntityUpdateDto {
    private int year;
    private int month;
    private List<Word> wordList;

    @Builder
    public CollectionEntityUpdateDto(int year, int month, List<Word> wordList) {
        this.year = year;
        this.month = month;
        this.wordList = wordList;
    }
}
