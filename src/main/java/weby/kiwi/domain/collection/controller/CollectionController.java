package weby.kiwi.domain.collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionReqDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.service.CollectionService;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collection") //url 부분 설정 필요
@RequiredArgsConstructor
@Validated
public class CollectionController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    //월별 collection 정보 조회
    @GetMapping("/{userId}/month/{month}")
    public ResponseEntity<CollectionResDto> getWordListForMonth(  @Valid @ModelAttribute CollectionReqDto reqDto) {
        Optional<Collection> optionalCollection = collectionService.findByUserIdAndMonth(reqDto);
        if (optionalCollection.isPresent()) {
            Collection collection = optionalCollection.get();
            CollectionResDto dto = collectionMapper.CollectiontoResDto(collection);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
