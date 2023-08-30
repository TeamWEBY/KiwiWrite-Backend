package weby.kiwi.domain.collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionReqDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.service.CollectionService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
@Validated
public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    @GetMapping
    public ResponseEntity<CollectionResDto> getWordListForMonth(@Valid @ModelAttribute CollectionReqDto reqDto) {
        Optional<Collection> optionalCollection = collectionService.getCollectionByUserIdAndMonth(reqDto.getUserId(), reqDto.getMonth());
        if (optionalCollection.isPresent()) {
            Collection collection = optionalCollection.get();
            CollectionResDto dto = collectionMapper.collectionToResDto(collection);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}