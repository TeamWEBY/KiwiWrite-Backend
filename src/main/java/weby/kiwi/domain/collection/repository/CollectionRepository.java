package weby.kiwi.domain.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weby.kiwi.domain.collection.entity.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {// pk타입으로 int지정했으나 추후 수정 필요함.

    //JpaRepository 기본CRUD메서드 자동 생성
    Collection findByUserIdAndMonth(int user_id, int month);
}
