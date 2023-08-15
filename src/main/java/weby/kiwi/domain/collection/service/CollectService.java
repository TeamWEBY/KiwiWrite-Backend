package weby.kiwi.domain.collection.service;

import weby.kiwi.domain.collection.entity.Collection;

public class CollectService {
//collection 객체는 이후 DB에 업데이트 후 되돌려 받는 것으로 변경
    public Collection createCol(Collection collection){
        Collection createdCol = collection;
        return createdCol;
    }

    public Collection updateCol(Collection collection){
        Collection updatedCol = collection;
        return updatedCol;
    }

    public Collection showCol(int user_id, int year, int month){
        //검색 기능 추가
        return null;
    }

    public Integer showColCnt(Collection collection){
        //countion기능 추가
        return 0;
    }

}
