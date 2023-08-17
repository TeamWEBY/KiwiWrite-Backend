package weby.kiwi.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import weby.kiwi.user.User;
import weby.kiwi.user.UserRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    public Note createNote(Long userId, String title, String content){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다"));

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setUser(user);

        return noteRepository.save(note);
    }
}
