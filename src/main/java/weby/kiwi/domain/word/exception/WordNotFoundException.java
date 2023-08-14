package weby.kiwi.domain.word.exception;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException() {
        super("해당 단어를 찾지 못했습니다.");
    }
}
