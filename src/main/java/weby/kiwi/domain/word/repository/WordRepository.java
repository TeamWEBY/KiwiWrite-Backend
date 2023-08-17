package weby.kiwi.domain.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weby.kiwi.domain.word.entity.Word;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByWordId(Long wordId);
    List<Word> findByMonthAndDay(int month, int day);
    Optional<Word> findByWordName(String wordName);
}