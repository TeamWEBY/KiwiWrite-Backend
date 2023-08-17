package weby.kiwi.domain.word.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;

    public void setWords() {
        wordRepository.saveWord(new Word(8, 1, "여름"));
        wordRepository.saveWord(new Word(8, 1, "불꽃"));
        wordRepository.saveWord(new Word(8, 2, "밤"));
        wordRepository.saveWord(new Word(8, 2, "약속"));
        wordRepository.saveWord(new Word(8, 3, "풀"));
        wordRepository.saveWord(new Word(8, 3, "소리"));
    }

    public Word findByWordId(int word_id) {
        Word oneWord = wordRepository.findByWordId(word_id);
        if (oneWord == null) {
            throw new WordNotFoundException("단어를 찾을 수 없습니다. ID: " + word_id);
        }
        return oneWord;
    }

    public List<Word> findByDate(int month, int day) {
        List<Word> twoWords = wordRepository.findByDate(month, day);
        if (twoWords.size() < 2) {
            throw new WordNotFoundException("해당 날짜에 충분한 단어가 존재하지 않습니다.");
        } else if (twoWords.size() > 2) {
            throw new WordNotFoundException("해당 날짜에 너무 많은 개수의 단어가 존재합니다.");
        }
        return twoWords;
    }

    public Word findByWord(String word) {
        Word oneWord = wordRepository.findByWord(word);
        if (oneWord == null) {
            throw new WordNotFoundException("단어를 찾을 수 없습니다. 단어: " + word);
        }
        return oneWord;
    }
}
