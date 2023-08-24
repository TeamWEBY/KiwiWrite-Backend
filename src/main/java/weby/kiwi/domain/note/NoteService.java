package weby.kiwi.domain.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.domain.word.entity.Word;
import weby.kiwi.domain.word.repository.WordRepository;
import weby.kiwi.user.User;
import weby.kiwi.user.UserRepository;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    private final WordRepository wordRepository;

    @Autowired // 생성자 주입을 사용, 두 Repository를 주입하는 방식으로 코드 수정
    public NoteService(NoteRepository noteRepository, UserRepository userRepository, WordRepository wordRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    public Note createNote(Long userId, String title, String content){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setUser(user);

        return noteRepository.save(note);
    }

    public Note createNoteWithWords(int targetMonth, int targetDay, String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);

        List<Word> words = wordRepository.findByMonthAndDay(targetMonth, targetDay);
        if (((List<?>) words).size() < 2) {
            throw new IllegalArgumentException("해당하는 날짜의 단어가 없습니다.");
        }

        // 첫 번째 단어와 두 번째 단어 선택
        Word firstWord = words.get(0);
        Word secondWord = words.get(1);

        note.setFirstWord(firstWord.getWordId());
        note.setSecondWord(secondWord.getWordId());

        // Note를 DB에 저장
        return noteRepository.save(note);
    }
}
