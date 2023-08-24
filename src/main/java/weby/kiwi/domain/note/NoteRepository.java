package weby.kiwi.domain.note;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // 별도의 메소드 선언 없이도 JPA가 제공하는 메소드를 활용할 수 있음
    // Note 객체를 어떻게 저장할지 생각해보기
}

