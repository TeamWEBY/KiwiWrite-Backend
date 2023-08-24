package weby.kiwi.domain.collection.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import weby.kiwi.domain.collection.dto.CollectionEntityUpdateDto;
import weby.kiwi.domain.word.entity.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity(name = "Collection")
public class Collection {
    @Id

    @Column(name = "userId", nullable = false)
    private long userId;
    @MapsId("userId")

    @OneToMany(mappedBy = "Collection")
    @Column(nullable = true)
    private List<Word> word = new ArrayList<>();

    @Column(nullable = true)
    private int month;

    @Column(nullable = true)
    private int wordCnt;

    @Builder
    public Collection(long userId, List<Word> word, int month, int wordCnt) {
        this.userId = userId;
        this.word = word;
        this.month = month;
        this.wordCnt = wordCnt;
    }

    @PrePersist
    public void prePersist() {
        this.month = 0;
        this.word = null;
        this.wordCnt = 0;
    }

    public long getUser_id() {
        return userId;
    }

    public List<Word> getWord() {
        return word;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setWordCnt(int wordCnt) {
        this.wordCnt = wordCnt;
    }

    public void updateCollection(CollectionEntityUpdateDto dto) {
        this.word.clear();
        this.word.addAll(dto.getWordList());
        this.wordCnt=this.word.size();
        this.month = dto.getMonth();
    }
    public void addWordCnt() {
        this.wordCnt++;
    }
}
