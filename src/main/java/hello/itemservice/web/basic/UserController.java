package hello.itemservice.web.basic;

import hello.itemservice.user.domain.User;
import hello.itemservice.user.repository.MemoryUserRepository;
import hello.itemservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/add")
    public String addForm(@ModelAttribute("user") User user){
        return "users/addUserForm";
    }

    @PostMapping("/add")
    private String save(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "users/addUserForm";
        }

        userRepository.save(user);
        return "redirect:/";
    }


    @PostMapping("/authorize")
    public String authorize(@RequestParam("id") Long id, Model model){
        log.info("1111user info={}",id);

        User findUser = userRepository.findById(id);

        userRepository.authorize(findUser.getId());

        model.addAttribute("user",findUser);
        return "loginHome";
    }









}
