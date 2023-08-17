package weby.kiwi.domain.collection.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.collection.dto.CollectionSaveReqDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CollectionService {
    public final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }
    //월별 collection 생성
    @Transactional
    public void createCollection(int user_id, int month, Word word){
        Collection collection = Collection.builder()
                .user_id(user_id)
                .word(null)
                .month(month)
                .word_cnt(0)
                .build();
        collectionRepository.save(collection);
    }

    //update: 기존 엔티티 정보 수정
    @Transactional
    public void updateCollection(int user_id, int month, CollectionSaveReqDto dto){
        Collection collection = findByUserIdAndMonth(user_id, month);
        Collection updatedCollection = collectionMapper.updateCollection(collection,dto);
        collectionRepository.save(updatedCollection);

    }

    //월별 collection 검색
    @Transactional
    public Collection findByUserIdAndMonth(int user_id, int month){
        return collectionRepository.findByUserIdAndMonth(user_id, month);
    }

    //collection 단어 추가
    //$$Find the Word entity by word_id. 이부분은 Word 레포지토리 확인 후 수정$$
    @Transactional
    public void addWordInCollection(Collection collection,int word_id){
        //Find the Word entity by word_id.
        Word word = WordRepository.findByWordId(word_id);
        collection.getWord().add(word);
        collection.addWordCnt();
        collectionRepository.save(collection);
    }

    //collection 단어 수정
    @Transactional
    public void updateWordInCollection(Collection collection,int before_id, int after_id){
        //Collection에 before 있는지 확인.
        Word before_word = WordRepository.findByWordId(before_id);
        //Find the Word entity by word_id
        Word after_word = WordRepository.findByWordId(after_id);
        int index =collection.getWord().indexOf(before_word);
        if (index != -1) {
            // Update the word in the collection's word list
            collection.getWord().set(index, after_word);

            // Save the updated Collection entity
            collectionRepository.save(collection);
        }
    }

    //collection 단어 삭제
    @Transactional
    public void deleteWordInCollection(Collection collection, int word_id){
        Word word = WordRepository.findByWordId(word_id);
       if (collection.getWord().remove(word)){
           collection.subWordCnt();
           collectionRepository.save(collection);
       }
    }
}
