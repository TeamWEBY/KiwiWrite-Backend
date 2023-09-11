package weby.kiwi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import weby.kiwi.domain.user.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPasswd1().equals(userCreateForm.getPasswd2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "비밀번호가 일치하지 않습니다.");
            return "signup_form";
        }

        userService.createUser(userCreateForm.getUserName(),
                userCreateForm.getEmail(), userCreateForm.getPasswd1());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}
//https://wikidocs.net/162141

//    @GetMapping("/signup")
//    public String signupReqDto(Model model) {
//        model.addAttribute("reqDto", new SignupReqDto());
//        return "user/login/register";
//    }
//    @PostMapping("/signup")
//    public String signup(@Valid @RequestBody SignupReqDto reqDto, BindingResult result) {
//        if(result.hasErrors()){
//            return "user/login/register";
//        }
//        userService.createUser(reqDto);
//        return "redirect:/";
//    }

//    //메소드 https://maenco.tistory.com/entry/Spring-MVC-Request-Mapping-%EA%B3%BC-REST-API-%EC%84%A4%EA%B3%84
//    @GetMapping("/login")
//    public String login() {
//        return "user login data";
//    }
//
//    @GetMapping("/logincheck")
//    public String loginCheck() {
//        return "user login check data";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        return "user logout data";
//    }
