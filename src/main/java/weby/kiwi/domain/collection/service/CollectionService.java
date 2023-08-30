package weby.kiwi.domain.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionReqDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.exception.CollectionNotFoundException;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;


import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class CollectionService {
    public final CollectionRepository collectionRepository;
    public final WordRepository wordRepository;
    private final CollectionMapper collectionMapper;
    @Autowired
    public CollectionService(CollectionRepository collectionRepository, WordRepository wordRepository, CollectionMapper collectionMapper) {
        this.collectionRepository = collectionRepository;
        this.wordRepository = wordRepository;
        this.collectionMapper = collectionMapper;
    }


    //컬렉션 단어 exists
    @Transactional
    public Boolean existsByUserIdAndWordId(long userId, Word word) {
        return collectionRepository.existsByUserIdAndWord(userId, word);
    }
    //find collection
    public Optional<Collection> getCollectionByUserIdAndMonth(long userId, int month) {
        return collectionRepository.findByUserIdAndMonth(userId, month);
    }
    //월별 엔티티 개수
    @Transactional
    public long getCountByUserIdAndMonth(long userId, int month) {
        return collectionRepository.countByUserIdAndMonth(userId, month);
    }
    //새로운 텐티티 생성
    @Transactional
    public Collection createCollection(long collectionId,long userId, Word word, int month) {
        Collection collection = Collection.builder()
                .collectionId(collectionId)
                .userId(userId)
                .word(word)
                .month(month)
                .build();
        return collectionRepository.save(collection);
    }
    //엔티티 삭제
    @Transactional
    public void deleteCollectionById(long collectionId) {
        collectionRepository.deleteById(collectionId);
    }
}
