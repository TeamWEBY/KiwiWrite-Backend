package weby.kiwi.domain.collection.dto;

import org.springframework.stereotype.Component;
import weby.kiwi.domain.collection.entity.Collection;

@Component
public class CollectionMapper {
    public Collection reqDtoToCollection(CollectionReqDto reqDto) {
        return Collection.builder()
                .userId(reqDto.getUserId())
                .month(reqDto.getMonth())
                .build();
    }
    public CollectionResDto collectionToResDto(Collection collection) {
        CollectionResDto resDto = new CollectionResDto();
        resDto.setMonth(collection.getMonth());
        resDto.setWord(collection.getWord());
        return resDto;
    }

}
