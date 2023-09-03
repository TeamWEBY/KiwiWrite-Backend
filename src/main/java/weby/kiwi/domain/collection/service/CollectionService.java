package weby.kiwi.domain.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.exception.CollectionNotFoundException;
import weby.kiwi.domain.collection.exception.DuplicateDataException;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public Boolean existsByUserIdAndWordId(Long userId, Word word) {
        return collectionRepository.existsByUserIdAndWord(userId, word);
    }

    //월별 collection 조회
    @Transactional
    public List<CollectionResDto> findCollectionByUserIdAndMonth(Long userId, int month) {
        List<Collection> collections = collectionRepository.findAllByUserIdAndMonth(userId, month);
        return collections.stream()
                .map(this::mapToResDto)
                .collect(Collectors.toList());
    }

    private CollectionResDto mapToResDto(Collection collection) {
        CollectionResDto dto = new CollectionResDto();
        dto.setCollectionId(collection.getCollectionId());
        dto.setUserId(collection.getUserId());
        dto.setWordName(collection.getWord().getWordName());
        dto.setMonth(collection.getMonth());
        return dto;
    }

    //월별 엔티티 개수
    @Transactional
    public long getCountByUserIdAndMonth(Long userId, int month) {
        return collectionRepository.countByUserIdAndMonth(userId, month);
    }

    //collection의 Id조회

    @Transactional
    public Optional<Collection> findByUserIdAndWord(Long userId, Word word){
        if(!collectionRepository.existsByUserIdAndWord(userId,word)){
            throw new CollectionNotFoundException("Collection not exists for the given user, word.");
        }
        return Optional.ofNullable(collectionRepository.findByUserIdAndWord(userId,word));
    }
    //새로운 엔티티 생성
    @Transactional
    public Collection createCollection(Long userId, Word word) {
        if (collectionRepository.existsByUserIdAndWord(userId, word)){
            throw new DuplicateDataException("Collection already exists for the given user, word.");
        }
        Collection collection = Collection.builder()
                .userId(userId)
                .word(word)
                .month(word.getMonth())
                .build();
        return collectionRepository.save(collection);
    }

    //엔티티 삭제
    @Transactional
    public void deleteCollectionById(Long collectionId) {
        collectionRepository.deleteById(collectionId);
    }
}
