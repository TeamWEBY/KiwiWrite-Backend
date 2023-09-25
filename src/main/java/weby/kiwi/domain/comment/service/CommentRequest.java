package weby.kiwi.domain.comment.service;

import weby.kiwi.domain.note.Note;

public class CommentRequest {
    private Long userId;
    private Note note;
    private String content;

    public CommentRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNote(Note note) {
        this.note = note;
    }
    public Note getNote() {
        return note;
    }

    public void setUserId() {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }


}