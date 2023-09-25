package weby.kiwi.domain.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// HTTP 리퀘스트를 처리하기 위한 NoteController 클래스 생성
@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/note")
    public ResponseEntity<String> createNote(@RequestBody NoteRequest noteRequest) {
        Note note = noteService.createNote(noteRequest.getUserId(), noteRequest.getTitle(), noteRequest.getContent());
        return ResponseEntity.ok("글이 업로드 되었습니다");
    }

    @GetMapping("/note")
    public String save() {
        return "글을 저장했습니다"; // 사용자에게 보여줄 내용
    }
}
