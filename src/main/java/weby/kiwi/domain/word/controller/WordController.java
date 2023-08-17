/*package weby.kiwi.domain.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.service.WordService;

@RestController
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping("/words")
    public ResponseEntity<Word> createWord(@RequestBody WordRequest request) {
        Word newWord = wordService.createWord(request.getMonth(), request.getWord());
        return ResponseEntity.ok(newWord);
    }
}*/
