package weby.kiwi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.user.service.UserService;

@RestController //https://7942yongdae.tistory.com/136
@RequestMapping("/home") //https://mungto.tistory.com/436
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

//    @PostMapping("/login")
//    public String createAccount(@ModelAttribute User user) {
//        userService.createUser(user); //여기를 어떻게할까
//        return "home/signupsuccess";
//    }

//    public String home() {
//        return "home";
//  }
}
