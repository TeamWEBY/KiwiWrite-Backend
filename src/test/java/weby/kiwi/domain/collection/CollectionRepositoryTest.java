package weby.kiwi.domain.collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.collection.service.CollectionService;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.service.WordService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Rollback
public class CollectionRepositoryTest {
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private WordService wordService;

    @Test
    public void testExistsByUserIdAndWord() {
        Word word = wordService.findByWordId(75L);
        // 컬렉션 존재 여부 확인
        boolean exists = collectionRepository.existsByUserIdAndWord(1L, word);
        assertThat(exists).isTrue();
        //컬렉션 존재 여부 확인
    }

    @Test
    public void testFindByUserIdAndWordId() {
        Word word = wordService.findByWordId(77L);
        Collection collection = Collection.builder()
                .userId(1L)
                .word(word)
                .month(9)
                .build();
        // 컬렉션 ID 조회 및 검증
        Collection col = collectionRepository.findByUserIdAndWord(1L, word);
        assertThat(col).isEqualTo(collection);
    }

    @Test
    public void testFindAllByUserIdAndMonth() {
        // 컬렉션 조회 및 검증
        List<Collection> collections = collectionRepository.findAllByUserIdAndMonth(1L, 8);
        assertThat(collections.size()).isEqualTo(2);
    }
    @Test
    public void testCountByUserIdAndMonth() {
        // 특정 사용자의 특정 월에 해당하는 컬렉션 엔티티의 개수 조회 및 검증
        long count = collectionRepository.countByUserIdAndMonth(1L, 8);
        assertThat(count).isEqualTo(2);
    }
}
