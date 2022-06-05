package bg.softuni.DemoMVC.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String newUser(){
        return "newuser";
    }

    @PostMapping
    public String createUser(UserDto userDto){
        System.out.println("Creating new user... " + userDto);
        return "usercreated";
    }
}
