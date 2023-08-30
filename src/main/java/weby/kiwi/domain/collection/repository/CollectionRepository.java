package weby.kiwi.domain.collection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import weby.kiwi.domain.collection.entity.Collection;
import weby.kiwi.domain.word.entity.Word;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {
    //JpaRepository 기본CRUD메서드 자동 생성
    Boolean existsByUserIdAndWord(long userId, Word word);
    List<Collection> findAllByUserIdAndMonth(Long userId, int month);
    long countByUserIdAndMonth(long userId, int month);
}
