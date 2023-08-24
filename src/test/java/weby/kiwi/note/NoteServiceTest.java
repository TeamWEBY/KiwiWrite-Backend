package weby.kiwi.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import weby.kiwi.user.User;
import weby.kiwi.user.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

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
}
