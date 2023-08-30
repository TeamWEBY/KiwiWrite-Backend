package weby.kiwi.domain.collection.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import weby.kiwi.domain.word.entity.Word;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id", nullable = false)
    private long collectionId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계 설정
    @JoinColumn(name = "word_id")       // 외래 키 매핑
    private Word word;  // Word 엔티티와의 관계

    @Column(name = "month", nullable = false)
    private int month;

    @Builder
    public Collection(long collectionId, long userId,  Word word, int month) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.word = word;
        this.month = month;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
