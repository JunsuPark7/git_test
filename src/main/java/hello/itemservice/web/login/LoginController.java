package hello.itemservice.web.login;

import hello.itemservice.user.domain.LoginService;
import hello.itemservice.user.domain.User;
import hello.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }
// 주석표시2

//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response){
//
//        if(bindingResult.hasErrors()){
//            return "login/loginForm";
//        }
//
//        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
//        log.info("login? {}", loginUser);
//
//        if(loginUser == null){
//            bindingResult.reject("loginFail","아이디 혹은 비밀번호가 맞지 않습니다.");
//            return "login/loginForm";
//        }
//        //로그인 성공 로직
//        Cookie idCookie = new Cookie("userId",
//                String.valueOf(loginUser.getId()));
//        response.addCookie(idCookie);
//
//
//        return "redirect:/";
//    }


//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("login? {}", loginUser);

        if(loginUser == null){
            bindingResult.reject("loginFail","아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        sessionManager.createSession(loginUser,response);

        return "redirect:/";
    }

//    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("login ? {}",loginUser);

        if(loginUser == null){
            bindingResult.reject("loginFail","아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/login/Form";
        }
        //HttpServletRequest에 세션을 생성 할 수 있음.
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성.
        // 세션이 없으면 새로운 세션을 생성해서 반환!!
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm loginForm,
                          BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,
                          HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        User loginUser = loginService.login(loginForm.getLoginId(), loginForm.getPassword());
        log.info("login ? {}",loginUser);

        if(loginUser == null){
            bindingResult.reject("loginFail","아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }
        //HttpServletRequest에 세션을 생성 할 수 있음.
        //세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성.
        // 세션이 없으면 새로운 세션을 생성해서 반환!!
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        return "redirect:" + redirectURL;
    }


//    @PostMapping("/logout")
//    public String logout(HttpServletResponse response) {
//        expireCookie(response, "memberId");
//        return "redirect:/";
//    }
//    private void expireCookie(HttpServletResponse response, String cookieName) {
//        Cookie cookie = new Cookie(cookieName, null);
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }










}
