package weby.kiwi.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// HTTP 리퀘스트를 처리하기 위한 NoteController 클래스 생성
@Controller
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    @GetMapping("/note/save")
    public String save(){
        return "layout/note/note-save";
    }

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteRequest noteRequest) {
        Note newNote = new Note();
        newNote.setTitle(noteRequest.getTitle());
        newNote.setContent(noteRequest.getContent());

        Note savedNote = noteRepository.save(newNote);

        return ResponseEntity.ok(savedNote);
    }
}