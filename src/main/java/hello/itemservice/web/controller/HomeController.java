package hello.itemservice.web.controller;

import hello.itemservice.user.domain.User;
import hello.itemservice.web.login.LoginForm;
import hello.itemservice.web.login.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {



    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
            @ModelAttribute("loginForm") LoginForm form,
            Model model
    ){
        if(loginUser == null){
            return "login";
        }

        //세션이 유지되면 로그인으로 이동.
        model.addAttribute("user", loginUser);
        return "home";
    }




}
