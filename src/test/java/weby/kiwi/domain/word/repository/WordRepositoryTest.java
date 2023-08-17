package weby.kiwi.domain.word.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import weby.kiwi.domain.word.entity.Word;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class WordRepositoryTest {

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void testSaveWord() {
        Word word = new Word(8, 1, "여름");
        wordRepository.save(word);

        Optional<Word> savedWord = wordRepository.findByWordId(word.getWordId());
        assertTrue(savedWord.isPresent());
        assertEquals("여름", savedWord.get().getWordName());
    }

    @Test
    public void testDeleteWord() {
        Word word = new Word(8, 1, "불꽃");
        wordRepository.save(word);

        wordRepository.delete(word);

        Optional<Word> deletedWord = wordRepository.findByWordId(word.getWordId());
        assertFalse(deletedWord.isPresent());
    }

    @Test
    public void testFindByWordId() {
        Word word = new Word(8, 1, "밤");
        wordRepository.save(word);

        Optional<Word> foundWord = wordRepository.findByWordId(word.getWordId());
        assertTrue(foundWord.isPresent());
        assertEquals("밤", foundWord.get().getWordName());
    }

    @Test
    public void testFindByMonthAndDay() {
        Word word1 = new Word(8, 2, "약속");
        Word word2 = new Word(8, 2, "별");

        wordRepository.save(word1);
        wordRepository.save(word2);

        List<Word> foundWords = wordRepository.findByMonthAndDay(8, 2);
        assertEquals(2, foundWords.size());
    }

    @Test
    public void testFindByWordName() {
        Word word = new Word(8, 3, "풀");
        wordRepository.save(word);

        Optional<Word> foundWord = wordRepository.findByWordName("풀");
        assertTrue(foundWord.isPresent());
        assertEquals("풀", foundWord.get().getWordName());
    }
}