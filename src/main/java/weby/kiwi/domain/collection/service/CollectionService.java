package weby.kiwi.domain.collection.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.collection.dto.CollectionEntityUpdateDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.repository.CollectionRepository;
import weby.kiwi.domain.word.Word;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectionService {
    public final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    //월별 collection 검색
    @Transactional
    public Collection findByUserIdAndMonth(int user_id, int year, int month){
        return collectionRepository.findByUserIdAndMonth(user_id, year, month);
    }
    //save: 새로운 텐티티 생성 및 저장

    //update: 기존 엔티티 정보 수정
    @Transactional
    public CollectionEntityUpdateDto updateCollection(int user_id,int year, int month, CollectionEntityUpdateDto dto){
        Collection colletcion = findByUserIdAndMonth(user_id, year, month);

    }

    //collection 단어 추가
    //$$Find the Word entity by word_id. 이부분은 Word 레포지토리 확인 후 수정$$
    @Transactional
    public void addWordInCollection(Collection collection,int word_id){
        //Find the Word entity by word_id.
        Word word = wordRepository.findById(word_id);
        collection.getWord().add(word);
        collection.addWordCnt();
        collectionRepository.save(collection);
    }

    //collection 단어 수정
    @Transactional
    public void updateWordInCollection(Collection collection,int before_id, int after_id){
        //Collection에 before 있는지 확인.

        //Find the Word entity by word_id
        Word after_word = wordRepository.findById(after_id);
        Word before_word = collectionRepository;
        int index =collection.getWord().indexOf(before_id);
    }
    //collection 단어 삭제
}
