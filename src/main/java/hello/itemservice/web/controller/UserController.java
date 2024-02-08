package hello.itemservice.web.controller;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    private String save(@Valid @ModelAttribute User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "users/addUserForm";
        }

        Optional<User> sameId = userRepository.findByLoginId(user.getLoginId());
        if(sameId.isPresent()){
            bindingResult.reject("loginId","ID가 중복되었습니다. 다른 ID로 로그인 해주십시오");
            return "users/addUserForm";
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return "redirect:/";
    }


    @GetMapping("/find")
    public String findForm(@ModelAttribute("user") User user){
        return "users/findForm";
    }

    @PostMapping("/find")
    private String find(@ModelAttribute User user,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        Optional<User> loginUserOptional = userRepository.findByLoginId(user.getLoginId());
        if(loginUserOptional.isEmpty()){
            bindingResult.reject("loginId","ID가 없습니다. 다른 ID를 검색해주세요.");
            return "users/findForm";
        }

        User loginUser = loginUserOptional.get();
        redirectAttributes.addFlashAttribute("user",loginUser);
        return "redirect:/users/password";
    }

    @GetMapping("/password")
    public String findPassword(@ModelAttribute("user") User user){
        return "users/passwordForm";
    }

    @PostMapping("/password")
    private String resetPassword(@ModelAttribute User user,
                        BindingResult bindingResult,
                                 Model model){
        Optional<User> loginUserOptional = userRepository.findByLoginId(user.getLoginId());
        if(loginUserOptional.isEmpty()){
            bindingResult.reject("loginId","ID가 없습니다. 다른 ID를 검색해주세요.");
            return "users/passwordForm";
        }
        User loginUser = loginUserOptional.get();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        userRepository.changePassword(loginUser.getId(), encodedPassword);
        return "redirect:/";
    }


    @PostMapping("/authorize")
    public String authorize(@RequestParam("id") Long id, HttpServletRequest request, RedirectAttributes redirectAttributes){
        User findUser = userRepository.findById(id);
        userRepository.authorize(findUser.getId());

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        redirectAttributes.addFlashAttribute("user",findUser);
        return "redirect:/"; // loginHome으로 리다이렉트
    }


}
