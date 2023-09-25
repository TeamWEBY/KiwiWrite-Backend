package weby.kiwi.domain.collection.dto;

public class CollectionReqDto {
    private Long userId;
    private int month;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
