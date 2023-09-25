package weby.kiwi.domain.comment.entity;

import weby.kiwi.domain.note.Note;
import weby.kiwi.domain.user.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "com_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "note_id", referencedColumnName = "note_id")
    private Note note;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

    public Note getNote() {
        return note;
    }

    public Object getCommentId() {
        return commentId;
    }

    public void setUserId(Long userId) {
    }
}
