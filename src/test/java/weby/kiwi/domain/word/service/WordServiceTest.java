package weby.kiwi.domain.word.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.exception.WordNotFoundException;
import weby.kiwi.domain.word.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class WordServiceTest {

    private WordService wordService;

    @Mock
    private WordRepository testRepo;

    private List<Word> testWords;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        testWords = new ArrayList<>();
        testWords.add(new Word(8, 1, "여름"));
        testWords.add(new Word(8, 1, "불꽃"));
        testWords.add(new Word(8, 2, "밤"));
        testWords.add(new Word(8, 2, "약속"));
        testWords.add(new Word(8, 3, "풀"));
        testWords.add(new Word(8, 3, "소리"));

        // Simulate the behavior of the mock repository
        when(testRepo.findByWordId(anyLong())).thenAnswer(invocation -> {
            Long wordId = invocation.getArgument(0);
            return testWords.stream().filter(word -> word.getWordId().equals(wordId)).findFirst();
        });

        when(testRepo.findByMonthAndDay(anyInt(), anyInt())).thenAnswer(invocation -> {
            int month = invocation.getArgument(0);
            int day = invocation.getArgument(1);
            return testWords.stream().filter(word -> word.getMonth() == month && word.getDay() == day)
                    .collect(Collectors.toList());
        });

        when(testRepo.findByWordName(anyString())).thenAnswer(invocation -> {
            String wordName = invocation.getArgument(0);
            return testWords.stream().filter(word -> word.getWordName().equals(wordName)).findFirst();
        });

        wordService = new WordService(testRepo);
    }

//    @Test
//    public void testFindByWordIdExistingId() {
//        Long wordId = testWords.get(0).getWordId();
//        Word foundWord = wordService.findByWordId(wordId);
//        assertNotNull(foundWord);
//        assertEquals("여름", foundWord.getWordName());
//    }
//
//    @Test
//    public void testFindByWordIdNonExistingId() {
//        Long wordId = 100L;
//        assertThrows(WordNotFoundException.class, () -> wordService.findByWordId(wordId));
//    }

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
