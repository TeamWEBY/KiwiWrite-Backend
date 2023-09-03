package weby.kiwi.domain.collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.collection.dto.CollectionMapper;
import weby.kiwi.domain.collection.dto.CollectionReqDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.service.CollectionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/collection")
@Validated
public class CollectionController {

    private final CollectionService collectionService;
    @Autowired
    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }
    @GetMapping("/collections")
    public ResponseEntity<List<CollectionResDto>> getCollectionByUserIdAndMonth(
            @RequestParam(name = "userId") @NotNull Long userId,
            @RequestParam(name = "month") @NotNull Integer month) {
        List<CollectionResDto> collectionDtoList = collectionService.findCollectionByUserIdAndMonth(userId, month);
        if (collectionDtoList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(collectionDtoList);
    }
}