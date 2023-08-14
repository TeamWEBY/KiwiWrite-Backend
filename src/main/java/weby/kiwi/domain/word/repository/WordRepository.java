package weby.kiwi.domain.word.repository;
import weby.kiwi.domain.word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    Optional<Word> findByWordId(int word_id); //단어번호로 단어를 검색

    List<Word> findByMonth(int month); //월 정보로 단어를 검색

    Optional<Word> findByWord(String word); //단어로 단어를 검색

}
