package weby.kiwi.domain.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.comment.entity.Comment;
import weby.kiwi.domain.comment.service.CommentRequest;
import weby.kiwi.domain.comment.service.*;
import weby.kiwi.domain.note.Note;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentRequest commentRequest) {
        return commentService.createComment(commentRequest.getUserId(), commentRequest.getNote(), commentRequest.getContent());
    }

    /*
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
    */
}
