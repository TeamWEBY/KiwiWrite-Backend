package weby.kiwi.domain.word.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordServiceTest {

    private WordService wordService;
    private final WordRepository testRepo;
    private List<Word> testWords;

    public WordServiceTest(WordRepository wordRepository) {
        this.testRepo = wordRepository;
    }

    @BeforeEach
    public void setUp() {
        testWords = new ArrayList<>();
        testWords.add(new Word(8, 1, "여름"));
        testWords.add(new Word(8, 1, "불꽃"));
        testWords.add(new Word(8, 2, "밤"));
        testWords.add(new Word(8, 2, "약속"));
        testWords.add(new Word(8, 3, "풀"));
        testWords.add(new Word(8, 3, "소리"));

        testRepo.saveAll(testWords);
        wordService = new WordService(testRepo);
    }

    @Test
    public void testFindByWordIdExistingId() {
        Long wordId = testWords.get(0).getWordId();
        Word foundWord = wordService.findByWordId(wordId);
        assertNotNull(foundWord);
        assertEquals("여름", foundWord.getWordName());
    }

    @Test
    public void testFindByWordIdNonExistingId() {
        Long wordId = 100L;
        assertThrows(WordNotFoundException.class, () -> wordService.findByWordId(wordId));
    }

    @Test
    public void testFindByMonthAndDay() {
        int month = 8;
        int day = 1;
        List<Word> foundWords = wordService.findByMonthAndDay(month, day);
        assertNotNull(foundWords);
        assertEquals(2, foundWords.size());
    }

    @Test
    public void testFindByWordNameExistingName() {
        String wordName = "여름";
        Word foundWord = wordService.findByWordName(wordName);
        assertNotNull(foundWord);
        assertEquals(wordName, foundWord.getWordName());
    }

    @Test
    public void testFindByWordNameNonExistingName() {
        String wordName = "없는단어";
        assertThrows(WordNotFoundException.class, () -> wordService.findByWordName(wordName));
    }
}
