package weby.kiwi.domain.user.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    //private final UserService userService;
    /*@PostMapping("/login")
    public String createAccount(@ModelAttribute User user) {
        userService.createUser(user); //여기를 어떻게할까
        return "home/signupsuccess";
    }*/
//    @RequestMapping("/home")
//    public String index() {
//        return "index";
//    }
}