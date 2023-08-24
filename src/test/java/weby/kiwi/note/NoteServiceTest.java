package weby.kiwi.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.note.Note;
import weby.kiwi.domain.note.NoteRepository;
import weby.kiwi.domain.note.NoteService;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;
import weby.kiwi.user.User;
import weby.kiwi.user.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void testCreateNote() {
        // User 객체 생성
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPasswd("testPassword");
        userRepository.save(user);


        // 테스트 로직 실행
        Note note = new Note();
        note.setUser(user);
        note.setTitle("Test Title");
        note.setContent("Test Content");
        note.setVisible(Boolean.TRUE);
        noteRepository.save(note);

        // 결과 비교 : 테스트 결과 검증
        Note savedNote = noteRepository.findById(note.getId()).orElse(null);
        assertEquals(user.getId(), savedNote.getUser().getId());
        assertEquals("Test Title", savedNote.getTitle());
        assertEquals("Test Content", savedNote.getContent());
    }

    @Test
    public void testCreateNoteWithWords() {
        //테스트 할 Month와 Day 설정
        int targetMonth = 8;
        int targetDay = 1;
        String title = "Test Title";
        String content = "Test Content";

        // 테스트 유저 설정
        User user = new User();
        user.setId(2L);
        user.setUsername("김이화");
        user.setEmail("test@ewhain.net");
        user.setPasswd("testPassword");
        userRepository.save(user);

        List<Word> words = wordRepository.findByMonthAndDay(targetMonth, targetDay);
        assertTrue(words.size() >= 2, "해당 월/일의 단어가 DB에 두 개 이상 존재 합니다!");

        // 첫 번째 단어와 두 번째 단어 선택
        Word firstWord = words.get(0);
        Word secondWord = words.get(1);

        // Call the method to be tested
        Note createdNote = noteService.createNoteWithWords(targetMonth, targetDay, title, content);
        createdNote.setUser(user);
        createdNote.setVisible(Boolean.TRUE);

        // Assertions
        assertNotNull(createdNote);
        assertEquals(title, createdNote.getTitle());
        assertEquals(content, createdNote.getContent());
        assertEquals(user, createdNote.getUser());
        assertEquals(firstWord.getWordId(), createdNote.getFirstWord());
        assertEquals(secondWord.getWordId(), createdNote.getSecondWord());

        // Print word details + 내가 의도한 DB의 word 맞는지 확인
        System.out.println("First Word: " + firstWord.getWordName() + " (ID: " + firstWord.getWordId() + ")");
        System.out.println("Second Word: " + secondWord.getWordName() + " (ID: " + secondWord.getWordId() + ")");
    }
}
