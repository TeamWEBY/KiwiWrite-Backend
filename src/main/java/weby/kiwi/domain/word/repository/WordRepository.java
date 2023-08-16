package weby.kiwi.domain.word.repository;
import weby.kiwi.domain.word.entity.Word;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WordRepository {

    @PersistenceContext
    EntityManager em;

    public void saveWord(Word word) {
        em.persist(word);
    }

    public void deleteWord(Word word) {
        em.remove(word);
    }

    public Word findByWordId(int word_id) {
        return em.find(Word.class, word_id);
    }

    public List<Word> findAllByMonth(int month) {
        TypedQuery<Word> query = em.createQuery(
                "SELECT w FROM Word w WHERE w.month = :month",
                Word.class
        );
        query.setParameter("month", month);
        return query.getResultList();
    }

    public Word findByWord(String word) {
        return em.find(Word.class, word);
    }
}