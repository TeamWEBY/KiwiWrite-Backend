package weby.kiwi.domain.word.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
        if (this.wordRepository.findAll().isEmpty())
            setWords();
    }

    private void setWords() {
        wordRepository.save(new Word(8, 1, "여름"));
        wordRepository.save(new Word(8, 1, "불꽃"));
        wordRepository.save(new Word(8, 2, "밤"));
        wordRepository.save(new Word(8, 2, "약속"));
        wordRepository.save(new Word(8, 3, "풀"));
        wordRepository.save(new Word(8, 3, "소리"));
    }

    @Transactional(readOnly = true)
    public Word findByWordId(Long wordId) {
        return wordRepository.findByWordId(wordId)
                .orElseThrow(() -> new WordNotFoundException(("해당 ID의 단어를 찾을 수 없습니다.")));
    }

    @Transactional(readOnly = true)
    public List<Word> findByMonthAndDay(int month, int day) {
        List<Word> words = wordRepository.findByMonthAndDay(month, day);
        if (words.size() < 2) {
            throw new WordNotFoundException("해당 날짜에 충분한 단어가 존재하지 않습니다.");
        } else if (words.size() > 2) {
            throw new WordNotFoundException("해당 날짜에 너무 많은 개수의 단어가 존재합니다.");
        }
        return words;
    }

    @Transactional(readOnly = true)
    public Word findByWordName(String wordName) {
        return wordRepository.findByWordName(wordName)
                .orElseThrow(() -> new WordNotFoundException(("해당 단어를 찾을 수 없습니다.")));
    }
}