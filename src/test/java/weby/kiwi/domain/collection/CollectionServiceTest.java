package weby.kiwi.domain.collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.collection.service.CollectionService;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.service.WordService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class CollectionServiceTest {
    @Autowired
    private CollectionService collectionService;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private WordService wordService;

    @Test
    public void testExistsByUserIdAndWordId() {
        Word word1 = wordService.findByWordId(78L);

        // 컬렉션 생성
        Collection collection1 = Collection.builder()
                .userId(1L)
                .word(word1)
                .month(8)
                .build();
        collectionRepository.save(collection1);

        Boolean exists = collectionService.existsByUserIdAndWordId(1L, word1);
        assertThat(exists).isTrue();

        Boolean nonExistent = collectionService.existsByUserIdAndWordId(2L, word1);
        assertThat(nonExistent).isFalse();
    }

    @Test
    public void testGetCollectionByUserIdAndMonth() {
        Word word = wordService.findByWordId(75L);
        Collection collection = Collection.builder()
                .userId(1L)
                .word(word)
                .month(9)
                .build();
        collectionRepository.save(collection);
        // 테스트 로직 작성
        List<CollectionResDto> collections = collectionService.findCollectionByUserIdAndMonth(1L, 8);

        assertThat(collections.size()).isEqualTo(2);

        assertThat(collections.get(0).getUserId()).isEqualTo(1L);
        assertThat(collections.get(1).getUserId()).isEqualTo(1L);

        assertThat(collections.size()).isEqualTo(2);

        assertThat(collections.get(1).getMonth()).isEqualTo(8);
        assertThat(collections.get(0).getMonth()).isEqualTo(8);
    }

    @Test
    public void testGetCountByUserIdAndMonth() {
        // 테스트 로직 작성
        long num = collectionService.getCountByUserIdAndMonth(1L,8);
        List<CollectionResDto> collections = collectionService.findCollectionByUserIdAndMonth(1L, 8);
        assertThat(num).isEqualTo(collections.size());
    }

    @Test
    public void testCreateCollection() {
        // 테스트 로직 작성
        Word word = wordService.findByWordId(79L);
        collectionService.createCollection(1L,word);

        Boolean exists = collectionService.existsByUserIdAndWordId(1L, word);
        assertThat(exists).isTrue();
    }

    @Test
    public void testDeleteCollectionById() {
        Word word = wordService.findByWordId(75L);
//        Word word = wordService.findByWordId(77L);
        Boolean exists = collectionService.existsByUserIdAndWordId(1L, word);
//        Boolean exists = collectionService.existsByUserIdAndWordId(1L, word);
        if (exists) {
            Optional<Collection> collectionOptional = collectionService.findByUserIdAndWord(1L, word);
            Collection collection = collectionOptional.get();
            collectionService.deleteCollectionById(collection.getCollectionId());
        } else {
            System.out.println("컬렉션이 존재하지 않습니다.");
        }
    }
}
