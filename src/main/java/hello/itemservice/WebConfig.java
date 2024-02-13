package hello.itemservice;

import hello.itemservice.web.interceptor.AdminCheckInterceptor;
import hello.itemservice.web.interceptor.LogInterceptor;
import hello.itemservice.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new LogInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error");

        //로그인 인증 권한
//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/", "/users/add", "/login", "/logout", "/users/find", "/users/password",
//                        "/css/**","/js/**", "/*.ico", "/img/**", "/html/**",
//                        "/error", "/layout"
//                );

        //Admin, user 인증 권한
//        registry.addInterceptor(new AdminCheckInterceptor())
//                .order(1)
//                .addPathPatterns("/admin/members");
    }




}
