package weby.kiwi.domain.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weby.kiwi.domain.user.dto.SignupReqDto;
import weby.kiwi.domain.user.entity.User;
import weby.kiwi.domain.user.exception.UserNotFoundException;
import weby.kiwi.domain.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public Long createUser(SignupReqDto reqDto) {
        User user = reqDto.toEntity();
        userRepository.save(user);
        return user.getUserId();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        if (this.userRepository.findAll().isEmpty())
            userRepository.save(new User("TestUser", "abc@gmail.com", "qwer"));
    }

    @Transactional(readOnly = true)
    public User findByUserId(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(("해당 ID의 유저를 찾을 수 없습니다.")));
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(("해당 이메일의 유저를 찾을 수 없습니다.")));
    }

    @Transactional(readOnly = true)
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(("해당 이름의 유저를 찾을 수 없습니다.")));
    }
}
