package weby.kiwi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.user.dto.SignupReqDto;
import weby.kiwi.domain.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupReqDto(Model model) {
        model.addAttribute("reqDto", new SignupReqDto());
        return "user/login/register";
    }
    @PostMapping("/signup")
    public String signup(@Valid @RequestBody SignupReqDto reqDto, BindingResult result) {
        if(result.hasErrors()){
            return "user/login/register";
        }
        userService.createUser(reqDto);
        return "redirect:/";
    }

//    //메소드 https://maenco.tistory.com/entry/Spring-MVC-Request-Mapping-%EA%B3%BC-REST-API-%EC%84%A4%EA%B3%84
    @GetMapping("/login")
    public String login() {
        return "user login data";
    }

    @GetMapping("/logincheck")
    public String loginCheck() {
        return "user login check data";
    }

    @GetMapping("/logout")
    public String logout() {
        return "user logout data";
    }
}
