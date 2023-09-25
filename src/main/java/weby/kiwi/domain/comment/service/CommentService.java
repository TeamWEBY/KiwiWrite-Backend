package weby.kiwi.domain.comment.service;


import org.springframework.beans.factory.annotation.Autowired;
import weby.kiwi.domain.comment.entity.Comment;
import weby.kiwi.domain.comment.repository.CommentRepository;
import weby.kiwi.domain.note.Note;
import weby.kiwi.domain.note.NoteRepository;
import weby.kiwi.domain.user.repository.UserRepository;
import weby.kiwi.domain.user.entity.User;

import java.util.List;

public class CommentService {

    private final CommentRepository commentRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(NoteRepository noteRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /*
    public List<Comment> getCommentsForNote(Note note) {
        return commentRepository.findByNote(note);
    }
     */

    public Comment createComment(Long username, Note note, String content) {
        User user = userRepository.findByUserName(String.valueOf(username))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setNote(note);
        comment.setContent(content);

        return commentRepository.save(comment);
    }
}
