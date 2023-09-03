package weby.kiwi.domain.collection.dto;

import org.springframework.stereotype.Component;
import weby.kiwi.domain.collection.entity.Collection;

import java.util.ArrayList;
import java.util.List;

@Component
public class CollectionMapper {
    public Collection reqDtoToCollection(CollectionReqDto reqDto) {
        return Collection.builder()
                .userId(reqDto.getUserId())
                .month(reqDto.getMonth())
                .build();
    }
    public CollectionResDto toResDto(Collection collection) {
        CollectionResDto dto = new CollectionResDto();
        dto.setCollectionId(collection.getCollectionId());
        dto.setUserId(collection.getUserId());
        dto.setWordName(collection.getWord().getWordName());
        dto.setMonth(collection.getMonth());
        return dto;
    }
}
