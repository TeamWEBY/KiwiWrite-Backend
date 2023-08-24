package weby.kiwi.domain.note;

public class NoteRequest {
    private Long userId;
    private String title;
    private String content;

    // 기본 생성자와 Getter, Setter
    public NoteRequest() {

    }

    // 타이틀
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    // 컨텐츠
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


    // 유저 아이디
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getUserId() { return userId; }

}