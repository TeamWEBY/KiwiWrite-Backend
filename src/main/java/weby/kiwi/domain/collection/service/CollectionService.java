package weby.kiwi.domain.collection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.collection.dto.CollectionEntityUpdateDto;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionReqDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.exception.CollectionNotFoundException;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;


import javax.transaction.Transactional;
import java.util.List;
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


    @Transactional
    public Optional<Collection> findByUserIdAndMonth(CollectionReqDto dto) {
        Optional<Collection> optionalCollection = collectionRepository.findByUserIdAndMonth(dto.getUserId(), dto.getMonth());
        if (!optionalCollection.isPresent()) {
            throw new CollectionNotFoundException("Collection not found for user " + dto.getUserId() + " and month " + dto.getMonth());
        }
        return optionalCollection;
    }


    @Transactional
    public Boolean existsByCollectionAndWord(Collection collection, Word word) {
        return collectionRepository.existsByCollectionAndWord(collection, word);
    }

    @Transactional
    public List<Word> findWordList(Collection collection) {
        return collectionRepository.findWordList(collection);
    }

    //create: 새로운 텐티티 생성
    @Transactional
    public Collection createCollection(long userId, int month) {
        Collection collection = Collection.builder()
                .userId(userId)
                .word(null)
                .month(month)
                .wordCnt(0)
                .build();
        return collectionRepository.save(collection);
    }

    //update: 엔티티 수정
    @Transactional
    public void updateCollection(CollectionReqDto reqDto,
                                 CollectionEntityUpdateDto updateDto) {
        Optional<Collection> optionalCollection = findByUserIdAndMonth(reqDto);
        optionalCollection.ifPresent(collection -> {
            collectionMapper.updateCollectionFromUpdateDto(updateDto, collection);
            collectionRepository.save(collection);
        });
    }

    //엔티티 삭제
    @Transactional
    public void deleteCollection(CollectionReqDto dto) {
        Optional<Collection> optionalCollection = findByUserIdAndMonth(dto);
        optionalCollection.ifPresent(collection -> collectionRepository.delete(collection));
    }


    @Transactional
    public void addWordInCollection(Collection collection, Word word) {
        collection.getWord().add(word);
        collection.addWordCnt();
        collectionRepository.save(collection);
    }

    @Transactional
    public void deleteWordInCollection(Collection collection, Word word) {
        collection.getWord().remove(word);
        collectionRepository.save(collection);
    }
}
