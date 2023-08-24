package weby.kiwi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.user.entity.User;
import weby.kiwi.domain.user.service.UserService;

@RestController //https://7942yongdae.tistory.com/136
@RequestMapping("/home") //https://mungto.tistory.com/436
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "home/login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "home/signup";
    }

    @PostMapping("/signup")
    public String createAccount(@ModelAttribute User user) {
        userService.signUp(user); //여기를 어떻게할까
        return "home/signupsuccess";
    }
    //reqDto에 대한 공부가 필요
//    public String home() {
//        return "home";
//    }
}
