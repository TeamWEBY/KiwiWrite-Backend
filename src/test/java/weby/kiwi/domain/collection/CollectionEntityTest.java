package weby.kiwi.domain.collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback
public class CollectionEntityTest {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void saveCollectionTest() {
        // 이미 저장된 Word 엔티티를 가져옴
        Word savedWord = wordRepository.findById(75L).orElse(null);

        Collection collection = Collection.builder()
                .userId(1L)
                .word(savedWord)
                .month(8)
                .build();

        Collection savedCollection = collectionRepository.save(collection);

        assertThat(savedCollection).isNotNull();
        assertThat(savedCollection.getUserId()).isEqualTo(collection.getUserId());
        assertThat(savedCollection.getWord()).isEqualTo(collection.getWord());
        assertThat(savedCollection.getMonth()).isEqualTo(collection.getMonth());
    }

    @Test
    public void findCollectionTest() {
        // 이미 저장된 Word 엔티티를 가져옴
        Word savedWord = wordRepository.findById(75L).orElse(null);

        Collection collection = Collection.builder()
                .userId(1L)
                .word(savedWord)
                .month(8)
                .build();

        Collection savedCollection = collectionRepository.save(collection);

        Collection foundCollection = collectionRepository.findById(savedCollection.getCollectionId()).orElse(null);

        assertThat(foundCollection).isNotNull();
        assertThat(foundCollection.getUserId()).isEqualTo(collection.getUserId());
        assertThat(foundCollection.getWord()).isEqualTo(collection.getWord());
        assertThat(foundCollection.getMonth()).isEqualTo(collection.getMonth());
    }
}