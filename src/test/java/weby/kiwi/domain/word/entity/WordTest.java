package weby.kiwi.domain.word.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordTest {

    @Test
    public void testCreateWord() {
        // Arrange
        int month = 8;
        int day = 1;
        String wordName = "여름";

        // Act
        Word newWord = new Word(month, day, wordName);

        // Assert
        assertEquals(month, newWord.getMonth());
        assertEquals(day, newWord.getDay());
        assertEquals(wordName, newWord.getWordName());
    }

    @Test
    public void testCreateInvalidWord() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Word(0, 1, "올바르지 않은 월"));
        assertThrows(IllegalArgumentException.class, () -> new Word(13, 1, "올바르지 않은 월"));
        assertThrows(IllegalArgumentException.class, () -> new Word(1, 0, "올바르지 않은 일"));
        assertThrows(IllegalArgumentException.class, () -> new Word(1, 32, "올바르지 않은 일"));
        assertThrows(IllegalArgumentException.class, () -> new Word(1, 1, null));
    }
}
