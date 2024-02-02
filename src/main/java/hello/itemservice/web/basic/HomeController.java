package hello.itemservice.web.basic;

import hello.itemservice.user.domain.LoginService;
import hello.itemservice.user.domain.User;
import hello.itemservice.user.repository.MemoryUserRepository;
import hello.itemservice.web.login.LoginForm;
import hello.itemservice.web.login.SessionConst;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final MemoryUserRepository memoryUserRepository;
    private final SessionManager sessionManager;

    private final LoginService loginService;


    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
            @ModelAttribute("loginForm") LoginForm form,
            Model model
    ){
        if(loginUser == null){
            return "home";
        }

        //세션이 유지되면 로그인으로 이동.
        model.addAttribute("user", loginUser);
        return "loginHome";
    }







}
