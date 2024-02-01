package hello.itemservice.web.basic;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.repository.MemoryUserRepository;
import hello.itemservice.web.login.SessionConst;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemoryUserRepository memoryUserRepository;
    private final SessionManager sessionManager;


//    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        User loginUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
        //세션에 회원 데이터가 없으면 ... home
        if(loginUser == null){
            return "home";
        }

        model.addAttribute("user",loginUser);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser,
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
