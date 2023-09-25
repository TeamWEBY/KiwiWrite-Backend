package weby.kiwi.domain.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import weby.kiwi.domain.comment.entity.Comment;
import weby.kiwi.domain.comment.repository.CommentRepository;
import weby.kiwi.domain.comment.service.CommentService;
import weby.kiwi.domain.comment.service.CommentRequest;
import weby.kiwi.domain.note.Note;
import weby.kiwi.domain.note.NoteRepository;
import weby.kiwi.domain.user.entity.User;
import weby.kiwi.domain.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;
    private CommentRequest commentRequest;

    @Test
    public void testCreateComment() {
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPasswd("testPassword");
        userRepository.save(user);

        Note note = new Note();
        note.setUser(user);
        note.setTitle("Test Title");
        note.setContent("Test Content");
        note.setVisible(Boolean.TRUE);
        noteRepository.save(note);

        Comment comment = new Comment();
        comment.setUserId(user.getUserId());
        comment.setNote(note);
        comment.setContent("Test Content");

        Comment savedComment = commentService.createComment(commentRequest.getUserId(), commentRequest.getNote(), commentRequest.getContent());

        Comment retrievedComment = (Comment) commentRepository.findByNote(note);

        assertNotNull(retrievedComment);
        assertEquals(user.getUserId(), retrievedComment.getUser().getUserId());
        assertEquals(note.getId(), retrievedComment.getNote().getId());
        assertEquals("Test Content", retrievedComment.getContent());
    }

    @Test
    public void testGetComment() {
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPasswd("testPassword");

        entityManager.persist(user);
        entityManager.flush(); // Flush to persist the user

        Note note = new Note();
        note.setUser(user);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        entityManager.persist(note);
        entityManager.flush();

        Comment comment = new Comment();
        comment.setNote(note);

        entityManager.persist(comment);
        entityManager.flush();

        List<Comment> comments = commentRepository.findByNote(note);

        // Perform assertions
        assertNotNull(comments);
        assertEquals(1, comments.size());
        assertEquals(comment.getCommentId(), comments.get(0).getCommentId());
    }

}
