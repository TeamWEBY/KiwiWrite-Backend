package weby.kiwi.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weby.kiwi.domain.comment.entity.Comment;
import weby.kiwi.domain.note.Note;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAll();
    List<Comment> findByNote(Note note);
}
