package weby.kiwi.domain.word.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public void saveWord() {
        ArrayList<Word> wordset = new ArrayList<>();
        wordset.add(new Word(8, "여름"));
        wordset.add(new Word(8, "하늘"));
        wordset.add(new Word(8, "밤"));
        wordset.add(new Word(8, "불꽃"));
        wordset.add(new Word(8, "풀"));
    }
    public Word findWordById(int word_id) {
        Optional<Word> optionalWord = wordRepository.findByWordId(word_id);
        return optionalWord.orElseThrow(() -> new WordNotFoundException());
    }
}
