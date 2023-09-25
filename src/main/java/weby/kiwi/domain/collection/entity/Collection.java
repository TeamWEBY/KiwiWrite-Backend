package weby.kiwi.domain.collection.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import weby.kiwi.domain.word.entity.Word;

import javax.persistence.*;
@Entity
@Table(name = "collections")
@Getter
@Setter
@NoArgsConstructor
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "col_id", nullable = false)
    private Long collectionId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)  // 다대일 관계 설정
    @JoinColumn(name = "word_id",nullable=false)       // 외래 키 매핑
    private Word word;  // Word 엔티티와의 관계

    @Column(name = "month", nullable = false)
    private int month;

    @Builder
    public Collection(Long collectionId, Long userId,  Word word, int month) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.word = word;
        this.month = month;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public Long getUserId() {
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
