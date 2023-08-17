package weby.kiwi.domain.collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.collection.dto.CollectPostDto;
import weby.kiwi.domain.collection.dto.CollectionResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.service.CollectionService;
import weby.kiwi.domain.user.entity.User;
import weby.kiwi.domain.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/collection") //url 부분 설정 필요
@RequiredArgsConstructor
@Validated
public class CollectionController {

    private final CollectionService collectionService;

    public CollectionController() {
        this.collectionService = new CollectionService();
    }

    //수집 정보 등록
    @PostMapping
    public ResponseEntity postCollection(@Valid @RequestBody CollectPostDto collectPostDto) {

        Collection collection = new Collection();

        return new ResponseEntity<>(collectPostDto, HttpStatus.CREATED); //JSON형식으로 자동변환, HttpStatus.CREATED 변경,응답 상태 전달
    }

    //월별 수집 정보 조회
    @GetMapping
    public List<CollectionResDto> getCollection(@RequestParam("user_id") int user_id,
                                                @RequestParam("year") int year,
                                                @RequestParam("month") int month) {
        //test
        System.out.println("user_id : " + user_id);
        System.out.println("year : " + year);
        System.out.println("month : " + month);
        //
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<CollectionResDto> getCollectionByMonth(@RequestParam("user_id") int user_id,
                                                       @RequestParam("year") int year,
                                                       @RequestParam("month") int month) {
        User user = UserService.findByUserId(user_id);
        Collection collection = collectionService.findByUserIdAndMonth(user_id,year,month);

    }

}
