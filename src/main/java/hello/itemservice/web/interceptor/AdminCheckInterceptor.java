package hello.itemservice.web.interceptor;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.domain.UserType;
import hello.itemservice.web.login.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("권한 인증 UIR = {}", requestURI);
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        if(user.getType() == UserType.USER){
            log.info("미인증 권한 요청");

            String errorMessage = "잘못된 권한입니다.";

            // JavaScript 코드를 사용하여 메시지 박스를 띄우도록 응답에 추가
            String script = "<script>alert(decodeURIComponent('" + errorMessage + "')); window.location.href='/';</script>";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(script);

            return false;
        }
        return true;
    }
}
