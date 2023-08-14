package weby.kiwi.domain.word.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.service.WordService;

@RestController
public class WordController {
    @Autowired
    private WordService wordService;

    public Word findWordById(@PathVariable int word_id) {
        try {
            return wordService.findWordById(word_id);
        } catch (WordNotFoundException ex) {
            //return
            throw ex;
        }
    }
}