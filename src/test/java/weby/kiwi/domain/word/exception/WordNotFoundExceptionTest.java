package weby.kiwi.domain.word.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordNotFoundExceptionTest {

    @Test
    public void testConstructor() {
        // Arrange
        String message = "테스트 메시지";

        // Act
        WordNotFoundException exception = new WordNotFoundException(message);

        // Assert
        assertEquals(message, exception.getMessage());
    }
}
