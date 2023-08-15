package weby.kiwi.domain.collection.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.collection.dto.CollectResDto;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.collection.service.CollectService;

import javax.validation.Valid;

@RestController
@RequestMapping("/collection") //url 부분 설정 필요
@RequiredArgsConstructor @Validated
public class CollectController {

    private final CollectService collectService;
    public CollectController(){
        this.collectService=new CollectService();
    }
    //수집 정보 등록
    @PostMapping
    public ResponseEntity postCollection(@Valid @RequestBody CollectResDto collectResDto){

        Collection collection = new Collection();

        return new ResponseEntity<>(collectResDto, HttpStatus.CREATED); //JSON형식으로 자동변환, HttpStatus.CREATED 변경,응답 상태 전달
    }

    //월별 수집 정보 조회
    @PostMapping
    public ResponseEntity getCollection(@RequestParam("user_id") int user_id,
                                @RequestParam("year") int year,
                                @RequestParam("month") int month) {
        //test
        System.out.println("user_id : " + user_id);
        System.out.println("year : " + year);
        System.out.println("month : " + month);
        //
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
