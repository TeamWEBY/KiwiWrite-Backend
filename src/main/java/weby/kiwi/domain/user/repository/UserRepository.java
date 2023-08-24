package weby.kiwi.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import weby.kiwi.domain.user.entity.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //CRUD 나중에 다시 확인
    Optional<User> findByUserId(Long userId);
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
}