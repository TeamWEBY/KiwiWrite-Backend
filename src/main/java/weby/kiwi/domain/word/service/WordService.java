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
    private int currentIndex = 0; //getTodayWords()로 넘길 단어 인덱스

    public List<Word> getTodayWords(int month) {
        List<Word> monthlyWords = findAllByMonth(month);
        List<Word> todayWords = new ArrayList<>();
        if (currentIndex >= monthlyWords.size()) {
            currentIndex = 0;  // 인덱스 초기화
        }
        for (int i = 0; i < 2; i++) {
            if (currentIndex < monthlyWords.size()) {
                todayWords.add(monthlyWords.get(currentIndex++));
            } else {
                throw new WordNotFoundException(month + "월에 충분한 단어가 존재하지 않습니다.");
            }
        }
        return todayWords;
    }

    public void setWords() {
        wordRepository.saveWord(new Word(8, "여름"));
        wordRepository.saveWord(new Word(8, "하늘"));
        wordRepository.saveWord(new Word(8, "밤"));
        wordRepository.saveWord(new Word(8, "불꽃"));
        wordRepository.saveWord(new Word(8, "풀"));
    }

    public Word findByWordId(int word_id) {
        Word foundWord = wordRepository.findByWordId(word_id);
        if (foundWord == null) {
            throw new WordNotFoundException("단어를 찾을 수 없습니다. ID: " + word_id);
        }
        return foundWord;
    }

    public List<Word> findAllByMonth(int month) {
        List<Word> foundWords = wordRepository.findAllByMonth(month);
        if (foundWords.isEmpty()) {
            throw new WordNotFoundException(month + "월의 단어를 찾을 수 없습니다.");
        }
        return foundWords;
    }

    public Word findByWord(String word) {
        Word foundWord = wordRepository.findByWord(word);
        if (foundWord == null) {
            throw new WordNotFoundException("단어를 찾을 수 없습니다. 단어: " + word);
        }
        return foundWord;
    }
}
