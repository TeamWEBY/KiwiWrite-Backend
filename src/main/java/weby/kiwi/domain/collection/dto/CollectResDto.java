package weby.kiwi.domain.collection.dto;

import com.sun.istack.NotNull;

public class CollectResDto {//collection에 단어 추가. 즉 collection update
    @NotNull
    private int user_id;
    @NotNull
    private int year;
    @NotNull
    private int month;
    @NotNull
    private int word_id;

    public int getUser_id() {
        return user_id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getWord_id() {
        return word_id;
    }
}
