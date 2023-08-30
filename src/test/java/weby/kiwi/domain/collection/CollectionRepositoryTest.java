package weby.kiwi.domain.collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class CollectionRepositoryTest {
    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private WordRepository wordRepository;
    @BeforeEach
    public void cleanup() {
        collectionRepository.deleteAll();
    }
    @Test
    public void testFindAllByUserIdAndMonth() {
        Word word1 = wordRepository.findById(75L).orElse(null);

        // 컬렉션 생성
        Collection collection1 = Collection.builder()
                .userId(1L)
                .word(word1)
                .month(8)
                .build();
        collectionRepository.save(collection1);

        Word word2 = wordRepository.findById(76L).orElse(null);
        Collection collection2 = Collection.builder()
                .userId(1L)
                .word(word2)
                .month(8)
                .build();
        collectionRepository.save(collection2);

        // 컬렉션 조회 및 검증
        List<Collection> collections = collectionRepository.findAllByUserIdAndMonth(1L, 8);
        assertThat(collections.size()).isEqualTo(2);
    }

    @Test
    public void testExistsByUserIdAndWord() {
        Word word = wordRepository.findById(75L).orElse(null);

        // 컬렉션 생성
        Collection collection = Collection.builder()
                .userId(1L)
                .word(word)
                .month(8)
                .build();
        collectionRepository.save(collection);


        // 컬렉션 존재 여부 확인
        boolean exists = collectionRepository.existsByUserIdAndWord(1L, word);
        assertThat(exists).isTrue();
    }

    @Test
    public void testCountByUserIdAndMonth() {
        // 저장할 Word 엔티티 생성
        Word word1 = wordRepository.findById(75L).orElse(null);

        // 컬렉션 생성
        Collection collection1 = Collection.builder()
                .userId(1L)
                .word(word1)
                .month(8)
                .build();
        collectionRepository.save(collection1);

        Word word2 = wordRepository.findById(76L).orElse(null);
        Collection collection2 = Collection.builder()
                .userId(1L)
                .word(word2)
                .month(8)
                .build();
        collectionRepository.save(collection2);

        Word word3 = wordRepository.findById(77L).orElse(null);
        Collection collection3 = Collection.builder()
                .userId(1L)
                .word(word3)
                .month(9)
                .build();
        collectionRepository.save(collection3);

        // 특정 사용자의 특정 월에 해당하는 컬렉션 엔티티의 개수 조회 및 검증
        long count = collectionRepository.countByUserIdAndMonth(1L, 8);
        assertThat(count).isEqualTo(2);
    }
}
