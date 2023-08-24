package weby.kiwi.domain.collection.dto;
import weby.kiwi.domain.word.entity.Word;
import java.util.List;
public class CollectionResDto {
    private int month;
    private List<Word> wordList;
    private int wordCnt;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }

    public int getWordCnt() {
        return wordCnt;
    }

    public void setWordCnt(int wordCnt) {
        this.wordCnt = wordCnt;
    }
}
