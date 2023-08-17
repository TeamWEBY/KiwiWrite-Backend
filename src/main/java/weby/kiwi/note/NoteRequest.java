package weby.kiwi.note;

public class NoteRequest {
    private Long userId;
    private String title;
    private String content;

    // 기본 생성자와 Getter, Setter
    public NoteRequest() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() { return userId; }
}