package weby.kiwi.domain.collection.dto;

import org.springframework.stereotype.Component;
import weby.kiwi.domain.collection.dto.CollectionEntityUpdateDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
@Component
public class CollectionMapper {
    public Collection ReqDtotoCollection(CollectionReqDto reqDto) {
        return Collection.builder()
                .userId(reqDto.getUserId())
                .month(reqDto.getMonth())
                .build();
    }
    public CollectionResDto CollectiontoResDto(Collection collection) {
        CollectionResDto resDto = new CollectionResDto();
        resDto.setMonth(collection.getMonth());
        resDto.setWordList(collection.getWord());
        resDto.setWordCnt(collection.getWordCnt());
        return resDto;
    }

    public void updateCollectionFromUpdateDto(CollectionEntityUpdateDto dto, Collection collection) {
        collection.setMonth(dto.getMonth());
        collection.getWord().clear();
        collection.getWord().addAll(dto.getWordList());
        collection.setWordCnt(dto.getWordList().size());
    }
}
